//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.08.31 at 06:39:00 PM BRT 
//


package ws.oee.ordemProducao.types;

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
 *         &lt;element ref="{ordemProducao.ws.oee.tc.feevale.br}codigo"/>
 *         &lt;element ref="{ordemProducao.ws.oee.tc.feevale.br}descricao"/>
 *         &lt;element ref="{ordemProducao.ws.oee.tc.feevale.br}codigoEquipamento"/>
 *         &lt;element ref="{ordemProducao.ws.oee.tc.feevale.br}unidadesPorMinuto"/>
 *         &lt;element ref="{ordemProducao.ws.oee.tc.feevale.br}situacao"/>
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
    "descricao",
    "codigoEquipamento",
    "unidadesPorMinuto",
    "situacao"
})
@XmlRootElement(name = "inserirOuAlterarOrdemProducaoRequest")
public class InserirOuAlterarOrdemProducaoRequest {

    protected int codigo;
    @XmlElement(required = true)
    protected String descricao;
    protected int codigoEquipamento;
    protected double unidadesPorMinuto;
    @XmlElement(required = true)
    protected String situacao;

    /**
     * Gets the value of the codigo property.
     * 
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Sets the value of the codigo property.
     * 
     */
    public void setCodigo(int value) {
        this.codigo = value;
    }

    /**
     * Gets the value of the descricao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Sets the value of the descricao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescricao(String value) {
        this.descricao = value;
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
     * Gets the value of the unidadesPorMinuto property.
     * 
     */
    public double getUnidadesPorMinuto() {
        return unidadesPorMinuto;
    }

    /**
     * Sets the value of the unidadesPorMinuto property.
     * 
     */
    public void setUnidadesPorMinuto(double value) {
        this.unidadesPorMinuto = value;
    }

    /**
     * Gets the value of the situacao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSituacao() {
        return situacao;
    }

    /**
     * Sets the value of the situacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSituacao(String value) {
        this.situacao = value;
    }

}
