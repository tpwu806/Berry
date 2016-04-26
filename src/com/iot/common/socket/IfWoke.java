package com.iot.common.socket;

/**
 * 模拟从数据库中获得结果
 * */
public class IfWoke{
	
	private boolean cmdstatus=true;//指令状态,ture开启 false关闭
	
	public boolean isCmdstatus() {
		return cmdstatus;
	}
	public void setCmdstatus(boolean cmdstatus) {
		this.cmdstatus = cmdstatus;
	}
}
