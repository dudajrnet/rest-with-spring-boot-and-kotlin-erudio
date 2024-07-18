package br.com.erudio.services

import br.com.erudio.data.vo.v1.BookVO
import br.com.erudio.mapper.DozerMapper
import br.com.erudio.exceptions.ResourceNotFoundException
import br.com.erudio.exceptions.RequiredObjectIsNullException
import br.com.erudio.model.BookModel
import br.com.erudio.repository.BookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.logging.Logger
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import br.com.erudio.controller.BookController

@Service
class BookService {

    @Autowired
    private lateinit var repository: BookRepository

    private val logger = Logger.getLogger(BookService::class.java.name)

    fun findAll() : List<BookVO>{
        logger.info("Finding all people!")
        val books = repository.findAll()
        val bookVOs = DozerMapper.parseListObject(books, BookVO::class.java)

        for(BookVO in bookVOs){
            val withSelfRel = linkTo(BookController::class.java).slash(BookVO.key).withSelfRel()
            BookVO.add(withSelfRel)
        }

        return bookVOs
    }

    fun findById(id: Long) : BookVO{
        logger.info("Finding one Book with Id: $id!")
        var book = repository.findById(id)
            .orElseThrow {ResourceNotFoundException("No records found for this Id!")}
        val BookVO : BookVO = DozerMapper.parseObject(book, BookVO::class.java)
        val withSelfRel = linkTo(BookController::class.java).slash(BookVO.key).withSelfRel()
        BookVO.add(withSelfRel)
        return BookVO
    }

    fun create(book: BookVO?) : BookVO {
        if(book == null) throw RequiredObjectIsNullException()
        logger.info("Creating one Book with title: ${book.title}")
        var entity : BookModel = DozerMapper.parseObject(book, BookModel::class.java)
        val BookVO : BookVO = DozerMapper.parseObject(repository.save(entity), BookVO::class.java)
        val withSelfRel = linkTo(BookController::class.java).slash(BookVO.key).withSelfRel()
        BookVO.add(withSelfRel)
        return BookVO
    }

    fun update(book: BookVO?) : BookVO{
        if(book == null) throw RequiredObjectIsNullException()
        logger.info("Updating one Book with Id: ${book.key}")
        val entity = repository.findById(book.key)
            .orElseThrow{ResourceNotFoundException("No records found for this Id!")}
        entity.title = book.title
        entity.author = book.author
        entity.price = book.price
        entity.launchDate = book.launchDate
        val BookVO : BookVO = DozerMapper.parseObject(repository.save(entity), BookVO::class.java)
        val withSelfRel = linkTo(BookController::class.java).slash(BookVO.key).withSelfRel()
        BookVO.add(withSelfRel)
        return BookVO
    }

    fun delete(id: Long){
        logger.info("Deleting one Book with Id: $id")
        val entity = repository.findById(id)
            .orElseThrow{ResourceNotFoundException("No records found for this Id!")}
        repository.delete(entity)
    }
}