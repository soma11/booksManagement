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
  
  def edit = Action {
    Ok(views.html.mockEdit("管理画面", this.mockEditModels()))
  }
  private def mockEditModels():List[Book] = {
    List(Book(1, "P_PHP.jpg", "mock1", "2013/04/01", "2013/04/01", "", 1)
        , Book(2, "P_PHP.jpg", "mock2", "2013/04/01", "2013/04/01", "", 1)
        , Book(3, "P_PHP.jpg", "mock3", "2013/04/01", "2013/04/01", "", 1)
        , Book(4, "P_PHP.jpg", "mock4", "2013/04/01", "2013/04/01", "", 1)
        , Book(5, "P_PHP.jpg", "mock5", "2013/04/01", "2013/04/01", "", 1)
        , Book(6, "P_PHP.jpg", "mock6", "2013/04/01", "2013/04/01", "", 1)
        , Book(7, "P_PHP.jpg", "mock7", "2013/04/01", "2013/04/01", "", 1)
        , Book(8, "P_PHP.jpg", "mock8", "2013/04/01", "2013/04/01", "", 1)
        , Book(9, "P_PHP.jpg", "mock9", "2013/04/01", "2013/04/01", "", 1))
  }
  
}

