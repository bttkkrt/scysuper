package com.jshx.core.exception;

import org.springframework.dao.DataAccessException;

public class BaseDaoException extends DataAccessException
{
  private boolean isI18nArgs;
  private String[] args;

  public BaseDaoException(String message)
  {
    super(message);
  }

  public BaseDaoException(String message, Throwable cause)
  {
    super(message, cause);
  }

  public BaseDaoException(String message, String[] args) {
    super(message);
    this.args = args;
    this.isI18nArgs = true;
  }

  public BaseDaoException(String message, String[] args, boolean isI18nArgs) {
    super(message);
    this.args = args;
    this.isI18nArgs = isI18nArgs;
  }

  public boolean isI18nArgs() {
    return this.isI18nArgs;
  }

  public void setI18nArgs(boolean isI18nArgs) {
    this.isI18nArgs = isI18nArgs;
  }

  public String[] getArgs() {
    return this.args;
  }

  public void setArgs(String[] args) {
    this.args = args;
  }
}