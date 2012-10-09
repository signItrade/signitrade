package uk.co.signitrade.app.ui.util;

import java.beans.PropertyDescriptor;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Method;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XMLUtils
{
	private final static Logger logger = Logger.getLogger(XMLUtils.class);

	/**
	 * This method converts the supplied bean to XML and return the xml string
	 * 
	 * @param bean
	 * @param cls
	 * @return xml string
	 * @throws JAXBException
	 */
	public static String convertToXML(Object bean, Class<?> cls) throws JAXBException
	{
		String result;
		StringWriter out = new StringWriter();

		JAXBContext XMLContext = JAXBContext.newInstance(cls);
		Marshaller marshaller = XMLContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.marshal(new JAXBElement(new QName(bean.getClass().getSimpleName()), bean.getClass(), bean), out);

		result = out.toString();
		return result;
	}

	/**
	 * This method generates the html based on the template (XSL) and XML. The
	 * parameter xslPath is the classpath of the xsl file & the xmlString is the
	 * generated xml. As the xml is generated dynamically so one will need to
	 * pass the whole xml content as xmlString.
	 * 
	 * @param xsl
	 * @param xml
	 * @return Generated HTML as String
	 * @throws TransformerException
	 * @throws IOException 
	 * @throws FactoryConfigurationError 
	 * @throws ParserConfigurationException 
	 * @throws SAXException 
	 */
	public static String getHTML(String xslPath, String xmlString) throws TransformerException, IOException, ParserConfigurationException, FactoryConfigurationError, SAXException
	{
		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer(new StreamSource(getInputStream(xslPath)));
		transformer.setOutputProperty(OutputKeys.METHOD, "html");
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document document = builder.parse(new ByteArrayInputStream(xmlString.getBytes("UTF-8")));
		DOMSource source = new DOMSource(document);

		Result result = new StreamResult(out);
		transformer.transform(source, result);

		return out.toString("UTF-8");
	}

	/**
	 * This method generates the html based on the template (XSL) and XML. It
	 * takes two parameters viz. xsl and xml javax.xml.transform.Source objects.
	 * 
	 * @param xsl
	 * @param xml
	 * @return Generated HTML as String
	 * @throws TransformerException
	 */
	/*public static String getHTML(Source xsl, Source xml) throws TransformerException
	{
		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer(xsl);
		transformer.setOutputProperty(OutputKeys.METHOD, "html");
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		//transformer.setParameter("encoding", "UTF-8");

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Result result = new StreamResult(out);
		File f = new File(xsl.);
		  DocumentBuilder builder = factory.newDocumentBuilder();
		  document = builder.parse(f);

		transformer.transform(xml, result);
		return out.toString();
	}*/
	/**
	 * Method to marshal any object
	 * 
	 * @param obj
	 * @return String as XML
	 * @throws JAXBException
	 */
	public static String marshal(Object obj) throws JAXBException
	{
		logger.debug("Entering String marshal(Object obj)");
		Writer outStream = new StringWriter();
		JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
		jaxbContext.createMarshaller().marshal(obj, outStream);
		logger.debug("Exiting String marshal(Object obj) with " + outStream.toString());
		return outStream.toString();

	}

	public static String marshal(Object obj, String outputFile) throws JAXBException
	{
		logger.debug("Entering String marshal(Object obj)");
		File file = new File(outputFile);
		JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
		jaxbContext.createMarshaller().marshal(obj, file);
		logger.debug("Exiting String marshal(Object obj,,String outputFile) with " + outputFile);
		return outputFile;

	}

	/**
	 * Method to unmarshal the XML String to any object of the provided class
	 * 
	 * @param clazz
	 * @param xml
	 * @return Object
	 * @throws JAXBException
	 */
	@SuppressWarnings("restriction")
	public static Object unmarshal(Class clazz, String xml) throws JAXBException
	{
		logger.debug("Calling unmarshal(Class clazz, String xml)");
		JAXBContext jc = JAXBContext.newInstance(clazz);
		Unmarshaller unmarsaller = jc.createUnmarshaller();
		unmarsaller.setListener(new Listener());
		return unmarsaller.unmarshal(new StringReader(xml));
	}

	/**
	 * Method to unmarshal the XML sent as InputStream to any object of the provided class
	 * @param clazz
	 * @param is
	 * @return object
	 * @throws JAXBException
	 */
	public static Object unmarshal(Class clazz, InputStream is) throws JAXBException
	{
		logger.debug("Calling unmarshal(Class clazz, InputStream is)");
		JAXBContext jc = JAXBContext.newInstance(clazz);
		Unmarshaller unmarsaller = jc.createUnmarshaller();
		unmarsaller.setListener(new Listener());
		return unmarsaller.unmarshal(is);
	}
	public static InputStream getInputStream(String relativePath) {
		return XMLUtils.class.getClassLoader().getResourceAsStream(
				relativePath);
	}
	/**
	 * @Important
	 * The inner class to handle afterUnmarshal Event
	 *
	 */
	private static class Listener extends Unmarshaller.Listener
	{
		/**
		 * 
		 */
		public void afterUnmarshal(Object target, Object parent)
		{
			BeanWrapper wrapper = new BeanWrapperImpl(target);
			for (PropertyDescriptor pd : wrapper.getPropertyDescriptors())
			{
				if (pd.getPropertyType() != null)
				{
					if (!BeanUtils.isSimpleProperty(pd.getPropertyType()))
					{
						try
						{
							Method setter = pd.getWriteMethod();
							if (setter != null)
							{
								Method getter = pd.getReadMethod();
								if (getter != null)
									setter.invoke(target, getter.invoke(target));
							}
						} catch (Exception ex)
						{
							ex.printStackTrace();
						}
					}
				}
			}
		}
	}
}
