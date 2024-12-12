package in.vishalit.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.vishalit.entity.CitizenPlan;
import in.vishalit.request.SearchRequest;
import in.vishalit.service.ReportService;

@Controller
public class ReportController {
	
	@Autowired
	private ReportService service;
	
	@GetMapping("/pdf")
	public void pdfExport(HttpServletResponse response,Model model) throws Exception {
		response.setContentType("application/pdf");
		
		response.addHeader("content-Disposition", "attachment;filename=plans.pdf");
		
		service.exportPdf(response);
	}
	
	@GetMapping("/excel")
	public void excelExport(HttpServletResponse response) throws Exception {
		response.setContentType("application/octet-stream");
		
		response.addHeader("content-Disposition", "attachment;filename=plans.xls");
		
		service.exportExcel(response);
	}
	
	
	@PostMapping("/search")
	public String handleSearch(@ModelAttribute("search") SearchRequest search,  Model model) {
		
		//System.out.println(search); 
		List<CitizenPlan> plans = service.search(search);
		model.addAttribute("plans", plans);
		//@ModelAttribute("search") other option is below 
		//model.addAttribute("search", search); binding data back to the form 
		init(model);
		
		return "index";
	}
	
	@GetMapping("/")
	public String indexPage(Model model) {
		//SearchRequest searchObj = new SearchRequest();
		//model.addAttribute("search", searchObj);
		//or
		model.addAttribute("search", new SearchRequest());//when you send a request empty binding sending 
		init(model);
		return "index";
	}

	private void init(Model model) {
		//model.addAttribute("search", searchObj);
		//or
		//model.addAttribute("search", new SearchRequest());//this is meant by new search object is created
		model.addAttribute("names", service.getPlanNames());
		model.addAttribute("status", service.getPlanStatuses());
	}
}
