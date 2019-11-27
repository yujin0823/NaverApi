package net.gondr.views;

import java.awt.Desktop;
import java.net.URI;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ShopItemController {
	@FXML
	private Label lblTitle;
	@FXML
	public Label lblDesc;

	private String url;

	public void setData(String title, String desc, String url) {
		lblTitle.setText(title);
		lblDesc.setText(desc);
		this.url = url;
	}

	public void openBrowser() {
		try {
			Desktop.getDesktop().browse(new URI(url));
		} catch (Exception e) {
			System.out.println("브라우저 오픈중 오류발생");
		}
	}
}
