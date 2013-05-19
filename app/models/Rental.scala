package models

import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current
import java.util.Date

/**
 * Created with IntelliJ IDEA.
 * User: tdoe
 * Date: 2013/05/19
 * Time: 14:45
 */

case class Rental(bookId: Int, userId: Int, rentalDate: Date)

object Rental {

  //base select sql
  val selectSql = "SELECT * FROM RENTAL"

  val rental = {
    get[Int]("bookId") ~
      get[Int]("userId") ~
      get[Date]("rentalDate") map {
      case bookId ~ userId ~ rentalDate => Rental(bookId, userId, rentalDate)
    }
  }

  def findAll(): List[Rental] = DB.withConnection {
    implicit c => {
      SQL(selectSql).as(rental *)
    }
  }

  def findByUserId(userId: Int): List[Rental] = DB.withConnection {
    implicit con => {
      SQL(selectSql + " WHERE USER_ID = {userId}").on('userId -> userId).as(rental *)
    }
  }

  def findByBookId(bookId: Int): List[Rental] = DB.withConnection {
    implicit con => {
      SQL(selectSql + " WHERE BOOK_ID = {bookId}").on('bookId -> bookId).as(rental *)
    }
  }

  def update(rental: Rental) {
    DB.withConnection {
      implicit c => {
        SQL("UPDATE RENTAL SET ({bookId}, {userId}, {rentalDate})")
          .on(
          'bookId -> rental.bookId,
          'userId -> rental.userId,
          'rentalDate -> rental.rentalDate
        ).executeUpdate()
      }
    }
  }

  def create(rental: Rental) {
    DB.withConnection {
      implicit c => {
        SQL( """
            INSERT INTO RENTAL (BOOK_ID, USER_ID, RENTAL_DATE)
            VALUES ({bookId}, {userId}, {rentalDate})
             """")
          .on(
          'bookId -> rental.bookId,
          'userId -> rental.userId,
          'rentalDate -> rental.rentalDate
        ).executeUpdate()
      }
    }
  }

}