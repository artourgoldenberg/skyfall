

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebsiteCrawler {

	
	public void getLinks(){
		
		Document doc = getDocument("msft", 2);
		
		parseDocument(doc);
		
	}
	
	
	
	
	private Elements parseDocument(Document doc){
		
//	System.out.println(doc.toString());
	Elements links =null;	
		
	Elements contents = doc.getElementsByClass("content");
		
	for (Element content : contents){
	
		// get all links that are children of div and contain article in their a href element
		// doing this to remove repeat elements because of the comment div in the website
	links = content.select("div > a[href*=article]");

									}		
	return links;
		
	}
	
	
	
	
	
	
	
	
	
	private Document getDocument(String name, int page){
		
		try {
			return Jsoup.connect("http://seekingalpha.com/account/ajax_headlines_content").data("type", "all").data("page",Integer.toString(page)).data("slugs",name).data("is_symbol_page","true").post();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	
	
	
	public List<String> extractLinks(String url){
		Document doc;
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		System.out.println(e.getMessage());
		}
		
		
		return null;
	}
	
	
	
	
}
