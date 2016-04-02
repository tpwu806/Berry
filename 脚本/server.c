/****************** server program *****************/
#include  <stdio.h>
#include  <sys/types.h>
#include  <sys/socket.h>
#include  <string.h>
#include  <stdlib.h>
#include  <sys/un.h>
#include  <pthread.h>
#include  <arpa/inet.h>

#include <wiringPi.h>
#include <stdint.h>
#define MAXTIMINGS	85
#define DHTPIN		7

int dht11_dat[5] = { 0, 0, 0, 0, 0 };
int tem1;
int sockfd,newfd;

void *read_socket();
void read_dht11_dat(); 

int main()
{
    int ret;
    pthread_t read_tid;
    struct sockaddr_in server_addr;
    server_addr.sin_family=AF_INET;/*设置域为IPV4*/
    server_addr.sin_addr.s_addr=INADDR_ANY;/*绑定到 INADDR_ANY 地址*/
    server_addr.sin_port=htons(6789);/*通信端口号为5678，注意这里必须要用htons函数处理一下，不能直接写5678，否则可能会连不上*/
    sockfd=socket(AF_INET,SOCK_STREAM,0);
    if (sockfd<0)
    {
        printf("调用socket函数建立socket描述符出错！\n");
         exit(1);
    }
    printf("调用socket函数建立socket描述符成功！\n");
    ret=bind(sockfd,(struct sockaddr *)(&server_addr),sizeof(server_addr));
    perror("server");
    if (ret<0)
    {
        printf("调用bind函数绑定套接字与地址出错！\n");
         exit(2);
    }
    printf("调用bind函数绑定套接字与地址成功！\n");
    ret=listen(sockfd,4);
    if (ret<0)
    {
        printf("调用listen函数出错，无法宣告服务器已经可以接受连接！\n");
         exit(3);
    }
    printf("调用listen函数成功，宣告服务器已经可以接受连接请求！\n");
    newfd=accept(sockfd,NULL,NULL);/*newfd连接到调用connect的客户端*/
    if (newfd<0)
    {
        printf("调用accept函数出错，无法接受连接请求，建立连接失败！\n");
         exit(4);
    }
    printf("调用accept函数成功，服务器与客户端建立连接成功！\n");

    /** 服务器端建立一个线程，负责从socket读取数据，当然此处并不一定非要建立一个新线程，直接在原线程中写也可以 **/
    pthread_create(&read_tid,NULL,read_socket,NULL);


    /********************* 令原线程睡眠 ************************/
    while(1)
    {
        delay(10000);
    }
}

/****************** 从socket读取数据的线程 ********************/
void *read_socket()
{
    int recv_num,recv_num_total=0;
    char recv_buf[50];
    char buf[10];
    while (1)
    {
        memset(recv_buf,0,sizeof(recv_buf));/*清空一下recv_buf缓存区*/
        recv_num=recv(newfd,recv_buf,26,0);
        if (recv_num<0)
            printf("服务器端：调用recv接收失败！\n");
        else if(recv_num>0)
        {
            recv_num_total+=recv_num;
            printf("服务器端：调用recv接收成功！本次接收到%d个字节，内容为：\"%s\"。共收到%d个字节的数据。\n",recv_num,recv_buf,recv_num_total);
			/*******************************************************/
			if ( wiringPiSetup() == -1 )
		    exit( 1 );
		    read_dht11_dat();
			//delay(1000);
            memset(buf,0,sizeof(buf));
           // itoa(tem1,buf,10);
            sprintf(buf,"%d",tem1);
            write(newfd,buf,32);
            
            //   printf("write fail\r\n");
              // return -1;
           // }
            printf("write ok!\r\n");
			/**************************************************/
            sync();
            
        }
        else/*收到数据为0，表明服务器与客户端的连接已经中断*/
        {
            printf("服务器端：与客户端的连接已中断，当前共收到%d个字节的数据。服务器将再次等待客户端的连接。\n",recv_num_total);
            newfd=accept(sockfd,NULL,NULL);/*当客户端退出后，再次开始接收客户端的连接*/
        }
        delay( 1000 );
    }
}



 /****************** read_dht11_dat()从dht11传感器读取温湿度 ********************/


 
void read_dht11_dat()
{
	uint8_t laststate	= HIGH;
	uint8_t counter		= 0;
	uint8_t j		= 0, i;
	float	f; /* fahrenheit */
 
	dht11_dat[0] = dht11_dat[1] = dht11_dat[2] = dht11_dat[3] = dht11_dat[4] = 0;
 
	/* pull pin down for 18 milliseconds */
	pinMode( DHTPIN, OUTPUT );
	digitalWrite( DHTPIN, LOW );
	delay( 18 );
	/* then pull it up for 40 microseconds */
	digitalWrite( DHTPIN, HIGH );
	delayMicroseconds( 40 );
	/* prepare to read the pin */
	pinMode( DHTPIN, INPUT );
 
	/* detect change and read data */
	for ( i = 0; i < MAXTIMINGS; i++ )
	{
		counter = 0;
		while ( digitalRead( DHTPIN ) == laststate )
		{
			counter++;
			delayMicroseconds( 1 );
			if ( counter == 255 )
			{
				break;
			}
		}
		laststate = digitalRead( DHTPIN );
 
		if ( counter == 255 )
			break;
 
		/* ignore first 3 transitions */
		if ( (i >= 4) && (i % 2 == 0) )
		{
			/* shove each bit into the storage bytes */
			dht11_dat[j / 8] <<= 1;
			if ( counter > 16 )
				dht11_dat[j / 8] |= 1;
			j++;
		}
	}
 
	/*
	 * check we read 40 bits (8bit x 5 ) + verify checksum in the last byte
	 * print it out if data is good
	 */
	if ( (j >= 40) &&
	     (dht11_dat[4] == ( (dht11_dat[0] + dht11_dat[1] + dht11_dat[2] + dht11_dat[3]) & 0xFF) ) )
	{
		f = dht11_dat[2] * 9. / 5. + 32;
		printf( "Humidity = %d.%d %% Temperature = %d.%d *C (%.1f *F)\n",
			dht11_dat[0], dht11_dat[1], dht11_dat[2], dht11_dat[3], f );
		tem1=dht11_dat[2];
	//	return tem1;
		
	}else  {
		printf( "Data not good, skip\n" );
		
		tem1=0;
	}
}
 


