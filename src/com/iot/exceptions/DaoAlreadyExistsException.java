package com.iot.exceptions;

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