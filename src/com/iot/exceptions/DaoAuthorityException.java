package com.iot.exceptions;
/**
 * *****************************************************************
 * Created on 2016年1月18日 上午11:06:12
 * @author zhanggp(mailto:zhanggp@sdas.org)
 * 功能说明： 访问权限异常
 *
 * 修改历史
 * Revision 1.0.1   2016年1月18日 上午11:06:12 by zhanggp
 * Update: ------ empty log ------
 ******************************************************************
 */
public class DaoAuthorityException extends Exception
{
  public DaoAuthorityException()
  {
  }

  public DaoAuthorityException(String message)
  {
    super(message);
  }
}