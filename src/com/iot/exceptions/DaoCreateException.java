package com.iot.exceptions;
/**
 * *****************************************************************
 * Created on 2016年1月18日 上午11:09:08
 * @author zhanggp (mailto:zhanggp@sdas.org)
 * 功能说明：数据创建异常
 *
 * 修改历史
 * Revision 1.0.1   2016年1月18日 上午11:09:08 by zhanggp
 * Update: ------ empty log ------
 ******************************************************************
 */
public class DaoCreateException extends Exception
{
  public DaoCreateException()
  {
  }

  public DaoCreateException(String msg)
  {
    super(msg);
  }
}