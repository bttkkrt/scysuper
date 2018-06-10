package com.jshx.http.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

public class SaveOrUpdateDxx {
	public String saveDot(String type,String mkKey,String lot,String lat)
	{
		try {
			String shape =  "POINT(" + lot + "%20" + lat + ")";
			URL restURL = new URL("http://58.210.9.131/scms/sipsd/service/DBService/208f36d6-d19f-4b82-a5ac-2acdba315e44?token=M9YXgn9o7FOvu9uiGzJufUm6rIGlnRfF%2fFn03xGFbd7f5cQXbATfbFfk5xXAe%2f%2f5e2gZ6iFJGP3aQ8y15Ev4xBdL7FIT3zmZnHo0o3lQtUHNK4a%2bOIaB3Q%3d%3d&ACTIONTYPE=" + type +"&KEY=" + mkKey + "&MODNAME=&COMNAE=&ADDRESS=&REGION=&SHAPE=" + shape + "&f=list"); 
			 HttpURLConnection conn = (HttpURLConnection) restURL.openConnection(); 
			 conn.setRequestMethod("GET"); 
			 conn.setDoOutput(true); 
			 conn.setAllowUserInteraction(false); 
			 conn.setRequestProperty("Charsert", "UTF-8");
			    BufferedReader bReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8")); 
			    String line,resultStr=""; 
			    while(null != (line=bReader.readLine())) 
			    { 
			        resultStr +=line; 
			    } 
			    bReader.close(); 
			    System.out.println(resultStr);
			    return resultStr;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}
