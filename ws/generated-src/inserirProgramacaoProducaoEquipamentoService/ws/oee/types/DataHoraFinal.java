//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.08.26 at 09:54:06 PM BRT 
//


package ws.oee.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *       &lt;attribute name="ano" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="mes" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="dia" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="hora" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="minuto" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "dataHoraFinal")
public class DataHoraFinal {

    @XmlAttribute(name = "ano", required = true)
    protected int ano;
    @XmlAttribute(name = "mes", required = true)
    protected int mes;
    @XmlAttribute(name = "dia", required = true)
    protected int dia;
    @XmlAttribute(name = "hora", required = true)
    protected int hora;
    @XmlAttribute(name = "minuto", required = true)
    protected int minuto;

    /**
     * Gets the value of the ano property.
     * 
     */
    public int getAno() {
        return ano;
    }

    /**
     * Sets the value of the ano property.
     * 
     */
    public void setAno(int value) {
        this.ano = value;
    }

    /**
     * Gets the value of the mes property.
     * 
     */
    public int getMes() {
        return mes;
    }

    /**
     * Sets the value of the mes property.
     * 
     */
    public void setMes(int value) {
        this.mes = value;
    }

    /**
     * Gets the value of the dia property.
     * 
     */
    public int getDia() {
        return dia;
    }

    /**
     * Sets the value of the dia property.
     * 
     */
    public void setDia(int value) {
        this.dia = value;
    }

    /**
     * Gets the value of the hora property.
     * 
     */
    public int getHora() {
        return hora;
    }

    /**
     * Sets the value of the hora property.
     * 
     */
    public void setHora(int value) {
        this.hora = value;
    }

    /**
     * Gets the value of the minuto property.
     * 
     */
    public int getMinuto() {
        return minuto;
    }

    /**
     * Sets the value of the minuto property.
     * 
     */
    public void setMinuto(int value) {
        this.minuto = value;
    }

}
