package models

import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current

/**
 * Created with IntelliJ IDEA.
 * User: tdoe
 * Date: 2013/05/19
 * Time: 15:08
 */
case class UserMaster(id: Int)

object UserMaster {

  //base select sql
  val selectSql = "SELECT * FROM USERMASTER"

  val userMaster = {
    get[Int]("id") map {
      case id => UserMaster(id)
    }
  }

  def findAll(): List[UserMaster] = DB.withConnection {
    implicit c => {
      SQL(selectSql).as(userMaster *)
    }
  }

  def findById(id: Int): UserMaster = DB.withConnection {
    implicit con => {
      SQL(selectSql + " WHERE id = {id}").on('id -> id).as(userMaster *).head
    }
  }

  def create() {
    DB.withConnection {
      implicit c => {
        SQL("INSERT INTO USERMASTER (id) VALUES (NULL)").executeInsert()
      }
    }
  }

}
