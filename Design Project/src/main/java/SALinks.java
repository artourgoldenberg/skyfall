

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang3.StringUtils;

public class SALinks {

	public static List<String> getLinks(String companyTicker) {

		List<String> links = new ArrayList<String>();
		HttpClient client = new HttpClient();
		client.getParams().setParameter("http.useragent", "Test Client");

		BufferedReader br = null;
		for(int page = 2; page < 51; page++) {
			PostMethod method = new PostMethod("http://seekingalpha.com/account/ajax_headlines_content");
			method.addParameter("type", "all");
			method.addParameter("page", Integer.toString(page));
			method.addParameter("slugs", companyTicker);
			method.addParameter("is_symbol_page", "true");
	
			try{
				int returnCode = client.executeMethod(method);
				if(returnCode == HttpStatus.SC_NOT_IMPLEMENTED) {
					System.out.println("The Post method is not implemented by this URI");
					// still consume the response body
					method.getResponseBodyAsString();
				} else {
					br = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream()));
					String readLine;
					while(((readLine = br.readLine()) != null)) {
						if(readLine.contains("a href")) {
							String link = StringUtils.substringBetween(readLine, "a href=\"", "\" sasource=");
							//System.out.println(readLine);
							if(link != null) {
								link = "http://seekingalpha.com" + link;
								links.add(link);
								//System.out.println(link);
								// test
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				method.releaseConnection();
				if(br != null) { 
					try { 
						br.close(); 
					} catch (Exception fe) {
						fe.printStackTrace();
					}
				}
			}
		}
		System.out.println(links);
		return links;
	}
	
	public static void main(String[] args) {
		getLinks("bac");
	}
}
