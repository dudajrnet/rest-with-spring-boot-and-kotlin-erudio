package br.com.erudio.mapper.custom

import br.com.erudio.data.vo.v2.PersonVO
import br.com.erudio.model.PersonModel
import org.springframework.stereotype.Service
import java.util.*

@Service
class PersonMapper {
    fun mapEntityToVO(person: PersonModel): PersonVO {
        val vo = PersonVO()
        vo.id = person.id
        vo.firstName = person.firstName
        vo.lastName = person.lastName
        vo.adress = person.adress
        vo.gender = person.gender
        vo.birthday = Date()
        return vo
    }

    fun mapVOToEntity(person: PersonVO):  PersonModel {
        val entity = PersonModel()
        entity.id = person.id
        entity.firstName = person.firstName
        entity.lastName = person.lastName
        entity.adress = person.adress
        entity.gender = person.gender
        return entity
    }
}