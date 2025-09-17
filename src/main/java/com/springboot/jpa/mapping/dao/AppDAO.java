package com.springboot.jpa.mapping.dao;

import com.springboot.jpa.mapping.entity.Instructor;
import com.springboot.jpa.mapping.entity.InstructorDetail;

public interface AppDAO {

    void save(Instructor theInstructor);

    Instructor findInstructorById(int theId);

    void deleteInstructorById(int theId);

    InstructorDetail findInstructorDetailById(int theId);

    void deleteInstructorDetailById(int theId);



}
