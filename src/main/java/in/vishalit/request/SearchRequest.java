package in.vishalit.request;





public class SearchRequest {
	private String planName;
	private String planStatus;
	private String gender;
	
	private String startDate;
	private String endDate;
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public String getPlanStatus() {
		return planStatus;
	}
	public void setPlanStatus(String planStatus) {
		this.planStatus = planStatus;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@Override
	public String toString() {
		return "SearchRequest [planName=" + planName + ", planStatus=" + planStatus + ", gender=" + gender
				+ ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	
}
