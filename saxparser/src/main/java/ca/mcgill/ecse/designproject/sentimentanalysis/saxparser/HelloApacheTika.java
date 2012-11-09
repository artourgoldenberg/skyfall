package ca.mcgill.ecse.designproject.sentimentanalysis.saxparser;

import java.io.InputStream;
import java.net.URL;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.html.HtmlParser;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.sax.LinkContentHandler;
import org.apache.tika.sax.TeeContentHandler;
import org.apache.tika.sax.ToHTMLContentHandler;
import org.xml.sax.ContentHandler;

public class HelloApacheTika {

    public static void main (String args[]) throws Exception {
        URL url = new URL("http://chrisjordan.ca/post/15219674437/parsing-html-with-apache-tika");
        InputStream input = url.openStream();
        LinkContentHandler linkHandler = new LinkContentHandler();
        ContentHandler textHandler = new BodyContentHandler();
        ToHTMLContentHandler toHTMLHandler = new ToHTMLContentHandler();
        TeeContentHandler teeHandler = new TeeContentHandler(linkHandler, textHandler, toHTMLHandler);
        Metadata metadata = new Metadata();
        ParseContext parseContext = new ParseContext();
        HtmlParser parser = new HtmlParser();
        parser.parse(input, teeHandler, metadata, parseContext);
        System.out.println("title:\n" + metadata.get("title"));
        //System.out.println("links:\n" + linkHandler.getLinks());
        String text = textHandler.toString();
        String newText = text.replaceAll("//s", "");
        System.out.println("text:\n" + newText);
        //System.out.println("html:\n" + toHTMLHandler.toString());
    }
}