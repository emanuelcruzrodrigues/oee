//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.08.26 at 07:53:33 PM BRT 
//


package ws.oee.programacao.inclusao.types;

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
 *         &lt;element ref="{programacao.ws.oee.tc.feevale.br}erros"/>
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
    "erros"
})
@XmlRootElement(name = "inserirProgramacaoProducaoEquipamentoResponse")
public class InserirProgramacaoProducaoEquipamentoResponse {

    @XmlElement(required = true)
    protected String erros;

    /**
     * Gets the value of the erros property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErros() {
        return erros;
    }

    /**
     * Sets the value of the erros property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErros(String value) {
        this.erros = value;
    }

}
