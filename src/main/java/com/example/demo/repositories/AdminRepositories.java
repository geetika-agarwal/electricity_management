package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Admin;

@Repository
@Component
public interface AdminRepositories extends JpaRepository<Admin, String> {

}
