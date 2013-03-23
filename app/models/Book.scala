package models

import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current
case class Book(
                 id: Int,
                 imageUrl: String,
                 title: String,
                 acquisitionDate: String,
                 releaseDate: String,
                 url: String,
                 count: Int)

object Book {

  val book = {
    get[Int]("id") ~
      get[String]("imageUrl") ~
        get[String]("title") ~
          get[String]("acquisitionDate") ~
            get[String]("releaseDate") ~
              get[String]("url") ~
                get[Int]("count") map {
      case id~imageUrl~title~acquisitionDate~releaseDate~url~count
      => Book(id, imageUrl, title, acquisitionDate, releaseDate, url, count)
    }
  }

  def all(): List[Book] = DB.withConnection { implicit c =>
    SQL("select * from BOOK").as(book *)
  }
}

