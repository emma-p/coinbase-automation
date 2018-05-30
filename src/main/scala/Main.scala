import coinbase._
import coinbase.PaymentMethod._

import scala.concurrent.Future
import scala.util.Try

object Coinbase {
  import scala.concurrent.ExecutionContext.Implicits.global

  def main(args: Array[String]): Unit = {
//    val minThreshold = args(0).toFloat
//    val maxThreshold = args(1).toFloat
//    val amountToTrade = args(2).toFloat
    ServerTime.time.map(seq =>
      println(s"seq is ${seq}")
    ).failed.foreach(e =>
      println(s"Future failed: ${e}")
    )

//    for {
//       rate <- Currencies.exchangeRates(BTC)
//       accountBalance <- Accounts.showAccount(EUR).map(_.balance.amount)
//     } yield {
    //TODO add logic to see strength of a trend
//       if (accountBalance >= amountToTrade && rate < minThreshold) {
//         Buys.placeBuyOrder(amountToTrade, BTC, SEPA)
//       } else Future()
//     }
  }
}
