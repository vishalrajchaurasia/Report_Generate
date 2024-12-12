package in.vishalit.service;

import java.io.File;
//import java.io.File;
//import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

//import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;



import in.vishalit.entity.CitizenPlan;
import in.vishalit.repo.CitizenPlanRepository;
import in.vishalit.request.SearchRequest;
import in.vishalit.util.EmailUtil;
import in.vishalit.util.ExcelGenerator;
import in.vishalit.util.PdfGenerator;
@Service
public class ReportServiceImpl implements ReportService {
	
	@Autowired
	private EmailUtil emailUtils;
	
	@Autowired
	private ExcelGenerator excelGenerator;
	
	@Autowired
	private CitizenPlanRepository planRepo;
	
	@Autowired
	private PdfGenerator pdfGenerator;

	@Override
	public List<String> getPlanNames() { 
		 List<String> planNames = planRepo.getPlanNames();
		 return planNames;
		
	}

	@Override
	public List<String> getPlanStatuses() {
		 List<String> planStatus = planRepo.getPlanStatus();
		 return planStatus;
	}

	@Override
	public List<CitizenPlan> search(SearchRequest request) {
		CitizenPlan entity = new CitizenPlan();
		if(null!=request.getPlanName() && !"".equals(request.getPlanName())) {
			entity.setPlanName(request.getPlanName());
		}
		if(null!=request.getPlanStatus() && !"".equals(request.getPlanStatus())) {
			entity.setPlanStatus(request.getPlanStatus());
		}
		if(null!=request.getGender() && !"".equals(request.getGender())) {
			entity.setGender(request.getGender());
		}
		if(null !=request.getStartDate() && !"".equals(request.getStartDate() )) {
			String startDate= request.getStartDate();
			DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy-MM-dd");
					//convert String to lOcalDate
			LocalDate localDate = LocalDate.parse(startDate,formatter);
			entity.setPlanStartDate(localDate);
		}
		if(null !=request.getEndDate() && !"".equals(request.getEndDate() )) {
			String endDate= request.getEndDate();
			DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy-MM-dd");
					//convert String to lOcalDate
			LocalDate localDate = LocalDate.parse(endDate,formatter);
			entity.setPlanStartDate(localDate);
		}
		
		Example<CitizenPlan> Entityof = Example.of(entity); //example is interface and of is static method 
		return planRepo.findAll(Entityof);
		
	}

	@Override
	public boolean exportExcel(HttpServletResponse response) throws Exception {
		
		File f = new File("Plans.xls");
		
		List<CitizenPlan> plans = planRepo.findAll();
		excelGenerator.generate(response, plans,f);
		
		String subject= "Test mail subject";
		String body="<h1>Test mail body</h1>";
		String to="vchaurasia7@gmail.com";
		
		emailUtils.sendEmail(subject, body, to,f);
		f.delete();
		return true;
	}

	@Override
	public boolean exportPdf(HttpServletResponse response) throws Exception {
		File f = new File("Plans.pdf");
		List<CitizenPlan> plans = planRepo.findAll();
		
		pdfGenerator.generate(response, plans,f);
		String subject= "Test mail subject";
		String body="<h1>Test mail body</h1>";
		String to="vchaurasia7@gmail.com";
		
		emailUtils.sendEmail(subject, body, to,f);
		f.delete();
		return true;
	}

}
