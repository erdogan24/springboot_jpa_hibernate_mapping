package com.springboot.jpa.mapping.dao;

import com.springboot.jpa.mapping.entity.Instructor;

public interface AppDAO {

    void save(Instructor theInstructor);

    Instructor findInstructorById(int theId);
}
