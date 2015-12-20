package controllers

import org.apache.spark.SparkConf
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext




/**
 * Created by tumkur on 10/14/15.
 */
object SparkCommons {
  //build the SparkConf  object at once
  lazy val conf = {
    new SparkConf(false)
      .setMaster("local[*]")
      .setAppName("play demo")
      .set("spark.logConf", "true")
  }

  lazy val sc = SparkContext.getOrCreate(conf)

}

