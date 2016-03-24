package com.iot.exceptions;
/**
 * *****************************************************************
 * Created on 2016年1月18日 上午11:09:50
 * @author zhanggp (mailto:zhanggp@sdas.org)
 * 功能说明： 数据删除异常
 *
 * 修改历史
 * Revision 1.0.1   2016年1月18日 上午11:09:50 by zhanggp
 * Update: ------ empty log ------
 ******************************************************************
 */
public class DaoDeleteException extends Exception
{
  public DaoDeleteException()
  {
  }

  public DaoDeleteException(String message)
  {
    super(message);
  }
}