//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.08.26 at 09:54:02 PM BRT 
//


package ws.oee.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element ref="{apontamento.ws.oee.tc.feevale.br}codigoOrdemProducao"/>
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
    "codigoOrdemProducao"
})
@XmlRootElement(name = "iniciarApontamentoProducaoRequest")
public class IniciarApontamentoProducaoRequest {

    protected int codigoOrdemProducao;

    /**
     * Gets the value of the codigoOrdemProducao property.
     * 
     */
    public int getCodigoOrdemProducao() {
        return codigoOrdemProducao;
    }

    /**
     * Sets the value of the codigoOrdemProducao property.
     * 
     */
    public void setCodigoOrdemProducao(int value) {
        this.codigoOrdemProducao = value;
    }

}
