package controllers

import play.api._
import play.api.mvc._
import com.sun.xml.internal.ws.resources.WsdlmodelMessages

object Application extends Controller {
  
  def index = Action {

    System.out.println("branch a!!!!!");

    
    
    
    Ok(views.html.index("Your new application is ready."))
  }
}
