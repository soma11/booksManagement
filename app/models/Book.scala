package models

import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current
import java.util.Date
case class Book(
  id: Int,
  title: String,
  acquisition_Date: Date,
  release_Date: Date,
  stok: Int,
  url: String,
  photoFileName: String)

object Book {

  val book = {
    get[Int]("id") ~
      get[String]("title") ~
        get[Date]("acquisition_Date") ~
          get[Date]("release_Date") ~
            get[Int]("stok") ~
              get[String]("url") ~
                get[String]("photoFileName") map {
        case id ~ title ~ acquisition_Date ~ release_Date ~ stok ~ url ~ photoFileName => Book(id, title, acquisition_Date, release_Date, stok, url, photoFileName)
      }
  }

  def all(): List[Book] = DB.withConnection { implicit c =>
    SQL("select * from BOOK").as(book *)
  }

  def create(newBook : Book) {
    DB.withConnection { implicit c => 
      SQL("insert into BOOK (id, title, acquisition_Date, release_Date, stok, url, photoFileName) " +
            "values ({id}, {title}, {acquisition_Date}, {release_Date}, {stok}, {url}, {photoFileName})")
            .on('id -> newBook.id,
                'titel -> newBook.title,
                'acquisition_Date -> newBook.acquisition_Date,
                'release_Date -> newBook.release_Date,
                'stok -> newBook.stok,
                'url -> newBook.url,
                'photoFileName -> newBook.photoFileName).executeUpdate()
    }
  }
  
  def delete(id: Int) {
    DB.withConnection { implicit c => 
      SQL("delete from BOOK	where id = {id}").on('id -> id).executeUpdate()
    }
  }
}
