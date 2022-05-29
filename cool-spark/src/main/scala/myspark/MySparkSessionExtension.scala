package myspark

import org.apache.spark.sql.SparkSessionExtensions

class MySparkSessionExtension  extends (SparkSessionExtensions => Unit) {
  override def apply(extensions: SparkSessionExtensions): Unit = {
    extensions.injectParser { (session, parser) =>
      new MySqlParser(parser)
    }
   }
}
