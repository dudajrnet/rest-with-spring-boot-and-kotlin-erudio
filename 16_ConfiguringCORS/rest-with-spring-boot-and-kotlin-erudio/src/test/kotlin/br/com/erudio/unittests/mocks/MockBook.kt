package br.com.erudio.unittests.mocks

import br.com.erudio.data.vo.v1.BookVO
import br.com.erudio.model.BookModel
import java.util.*

class MockBook {
    fun mockEntity(): BookModel {
        return mockEntity(0)
    }

    fun mockVO(): BookVO {
        return mockVO(0)
    }

    fun mockEntityList(): ArrayList<BookModel> {
        val books: ArrayList<BookModel> = ArrayList<BookModel>()
        for (i in 0..13) {
            books.add(mockEntity(i))
        }
        return books
    }

    fun mockVOList(): ArrayList<BookVO> {
        val books: ArrayList<BookVO> = ArrayList()
        for (i in 0..13) {
            books.add(mockVO(i))
        }
        return books
    }

    fun mockEntity(number: Int): BookModel {
        val book = BookModel()
        book.title = "Title Test$number"
        book.author = "Author Test$number"
        book.price = 25.00
        book.id = number.toLong()
        return book
    }

    fun mockVO(number: Int): BookVO {
        val book = BookVO()
        book.title = "Title Test$number"
        book.author = "Author Test$number"
        book.price = 25.00
        book.key = number.toLong()
        return book
    }
}