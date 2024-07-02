package com.example.grades.Entities

import jakarta.persistence.*

@Entity
class Grades {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null

    @Column(name="student_name")
    var studentName: String? = null

    var teacher: String? = null

    var calification: Int?= null
}