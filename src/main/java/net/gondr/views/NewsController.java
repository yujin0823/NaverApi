package net.gondr.views;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import net.gondr.NaverApi.App;

public class NewsController extends MasterController {
	@FXML
	private TextField txtWord;
	@FXML
	private VBox newsList;
	@FXML
	private AnchorPane newsPage;
	
	private String ci = ""; //발급받은 클라이언트 아이디
	private String cs = ""; //발급받은 클라이언트 시크릿
	private String apiURL = "https://openapi.naver.com/v1/search/news.json";
	
	@FXML
	public void initialize() {
		newsList.getChildren().clear();
	}
	
	public void search() {
		try {
			String word = txtWord.getText();
			if(word.isEmpty()) {
				return;
			}
			word = URLEncoder.encode(word, "UTF-8");
			
			
			URL urlIns = new URL(apiURL+ "?query=" + word + "&display=40");
			HttpURLConnection con = (HttpURLConnection) urlIns.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Naver-Client-Id", ci);
			con.setRequestProperty("X-Naver-Client-Secret", cs);
			
			int resCode = con.getResponseCode();
			
			BufferedReader br;
			if(resCode == 200) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			
			String inputLine;
			StringBuffer resString = new StringBuffer();
			while((inputLine = br.readLine()) != null) {
				resString.append(inputLine);
			}
			br.close();			
			
			String json = resString.toString();
			System.out.println(json);
			
			JsonParser parser = new JsonParser();
			
			JsonElement jsonElem = parser.parse(json);
			JsonArray items = jsonElem.getAsJsonObject().get("items").getAsJsonArray();
			
			makeFXML(items);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("API 호출중 오류 발생");
		}
	}
	
	private void makeFXML(JsonArray items) throws Exception {
		newsList.getChildren().clear();
		
		for(JsonElement item : items) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/net/gondr/views/NewsItem.fxml"));
			AnchorPane root = (AnchorPane)loader.load();
			
			NewsItemController ic = loader.getController();
			
			String title = item.getAsJsonObject().get("title").getAsString();
			String desc = item.getAsJsonObject().get("description").getAsString();
			String link = item.getAsJsonObject().get("link").getAsString();
			ic.setData(title, desc, link);
			newsList.getChildren().add(root);
		}
	}
	
	public void keypressHandle(KeyEvent e) {
		if( e.getCode() == KeyCode.ENTER ) {
			search();
		}
	}
	
	public void Previous() {
		App.app.slideOut(newsPage);
	}
}
