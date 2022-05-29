package myspark

import org.apache.spark.sql.{DataFrame, Row, SaveMode, SparkSession}
import org.apache.spark.sql.catalyst.TableIdentifier
import org.apache.spark.sql.catalyst.expressions.{Attribute, AttributeReference}
import org.apache.spark.sql.execution.command.LeafRunnableCommand
import org.apache.spark.sql.types.StringType

case class CompactTableCommand(table: TableIdentifier,
                               fileNum: Option[Int]) extends LeafRunnableCommand {

  override def output: Seq[Attribute] = Seq(AttributeReference("no_return", StringType, false)())

  override def run(spark: SparkSession): Seq[Row] = {

    val dataDF: DataFrame = spark.table(table.toString())
    val num: Int = fileNum match {
      case Some(i) => i
      case _ =>
        (spark
          .sessionState
          .executePlan(dataDF.queryExecution.logical)
          .optimizedPlan
          .stats.sizeInBytes / (1024L * 1024L * 128L)
          ).toInt
    }
    log.warn(s"fileNum is $num")
    val tmpTableName = table.identifier+"_tmp"
    dataDF.write.mode(SaveMode.Overwrite).saveAsTable(tmpTableName)
    spark.table(tmpTableName).repartition(num).write.mode(SaveMode.Overwrite).saveAsTable(table.identifier)
    spark.sql(s"drop table if exists $tmpTableName")
    log.warn("Compacte Table Completed.")
    Seq(Row.empty)
  }

}
