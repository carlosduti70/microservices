package com.example.grades.Repository

import com.example.grades.Entities.Grades
import org.springframework.data.jpa.repository.JpaRepository

interface GradesRepository: JpaRepository<Grades, Long> {

    fun findById(id: Long?): Grades?
}