package com.iot.exceptions;
/**
 * *****************************************************************
 * Created on 2016年1月18日 上午11:11:28
 * @author zhanggp (mailto:zhanggp@sdas.org)
 * 功能说明： 访问方法异常
 *
 * 修改历史
 * Revision 1.0.1   2016年1月18日 上午11:11:28 by zhanggp
 * Update: ------ empty log ------
 ******************************************************************
 */
public class DaoMethodException extends Exception
{
  public DaoMethodException()
  {
  }

  public DaoMethodException(String msg)
  {
    super(msg);
  }
}