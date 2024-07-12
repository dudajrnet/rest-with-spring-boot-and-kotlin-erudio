package br.com.erudio.services

import br.com.erudio.model.PersonModel
import br.com.erudio.repository.PersonRepository
import br.com.erudio.exceptions.ResourceNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class PersonService {

    @Autowired
    private lateinit var repository: PersonRepository

    private val logger = Logger.getLogger(PersonService::class.java.name)

    fun findAll() : List<PersonModel>{
        logger.info("Finding all people!")

        return repository.findAll()
    }

    fun findById(id: Long) : PersonModel{
        logger.info("Finding one person!")

        return repository.findById(id)
            .orElseThrow({ResourceNotFoundException("No records found for this Id!")})
    }

    fun create(person: PersonModel) : PersonModel {
        logger.info("Creating one person with name ${person.firstName}")
        return repository.save(person)
    }

    fun update(person: PersonModel) : PersonModel{
        logger.info("Updating one person with Id: ${person.id}")
        val entity = repository.findById(person.id)
            .orElseThrow{ResourceNotFoundException("No records found for this Id!")}

        entity.firstName = person.firstName
        entity.lastName = person.lastName
        entity.adress = person.adress
        entity.gender = person.gender

        return repository.save(entity)
    }



    fun delete(id: Long){
        logger.info("Deleting one person with Id: $id")
        val entity = repository.findById(id)
            .orElseThrow{ResourceNotFoundException("No records found for this Id!")}
        repository.delete(entity)
    }
}