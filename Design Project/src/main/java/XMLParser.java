

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XMLParser {

	Document doc;
	
	
	
	XMLParser(Document doc){
		this.doc = doc;
	}
	
	
	
	
	
	
    
    public boolean parseDocumentToVerifyRelevance(String name, Double relevanceThresh){
    	
    	
    	Element el = doc.getDocumentElement();
    	
		NodeList nl = el.getElementsByTagName("entity");
		
		if(nl != null && nl.getLength() > 0) {
			for(int i = 0 ; i < nl.getLength();i++) {

				//get the entity element
				Element elem = (Element)nl.item(i);
	
				String entityName = getTextValue(elem, "text");
				Double entityRelevance = getDoubleValue(elem, "relevance");
				
				
    if (entityName.toLowerCase().contains("microsoft") && entityRelevance > relevanceThresh)	
			return true;	
    }
			
		}
		
		return false;
    }
    
    
    private String getTextValue(Element ele, String tagName) {
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if(nl != null && nl.getLength() > 0) {
			Element el = (Element)nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}

		return textVal;
	}
    
    private Double getDoubleValue(Element ele, String tagName) {
		String textVal = null;
		Double number=null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if(nl != null && nl.getLength() > 0) {
			Element el = (Element)nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
			number = Double.parseDouble(textVal);
		}

		return number;
	}
	
	
}
