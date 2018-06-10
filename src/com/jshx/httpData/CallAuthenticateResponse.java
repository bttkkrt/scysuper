package com.jshx.httpData;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CallAuthenticateResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "callAuthenticateResult" })
@XmlRootElement(name = "CallAuthenticateResponse")
public class CallAuthenticateResponse {

	@XmlElement(name = "CallAuthenticateResult")
	protected String callAuthenticateResult;

	/**
	 * Gets the value of the callAuthenticateResult property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCallAuthenticateResult() {
		return callAuthenticateResult;
	}

	/**
	 * Sets the value of the callAuthenticateResult property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCallAuthenticateResult(String value) {
		this.callAuthenticateResult = value;
	}

}
