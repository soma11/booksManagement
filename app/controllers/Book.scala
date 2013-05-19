package controllers

import play.api.mvc.{Action, Controller}
import play.api.data.Form
import play.api.data.Forms
import play.api.data.Forms._

import models.Book
import java.util.Date

/**
 * Created with IntelliJ IDEA.
 * User: tdoe
 * Date: 2013/05/19
 * Time: 17:53
 */

case class BookForm(
                     title: String,
                     acquisition_Date: String,
                     release_Date: String,
                     stok: String,
                     url: String,
                     photoFileName: String)

object Book extends Controller {

  val bookForm = Form(
    Forms
      .mapping(
      "title" -> nonEmptyText(minLength = 1),
      "acquisition_Date" -> text,
      "release_Date" -> text,
      "stok" -> text,
      "url" -> text,
      "photoFileName" -> text
    )(BookForm.apply)(BookForm.unapply)
  )

  def blank = Action {
    Ok(views.html.blank(bookForm))
  }

  def create = Action {
    implicit request =>
    //TODO modelsのcase class Bookそのまま使ったほうが楽
      bookForm.bindFromRequest.fold(
        errors => BadRequest(views.html.blank(bookForm)),
        bookForm => {
          val title = bookForm.title
          val acquisition_Date = bookForm.acquisition_Date
          val release_Date = bookForm.release_Date
          val stok = bookForm.stok
          val url = bookForm.url
          val photoFileName = bookForm.photoFileName
          val date = new Date()

          val book = new Book(1, title, date, date, 1, url, photoFileName)

          models.Book.create(book)

          Redirect(routes.Application.index())
        }
      )
  }

}
