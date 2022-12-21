package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Bill;
import com.example.demo.entity.Consumer;

@Repository
@Component
public interface BillRepository extends JpaRepository<Bill, Integer>{
	// sql query for delete bill
	//delete operation
	@Modifying
	@Query("DELETE Bill b WHERE b.consumer = :consumer")
	int deleteAllByEmail(Consumer consumer);
	
	//retrieve operation
	// sql query for getting bill
	@Query("SELECT b FROM Bill b WHERE b.consumer = :consumer")
	List<Bill> findByEmail(Consumer consumer);
}
