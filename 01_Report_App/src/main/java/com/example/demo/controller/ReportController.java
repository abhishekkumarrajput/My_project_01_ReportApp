package com.example.demo.controller;

import java.net.http.HttpResponse;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.CitiZenPlan;
import com.example.demo.Request.SearchRequest;
import com.example.demo.Service.ReportService;
import com.example.demo.serviceImpl.ReportServiceImpl;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ReportController {
	
	
	@Autowired
	private ReportService reportService;
	
	
	//download Excel
	
	@GetMapping("/excel")
    public void excelExport(HttpServletResponse httpResponse) throws Exception {   //this HttpServletResponse as a parameter because i want to send excel file to the browser to the response object this is why
	
	httpResponse.setContentType("application/octet-stream");  // when browser sending the request we need to tell the browser in which format coming the browser like Pdf type or octel-stream agr pdf mein chaiye toh pdf laga dege
	
	httpResponse.addHeader("Content-Disposition", "attachment;filename=plans.xls");   //why use addHeader because i w to tell to the browser i am send one file as a Attachment you just Download in the browser
	
 this.reportService.exportExcel(httpResponse);
	
}
	
	// Download PDF

	@GetMapping("/pdf")
    public void pdfExport(HttpServletResponse httpResponse) throws Exception {  
	
	httpResponse.setContentType("application/pdf"); 
	
	httpResponse.addHeader("Content-Disposition", "attachment;filename=plans.pdf");
	
 this.reportService.exportPdf(httpResponse);
	
}
	
	// FOR SEARCHING 
	
	@PostMapping("/search")
	private String handelRequest( @ModelAttribute("search") SearchRequest Request,Model model) {
		                                                 //help of @ModelAttribute we can send data backend to uI aur agar hum isko nahi lagayege toh hum model.addAttribute ka bhi use kar sakte dono same kaam karte hain
		List<CitiZenPlan> searchData = this.reportService.search(Request);
		model.addAttribute("data", searchData);           // humare pass searchdata aa gaya ab model.addAttribute ki help se hum is data ko uI per send karte hai
		
		System.out.println(Request);
		init(model);
		return "index";
	}
	
	
	@GetMapping("/")
		public String indexPages(Model model) {    // model used to send the data controller to uI
		model.addAttribute("search", new SearchRequest());  //when my 1st method is loaded then show only empty object
		init(model);
			return "index"; 
		}
	
	
	private void init (Model model) {    //why we use init method => wheneve we use Reoload the page data not avilable into a same page 
                                         //toh is problem ko solve  karne ke humne init method ka use kiya jab page reolad ho hamare data init method mein fir se aajye;
		
		model.addAttribute("names", reportService.getPlaneName());
        model.addAttribute("search",reportService.getPlaneStatus());


	}
	
	}
	


	
	