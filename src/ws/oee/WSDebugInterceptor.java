package ws.oee;

import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.saaj.SaajSoapMessage;
import org.w3c.dom.Document;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 06/08/2015
 */
public class WSDebugInterceptor implements EndpointInterceptor{

	@Override
	public void afterCompletion(MessageContext messageContext, Object object, Exception exception) throws Exception {}

	@Override
	public boolean handleFault(MessageContext messageContext, Object object) throws Exception {
		return true;
	}

	@Override
	public boolean handleRequest(MessageContext messageContext, Object object) throws Exception {
		String xml = getXMLContent((SaajSoapMessage) messageContext.getRequest());
		
		Logger.getLogger(this.getClass()).info("\nREQUEST XML: \n" + xml + "\n\n");
		return true;
	}

	@Override
	public boolean handleResponse(MessageContext messageContext, Object object) throws Exception {
		String xml = getXMLContent((SaajSoapMessage) messageContext.getResponse());
		Logger.getLogger(this.getClass()).info("\nRESPONSE XML: \n" + xml + "\n\n");
		return true;
	}

	public String getXMLContent(SaajSoapMessage saajSoapMessage)    {
		try{
			Document document = saajSoapMessage.getDocument();
			DOMSource domSource = new DOMSource(document);
			StringWriter writer = new StringWriter();
			StreamResult result = new StreamResult(writer);
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(domSource, result);
			writer.flush();
			return writer.toString();

		}catch(TransformerException ex){
			ex.printStackTrace();
			return null;
		}
	}



}
