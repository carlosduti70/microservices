package com.example.students.model

import jakarta.persistence.*

@Entity
@Table(name = "students")
class Students {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null

    @Column(name="first_name")
    var firstName: String? = null

    @Column(name="last_name")
    var lastName: String? = null
}
