package com.jshx.httpData;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="strLoginID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="strPwd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "strLoginID", "strPwd" })
@XmlRootElement(name = "CallAuthenticate")
public class CallAuthenticate {

	protected String strLoginID;
	protected String strPwd;

	/**
	 * Gets the value of the strLoginID property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getStrLoginID() {
		return strLoginID;
	}

	/**
	 * Sets the value of the strLoginID property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setStrLoginID(String value) {
		this.strLoginID = value;
	}

	/**
	 * Gets the value of the strPwd property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getStrPwd() {
		return strPwd;
	}

	/**
	 * Sets the value of the strPwd property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setStrPwd(String value) {
		this.strPwd = value;
	}

}
