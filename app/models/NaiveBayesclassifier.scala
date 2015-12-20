package models
import controllers.SparkCommons
import org.apache.spark
import org.apache.spark.mllib.classification.NaiveBayes
import org.apache.spark.mllib.evaluation.MulticlassMetrics
import org.apache.spark.mllib.feature.HashingTF
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.rdd.RDD


/**
 * Created by tumkur on 10/18/15.
 */

object NaiveBayesclassifier {

  def algo():String= {
    // This code is straight-forward, I'm just reading here!
    //val data = SparkCommons.sc.textFile("/Users/tumkur/Desktop/FraudAnalytics/Data/naive_bayes.txt")
    val htf = new HashingTF(100000)
    //var path="/Users/tumkur/Documents/FraudAnalytics/ClickFraud/Data"
    val Data = SparkCommons.sc.textFile("/Users/tumkur/Desktop/FraudAnalytics/Data/publishers_08mar12.csv").
      map{ text=> text.contains("OK")}



    println(Data.take(5).foreach(println))



    // println("the data" + " " + Data + " " + Data.take(5).foreach(println))



    val positiveData = SparkCommons.sc.textFile("/Users/tumkur/Desktop/FraudAnalytics/Data/publishers*")
      .filter(text => text.contains("OK"))
      .map { text => new LabeledPoint(1, htf.transform(text.split(" ")))};

    val OK = SparkCommons.sc.textFile("/Users/tumkur/Desktop/FraudAnalytics/Data/publishers*")
      .filter(text => text.contains("OK")).count()
    println(OK)

    //val textBook = SparkCommons.sc.textFile("/Users/tumkur/Desktop/Interview/Vishal.PDF")
                //.map {text => new LabeledPoint(1, htf.transform(text.split("Ok")))}

    val negativeData = SparkCommons.sc.textFile("/Users/tumkur/Desktop/FraudAnalytics/Data/publishers*")
      .filter(line =>  line.contains("Fraud"))
      .map { text => new LabeledPoint(0, htf.transform(text.split("Fraud")))}


    val posSplits = positiveData.randomSplit(Array(0.6, 0.4), seed = 11L)
    val negSplits = negativeData.randomSplit(Array(0.6, 0.4), seed = 11L)
    val training = posSplits(0).union(negSplits(0))
    val test = posSplits(1).union(negSplits(1))
    // Here I'm training the model
    val model = NaiveBayes.train(training)
    //System.out.print(model);
    val predictionAndLabels = test.map { point =>

      val score = model.predict(point.features)

      (score, point.label)
    }
    System.out.println(predictionAndLabels.count())
    //predictionAndLabels.saveAsTextFile("/Users/tumkur/Desktop/FraudAnalytics/Data/Final.txt" )
    /* metrics */
    val metrics = new MulticlassMetrics(predictionAndLabels)
    println(metrics.confusionMatrix)
    /* output F1-measure for all labels (0 and 1, negative and positive) */
    //metrics.labels.foreach( l => println(metrics.fMeasure(l)+" "+ metrics.falsePositiveRate(l)))
    metrics.labels.foreach( l => println(metrics.precision(l)))
    //here I need to read data in csv


    return "dars"
  }

  //Statistics
  def statistics (): String ={
    //var publisher = SparkCommons.sc.textFile("/Users/tumkur/Desktop/FraudAnalytics/Data/publishers_09feb12.csv", 2).cache()
    //var click  = SparkCommons.sc.textFile("/Users/tumkur/Desktop/FraudAnalytics/Data/clicks_09feb12.csv")
    var htf = new HashingTF(1000)
    val Data = SparkCommons.sc.textFile("/Users/tumkur/Desktop/FraudAnalytics/Data/final.csv")

    println(Data.count())


     val Fraud = Data.map { text => new LabeledPoint(1, htf.transform(text.split("Fraud")))}
     //Fraud.foreach(println)
     val OK= Data.map { text => new LabeledPoint(0, htf.transform(text.split("Ok")))}






    val posSplits = OK.randomSplit(Array(0.6, 0.4), seed = 11L)
    val negSplits = Fraud.randomSplit(Array(0.6, 0.4), seed = 11L)
    val training = posSplits(0).union(negSplits(0))
    val test = posSplits(1).union(negSplits(1))
    // Here I'm training the model
    val model = NaiveBayes.train(training)
    //System.out.print(model);
    val predictionAndLabels = test.map { point =>

      val score = model.predict(point.features)

      (score, point.label)
    }

    val metrics = new MulticlassMetrics(predictionAndLabels)
    metrics.labels.foreach( l => println(metrics.precision(l)))
    //metrics.labels.foreach( l => println(metrics.fMeasure(l)+" "+ metrics.falsePositiveRate(l)))

    println(metrics.confusionMatrix)



    //val Ok = Data.filter(line => line.contains("O")).count()
    //val Fraud = Data.filter(line => line.contains("F")).count()

    print("Darshan Murthy")



   // val Fraud =SparkCommons.sc.textFile("/Users/tumkur/Desktop/FraudAnalytics/Data/publishers_09feb12.csv")
     // .filter(text1 => text1.contains("OK")).count()
    //println(Fraud)



    // val input = SparkCommons.sc.textFile("/Users/tumkur/Desktop/FraudAnalytics/Data/publishers_23feb12.csv")
      //.map(line => line.split(" "))



    //println("The number of values"+ positiveData.count());
    //println("The disticct values"+ positiveData.distinct());

    //positiveData.foreach(println)

    // val Fraud = SparkCommons.sc.textFile("/Users/tumkur/Desktop/FraudAnalytics/Data/*").map(line => line.contains("Fraud"))
    //println("The Fraud count" + Fraud.count())
    //val ok = SparkCommons.sc.textFile("/Users/tumkur/Desktop/FraudAnalytics/Data/*").map(line => line.contains("OK"))
    //println("The Non-fraud count"+ ok.count())

    //System.out.println(positiveData.collect().forall());


    return "Darshan";

  }



}
