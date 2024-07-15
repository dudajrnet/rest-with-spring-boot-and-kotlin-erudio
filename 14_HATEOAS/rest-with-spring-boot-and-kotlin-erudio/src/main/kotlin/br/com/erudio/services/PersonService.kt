package br.com.erudio.services

import br.com.erudio.data.vo.v1.PersonVO
import br.com.erudio.mapper.DozerMapper
import br.com.erudio.exceptions.ResourceNotFoundException
import br.com.erudio.exceptions.RequiredObjectIsNullException
import br.com.erudio.model.PersonModel
import br.com.erudio.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.logging.Logger
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import br.com.erudio.controller.PersonController

@Service
class PersonService {

    @Autowired
    private lateinit var repository: PersonRepository

    private val logger = Logger.getLogger(PersonService::class.java.name)

    fun findAll() : List<PersonVO>{
        logger.info("Finding all people!")
        val persons = repository.findAll()
        val personVOs = DozerMapper.parseListObject(persons, PersonVO::class.java)

        for(personVO in personVOs){
            val withSelfRel = linkTo(PersonController::class.java).slash(personVO.key).withSelfRel()
            personVO.add(withSelfRel)
        }

        return personVOs
    }

    fun findById(id: Long) : PersonVO{
        logger.info("Finding one person with Id: $id!")
        var person = repository.findById(id)
            .orElseThrow({ResourceNotFoundException("No records found for this Id!")})
        val personVO : PersonVO = DozerMapper.parseObject(person, PersonVO::class.java)
        val withSelfRel = linkTo(PersonController::class.java).slash(personVO.key).withSelfRel()
        personVO.add(withSelfRel)
        return personVO
    }

    fun create(person: PersonVO?) : PersonVO {
        if(person == null) throw RequiredObjectIsNullException()
        logger.info("Creating one person with name ${person.firstName}")
        var entity : PersonModel = DozerMapper.parseObject(person, PersonModel::class.java)
        val personVO : PersonVO = DozerMapper.parseObject(repository.save(entity), PersonVO::class.java)
        val withSelfRel = linkTo(PersonController::class.java).slash(personVO.key).withSelfRel()
        personVO.add(withSelfRel)
        return personVO
    }

    fun update(person: PersonVO?) : PersonVO{
        if(person == null) throw RequiredObjectIsNullException()
        logger.info("Updating one person with Id: ${person.key}")
        val entity = repository.findById(person.key)
            .orElseThrow{ResourceNotFoundException("No records found for this Id!")}
        entity.firstName = person.firstName
        entity.lastName = person.lastName
        entity.adress = person.adress
        entity.gender = person.gender
        val personVO : PersonVO = DozerMapper.parseObject(repository.save(entity), PersonVO::class.java)
        val withSelfRel = linkTo(PersonController::class.java).slash(personVO.key).withSelfRel()
        personVO.add(withSelfRel)
        return personVO
    }

    fun delete(id: Long){
        logger.info("Deleting one person with Id: $id")
        val entity = repository.findById(id)
            .orElseThrow{ResourceNotFoundException("No records found for this Id!")}
        repository.delete(entity)
    }
}