package com.iot.exceptions;
/**
 * *****************************************************************
 * Created on 2016年1月18日 上午11:10:35
 * @author zhanggp(mailto:zhanggp@sdas.org)
 * 功能说明： 查询数据异常 
 *
 * 修改历史
 * Revision 1.0.1   2016年1月18日 上午11:10:35 by zhanggp
 * Update: ------ empty log ------
 ******************************************************************
 */
public class DaoFinderException extends Exception
{
  public DaoFinderException()
  {
  }

  public DaoFinderException(String message)
  {
    super(message);
  }
}