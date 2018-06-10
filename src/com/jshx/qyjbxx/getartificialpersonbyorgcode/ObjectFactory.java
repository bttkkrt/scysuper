
package com.jshx.qyjbxx.getartificialpersonbyorgcode;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.apusic.esb.getartificialpersonbyorgcode package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetArtificialPersonByOrgCode_QNAME = new QName("http://www.apusic.com/esb/GetArtificialPersonByOrgCode", "GetArtificialPersonByOrgCode");
    private final static QName _GetArtificialPersonByOrgCodeResponse_QNAME = new QName("http://www.apusic.com/esb/GetArtificialPersonByOrgCode", "GetArtificialPersonByOrgCodeResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.apusic.esb.getartificialpersonbyorgcode
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetArtificialPersonByOrgCode }
     * 
     */
    public GetArtificialPersonByOrgCode createGetArtificialPersonByOrgCode() {
        return new GetArtificialPersonByOrgCode();
    }

    /**
     * Create an instance of {@link GetArtificialPersonByOrgCodeResponse }
     * 
     */
    public GetArtificialPersonByOrgCodeResponse createGetArtificialPersonByOrgCodeResponse() {
        return new GetArtificialPersonByOrgCodeResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetArtificialPersonByOrgCode }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.apusic.com/esb/GetArtificialPersonByOrgCode", name = "GetArtificialPersonByOrgCode")
    public JAXBElement<GetArtificialPersonByOrgCode> createGetArtificialPersonByOrgCode(GetArtificialPersonByOrgCode value) {
        return new JAXBElement<GetArtificialPersonByOrgCode>(_GetArtificialPersonByOrgCode_QNAME, GetArtificialPersonByOrgCode.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetArtificialPersonByOrgCodeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.apusic.com/esb/GetArtificialPersonByOrgCode", name = "GetArtificialPersonByOrgCodeResponse")
    public JAXBElement<GetArtificialPersonByOrgCodeResponse> createGetArtificialPersonByOrgCodeResponse(GetArtificialPersonByOrgCodeResponse value) {
        return new JAXBElement<GetArtificialPersonByOrgCodeResponse>(_GetArtificialPersonByOrgCodeResponse_QNAME, GetArtificialPersonByOrgCodeResponse.class, null, value);
    }

}
