
package com.lkdj.kzk;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tkzxxs" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="appid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="sjly" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="kzxx" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="rksj" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="zjhm" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="gsid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "tkzxxs"
})
@XmlRootElement(name = "getTkzxxRequest")
public class GetTkzxxRequest {

    protected List<GetTkzxxRequest.Tkzxxs> tkzxxs;

    /**
     * Gets the value of the tkzxxs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tkzxxs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTkzxxs().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GetTkzxxRequest.Tkzxxs }
     * 
     * 
     */
    public List<GetTkzxxRequest.Tkzxxs> getTkzxxs() {
        if (tkzxxs == null) {
            tkzxxs = new ArrayList<GetTkzxxRequest.Tkzxxs>();
        }
        return this.tkzxxs;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="appid" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="sjly" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="kzxx" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="rksj" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="zjhm" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="gsid" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "appid",
        "sjly",
        "kzxx",
        "rksj",
        "zjhm",
        "gsid"
    })
    public static class Tkzxxs {

        @XmlElement(required = true)
        protected String appid;
        @XmlElement(required = true)
        protected String sjly;
        @XmlElement(required = true)
        protected String kzxx;
        @XmlElement(required = true)
        protected String rksj;
        @XmlElement(required = true)
        protected String zjhm;
        @XmlElement(required = true)
        protected String gsid;

        /**
         * Gets the value of the appid property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAppid() {
            return appid;
        }

        /**
         * Sets the value of the appid property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAppid(String value) {
            this.appid = value;
        }

        /**
         * Gets the value of the sjly property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSjly() {
            return sjly;
        }

        /**
         * Sets the value of the sjly property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSjly(String value) {
            this.sjly = value;
        }

        /**
         * Gets the value of the kzxx property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getKzxx() {
            return kzxx;
        }

        /**
         * Sets the value of the kzxx property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setKzxx(String value) {
            this.kzxx = value;
        }

        /**
         * Gets the value of the rksj property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRksj() {
            return rksj;
        }

        /**
         * Sets the value of the rksj property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRksj(String value) {
            this.rksj = value;
        }

        /**
         * Gets the value of the zjhm property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getZjhm() {
            return zjhm;
        }

        /**
         * Sets the value of the zjhm property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setZjhm(String value) {
            this.zjhm = value;
        }

        /**
         * Gets the value of the gsid property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getGsid() {
            return gsid;
        }

        /**
         * Sets the value of the gsid property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setGsid(String value) {
            this.gsid = value;
        }

    }

}
