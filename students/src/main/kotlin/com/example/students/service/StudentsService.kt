package com.example.students.service

import com.example.students.model.Students
import com.example.students.repository.StudentsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class StudentsService {

    @Autowired
    lateinit var studentsRepository: StudentsRepository

    fun list ():List<Students>{
        return studentsRepository.findAll()
    }

    //post
    fun save(students:Students): Students {
        try{
            students.firstName?.takeIf {it.trim().isNotEmpty()}
                ?:throw Exception("Nombre no debe ser vacio")
            students.lastName?.takeIf {it.trim().isNotEmpty()}
                ?:throw Exception("Apellido no debe ser vacio")
            return studentsRepository.save(students)


        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST,ex.message)
        }


    }

    //put
    fun update(students: Students): Students
    {
        try {studentsRepository
            studentsRepository.findById(students.id)
                ?: throw Exception("ID no existe")

            return studentsRepository.save(students)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun updateName(students: Students): Students{
        try{
            val response = studentsRepository.findById(students.id)
                ?: throw Exception("ID no existe")
            response.apply {
                lastName=students.lastName //un atributo del modelo
            }
            return studentsRepository.save(response)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun listById (id:Long?):Students?{
        return studentsRepository.findById(id)
    }

    fun delete (id: Long?):Boolean?{
        try{
            val response = studentsRepository.findById(id)
                ?: throw Exception("ID no existe")
            studentsRepository.deleteById(id!!)
            return true
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
}