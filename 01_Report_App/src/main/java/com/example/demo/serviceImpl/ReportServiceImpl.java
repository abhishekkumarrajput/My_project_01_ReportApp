package com.example.demo.serviceImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import com.example.demo.Model.CitiZenPlan;
import com.example.demo.Repository.CitizenRepository;
import com.example.demo.Request.SearchRequest;
import com.example.demo.Service.ReportService;
import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private CitizenRepository citizenRepository;
	
	@Override
	public List<String> getPlaneName() {
	List<String> planName = this.citizenRepository.getPlaneName();
		return planName;
	}

	@Override
	public List<String> getPlaneStatus() {
		List<String> planeStaus = this.citizenRepository.getStatusName();
		return planeStaus;
	}

	@Override
	public List<CitiZenPlan> search(SearchRequest search) {
		
	CitiZenPlan citiZenPlan= new CitiZenPlan();	          // i need copy the data from binding object to entity object 

	if(search.getPlaneName()!=null && search.getPlaneName()!="") {   // searchRequest mein jo data aa Raha hai vo null aur empty bhi vo skate hain esliye humne condision lagaiye hain
		citiZenPlan.setPlaneName(search.getPlaneName());
		
	}
	
	if(search.getPlaneStatus()!=null && search.getPlaneStatus()!="") {
		citiZenPlan.setPlaneStatus(search.getPlaneStatus());
	}
	if(search.getGender()!=null && search.getGender()!="") {
		citiZenPlan.setGender(search.getGender());
		
	}
	if(search.getPlanStartDate()!= null && search.getPlanStartDate()!="") {
		String startDate = search.getPlanStartDate();
DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy-MM-dd");

// convert String to LocalDate  note:- humne convert kyu kiya kyuki humri entity mein Data ka jo type tha vo localDate mein tha aur Humre ui jo Date aa rahi thi vo String format mein thi esliye
LocalDate localDate = LocalDate.parse(startDate, formatter);
citiZenPlan.setPlanStartDate(localDate);
		
	}
	
	if(search.getPlanEndDate()!= null && search.getPlanEndDate()!="") {
		String endDate = search.getPlanEndDate();
DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy-MM-dd");

// convert String to LocalDate  note:- humne convert kyu kiya kyuki humri entity mein Data ka jo type tha vo localDate mein tha aur Humre ui jo Date aa rahi thi vo String format mein thi esliye
LocalDate localDate = LocalDate.parse(endDate, formatter);
citiZenPlan.setPlanEndDate(localDate);
		
	}
	
		 List<CitiZenPlan> findAllData = this.citizenRepository.findAll(Example.of(citiZenPlan));
	return findAllData;
	}
	

	@Override
	public boolean exportExcel(HttpServletResponse httpResponse) throws Exception {
	
		Workbook workbook = new HSSFWorkbook();    //create a Workbook this is interface and HSSFWorkbok is class;
		
		                                        //inside a Excel multiple sheet avilable so we can create sheet
		Sheet sheet = workbook.createSheet("Plans-data");  ///sheet name 
		
		Row headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("ID");
		headerRow.createCell(1).setCellValue("citiZenName");
		headerRow.createCell(2).setCellValue("planeName");
		headerRow.createCell(3).setCellValue("planeStatus");
		headerRow.createCell(4).setCellValue("gender");
		headerRow.createCell(5).setCellValue("planStartDate");
		headerRow.createCell(6).setCellValue("planEndDate");
		headerRow.createCell(7).setCellValue("benefitAmt");
		
		
		List<CitiZenPlan> records = this.citizenRepository.findAll();
		
		int dataRowIndex=1;    //data row index automatic ingrement hota jayege jitne humare pass colum hain
		
		for(CitiZenPlan plan:records) {
			
			Row dataRow= sheet.createRow(dataRowIndex);
			
			dataRow.createCell(0).setCellValue(plan.getCitiZenId());
			dataRow.createCell(1).setCellValue(plan.getCitiZenName());
			dataRow.createCell(2).setCellValue(plan.getPlaneName());
			dataRow.createCell(3).setCellValue(plan.getPlaneStatus());
			dataRow.createCell(4).setCellValue(plan.getGender());
			if(plan.getPlanStartDate()!=null) {
				dataRow.createCell(5).setCellValue(plan.getPlanStartDate()+"");  //last mein +"" esliye lagay kyu ki yeh date excel mein date formarte mein downlaod hogi isko convert kane ke liye humne String mein convert kar diya 
			}else {
				dataRow.createCell(5).setCellValue("N/A");
			}
			if(plan.getPlanEndDate()!=null) {
				dataRow.createCell(6).setCellValue(plan.getPlanEndDate()+"");
			}else {
				dataRow.createCell(6).setCellValue("N/A");
			}
			dataRow.createCell(7).setCellValue(plan.getBenefitAmt());
			
			dataRowIndex++;
			
		}
		
		// once Excel is create now file is create
	
		ServletOutputStream outputStream = httpResponse.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		
		return true;
	}

	@Override
	public boolean exportPdf(HttpServletResponse httpResponse) throws Exception{
		
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, httpResponse.getOutputStream());
		document.open();
		
		Paragraph p = new Paragraph("Citizen plane info");
		document.add(p);
		document.close();
		
		return true;
	}

}
