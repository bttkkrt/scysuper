package com.jshx.core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.UUID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * 对象与XML间互转工具
 * 
 * @author Chenjian
 *
 */
public class RWXml {	
	/**
	 * 将对象转为XML
	 * 
	 * @param elements
	 * @param classes
	 * @return
	 */
	public static String objectsToXml(Object elements, Class<?>... classes){
		try{
			JAXBContext context = JAXBContext.newInstance(classes);

	        Marshaller m = context.createMarshaller();  
	        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);  
	        m.setProperty("jaxb.encoding", "utf-8");
	        
	        //m.marshal(elements[i], System.out);
	        
	        String xmlFilePath=Struts2Util.getServletContext().getRealPath("/")+"temp\\";
	        UUID uuid=UUID.randomUUID();
	        String xmlFileName=uuid.toString();
	        File xmlFile = new File(xmlFilePath+xmlFileName+".xml");
	        
			m.marshal(elements, new FileOutputStream(xmlFile));
			
			return xmlFile.getPath();
		} catch (JAXBException e) {
			e.printStackTrace();
			return null;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}      
	}

	/**
	 * 将XML转为对象
	 * 
	 * @param path    XML文件的路径
	 * @param classes
	 * @return
	 * @throws JAXBException
	 */
	public static Object xmlToObjects(String path, Class<?>... classes) throws JAXBException{
			JAXBContext context = JAXBContext.newInstance(classes);
			
			Unmarshaller u = context.createUnmarshaller(); 
			Object tables;
			try {
				tables = u.unmarshal(new FileInputStream(path));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return null;
			}
			return tables;
	}
}