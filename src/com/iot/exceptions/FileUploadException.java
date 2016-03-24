package com.iot.exceptions;
/**
 * *****************************************************************
 * Created on 2016年1月18日 上午11:15:03
 * @author zhanggp (mailto:zhanggp@sdas.org)
 * 功能说明： 文件上传异常
 *
 * 修改历史
 * Revision 1.0.1   2016年1月18日 上午11:15:03 by zhanggp
 * Update: ------ empty log ------
 ******************************************************************
 */
public class FileUploadException extends Exception
{
  public FileUploadException()
  {
  }

  public FileUploadException(String msg)
  {
    super(msg);
  }
}