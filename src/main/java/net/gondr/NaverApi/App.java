package net.gondr.NaverApi;

import java.util.HashMap;
import java.util.Map;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import net.gondr.domain.UserVO;
import net.gondr.views.AreaController;
import net.gondr.views.BookController;
import net.gondr.views.DustController;
import net.gondr.views.EncycController;
import net.gondr.views.LoginController;
import net.gondr.views.MainController;
import net.gondr.views.MasterController;
import net.gondr.views.MovieController;
import net.gondr.views.NewsController;
import net.gondr.views.RegisterController;
import net.gondr.views.ShopController;

public class App extends Application {
	public static App app;
	
	private StackPane mainPage = null;

	private Map<String, MasterController> controllerMap = new HashMap<>();

	@Override
	public void start(Stage primaryStage) {
		app = this; //싱글톤을 만든다
		try {
			FXMLLoader mainloader = new FXMLLoader();
			mainloader.setLocation(getClass().getResource("/net/gondr/views/MainLayout.fxml"));
			mainPage = mainloader.load();
			
			MainController mc = mainloader.getController();
			mc.setRoot(mainPage);
			controllerMap.put("main", mc);
			
			FXMLLoader loginloader = new FXMLLoader();
			loginloader.setLocation(getClass().getResource("/net/gondr/views/LoginLayout.fxml"));
			AnchorPane loginPage = loginloader.load();
			
			LoginController lc = loginloader.getController();
			lc.setRoot(loginPage);
			controllerMap.put("login", lc);
			
			//회원가입 창
			FXMLLoader registerLoader = new FXMLLoader();
			registerLoader.setLocation(getClass().getResource("/net/gondr/views/RegisterLayout.fxml"));
			AnchorPane registerPage = registerLoader.load();
			
			RegisterController rc = registerLoader.getController();
			rc.setRoot(registerPage);;
			controllerMap.put("register", rc);
			
			//네이버Api - 뉴스
			FXMLLoader newsLoader = new FXMLLoader();
			newsLoader.setLocation(getClass().getResource("/net/gondr/views/NewsLayout.fxml"));
			AnchorPane newsPage = newsLoader.load();
			
			NewsController nc = newsLoader.getController();
			nc.setRoot(newsPage);
			controllerMap.put("news", nc);
			
			//네이버Api - 책
			FXMLLoader bookLoader = new FXMLLoader();
			bookLoader.setLocation(getClass().getResource("/net/gondr/views/BookLayout.fxml"));
			AnchorPane bookPage = bookLoader.load();
			
			BookController bc = bookLoader.getController();
			bc.setRoot(bookPage);
			controllerMap.put("book", bc);
			
			//네이버Api - 백과사전
			FXMLLoader encycLoader = new FXMLLoader();
			encycLoader.setLocation(getClass().getResource("/net/gondr/views/EncycLayout.fxml"));
			AnchorPane encycPage = encycLoader.load();
			
			EncycController ec = encycLoader.getController();
			ec.setRoot(encycPage);
			controllerMap.put("dictionary", ec);
			
			//네이버Api - 영화
			FXMLLoader movieLoader = new FXMLLoader();
			movieLoader.setLocation(getClass().getResource("/net/gondr/views/MovieLayout.fxml"));
			AnchorPane moviePage = movieLoader.load();
			
			MovieController moviec = movieLoader.getController();
			moviec.setRoot(moviePage);
			controllerMap.put("movie", moviec);
			
			//네이버Api - 지역
			FXMLLoader areaLoader = new FXMLLoader();
			areaLoader.setLocation(getClass().getResource("/net/gondr/views/AreaLayout.fxml"));
			AnchorPane areaPage = areaLoader.load();
			
			AreaController ac = areaLoader.getController();
			ac.setRoot(areaPage);
			controllerMap.put("area", ac);
			
			//네이버Api - 쇼핑
			FXMLLoader shopLoader = new FXMLLoader();
			shopLoader.setLocation(getClass().getResource("/net/gondr/views/ShopLayout.fxml"));
			AnchorPane shopPage = shopLoader.load();
			
			ShopController sc = shopLoader.getController();
			sc.setRoot(shopPage);
			controllerMap.put("shop", sc);

			Scene scene = new Scene(mainPage);
			mainPage.getChildren().add(loginPage);
			
			//공공데이터 - 미세먼지Api
			FXMLLoader dustLoader = new FXMLLoader();
			dustLoader.setLocation(getClass().getResource("/net/gondr/views/dustLayout.fxml"));
			AnchorPane dustPage = dustLoader.load();
			
			DustController dc = dustLoader.getController();
			dc.setRoot(dustPage);
			controllerMap.put("dust", dc);

			scene.getStylesheets().add(getClass().getResource("app.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("NaverApi, 공공데이터 미세먼지Api");
			primaryStage.setResizable(false);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("프로그램 로딩중 오류 발생");
		}
	}
	
	public void slideOut(Pane pane) {
		Timeline timeline = new Timeline();
		KeyValue toRight = new KeyValue(pane.translateXProperty(), 800);
		KeyValue fadeOut = new KeyValue(pane.opacityProperty(), 0);
		
		KeyFrame frame = new KeyFrame(Duration.millis(500), (e) -> {
			mainPage.getChildren().remove(pane);
		}, toRight, fadeOut);
		
		timeline.getKeyFrames().add(frame);
		timeline.play();
	}
	
	public void loadPage(String name) {
		MasterController c = controllerMap.get(name);
		Pane pane = c.getRoot();
		mainPage.getChildren().add(pane);
		
		pane.setTranslateX(-800);
		pane.setOpacity(0);
		
		Timeline timeline = new Timeline();
		KeyValue toRight = new KeyValue(pane.translateXProperty(), 0);
		KeyValue fadeIn = new KeyValue(pane.opacityProperty(), 1);
		
		KeyFrame frame = new KeyFrame(Duration.millis(500), toRight, fadeIn);
		
		timeline.getKeyFrames().add(frame);
		timeline.play();
	}
	
	public void setLoginInfo(UserVO vo) {
		MainController mc = (MainController) controllerMap.get("main");
		mc.setLoginInfo(vo);
	}

	public static void main(String[] args) {
		launch(args);
	}

}
