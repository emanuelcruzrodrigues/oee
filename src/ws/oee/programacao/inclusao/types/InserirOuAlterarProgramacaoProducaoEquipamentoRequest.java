//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.21 at 01:47:58 PM BRST 
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
 *         &lt;element ref="{programacao.ws.oee.tc.feevale.br}codigo"/>
 *         &lt;element ref="{programacao.ws.oee.tc.feevale.br}codigoEquipamento"/>
 *         &lt;element ref="{programacao.ws.oee.tc.feevale.br}dataHoraInicial"/>
 *         &lt;element ref="{programacao.ws.oee.tc.feevale.br}dataHoraFinal"/>
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
    "codigo",
    "codigoEquipamento",
    "dataHoraInicial",
    "dataHoraFinal"
})
@XmlRootElement(name = "inserirOuAlterarProgramacaoProducaoEquipamentoRequest")
public class InserirOuAlterarProgramacaoProducaoEquipamentoRequest {

    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer codigo;
    protected int codigoEquipamento;
    @XmlElement(required = true)
    protected DataHoraInicial dataHoraInicial;
    @XmlElement(required = true)
    protected DataHoraFinal dataHoraFinal;

    /**
     * Gets the value of the codigo property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCodigo() {
        return codigo;
    }

    /**
     * Sets the value of the codigo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCodigo(Integer value) {
        this.codigo = value;
    }

    /**
     * Gets the value of the codigoEquipamento property.
     * 
     */
    public int getCodigoEquipamento() {
        return codigoEquipamento;
    }

    /**
     * Sets the value of the codigoEquipamento property.
     * 
     */
    public void setCodigoEquipamento(int value) {
        this.codigoEquipamento = value;
    }

    /**
     * Gets the value of the dataHoraInicial property.
     * 
     * @return
     *     possible object is
     *     {@link DataHoraInicial }
     *     
     */
    public DataHoraInicial getDataHoraInicial() {
        return dataHoraInicial;
    }

    /**
     * Sets the value of the dataHoraInicial property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataHoraInicial }
     *     
     */
    public void setDataHoraInicial(DataHoraInicial value) {
        this.dataHoraInicial = value;
    }

    /**
     * Gets the value of the dataHoraFinal property.
     * 
     * @return
     *     possible object is
     *     {@link DataHoraFinal }
     *     
     */
    public DataHoraFinal getDataHoraFinal() {
        return dataHoraFinal;
    }

    /**
     * Sets the value of the dataHoraFinal property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataHoraFinal }
     *     
     */
    public void setDataHoraFinal(DataHoraFinal value) {
        this.dataHoraFinal = value;
    }

}
