package net.gondr.views;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import net.gondr.NaverApi.App;
import net.gondr.domain.UserVO;

public class MainController extends MasterController {
	
	@FXML
	private Label loginInfo;
	
	private UserVO user;
	
	public void newsPage() {
		App.app.loadPage("news");
	}
	
	public void bookPage() {
		App.app.loadPage("book");
	}
	
	public void dictionaryPage() {
		App.app.loadPage("dictionary");
	}
	
	public void moviePage() {
		App.app.loadPage("movie");
	}
	
	public void areaPage() {
		App.app.loadPage("area");
	}
	
	public void shopPage() {
		App.app.loadPage("shop");
	}
	
	public void dustPage() {
		App.app.loadPage("dust");
	}
	
	public void logout() {
		user = null;
		App.app.loadPage("login");
	}
	
	public UserVO getUser() {
		return user;
	}
	
	public void setLoginInfo(UserVO vo) {
		this.user = vo;
		loginInfo.setText(vo.getName() + "님" + "\n" + "[" + vo.getLocation() + "]");
	}

}
