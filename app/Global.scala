import play.api._
import play.api.mvc._

object Global extends GlobalSettings {
  override def onRouteRequest(request: RequestHeader): Option[Handler] = {
    // TODO ここでinterprism.sso.filter.CheckTicketFilterの処理を挟む
    //println("[認証しました]REQUEST=" + request.toString)
    super.onRouteRequest(request)
  }
}