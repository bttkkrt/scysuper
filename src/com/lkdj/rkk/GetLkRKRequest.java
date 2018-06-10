
package com.lkdj.rkk;

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
 *         &lt;element name="pici" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="lkrk" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="APPID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZJLX" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZJHM" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="XM" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="CYM" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="XB" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="GJ" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="MZ" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="CSRQ" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="JG" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZZMM" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZJXY" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="WHCD" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="HYZK" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="BYZK" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ZY" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="XX" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="HH" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="RYLB" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="RYZT" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "pici",
    "lkrk"
})
@XmlRootElement(name = "getLkRKRequest")
public class GetLkRKRequest {

    @XmlElement(required = true)
    protected String pici;
    protected List<GetLkRKRequest.Lkrk> lkrk;

    /**
     * Gets the value of the pici property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPici() {
        return pici;
    }

    /**
     * Sets the value of the pici property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPici(String value) {
        this.pici = value;
    }

    /**
     * Gets the value of the lkrk property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lkrk property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLkrk().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GetLkRKRequest.Lkrk }
     * 
     * 
     */
    public List<GetLkRKRequest.Lkrk> getLkrk() {
        if (lkrk == null) {
            lkrk = new ArrayList<GetLkRKRequest.Lkrk>();
        }
        return this.lkrk;
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
     *         &lt;element name="APPID" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZJLX" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZJHM" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="XM" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="CYM" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="XB" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="GJ" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="MZ" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="CSRQ" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="JG" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZZMM" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZJXY" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="WHCD" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="HYZK" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="BYZK" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ZY" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="XX" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="HH" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="RYLB" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="RYZT" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "zjlx",
        "zjhm",
        "xm",
        "cym",
        "xb",
        "gj",
        "mz",
        "csrq",
        "jg",
        "zzmm",
        "zjxy",
        "whcd",
        "hyzk",
        "byzk",
        "zy",
        "xx",
        "hh",
        "rylb",
        "ryzt"
    })
    public static class Lkrk {

        @XmlElement(name = "APPID", required = true)
        protected String appid;
        @XmlElement(name = "ZJLX", required = true)
        protected String zjlx;
        @XmlElement(name = "ZJHM", required = true)
        protected String zjhm;
        @XmlElement(name = "XM", required = true)
        protected String xm;
        @XmlElement(name = "CYM", required = true)
        protected String cym;
        @XmlElement(name = "XB", required = true)
        protected String xb;
        @XmlElement(name = "GJ", required = true)
        protected String gj;
        @XmlElement(name = "MZ", required = true)
        protected String mz;
        @XmlElement(name = "CSRQ", required = true)
        protected String csrq;
        @XmlElement(name = "JG", required = true)
        protected String jg;
        @XmlElement(name = "ZZMM", required = true)
        protected String zzmm;
        @XmlElement(name = "ZJXY", required = true)
        protected String zjxy;
        @XmlElement(name = "WHCD", required = true)
        protected String whcd;
        @XmlElement(name = "HYZK", required = true)
        protected String hyzk;
        @XmlElement(name = "BYZK", required = true)
        protected String byzk;
        @XmlElement(name = "ZY", required = true)
        protected String zy;
        @XmlElement(name = "XX", required = true)
        protected String xx;
        @XmlElement(name = "HH", required = true)
        protected String hh;
        @XmlElement(name = "RYLB", required = true)
        protected String rylb;
        @XmlElement(name = "RYZT", required = true)
        protected String ryzt;

        /**
         * Gets the value of the appid property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAPPID() {
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
        public void setAPPID(String value) {
            this.appid = value;
        }

        /**
         * Gets the value of the zjlx property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getZJLX() {
            return zjlx;
        }

        /**
         * Sets the value of the zjlx property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setZJLX(String value) {
            this.zjlx = value;
        }

        /**
         * Gets the value of the zjhm property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getZJHM() {
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
        public void setZJHM(String value) {
            this.zjhm = value;
        }

        /**
         * Gets the value of the xm property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getXM() {
            return xm;
        }

        /**
         * Sets the value of the xm property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setXM(String value) {
            this.xm = value;
        }

        /**
         * Gets the value of the cym property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCYM() {
            return cym;
        }

        /**
         * Sets the value of the cym property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCYM(String value) {
            this.cym = value;
        }

        /**
         * Gets the value of the xb property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getXB() {
            return xb;
        }

        /**
         * Sets the value of the xb property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setXB(String value) {
            this.xb = value;
        }

        /**
         * Gets the value of the gj property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getGJ() {
            return gj;
        }

        /**
         * Sets the value of the gj property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setGJ(String value) {
            this.gj = value;
        }

        /**
         * Gets the value of the mz property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMZ() {
            return mz;
        }

        /**
         * Sets the value of the mz property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMZ(String value) {
            this.mz = value;
        }

        /**
         * Gets the value of the csrq property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCSRQ() {
            return csrq;
        }

        /**
         * Sets the value of the csrq property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCSRQ(String value) {
            this.csrq = value;
        }

        /**
         * Gets the value of the jg property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getJG() {
            return jg;
        }

        /**
         * Sets the value of the jg property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setJG(String value) {
            this.jg = value;
        }

        /**
         * Gets the value of the zzmm property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getZZMM() {
            return zzmm;
        }

        /**
         * Sets the value of the zzmm property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setZZMM(String value) {
            this.zzmm = value;
        }

        /**
         * Gets the value of the zjxy property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getZJXY() {
            return zjxy;
        }

        /**
         * Sets the value of the zjxy property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setZJXY(String value) {
            this.zjxy = value;
        }

        /**
         * Gets the value of the whcd property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getWHCD() {
            return whcd;
        }

        /**
         * Sets the value of the whcd property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setWHCD(String value) {
            this.whcd = value;
        }

        /**
         * Gets the value of the hyzk property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getHYZK() {
            return hyzk;
        }

        /**
         * Sets the value of the hyzk property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setHYZK(String value) {
            this.hyzk = value;
        }

        /**
         * Gets the value of the byzk property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getBYZK() {
            return byzk;
        }

        /**
         * Sets the value of the byzk property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setBYZK(String value) {
            this.byzk = value;
        }

        /**
         * Gets the value of the zy property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getZY() {
            return zy;
        }

        /**
         * Sets the value of the zy property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setZY(String value) {
            this.zy = value;
        }

        /**
         * Gets the value of the xx property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getXX() {
            return xx;
        }

        /**
         * Sets the value of the xx property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setXX(String value) {
            this.xx = value;
        }

        /**
         * Gets the value of the hh property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getHH() {
            return hh;
        }

        /**
         * Sets the value of the hh property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setHH(String value) {
            this.hh = value;
        }

        /**
         * Gets the value of the rylb property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRYLB() {
            return rylb;
        }

        /**
         * Sets the value of the rylb property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRYLB(String value) {
            this.rylb = value;
        }

        /**
         * Gets the value of the ryzt property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRYZT() {
            return ryzt;
        }

        /**
         * Sets the value of the ryzt property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRYZT(String value) {
            this.ryzt = value;
        }

    }

}
