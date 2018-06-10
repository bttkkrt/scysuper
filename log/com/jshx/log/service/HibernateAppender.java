package com.jshx.log.service;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Calendar;

import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Appender;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jshx.core.utils.HibernateUtil;
import com.jshx.log.entity.ExceptionLog;
/**
 * 使用Hibernate来保存日志
 * 
 * @author Chenjian
 *
 */
public class HibernateAppender  extends AppenderSkeleton implements Appender{
	
	@Override
	public void close() {
		this.closed = true;
	}

	@Override
	public boolean requiresLayout() {
		return true;
	}

	@Override
	protected void append(LoggingEvent event) {
		ExceptionLog log = new ExceptionLog();
		Session session = HibernateUtil.openSession();
        Transaction tx = session.beginTransaction();
        try{
        	if("com.opensymphony.xwork2.util.logging.commons.CommonsLogger".equals(event.getLocationInformation().getClassName()) || "org.directwebremoting.util.CommonsLoggingOutput".equals(event.getLocationInformation().getClassName()))
        		return;
        	log.setClassName(event.getLocationInformation().getClassName());
            log.setMothodName(event.getLocationInformation().getMethodName());
            log.setCreateTime(Calendar.getInstance().getTime());
            Writer w = new StringWriter();
            if(event.getThrowableInformation()!=null)
            	event.getThrowableInformation().getThrowable().printStackTrace(new PrintWriter(w));
    		String eStackTrace = w.toString();
    		log.setMsg(eStackTrace);
            log.setDelFlag(0);
            switch(event.getLevel().toInt()){
            case org.apache.log4j.Priority.DEBUG_INT:
            	log.setLogLevel("DEBUG");
                break;
            case org.apache.log4j.Priority.ERROR_INT:
            	log.setLogLevel("ERROR");
                break;
            case org.apache.log4j.Priority.INFO_INT:
            	log.setLogLevel("INFO");
                break;
            case org.apache.log4j.Priority.FATAL_INT:
            	log.setLogLevel("FATAL");
                break;
            case org.apache.log4j.Priority.WARN_INT:
            	log.setLogLevel("WARN");
                break;
            case org.apache.log4j.Priority.OFF_INT:
            	log.setLogLevel("OFF");
                break;
            default : 
            	log.setLogLevel("ERROR");
                break;
            }
            session.save(log);
            tx.commit();
        }catch(Exception e){
        	tx.rollback();
        	e.printStackTrace();
        }finally{
        	try{
        		session.close();
            	tx = null;
            	session = null;
        	}catch(Exception e){
        		e.printStackTrace();
        	}
        }
        
	}

}
