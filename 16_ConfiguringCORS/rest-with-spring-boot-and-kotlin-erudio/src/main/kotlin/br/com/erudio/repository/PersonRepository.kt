package br.com.erudio.repository

import br.com.erudio.model.PersonModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository : JpaRepository<PersonModel, Long?>