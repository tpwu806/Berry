package com.iot.exceptions;
/**
 * *****************************************************************
 * Created on 2016年1月18日 上午11:14:26
 * @author zhanggp (mailto:zhanggp@sdas.org)
 * 功能说明：数据更新异常
 *
 * 修改历史
 * Revision 1.0.1   2016年1月18日 上午11:14:26 by zhanggp
 * Update: ------ empty log ------
 ******************************************************************
 */
public class DaoUpdateException extends Exception
{
  public DaoUpdateException()
  {
  }

  public DaoUpdateException(String msg)
  {
    super(msg);
  }
}