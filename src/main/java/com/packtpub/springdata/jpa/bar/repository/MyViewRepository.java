package com.packtpub.springdata.jpa.bar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.packtpub.springdata.jpa.bar.model.MyView;

public interface MyViewRepository extends  JpaRepository<MyView, Long>{
}
