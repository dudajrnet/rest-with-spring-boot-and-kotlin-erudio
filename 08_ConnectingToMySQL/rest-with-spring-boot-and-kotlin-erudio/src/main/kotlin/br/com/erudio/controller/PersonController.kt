package br.com.erudio.controller

import java.util.concurrent.atomic.AtomicLong
import br.com.erudio.exceptions.UnsupportedMathOperationException
import br.com.erudio.converters.NumberConverter
import br.com.erudio.math.SimpleMath
import br.com.erudio.model.PersonModel
import br.com.erudio.services.PersonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/person")
class PersonController {

    @Autowired
    private lateinit var service: PersonService

    @RequestMapping(method = [RequestMethod.GET],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findAll(): List<PersonModel> {
        return  service.findAll()
    }

    @RequestMapping(value = ["{id}"], method = [RequestMethod.GET],
                    produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findById(@PathVariable(value = "id")id: Long
            ): PersonModel {
        return  service.findById((id))
    }

    @RequestMapping(method = [RequestMethod.POST],
                    consumes = [MediaType.APPLICATION_JSON_VALUE],
                    produces = [MediaType.APPLICATION_JSON_VALUE])
    fun create(@RequestBody person: PersonModel): PersonModel {
        return  service.create(person)
    }

    @RequestMapping(method = [RequestMethod.PUT],
                    consumes = [MediaType.APPLICATION_JSON_VALUE],
                    produces = [MediaType.APPLICATION_JSON_VALUE])
    fun update(@RequestBody person: PersonModel): PersonModel {
        return  service.update(person)
    }

    @RequestMapping(value = ["{id}"], method = [RequestMethod.DELETE],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun delete(@PathVariable(value = "id")id: Long) {
        service.delete(id)
    }
}