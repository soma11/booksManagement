package mock

import play.api._
import play.api.mvc._
import com.sun.xml.internal.ws.resources.WsdlmodelMessages
import models.Book
import views.html.play20.book

/**
 * Created with IntelliJ IDEA.
 * User: shunsuke
 * Date: 2013/03/09
 * Time: 12:12
 * To change this template use File | Settings | File Templates.
 */
object MockApplication extends Controller{
  def index = Action {
    Ok(views.html.mock("一覧", this.mockModels(10)))
  }
  private def mockModels(count: Int):List[Book] = {
    Book.all()
  }
}

