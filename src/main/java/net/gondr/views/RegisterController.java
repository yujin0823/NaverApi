package net.gondr.views;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import net.gondr.NaverApi.App;
import net.gondr.Util.JDBCUtil;
import net.gondr.Util.Util;

public class RegisterController extends MasterController {
	@FXML
	private TextField txtId;
	@FXML
	private TextField txtName;
	@FXML
	private PasswordField pass;
	@FXML
	private PasswordField passConfirm;
	@FXML
	private TextField txtLocation;
	@FXML
	private AnchorPane registerPane;
	
	//public String location = "";
	
	public void register() {
		
		String id = txtId.getText().trim();
		String name = txtName.getText().trim();
		String strPass = pass.getText().trim();
		String confirm = passConfirm.getText().trim();
		String location = txtLocation.getText().trim();
		
		if(id.isEmpty() || name.isEmpty() || strPass.isEmpty() || location.isEmpty()) {
			Util.showAlert("비어있음", "회원가입을 위한 필수값이 비어있습니다.", AlertType.INFORMATION);
			return;
		}
		
		if(!strPass.equals(confirm)) {
			Util.showAlert("불일치", "비밀번호의 값이 일치하지 않습니다.", AlertType.INFORMATION);
			return;
		}
		
		Connection con = JDBCUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sqlExist = "SELECT * FROM Api_users WHERE id = ?";
		String sqlInsert = "INSERT INTO Api_users (`id`, `name`, `pass`, `location`) "
							+ " VALUES (?, ?, PASSWORD(?), ?)";
		
		try {
			pstmt = con.prepareStatement(sqlExist);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				//중복된 유저가 존재하는 거
				Util.showAlert("회원중복", "해당 아이디의 유저가 존재합니다.", AlertType.INFORMATION);
				return;				
			}
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
			
			pstmt = con.prepareStatement(sqlInsert);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, strPass);
			pstmt.setString(4, location);
			
			if(pstmt.executeUpdate() != 1) {
				Util.showAlert("에러", "데이터베이스 실행중 오류", AlertType.ERROR);
				return;	
			}
			
			Util.showAlert("성공", "성공적으로 가입되었습니다.", AlertType.INFORMATION);
			App.app.slideOut(getRoot());
		} catch (Exception e) {
			e.printStackTrace();
			Util.showAlert("에러", "데이터베이스 실행중 오류", AlertType.ERROR);
			return;
		} finally {
			JDBCUtil.close(rs);
			JDBCUtil.close(pstmt);
			JDBCUtil.close(con);
		}
	}
	
	public void cancel() {
		App.app.slideOut(getRoot());
	}
}
