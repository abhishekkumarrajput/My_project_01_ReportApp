package com.example.demo.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Model.CitiZenPlan;
import com.example.demo.Request.SearchRequest;

import jakarta.servlet.http.HttpServletResponse;

public interface ReportService {
	
List<String>getPlaneName();
	
List<String>getPlaneStatus();
	
List<CitiZenPlan>search(SearchRequest search);	
	
public boolean exportExcel(HttpServletResponse httpResponse) throws Exception;  //return type boolean because is going to send import into email return true if not send into a email return flase   

public boolean exportPdf(HttpServletResponse httpResponse) throws Exception;     
	

}
