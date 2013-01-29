
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class SentimentAnalysis {
	
	public static void main(String[] args){
		
		Document doc;
		Document namedEntities;
		String company;
		
		
		
		WebsiteCrawler wc = new WebsiteCrawler();
		
		wc.getLinks();
		
		
		
		
		
		
		
		
		
		
		
		
		
		// find the relevent articles on a company from seeking alpha
		
//		try{
//			
//			
//			
//		doc = AlchemyAPI.GetInstanceFromString("67e3847c59832f7e4d285d7048774ba79ff0d546").URLGetTextSentiment("http://seekingalpha.com/article/1119661-lowest-jobless-claims-reading-since-january-2008");
//		namedEntities = AlchemyAPI.GetInstanceFromString("67e3847c59832f7e4d285d7048774ba79ff0d546").URLGetRankedNamedEntities("http://seekingalpha.com/article/934051-microsoft-s-management-discusses-f1q13-results-earnings-call-transcript");
//		System.out.println(getStringFromDocument(doc));
//		//System.out.println(getStringFromDocument(namedEntities));
//		XMLParser parseEntities = new XMLParser(namedEntities);
//		System.out.println(parseEntities.parseDocumentToVerifyRelevance("microsoft", 0.6));
//			
//		}
//		
//
//		catch(Exception e){
//			System.out.println(e.getMessage());
//		}
	
		
		
		
		
		
	}
	
	
	
	  // utility method
    private static String getStringFromDocument(Document doc) {
        try {
            DOMSource domSource = new DOMSource(doc);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.transform(domSource, result);

            return writer.toString();
        } catch (TransformerException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    


    
}
	


