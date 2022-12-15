package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Consumer;

@Repository
@Component
public interface ConsumerRepository extends JpaRepository<Consumer, String>{

}
