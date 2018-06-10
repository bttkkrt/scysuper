
package com.jshx.module.form.web.action;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.core.utils.FileUpTool;
import com.jshx.core.utils.Struts2Util;
import com.jshx.module.form.entity.Attachfiles;
import com.jshx.module.form.service.AttachfileService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class FlashUploadAction extends ActionSupport
{
    /** 文件 */
    private List<File> filedata;

    /** 文件名 */
    private List<String> filedataFileName;

    /** 文件内容类型 */
    private List<String> filedataContentType;

    private String fileGroupGuid;

    private String rowguid;

    private Attachfiles attachFile = new Attachfiles();

    @Autowired
    private AttachfileService attachfileService;

    public List<File> getFiledata()
    {
        return filedata;
    }

    public void setFiledata(List<File> filedata)
    {
    	this.filedata = filedata;
    }

    public List<String> getFiledataFileName()
    {
        return filedataFileName;
    }

    public void setFiledataFileName(List<String> filedataFileName)
    {
        this.filedataFileName = filedataFileName;
    }

    public List<String> getFiledataContentType()
    {
        return filedataContentType;
    }

    public void setFiledataContentType(List<String> filedataContentType)
    {
    	this.filedataContentType = filedataContentType;
    }

    public String execute()
    {

        try
        {
            ActionContext context = ActionContext.getContext();

            // 得到三个对象
            Map request = (Map) context.get("request");
            //System.out.println(fileGroupGuid);
            //("fileGroupGuid",fileGroupGuid);
            request.put("fileGroupGuid", fileGroupGuid);
            if (filedata != null && filedata.isEmpty() == false)
            {
                FileUpTool fileUpTool=new FileUpTool();
                for (int i = 0; i < filedata.size(); i++)
                {
                    /*
                     * 存到硬盘上的方法
                     * */
                    String result=fileUpTool.saveFile(filedata.get(i), filedataFileName.get(i).toString());
                    //System.out.println(FiledataFileName.get(i).toString());
                    
                	attachFile = new Attachfiles();
                    attachFile.setAttachName(filedataFileName.get(i));
                    attachFile.setContentType(filedataContentType.get(i));
                    attachFile.setAttachType("BIG");
                    
                    /* 
                     * FileInputStream fis = new FileInputStream(filedata.get(i));
                    //转成Blob类型
                    attachFile.setContent(Hibernate.createBlob(fis));
                    */
                    attachFile.setFormRowGuid(fileGroupGuid);
                    attachFile.setRowguid(UUID.randomUUID().toString());
                    //attachFile.setTempId(tempId)
                    attachFile.setFileName(result);
                    attachfileService.save(attachFile);
                    
                }

            }

            return SUCCESS;

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return INPUT;
        }

    }

    public String readFile()
    {
        System.out.println("readFile——>" + this.rowguid);
        try
        {
            //attachFile = attachfileManager.get(attachId);
            attachFile = attachfileService.getAttFileByRowguid(rowguid);
            //this.attachId=attachFile.getAttachId();
            String fileName = new String(attachFile.getAttachName().getBytes("GBK"), "ISO8859-1");
            Blob photo = attachFile.getContent();
            if (photo != null)
            {
                InputStream in = photo.getBinaryStream();
                Struts2Util.getResponse().setContentType(attachFile.getContentType());
                Struts2Util.getResponse().addHeader("Content-Disposition", "attachment;filename=" + fileName);
                OutputStream out = Struts2Util.getResponse().getOutputStream();
                try
                {
                    byte[] buf = new byte[1024];
                    int len;
                    while ((len = in.read(buf)) != -1)
                    {
                        out.write(buf, 0, len);
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    in.close();
                    out.close();
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public String getFileGroupGuid()
    {
        return fileGroupGuid;
    }

    public void setFileGroupGuid(String fileGroupGuid)
    {
        this.fileGroupGuid = fileGroupGuid;
    }

    public String getRowguid()
    {
        return rowguid;
    }

    public void setRowguid(String rowguid)
    {
        this.rowguid = rowguid;
    }
}
