package br.com.erudio.repository

import br.com.erudio.model.PersonModel
import org.springframework.data.jpa.repository.JpaRepository
import br.com.erudio.model.BookModel
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : JpaRepository<BookModel, Long?>