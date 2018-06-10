package com.jshx.httpData;

import javax.xml.bind.annotation.XmlRegistry;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the com.jshx.httpData package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: com.jshx.httpData
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link CallAuthenticateResponse }
	 * 
	 */
	public CallAuthenticateResponse createCallAuthenticateResponse() {
		return new CallAuthenticateResponse();
	}

	/**
	 * Create an instance of {@link CallDestServiceResponse }
	 * 
	 */
	public CallDestServiceResponse createCallDestServiceResponse() {
		return new CallDestServiceResponse();
	}

	/**
	 * Create an instance of {@link CallDestService }
	 * 
	 */
	public CallDestService createCallDestService() {
		return new CallDestService();
	}

	/**
	 * Create an instance of {@link CallAuthenticate }
	 * 
	 */
	public CallAuthenticate createCallAuthenticate() {
		return new CallAuthenticate();
	}

}
