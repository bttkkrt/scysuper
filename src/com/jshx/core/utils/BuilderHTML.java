package com.jshx.core.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

public class BuilderHTML
{
  private Configuration freemarker_cfg = null;
  private static final String THIS_CLASS_PATH = "/webResources/template/com/easygenerate/";

  protected Configuration getFreeMarkerCFG()
  {
    if (this.freemarker_cfg == null)
    {
      this.freemarker_cfg = new Configuration();
      String webpath = ServletActionContext.getRequest().getRealPath("/webResources/template/com/easygenerate/");
      try
      {
        this.freemarker_cfg
          .setDirectoryForTemplateLoading(new File(webpath));
        this.freemarker_cfg.setDefaultEncoding("UTF-8");
      }
      catch (IOException e) {
        e.printStackTrace();
      }
    }
    return this.freemarker_cfg;
  }

  public String geneHtmlFile(String templateFileName, Map datas)
  {
    Template t = null;
    CharArrayWriter baos = null;
    try
    {
      t = getFreeMarkerCFG().getTemplate(templateFileName);
      baos = new CharArrayWriter();
      t.process(datas, baos);
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    catch (TemplateException e) {
      e.printStackTrace();
    }
    return baos.toString();
  }
}