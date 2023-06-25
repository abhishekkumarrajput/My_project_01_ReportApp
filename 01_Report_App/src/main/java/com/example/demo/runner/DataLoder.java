package com.example.demo.runner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import com.example.demo.Model.CitiZenPlan;
import com.example.demo.Repository.CitizenRepository;

@Component
public class DataLoder implements ApplicationRunner {

	@Autowired
	private CitizenRepository repo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		repo.deleteAll();
		
		CitiZenPlan c1= new CitiZenPlan();	
		//cash paln appovied data 
		c1.setCitiZenName("john");
		c1.setGender("Male");
		c1.setPlaneStatus("Approved");
		c1.setPlanStartDate(LocalDate.now());
		c1.setPlanEndDate(LocalDate.now().plusMonths(6));
		c1.setPlaneName("cash");
		c1.setBenefitAmt(50000.0);	
	
	//cash paln Denined data 
CitiZenPlan c2= new CitiZenPlan();			
c2.setCitiZenName("smith");
c2.setGender("Male");
c2.setPlaneStatus("Denined");
c2.setPlaneName("cash");
c2.setDeniedReason("Rental Income ");
	
//cash plane dermineted
CitiZenPlan c3= new CitiZenPlan();	
c3.setCitiZenName("Laxmi");
c3.setGender("Female");
c3.setPlaneStatus("Terminated");
c3.setPlanStartDate(LocalDate.now().minusMonths(4));
c3.setPlanEndDate(LocalDate.now().plusMonths(6));
c3.setPlaneName("cash");
c3.setBenefitAmt(50000.0);
c3.setTerminatedDate(LocalDate.now());
c3.setTerminatedReason("Employed");

//food plane data

CitiZenPlan c4= new CitiZenPlan();	
//food paln appovied data 
c4.setCitiZenName("David");
c4.setGender("Male");
c4.setPlaneStatus("Approved");
c4.setPlanStartDate(LocalDate.now());
c4.setPlanEndDate(LocalDate.now().plusMonths(6));
c4.setPlaneName("Food");
c4.setBenefitAmt(6000.0);	

//food paln Denined data 
CitiZenPlan c5= new CitiZenPlan();			
c5.setCitiZenName("Mahesh");
c5.setGender("Male");
c5.setPlaneStatus("Denined");
c5.setPlaneName("Food");
c5.setDeniedReason("Property income");

//food plane dermineted
CitiZenPlan c6= new CitiZenPlan();	
c6.setCitiZenName("mamta");
c6.setGender("Female");
c6.setPlaneStatus("Terminated");
c6.setPlanStartDate(LocalDate.now().minusMonths(4));
c6.setPlanEndDate(LocalDate.now().plusMonths(6));
c6.setPlaneName("Food");
c6.setBenefitAmt(5000.0);
c6.setTerminatedDate(LocalDate.now());
c6.setTerminatedReason("Goverment job");


//Medical plane data

CitiZenPlan c7= new CitiZenPlan();	
//food paln appovied data 
c7.setCitiZenName("abhishek");
c7.setGender("Male");
c7.setPlaneStatus("Approved");
c7.setPlanStartDate(LocalDate.now());
c7.setPlanEndDate(LocalDate.now().plusMonths(6));
c7.setPlaneName("Medical");
c7.setBenefitAmt(6000.0);	

//food paln Denined data 
CitiZenPlan c8= new CitiZenPlan();			
c8.setCitiZenName("rahul");
c8.setGender("Male");
c8.setPlaneStatus("Denined");
c8.setPlaneName("medical");
c8.setDeniedReason("Property income");

//food plane dermineted
CitiZenPlan c9= new CitiZenPlan();	
c9.setCitiZenName("rashmi");
c9.setGender("Female");
c9.setPlaneStatus("Terminated");
c9.setPlanStartDate(LocalDate.now().minusMonths(4));
c9.setPlanEndDate(LocalDate.now().plusMonths(6));
c9.setPlaneName("medical");
c9.setBenefitAmt(5000.0);
c9.setTerminatedDate(LocalDate.now());
c9.setTerminatedReason("youtuber");


//


//Employment paln appovied data
CitiZenPlan c10= new CitiZenPlan();	
 
c10.setCitiZenName("vijay");
c10.setGender("Male");
c10.setPlaneStatus("Approved");
c10.setPlanStartDate(LocalDate.now());
c10.setPlanEndDate(LocalDate.now().plusMonths(6));
c10.setPlaneName("Employment");
c10.setBenefitAmt(6000.0);	

//Employment paln Denined data 
CitiZenPlan c11= new CitiZenPlan();			
c11.setCitiZenName("lokendra");
c11.setGender("Male");
c11.setPlaneStatus("Denined");
c11.setPlaneName("Employment");
c11.setDeniedReason("Property income");

//Employment plane dermineted
CitiZenPlan c12= new CitiZenPlan();	
c12.setCitiZenName("nikita");
c12.setGender("Female");
c12.setPlaneStatus("Terminated");
c12.setPlanStartDate(LocalDate.now().minusMonths(4));
c12.setPlanEndDate(LocalDate.now().plusMonths(6));
c12.setPlaneName("Employment");
c12.setBenefitAmt(5000.0);
c12.setTerminatedDate(LocalDate.now());
c12.setTerminatedReason("youtuber");

List<CitiZenPlan> List = Arrays.asList(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12);
repo.saveAll(List);
		
	}

}
