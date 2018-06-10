
package com.lkdj.frk;

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
 *         &lt;element name="frjc" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="appid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="gszch" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="shxydm" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="fddbrxm" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="fddbrzjhm" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="jyfw" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="kyrq" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="dsdjrq" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="guosdjrq" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="gsdjrq" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="zclx" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="zcdz" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="zczb" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *                   &lt;element name="zcbz" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="sjjydz" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="qyzt" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="lxdh" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="yzbm" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="dwdm" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="nsrsbh" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="nsrbm" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="zzjgdm" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="jglx" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="jgmc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="qyxz" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="qyrs" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *                   &lt;element name="sshy" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ssqy" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "frjc"
})
@XmlRootElement(name = "getFRJCInsertRequest")
public class GetFRJCInsertRequest {

    protected List<GetFRJCInsertRequest.Frjc> frjc;

    /**
     * Gets the value of the frjc property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the frjc property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFrjc().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GetFRJCInsertRequest.Frjc }
     * 
     * 
     */
    public List<GetFRJCInsertRequest.Frjc> getFrjc() {
        if (frjc == null) {
            frjc = new ArrayList<GetFRJCInsertRequest.Frjc>();
        }
        return this.frjc;
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
     *         &lt;element name="gszch" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="shxydm" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="fddbrxm" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="fddbrzjhm" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="jyfw" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="kyrq" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="dsdjrq" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="guosdjrq" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="gsdjrq" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="zclx" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="zcdz" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="zczb" type="{http://www.w3.org/2001/XMLSchema}long"/>
     *         &lt;element name="zcbz" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="sjjydz" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="qyzt" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="lxdh" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="yzbm" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="dwdm" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="nsrsbh" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="nsrbm" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="zzjgdm" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="jglx" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="jgmc" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="qyxz" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="qyrs" type="{http://www.w3.org/2001/XMLSchema}long"/>
     *         &lt;element name="sshy" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ssqy" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "gszch",
        "shxydm",
        "fddbrxm",
        "fddbrzjhm",
        "jyfw",
        "kyrq",
        "dsdjrq",
        "guosdjrq",
        "gsdjrq",
        "zclx",
        "zcdz",
        "zczb",
        "zcbz",
        "sjjydz",
        "qyzt",
        "lxdh",
        "yzbm",
        "dwdm",
        "nsrsbh",
        "nsrbm",
        "zzjgdm",
        "jglx",
        "jgmc",
        "qyxz",
        "qyrs",
        "sshy",
        "ssqy"
    })
    public static class Frjc {

        @XmlElement(required = true)
        protected String appid;
        @XmlElement(required = true)
        protected String gszch;
        @XmlElement(required = true)
        protected String shxydm;
        @XmlElement(required = true)
        protected String fddbrxm;
        @XmlElement(required = true)
        protected String fddbrzjhm;
        @XmlElement(required = true)
        protected String jyfw;
        @XmlElement(required = true)
        protected String kyrq;
        @XmlElement(required = true)
        protected String dsdjrq;
        @XmlElement(required = true)
        protected String guosdjrq;
        @XmlElement(required = true)
        protected String gsdjrq;
        @XmlElement(required = true)
        protected String zclx;
        @XmlElement(required = true)
        protected String zcdz;
        protected long zczb;
        @XmlElement(required = true)
        protected String zcbz;
        @XmlElement(required = true)
        protected String sjjydz;
        @XmlElement(required = true)
        protected String qyzt;
        @XmlElement(required = true)
        protected String lxdh;
        @XmlElement(required = true)
        protected String yzbm;
        @XmlElement(required = true)
        protected String dwdm;
        @XmlElement(required = true)
        protected String nsrsbh;
        @XmlElement(required = true)
        protected String nsrbm;
        @XmlElement(required = true)
        protected String zzjgdm;
        @XmlElement(required = true)
        protected String jglx;
        @XmlElement(required = true)
        protected String jgmc;
        @XmlElement(required = true)
        protected String qyxz;
        protected long qyrs;
        @XmlElement(required = true)
        protected String sshy;
        @XmlElement(required = true)
        protected String ssqy;

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
         * Gets the value of the gszch property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getGszch() {
            return gszch;
        }

        /**
         * Sets the value of the gszch property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setGszch(String value) {
            this.gszch = value;
        }

        /**
         * Gets the value of the shxydm property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getShxydm() {
            return shxydm;
        }

        /**
         * Sets the value of the shxydm property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setShxydm(String value) {
            this.shxydm = value;
        }

        /**
         * Gets the value of the fddbrxm property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFddbrxm() {
            return fddbrxm;
        }

        /**
         * Sets the value of the fddbrxm property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFddbrxm(String value) {
            this.fddbrxm = value;
        }

        /**
         * Gets the value of the fddbrzjhm property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFddbrzjhm() {
            return fddbrzjhm;
        }

        /**
         * Sets the value of the fddbrzjhm property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFddbrzjhm(String value) {
            this.fddbrzjhm = value;
        }

        /**
         * Gets the value of the jyfw property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getJyfw() {
            return jyfw;
        }

        /**
         * Sets the value of the jyfw property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setJyfw(String value) {
            this.jyfw = value;
        }

        /**
         * Gets the value of the kyrq property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getKyrq() {
            return kyrq;
        }

        /**
         * Sets the value of the kyrq property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setKyrq(String value) {
            this.kyrq = value;
        }

        /**
         * Gets the value of the dsdjrq property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDsdjrq() {
            return dsdjrq;
        }

        /**
         * Sets the value of the dsdjrq property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDsdjrq(String value) {
            this.dsdjrq = value;
        }

        /**
         * Gets the value of the guosdjrq property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getGuosdjrq() {
            return guosdjrq;
        }

        /**
         * Sets the value of the guosdjrq property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setGuosdjrq(String value) {
            this.guosdjrq = value;
        }

        /**
         * Gets the value of the gsdjrq property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getGsdjrq() {
            return gsdjrq;
        }

        /**
         * Sets the value of the gsdjrq property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setGsdjrq(String value) {
            this.gsdjrq = value;
        }

        /**
         * Gets the value of the zclx property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getZclx() {
            return zclx;
        }

        /**
         * Sets the value of the zclx property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setZclx(String value) {
            this.zclx = value;
        }

        /**
         * Gets the value of the zcdz property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getZcdz() {
            return zcdz;
        }

        /**
         * Sets the value of the zcdz property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setZcdz(String value) {
            this.zcdz = value;
        }

        /**
         * Gets the value of the zczb property.
         * 
         */
        public long getZczb() {
            return zczb;
        }

        /**
         * Sets the value of the zczb property.
         * 
         */
        public void setZczb(long value) {
            this.zczb = value;
        }

        /**
         * Gets the value of the zcbz property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getZcbz() {
            return zcbz;
        }

        /**
         * Sets the value of the zcbz property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setZcbz(String value) {
            this.zcbz = value;
        }

        /**
         * Gets the value of the sjjydz property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSjjydz() {
            return sjjydz;
        }

        /**
         * Sets the value of the sjjydz property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSjjydz(String value) {
            this.sjjydz = value;
        }

        /**
         * Gets the value of the qyzt property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getQyzt() {
            return qyzt;
        }

        /**
         * Sets the value of the qyzt property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setQyzt(String value) {
            this.qyzt = value;
        }

        /**
         * Gets the value of the lxdh property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLxdh() {
            return lxdh;
        }

        /**
         * Sets the value of the lxdh property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLxdh(String value) {
            this.lxdh = value;
        }

        /**
         * Gets the value of the yzbm property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getYzbm() {
            return yzbm;
        }

        /**
         * Sets the value of the yzbm property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setYzbm(String value) {
            this.yzbm = value;
        }

        /**
         * Gets the value of the dwdm property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDwdm() {
            return dwdm;
        }

        /**
         * Sets the value of the dwdm property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDwdm(String value) {
            this.dwdm = value;
        }

        /**
         * Gets the value of the nsrsbh property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNsrsbh() {
            return nsrsbh;
        }

        /**
         * Sets the value of the nsrsbh property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNsrsbh(String value) {
            this.nsrsbh = value;
        }

        /**
         * Gets the value of the nsrbm property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNsrbm() {
            return nsrbm;
        }

        /**
         * Sets the value of the nsrbm property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNsrbm(String value) {
            this.nsrbm = value;
        }

        /**
         * Gets the value of the zzjgdm property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getZzjgdm() {
            return zzjgdm;
        }

        /**
         * Sets the value of the zzjgdm property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setZzjgdm(String value) {
            this.zzjgdm = value;
        }

        /**
         * Gets the value of the jglx property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getJglx() {
            return jglx;
        }

        /**
         * Sets the value of the jglx property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setJglx(String value) {
            this.jglx = value;
        }

        /**
         * Gets the value of the jgmc property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getJgmc() {
            return jgmc;
        }

        /**
         * Sets the value of the jgmc property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setJgmc(String value) {
            this.jgmc = value;
        }

        /**
         * Gets the value of the qyxz property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getQyxz() {
            return qyxz;
        }

        /**
         * Sets the value of the qyxz property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setQyxz(String value) {
            this.qyxz = value;
        }

        /**
         * Gets the value of the qyrs property.
         * 
         */
        public long getQyrs() {
            return qyrs;
        }

        /**
         * Sets the value of the qyrs property.
         * 
         */
        public void setQyrs(long value) {
            this.qyrs = value;
        }

        /**
         * Gets the value of the sshy property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSshy() {
            return sshy;
        }

        /**
         * Sets the value of the sshy property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSshy(String value) {
            this.sshy = value;
        }

        /**
         * Gets the value of the ssqy property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSsqy() {
            return ssqy;
        }

        /**
         * Sets the value of the ssqy property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSsqy(String value) {
            this.ssqy = value;
        }

    }

}
