import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class SeekingAlphaDB {

	public static void storeLinks(List<String> links, String ticker) throws SQLException {
		Connection conn = null;
		PreparedStatement prep = null;
		String updateSql = "INSERT INTO " + ticker + " (Link, Date) VALUES (?, ?)";
		
		try {
			File db = new File("C:\\Users\\Sudip\\Documents\\Development\\workspace\\Design Project\\SAdb.db");
			
			if(db.exists()) {
				
				for(int i = 0; i < links.size(); i++) {
					Class.forName("org.sqlite.JDBC");
					conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Sudip\\Documents\\Development\\workspace\\Design Project\\SAdb.db");
					System.out.println(links.get(i));
					Document document = Jsoup.connect(links.get(i)).get();
					//System.out.println(document.html()); 
					String date = StringUtils.substringBetween(document.html(), "<span>", "</span>");
					System.out.println(date);
					
					conn.setAutoCommit(false);
					prep = conn.prepareStatement(updateSql);
					prep.setString(1, links.get(i));
					prep.setString(2, date);
					prep.executeUpdate();
					conn.commit();
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (prep != null) {
	            prep.close();
	        }
	        if (conn != null) {
	        	conn.setAutoCommit(true);
	            conn.close();
	        }
	        
		}
		
	}
	
	public static void main(String[] args) throws SQLException {
		storeLinks(SALinks.getLinks("msft"), "msft");
	}
}
