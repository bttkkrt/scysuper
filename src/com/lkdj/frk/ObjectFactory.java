
package com.lkdj.frk;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.lkdj.frk package. 
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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.lkdj.frk
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetFRJCInsertRequest.Frjc }
     * 
     */
    public GetFRJCInsertRequest.Frjc createGetFRJCInsertRequestFrjc() {
        return new GetFRJCInsertRequest.Frjc();
    }

    /**
     * Create an instance of {@link GetFRJCInsertRequest }
     * 
     */
    public GetFRJCInsertRequest createGetFRJCInsertRequest() {
        return new GetFRJCInsertRequest();
    }

    /**
     * Create an instance of {@link GetFRJCInsertResponse }
     * 
     */
    public GetFRJCInsertResponse createGetFRJCInsertResponse() {
        return new GetFRJCInsertResponse();
    }

}
