package com.jshx.module.form.service.generate;

import com.jshx.module.form.entity.FormTable;

public interface CodeGenerate
{
    public void genCode(FormTable table,String randomFolderName);
}
