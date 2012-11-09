package ca.mcgill.ecse.designproject.sentimentanalysis.saxparser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.html.HtmlParser;
import org.apache.tika.sax.BodyContentHandler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/** 
 * Html Parser for reuters.com articles
 * @author Sudip
 *
 */
public class HtmlParserReuters {
	
	final static int writeLimit = 1000000;
	private static String title = null;
	private static String author = null;
	private static String date = null;
	private static String noHtmlString = null;
	
	/**
	 * Parses an html file using Apache Tika.
	 * @param htmlFile - the source html file
	 */
	public static void parse(File htmlFile) {
		
		try {
			InputStream input = new FileInputStream(htmlFile);
			
			//Apache Tika code to parse the html source code
			ContentHandler handler = new BodyContentHandler(writeLimit);
			Metadata metadata = new Metadata();
			new HtmlParser().parse(input, handler, metadata, new ParseContext());
			String plainText = handler.toString();
			
			//Specific for Reuters articles
			String someStuff = StringUtils.substringBetween(plainText, "<div id=\"articleInfo\">", "<div class=\"relatedTopicButtons\">");
			title = StringUtils.substringBetween(plainText, "<h1>", "</h1>");
			author = StringUtils.substringBetween(plainText, "<p class=\"byline\">By ", "</p>");
			date = StringUtils.substringBetween(someStuff, "<span class=\"timestamp\">", "</span>");
			String junk = StringUtils.substringAfter(someStuff, "</div>");
			String text = getTextOnlyFromHtmlText(junk);
			noHtmlString = text.replaceAll("\\s{2,}", "\n");
			
//			System.out.println("Title: " + title);
//			if (author != null) { 
//				System.out.println("Author: " + author);
//			}
//			System.out.println("Date: " + date);
//			System.out.println();
//			System.out.println(noHtmlString);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (TikaException e) {
			e.printStackTrace();
		}
		
    }
	
	/**
	 * Writes the Strings from the parse method to a text file
	 * @param textFile - .txt file
	 */
	public static void writeToTextFile(File textFile) {
		
		try {
			FileUtils.writeStringToFile(textFile, "Title: " + title);
			if (author != null) {
				FileUtils.writeStringToFile(textFile, "\nAuthor: " + author, true);
			}
			FileUtils.writeStringToFile(textFile, "\nDate: " + date, true);
			FileUtils.writeStringToFile(textFile, "\n\n" + noHtmlString, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Writes all of the html files in the specified folder to text files in another specified folder
	 */
	public static void writeAll() {
		
		File folder = new File("C:\\Users\\Sudip\\Desktop\\Microsoft\\");
		if (folder.exists()) {
			if (folder.isDirectory()) {
				File[] htmlFiles = new File("C:\\Users\\Sudip\\Desktop\\Microsoft\\").listFiles();
				int i = 0;
				for (File htmlFile: htmlFiles) {
					if (htmlFile.isFile()) {
						parse(htmlFile);
						writeToTextFile(new File("C:\\Users\\Sudip\\Desktop\\Microsoft\\TextFiles\\" + i + ".txt"));
					}
					i++;
				}
			}
		}
	}
	
	/**
	 * Method to strip html tags from a String (using JSoup, another html parser)
	 * @param htmlText - String of html text
	 * @return
	 */
	public static String getTextOnlyFromHtmlText(String htmlText){
		
	    Document doc = Jsoup.parse(htmlText);
	    doc.outputSettings().charset("UTF-8");
	    htmlText = Jsoup.clean(doc.body().html(), Whitelist.simpleText());
	    htmlText = StringEscapeUtils.unescapeHtml3(htmlText);
	    return htmlText;
	}
	
	public static void main(String[] args) {
		writeAll();
	}
	
}
