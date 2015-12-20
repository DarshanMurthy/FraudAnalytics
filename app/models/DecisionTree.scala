package models

import controllers.SparkCommons
import org.apache.spark.mllib.evaluation.MulticlassMetrics
import org.apache.spark.mllib.feature.HashingTF
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.tree.DecisionTree
import org.apache.spark.mllib.util.MLUtils


/**
 * Created by tumkur on 11/12/15.
 */
object DTree {

  def Tree(): String ={

    val htf = new HashingTF(10000)
    //var path="/Users/tumkur/Documents/FraudAnalytics/ClickFraud/Data"
    val positiveData = SparkCommons.sc.textFile("/Users/tumkur/Desktop/FraudAnalytics/Data/publishers_09feb12.csv")
      .map { text => new LabeledPoint(1, htf.transform(text.split("OK"))) }


    val negativeData = SparkCommons.sc.textFile("/Users/tumkur/Desktop/FraudAnalytics/Data/publishers_09feb12.csv")
      .map { text => new LabeledPoint(0, htf.transform(text.split("Fraud")))}


    val posSplits = positiveData.randomSplit(Array(0.6, 0.4), seed = 11L)
    val negSplits = negativeData.randomSplit(Array(0.6, 0.4), seed = 11L)
    val training = posSplits(0).union(negSplits(0))
    val test = posSplits(1).union(negSplits(1))

    // Train a DecisionTree model.
    //  Empty categoricalFeaturesInfo indicates all features are continuous.

    val numClasses = 2
    val categoricalFeaturesInfo = Map[Int, Int]((4,5))
    val impurity = "gini"
    val maxDepth = 5
    val maxBins = 6

    val model = DecisionTree.trainClassifier(training, numClasses, categoricalFeaturesInfo, impurity,
      maxDepth, maxBins)


    //Evaluate model on training instances and compute training error
    val labelAndPreds = test.map { point =>
      val prediction = model.predict(point.features)
      (point.label, prediction)
    }
    val trainErr = labelAndPreds.filter(r => r._1 != r._2).count.toDouble / test.count


    val metrics = new MulticlassMetrics(labelAndPreds)

    println("output precision for all labels (0 and 1, negative and positive)")
    metrics.labels.foreach( l => println( " the precision" + metrics.precision(l)))

    println("The confusion Matrix is")
    println(metrics.confusionMatrix)
    println("Training Error = " + trainErr)

    /* output F1-measure for all labels (0 and 1, negative and positive) */
    /*metrics.labels.foreach( l => println(metrics.fMeasure(l)+" "+ metrics.falsePositiveRate(l)+ " the precision" +
    metrics.precision(l)))*/

    val name="Darshan"
    return name

  }


}
