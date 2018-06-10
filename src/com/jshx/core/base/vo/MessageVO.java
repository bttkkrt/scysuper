package com.jshx.core.base.vo;

import java.io.Serializable;

public class MessageVO
  implements Serializable
{
  static final long serialVersionUID = 200611121742L;
  private String errorId;
  private String errorType;
  private Object[] arguments;
  private Boolean[] argsi18n;
  private String message;

  public String getMessage()
  {
    return this.message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Boolean[] getArgsi18n()
  {
    return this.argsi18n;
  }

  public void setArgsi18n(Boolean[] argsi18n)
  {
    this.argsi18n = argsi18n;
  }

  public Object[] getArguments()
  {
    return this.arguments;
  }

  public void setArguments(Object[] arguments)
  {
    this.arguments = arguments;
  }

  public String getErrorId()
  {
    return this.errorId;
  }

  public void setErrorId(String errorId)
  {
    this.errorId = errorId;
  }

  public String getErrorType()
  {
    return this.errorType;
  }

  public void setErrorType(String errorType)
  {
    this.errorType = errorType;
  }
}