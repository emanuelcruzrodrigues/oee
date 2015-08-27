//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.08.26 at 09:54:01 PM BRT 
//


package ws.oee.types;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ws.oee.types package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Erros_QNAME = new QName("apontamento.ws.oee.tc.feevale.br", "erros");
    private final static QName _CodigoEquipamento_QNAME = new QName("apontamento.ws.oee.tc.feevale.br", "codigoEquipamento");
    private final static QName _CodigoMotivoParada_QNAME = new QName("apontamento.ws.oee.tc.feevale.br", "codigoMotivoParada");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ws.oee.types
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link IniciarApontamentoParadaResponse }
     * 
     */
    public IniciarApontamentoParadaResponse createIniciarApontamentoParadaResponse() {
        return new IniciarApontamentoParadaResponse();
    }

    /**
     * Create an instance of {@link IniciarApontamentoParadaRequest }
     * 
     */
    public IniciarApontamentoParadaRequest createIniciarApontamentoParadaRequest() {
        return new IniciarApontamentoParadaRequest();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "apontamento.ws.oee.tc.feevale.br", name = "erros")
    public JAXBElement<String> createErros(String value) {
        return new JAXBElement<String>(_Erros_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "apontamento.ws.oee.tc.feevale.br", name = "codigoEquipamento")
    public JAXBElement<Integer> createCodigoEquipamento(Integer value) {
        return new JAXBElement<Integer>(_CodigoEquipamento_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "apontamento.ws.oee.tc.feevale.br", name = "codigoMotivoParada")
    public JAXBElement<Integer> createCodigoMotivoParada(Integer value) {
        return new JAXBElement<Integer>(_CodigoMotivoParada_QNAME, Integer.class, null, value);
    }

}
