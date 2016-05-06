/*[17:45:12][463 ms]
 * 改变device_device表DEVICESTATUS字段类型
 **/ 
 ALTER TABLE `iot`.`device_device` CHANGE `DEVICESTATUS` `DEVICESTATUS` INT(10) NOT NULL COMMENT '设备状态';