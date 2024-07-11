package br.com.erudio.services

import br.com.erudio.model.PersonModel
import org.springframework.stereotype.Service
import java.util.concurrent.atomic.AtomicLong
import java.util.logging.Logger

@Service
class PersonService {

    private  val counter: AtomicLong = AtomicLong()

    private val logger = Logger.getLogger(PersonService::class.java.name)

    fun findAll() : List<PersonModel>{
        logger.info("Finding all people!")

        val persons: MutableList<PersonModel> = ArrayList()
        for (i in 0 .. 7){
            val person = mockPersonModel(i)
            persons.add(person)
        }
        return persons
    }

    fun findById(id: Long) : PersonModel{
        logger.info("Finding one person!")

        val person = PersonModel()
        person.id = counter.incrementAndGet()
        person.firstName = "Jose"
        person.lastName = "Junior"
        person.adress = "Rua Joao Paulino de Faria, 505"
        person.gender = "Masculino"

        return  person
    }

    fun create(person: PersonModel) = person

    fun update(person: PersonModel) = person

    fun delete(id: Long){

    }

    private fun mockPersonModel(i: Int): PersonModel{
        val person = PersonModel()
        person.id = counter.incrementAndGet()
        person.firstName = "Person Name $i"
        person.lastName = "Last Name $i"
        person.adress = "Some Address in Brazil"
        person.gender = "Male"
        return  person
    }
}