package ca.mcgill.ecse.designproject.sentimentanalysis.saxparser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.html.HtmlParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

public class SaxParser {
	
	static InputStream input;
	
	public static void parse() {
		
		try {
			input = new FileInputStream("C:\\Users\\Sudip\\Desktop\\microsoft2012.html");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		ContentHandler handler = new BodyContentHandler();
		Metadata metadata = new Metadata();
		
		try {
			new HtmlParser().parse(input, handler, metadata, new ParseContext());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (TikaException e) {
			e.printStackTrace();
		}
		
		String plainText = handler.toString();
		String para = StringUtils.substringBetween(plainText, "<!-- surphace start -->", "<!-- surphace end -->");
		System.out.println(para);
		String[] theText = null;
		boolean still = true;
		for(int i = 0; true; i++) {
			theText[i] = StringUtils.substringAfter(para, ">");
			still = true;
		}
		
		//String nohtml = sb.toString().replaceAll("\\<.*?>","");
		//String para1 = StringUtils.substringAfter(para, ">");
		//System.out.println(metadata);
		
	}
	
	public static void main(String[] args) {
		parse();
		}
	
}
