package com.example.grades.Service

import com.example.grades.Entities.Grades
import com.example.grades.Repository.GradesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class GradesService {

    @Autowired
    lateinit var gradesRepository: GradesRepository

    fun list ():List<Grades>{
        return gradesRepository.findAll()
    }

    //post
    fun save(grades:Grades): Grades {
        try{
            grades.studentName?.takeIf {it.trim().isNotEmpty()}
                ?:throw Exception("Nombre no debe ser vacio")
            grades.studentName?.takeIf {it.trim().isNotEmpty()}
                ?:throw Exception("Apellido no debe ser vacio")
            return gradesRepository.save(grades)


        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST,ex.message)
        }


    }

    //put
    fun update(grades: Grades): Grades
    {
        try {gradesRepository
            gradesRepository.findById(grades.id)
                ?: throw Exception("ID no existe")

            return gradesRepository.save(grades)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun updateName(grades: Grades): Grades{
        try{
            val response = gradesRepository.findById(grades.id)
                ?: throw Exception("ID no existe")
            response.apply {
                teacher=grades.teacher //un atributo del modelo
            }
            return gradesRepository.save(response)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun listById (id:Long?):Grades?{
        return gradesRepository.findById(id)
    }

    fun delete (id: Long?):Boolean?{
        try{
            val response = gradesRepository.findById(id)
                ?: throw Exception("ID no existe")
            gradesRepository.deleteById(id!!)
            return true
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
}