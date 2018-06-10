package com.jshx.core.exception;

import com.jshx.core.base.vo.MessageVO;

public abstract class BaseException extends RuntimeException
{
  private MessageVO messageVO = new MessageVO();

  public BaseException()
  {
  }

  public BaseException(Throwable cause) {
    super(cause);
  }

  public BaseException(String message, Throwable cause) {
    super(message, cause);
  }

  public BaseException(String errorId, String errorType) {
    this.messageVO.setErrorId(errorId);
    this.messageVO.setErrorType(errorType);
  }

  public BaseException(String errorId, String errorType, Throwable cause)
  {
    this.messageVO.setErrorId(errorId);
    this.messageVO.setErrorType(errorType);
  }

  public BaseException(String errorId, String errorType, Object[] args) {
    this.messageVO.setErrorId(errorId);
    this.messageVO.setErrorType(errorType);
    this.messageVO.setArguments(args);
    initeArgumentsi18n();
  }

  public BaseException(String errorId, String errorType, Object[] args, Boolean[] argsi18n)
  {
    this.messageVO.setErrorId(errorId);
    this.messageVO.setErrorType(errorType);
    this.messageVO.setArguments(args);
    this.messageVO.setArgsi18n(argsi18n);
  }

  public BaseException(String errorId, String errorType, String message)
  {
    this.messageVO.setErrorId(errorId);
    this.messageVO.setErrorType(errorType);
    this.messageVO.setMessage(message);
    initeArgumentsi18n();
  }

  public BaseException(String errorId, String errorType, Object[] args, Throwable cause)
  {
    this.messageVO.setErrorId(errorId);
    this.messageVO.setErrorType(errorType);
    this.messageVO.setArguments(args);
    initeArgumentsi18n();
  }

  public BaseException(String errorId, String errorType, Object[] args, Boolean[] argsi18n, Throwable cause)
  {
    this.messageVO.setErrorId(errorId);
    this.messageVO.setErrorType(errorType);
    this.messageVO.setArguments(args);
    this.messageVO.setArgsi18n(argsi18n);
  }

  public BaseException(String errorId, String errorType, Object[] args, String message)
  {
    super(message);
    this.messageVO.setErrorId(errorId);
    this.messageVO.setErrorType(errorType);
    this.messageVO.setArguments(args);
    initeArgumentsi18n();
  }

  public BaseException(String errorId, String errorType, Object[] args, Boolean[] argsi18n, String message)
  {
    super(message);
    this.messageVO.setErrorId(errorId);
    this.messageVO.setErrorType(errorType);
    this.messageVO.setArguments(args);
    this.messageVO.setArgsi18n(argsi18n);
  }

  public BaseException(String errorId, String errorType, Object[] args, String message, Throwable cause)
  {
    this.messageVO.setErrorId(errorId);
    this.messageVO.setErrorType(errorType);
    this.messageVO.setArguments(args);
    initeArgumentsi18n();
  }

  public BaseException(String errorId, String errorType, Object[] args, Boolean[] argsi18n, String message, Throwable cause)
  {
    this.messageVO.setErrorId(errorId);
    this.messageVO.setErrorType(errorType);
    this.messageVO.setArguments(args);
    this.messageVO.setArgsi18n(argsi18n);
  }

  public BaseException(String errorId) {
    this.messageVO.setErrorId(errorId);
  }

  public String getErrorId() {
    return this.messageVO.getErrorId();
  }

  public String getErrorType() {
    return this.messageVO.getErrorType();
  }

  public Object[] getArguments() {
    return this.messageVO.getArguments();
  }

  public Boolean[] getArgsi18n() {
    return this.messageVO.getArgsi18n();
  }

  private void initeArgumentsi18n() {
    if (getArguments() != null) {
      int argSize = getArguments().length;

      Boolean[] argsi18n = new Boolean[argSize];
      for (int i = 0; i < argSize; i++) {
        argsi18n[i] = Boolean.TRUE;
      }

      this.messageVO.setArgsi18n(argsi18n);
    }
  }

  public abstract String toString();

  public MessageVO getMessageVO()
  {
    return this.messageVO;
  }
}