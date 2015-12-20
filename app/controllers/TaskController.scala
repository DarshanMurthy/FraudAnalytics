package controllers


import play.api._
import play.api.mvc._
import play.twirl.api.Html

/**
 * Created by tumkur on 10/14/15.
 */


  class TaskController extends Controller {
  def index = Action {
    Ok(views.html.index())
  }
}