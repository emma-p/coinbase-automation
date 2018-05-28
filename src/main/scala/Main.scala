import coinbase._
import coinbase.PaymentMethod._

import scala.concurrent.Future

object Coinbase {
  import scala.concurrent.ExecutionContext.Implicits.global

  def main(args: Array[String]): Unit = {
    val minThreshold = args(0).toFloat
    val maxThreshold = args(1).toFloat
    val amountToTrade = args(2).toFloat

   for {
      rate <- Currencies.exchangeRates(BTC)
      accountBalance <- Accounts.showAccount(EUR).map(_.balance.amount)
    } yield {
      if (accountBalance >= amountToTrade && rate < minThreshold) {
        Buys.placeBuyOrder(amountToTrade, BTC, SEPA)
      } else Future()
    }
  }
}
