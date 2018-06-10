package com.jshx.core.exception;

public class ValidationException extends BaseException
{
  static final long serialVersionUID = 20111109150002L;

  public ValidationException()
  {
  }

  public ValidationException(Throwable cause)
  {
    super(cause);
  }

  public ValidationException(String errorId, String errorType)
  {
    super(errorId, errorType);
  }

  public ValidationException(String errorId)
  {
    super(errorId, "error");
  }

  public ValidationException(String errorId, String errorType, Throwable cause)
  {
    super(errorId, errorType, cause);
  }

  public ValidationException(String errorId, String errorType, Object[] args)
  {
    super(errorId, errorType, args);
  }

  public ValidationException(String errorId, String errorType, Object[] args, Boolean[] argsi18n)
  {
    super(errorId, errorType, args, argsi18n);
  }

  public ValidationException(String errorId, String errorType, Object[] args, Throwable cause)
  {
    super(errorId, errorType, args, cause);
  }

  public ValidationException(String errorId, String errorType, Object[] args, Boolean[] argsi18n, Throwable cause)
  {
    super(errorId, errorType, args, argsi18n, cause);
  }

  public ValidationException(String errorId, String errorType, Object[] args, String message)
  {
    super(errorId, errorType, args, message);
  }

  public ValidationException(String errorId, String errorType, Object[] args, Boolean[] argsi18n, String message)
  {
    super(errorId, errorType, args, argsi18n, message);
  }

  public ValidationException(String errorId, String errorType, Object[] args, String message, Throwable cause)
  {
    super(errorId, errorType, args, message, cause);
  }

  public ValidationException(String errorId, String errorType, Object[] args, Boolean[] argsi18n, String message, Throwable cause)
  {
    super(errorId, errorType, args, argsi18n, message, cause);
  }

  public String toString() {
    StringBuffer buff = new StringBuffer();

    buff.append("[ValidationException]:").append(
      "errorId=" + getErrorId() + ",").append(
      "message=" + getMessage());

    return buff.toString();
  }

  public ValidationException(String errorId, String errorType, String message) {
    super(errorId, errorType, message);
  }
}