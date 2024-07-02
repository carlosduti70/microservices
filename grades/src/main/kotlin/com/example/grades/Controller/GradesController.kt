package com.example.grades.Controller

import com.example.grades.Entities.Grades
import com.example.grades.Service.GradesService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/grades")   //endpoint

@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.DELETE])
class GradesController {

    @Autowired
    lateinit var gradesService: GradesService


    @GetMapping
    fun list():String{
        return "calificaciones"
    }

    @PostMapping
    fun save (@RequestBody @Validated grades:Grades): ResponseEntity<Grades> {
        return ResponseEntity(gradesService.save(grades), HttpStatus.OK)
    }

    @PutMapping
    fun update (@RequestBody grades: Grades): ResponseEntity<Grades> {
        return ResponseEntity(gradesService.update(grades), HttpStatus.OK)
    }

    @PatchMapping
    fun updateName (@RequestBody grades: Grades): ResponseEntity<Grades> {
        return ResponseEntity(gradesService.updateName(grades), HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun listById (@PathVariable("id") id: Long): ResponseEntity<*> {
        return ResponseEntity(gradesService.listById (id), HttpStatus.OK)

    }

    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long):Boolean?{
        return gradesService.delete(id)
    }
}