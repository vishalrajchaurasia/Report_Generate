package in.vishalit.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import in.vishalit.entity.CitizenPlan;
import in.vishalit.request.SearchRequest;

public interface ReportService {
	
	public List<String> getPlanNames();
	
	public List<String> getPlanStatuses();
	
	public List<CitizenPlan> search(SearchRequest request);
	
	public boolean exportExcel(HttpServletResponse response) throws Exception;
	
	public boolean exportPdf (HttpServletResponse response) throws Exception;

}
