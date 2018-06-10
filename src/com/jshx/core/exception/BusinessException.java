
package com.jshx.core.exception;

import com.jshx.core.base.vo.MessageVO;
import java.util.ArrayList;
import java.util.List;

public class BusinessException extends RuntimeException
{
  static final long serialVersionUID = 20111109150001L;
  private List list;

  public BusinessException()
  {
    this.list = new ArrayList();
  }

  public void add(ValidationException ve)
  {
    this.list.add(ve);
  }

  public void add(String errorId, String errorType, String message)
  {
    Object[] args = { "", "" };
    add(new ValidationException(errorId, errorType, args, message));
  }

  public int count()
  {
    return this.list.size();
  }

  public boolean hasException()
  {
    return this.list.size() > 0;
  }

  public ValidationException[] getExceptions() {
    ValidationException[] ves = new ValidationException[this.list.size()];
    for (int i = 0; i < this.list.size(); i++) {
      ves[i] = ((ValidationException)this.list.get(i));
    }

    return ves;
  }

  public String toString() {
    StringBuffer buff = new StringBuffer();
    buff.append("[BussinessException]:\n");

    ValidationException[] ves = getExceptions();
    for (int i = 0; (ves != null) && (i < ves.length); i++) {
      buff.append(ves[i].toString() + "\n");
    }
    return buff.toString();
  }

  public MessageVO[] getMessageVOs() {
    ValidationException[] vexs = getExceptions();
    MessageVO[] messageVOs = new MessageVO[vexs.length];
    for (int i = 0; i < vexs.length; i++) {
//      messageVOs[i] = vexs[i].getMessageVO();
    }
    return messageVOs;
  }
}
