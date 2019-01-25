package com.iiht.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.iiht.spring.model.Book;
import com.iiht.spring.model.Subject;
@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long>{
}
