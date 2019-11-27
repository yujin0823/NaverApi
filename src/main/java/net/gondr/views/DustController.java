package net.gondr.views;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import net.gondr.NaverApi.App;
import net.gondr.domain.ItemVO;
import net.gondr.domain.ResponseVO;

public class DustController extends MasterController {
	@FXML
	private AnchorPane DustPage;
	@FXML
	private TextField cityTf;
	@FXML
	private Label where2;
	
	@FXML
	private Label when; //언제
	@FXML
	private Label pm10Value; //현재
	@FXML
	private Label pm10Value24; //24시간 평균
	@FXML
	private Label pm25Value; //초미세먼지 농도
	@FXML
	private Label pm25Grade1h; //초미세먼지 등급
	@FXML
	private Label o3Value; //오존 농도
	@FXML
	private Label o3Grade; //오존 등급
	@FXML
	private Label coValue; //일산화탄소 농도
	@FXML
	private Label coGrade; //일산화탄소 등급
	@FXML
	private Label so2Value; //아황산가스 농도 
	@FXML
	private Label so2Grade; //아황산가스 등급
	@FXML
	private Label no2Value; //이산화질소 농도
	@FXML
	private Label no2Grade; //이산화질소 등급
	@FXML
	private Label khaiValue; //통합대기 농도
	@FXML
	private Label khaiGrade; //통합대기 등급
	
	private String ws = "";
	private String pmVs = "";
	private String pmGs = "";
	private String pm25Vs = "";
	private String pm25Gs = "";
	private String o3Vs = "";
	private String o3Gs = "";
	private String coVs = "";
	private String so2Vs = "";
	private String so2Gs = "";
	private String coGs = "";
	private String no2Vs = "";
	private String no2Gs = "";
	private String khaiVs = "";
	private String khaiGs = "";
	
	private ArrayList<String> w = new ArrayList<String>();
	private ArrayList<String> pmV = new ArrayList<String>();
	private ArrayList<String> pmG = new ArrayList<String>();
	private ArrayList<String> pm25V = new ArrayList<String>();
	private ArrayList<String> pm25G = new ArrayList<String>();
	private ArrayList<String> o3V = new ArrayList<String>();
	private ArrayList<String> o3G = new ArrayList<String>();
	private ArrayList<String> coV = new ArrayList<String>();
	private ArrayList<String> coG = new ArrayList<String>();
	private ArrayList<String> so2V = new ArrayList<String>();
	private ArrayList<String> so2G = new ArrayList<String>();
	private ArrayList<String> no2V = new ArrayList<String>();
	private ArrayList<String> no2G = new ArrayList<String>();
	private ArrayList<String> khaiV = new ArrayList<String>();
	private ArrayList<String> khaiG = new ArrayList<String>();
	
	@FXML
	public void initialize() {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("오류");
		}
	}
	
	public void search() {
		try {
			String station = cityTf.getText();
			where2.setText(station + "기준");
			if (station.isEmpty()) {
				return;
			}
			station = URLEncoder.encode(station, "UTF-8");
			String Id = "";
			String apistr = "http://openapi.airkorea.or.kr/"
					+ "openapi/services/rest/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty" + "?stationName=";
			
			System.out.println(apistr);
			URL url = new URL(apistr + station + "&dataTerm=month&pageNo=1&numOfRows=10&" + "ServiceKey=" + Id + "&ver=1.3"
					+ "&_returnType=json");
			
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            
            int responseCode = con.getResponseCode(); //에러코드확인
            BufferedReader br;
			if (responseCode == 200) { // 정상호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // 에러발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
           
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			
			br.close();
			//요 아래가 첫번째 긴 줄이닿
			System.out.println(response.toString());
			
			String json = response.toString();
			Gson gson = new Gson(); //Gson객체를 선언하고
			//ResponseVO 형태로 변환한다.
			ResponseVO responseVO = gson.fromJson(json, ResponseVO.class);
			
			List<ItemVO> dustList = responseVO.getList();
			
			for (ItemVO item : dustList) {
				System.out.println(item.getDataTime());
				System.out.println("현재 미세먼지 농도 " + item.getPm10Value() + "㎍/㎥");
				System.out.println("24시간 평균 " + item.getPm10Value24() + "㎍/㎥");
				System.out.println("초미세먼지 " + item.getPm25Grade1h() + " " + item.getPm25Value() + "㎍/㎥");
				System.out.println("오존 " + item.getO3Grade() + " " + item.getO3Value() +"ppm");
				System.out.println("일산화탄소 " + item.getCoGrade() + " " + item.getCoValue() + "ppm");
				System.out.println("아황산가스 " + item.getSo2Grade() + " " + item.getSo2Value() + "ppm");
				System.out.println("이산화질소 " + item.getNo2Grade() + " " + item.getNo2Value() + "ppm");
				System.out.println("통합대기 " + item.getKhaiGrade() + " " + item.getKhaiValue());
				System.out.println();
				
				ws += item.getDataTime() + "기준";
				pmVs += item.getPm10Value();
				pmGs += item.getPm10Value24();
				pm25Vs += item.getPm25Value() + "㎍/㎥";
				pm25Gs += item.getPm25Grade1h();
				o3Vs += item.getO3Value() +"ppm";
			    o3Gs += item.getO3Grade();
			    coVs += item.getCoValue() + "ppm";
			    coGs += item.getCoGrade();
			    so2Vs = item.getSo2Value() + "ppm";
				so2Gs = item.getSo2Grade();
				no2Vs = item.getNo2Value() + "ppm";
				no2Gs = item.getNo2Grade();
				khaiVs = item.getKhaiValue();
				khaiGs = item.getKhaiGrade();
				
				w.add(ws);
				pmV.add(pmVs);
				pmG.add(pmGs);
				pm25V.add(pm25Vs);
				pm25G.add(pm25Gs);
				o3V.add(o3Vs);
				o3G.add(o3Gs);
				coV.add(coVs);
				coG.add(coGs);
				so2V.add(so2Vs);
				so2G.add(so2Gs);
				no2V.add(no2Vs);
				no2G.add(no2Gs);
				khaiV.add(khaiVs);
				khaiG.add(khaiGs);
				
			}
			
			for (int i=0; i<1; i++) {				
				System.out.println(pm25G.get(i));
				
				when.setText(w.get(i));
				pm10Value.setText(pmV.get(i));
				pm10Value24.setText(pmG.get(i));
				pm25Value.setText(pm25V.get(i));
				pm25Grade1h.setText(pm25G.get(i));
				o3Value.setText(o3V.get(i));
				o3Grade.setText(o3G.get(i));
				coValue.setText(coV.get(i));
				coGrade.setText(coG.get(i));
				so2Value.setText(so2V.get(i));
				so2Grade.setText(so2G.get(i));
				no2Value.setText(no2V.get(i));
				no2Grade.setText(no2G.get(i));
				khaiValue.setText(khaiV.get(i));
				khaiGrade.setText(khaiG.get(i));
				
				if (pm25G.get(i) == "1") {
					pm25Grade1h.setTextFill(Color.BLUE);
					o3Grade.setTextFill(Color.BLUE);
					coGrade.setTextFill(Color.BLUE);
					so2Grade.setTextFill(Color.BLUE);
					no2Grade.setTextFill(Color.BLUE);
					khaiGrade.setTextFill(Color.BLUE);
				}
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void keypressHandle(KeyEvent e) {
		if( e.getCode() == KeyCode.ENTER ) {
			search();
		}
	}
	
	public void Previous() {
		App.app.slideOut(DustPage);
	}
}
