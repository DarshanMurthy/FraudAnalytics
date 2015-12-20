package models

import controllers.SparkCommons

/**
 * Created by tumkur on 10/14/15.
 */
object Count {
  val link="/Users/tumkur/Desktop/Vishal.pdf"
  val logData = SparkCommons.sc.textFile(link, 2).cache()
  val numAs = logData.filter(line => line.contains("a")).count().toString()
  val numBs = logData.filter(line => line.contains("b")).count().toString()
}
