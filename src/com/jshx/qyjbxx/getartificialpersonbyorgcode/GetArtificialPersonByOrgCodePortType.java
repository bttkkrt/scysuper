package com.jshx.qyjbxx.getartificialpersonbyorgcode;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.1.4
 * 2015-12-15T15:54:17.723+08:00
 * Generated source version: 3.1.4
 * 
 */
@WebService(targetNamespace = "http://www.apusic.com/esb/GetArtificialPersonByOrgCode", name = "GetArtificialPersonByOrgCodePortType")
@XmlSeeAlso({ObjectFactory.class})
public interface GetArtificialPersonByOrgCodePortType {

    @WebResult(name = "result", targetNamespace = "http://www.apusic.com/esb/GetArtificialPersonByOrgCode")
    @RequestWrapper(localName = "GetArtificialPersonByOrgCode", targetNamespace = "http://www.apusic.com/esb/GetArtificialPersonByOrgCode", className = "com.jshx.qyjbxx.getartificialpersonbyorgcode.GetArtificialPersonByOrgCode")
    @WebMethod(operationName = "GetArtificialPersonByOrgCode", action = "http://www.apusic.com/esb/GetArtificialPersonByOrgCode/GetArtificialPersonByOrgCode")
    @ResponseWrapper(localName = "GetArtificialPersonByOrgCodeResponse", targetNamespace = "http://www.apusic.com/esb/GetArtificialPersonByOrgCode", className = "com.jshx.qyjbxx.getartificialpersonbyorgcode.GetArtificialPersonByOrgCodeResponse")
    public java.lang.String getArtificialPersonByOrgCode(
        @WebParam(name = "orgCode", targetNamespace = "http://www.apusic.com/esb/GetArtificialPersonByOrgCode")
        java.lang.String orgCode
    );
}
