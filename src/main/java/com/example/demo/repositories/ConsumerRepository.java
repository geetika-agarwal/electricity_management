package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Consumer;

public interface ConsumerRepository extends JpaRepository<Consumer, String>{

}
