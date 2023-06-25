package com.example.demo.Repository;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import com.example.demo.Model.CitiZenPlan;

@Repository
public interface CitizenRepository extends JpaRepository<CitiZenPlan,Integer>{

@Query("select distinct(planeName) from CitiZenPlan")
public  List<String>getPlaneName();
	
@Query("select distinct(planeStatus) from CitiZenPlan")
public  List<String>getStatusName();


}
