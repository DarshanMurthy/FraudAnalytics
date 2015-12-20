package controllers

import models._
import play.api._
import play.api.data.Form
import play.api.mvc._
import play.twirl.api.Html
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

class Application extends Controller {



  def FeatureAnalysis = Action {
    val content = Html("<p> Hello </p>")
    val numAs=Statistics.statistics()
    Ok(views.html.main("Life")(content)(numAs))
  }




  def basics = Action {
    val content = Html("<p> Hello </p>")
    var numAs="90"
    var numBs= Basics.test()
    Ok(views.html.main("Life")(content)(numAs+ " " + numBs))
  }




  def DecisionTree = Action {
    val content =Html("<p> Hello </p>")
    var numAs=100
    var numBs=200
    var fi = DTree.Tree()
   Ok(views.html.main("Life")(content)(fi + " " +numBs))
  }

  def CountAsBs = Action {
    val content = Html("<p> Hello </p>")
    val numAs=Count.numAs
    val numBs=Count.numBs
    Ok(views.html.main("Life")(content)(numAs+ " " + numBs))
  }



  def NaiveBayes = Action {
    val data=NaiveBayesclassifier.algo()
    Ok(views.html.test1(data.toString));
  }

  def statistics  = Action {
    val data=NaiveBayesclassifier.statistics()
    Ok(views.html.test1(data.toString));
  }
  def index = Action {
      val content = Html("<div>This is the content for the sample app<div>")
      val number="Darsha"
      Ok(views.html.main("Home")(content)(number))
  }

  def message = Action{
    val content= Html("<p> Hello </p>")
    var name=10
    Ok(views.html.test("Darshan")(content))
  }


}
