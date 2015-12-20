package models

import controllers.SparkCommons

/**
 * Created by tumkur on 11/12/15.
 */
object Basics {
  def test(): String ={
        //var textreader = SparkCommons.sc.textFile("/Users/tumkur/Desktop/Interview/Vishal.PDF")
        //var csvReader = SparkCommons.sc.textFile("FraudAnalytics/Data/publishers_23feb12.csv")
        //println(csvReader.take(5))

        var testreader= SparkCommons.sc.textFile("app/models/Basics.scala")


    return testreader.count().toString
  }

}
