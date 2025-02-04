package com.test.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.test.system.entity.Member;
import com.test.system.entity.ViewClassName;


public interface VwClassNameRepository extends JpaRepository<ViewClassName, Long> {

}
