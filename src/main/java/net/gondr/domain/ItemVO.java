package net.gondr.domain;

public class ItemVO {
	private String dataTime; //날짜
	private String pm10Value; //현재
	private String pm10Value24; //24시간 평균
	private String pm25Value; //초미세먼지 농도
	private String pm25Grade1h; //초미세먼지 등급
	private String o3Value; //오존 농도
	private String o3Grade; //오존 등급
	private String coValue; //일산화탄소 농도
	private String coGrade; //일산화탄소 등급
	private String so2Value; //아황산가스 농도 
	private String so2Grade; //아황산가스 등급
	private String no2Value; //이산화질소 농도
	private String no2Grade; //이산화질소 등급
	private String khaiValue; //통합대기 농도
	private String khaiGrade; //통합대기 등급

	public String getDataTime() {
		return dataTime;
	}

	public void setDataTime(String dataTime) {
		this.dataTime = dataTime;
	}

	public String getPm10Value() {
		return pm10Value;
	}

	public void setPm10Value(String pm10Value) {
		this.pm10Value = pm10Value;
	}

	public String getPm10Value24() {
		return pm10Value24;
	}

	public void setPm10Value24(String pm10Value24) {
		this.pm10Value24 = pm10Value24;
	}

	public String getPm25Value() {
		return pm25Value;
	}

	public void setPm25Value(String pm25Value) {
		this.pm25Value = pm25Value;
	}

	public String getPm25Grade1h() {
		return pm25Grade1h;
	}

	public void setPm25Grade1h(String pm25Grade1h) {
		this.pm25Grade1h = pm25Grade1h;
	}

	public String getO3Value() {
		return o3Value;
	}

	public void setO3Value(String o3Value) {
		this.o3Value = o3Value;
	}

	public String getO3Grade() {
		return o3Grade;
	}

	public void setO3Grade(String o3Grade) {
		this.o3Grade = o3Grade;
	}

	public String getCoValue() {
		return coValue;
	}

	public void setCoValue(String coValue) {
		this.coValue = coValue;
	}

	public String getCoGrade() {
		return coGrade;
	}

	public void setCoGrade(String coGrade) {
		this.coGrade = coGrade;
	}

	public String getSo2Value() {
		return so2Value;
	}

	public void setSo2Value(String so2Value) {
		this.so2Value = so2Value;
	}

	public String getSo2Grade() {
		return so2Grade;
	}

	public void setSo2Grade(String so2Grade) {
		this.so2Grade = so2Grade;
	}

	public String getNo2Value() {
		return no2Value;
	}

	public void setNo2Value(String no2Value) {
		this.no2Value = no2Value;
	}

	public String getNo2Grade() {
		return no2Grade;
	}

	public void setNo2Grade(String no2Grade) {
		this.no2Grade = no2Grade;
	}

	public String getKhaiValue() {
		return khaiValue;
	}

	public void setKhaiValue(String khaiValue) {
		this.khaiValue = khaiValue;
	}

	public String getKhaiGrade() {
		return khaiGrade;
	}

	public void setKhaiGrade(String khaiGrade) {
		this.khaiGrade = khaiGrade;
	}

}
