package com.iot.exceptions;
/**
 * *****************************************************************
 * Created on 2016年1月18日 上午10:47:33
 * @author zhanggp (mailto:zhanggp@sdas.org)
 * 功能说明：已经存在异常
 *
 * 修改历史
 * Revision 1.0.1   2016年1月18日 上午10:47:33 by zhanggp
 * Update: ------ empty log ------
 ******************************************************************
 */
public class DaoAlreadyExistsException extends Exception
{
  public DaoAlreadyExistsException()
  {
  }

  public DaoAlreadyExistsException(String msg)
  {
    super(msg);
  }
}