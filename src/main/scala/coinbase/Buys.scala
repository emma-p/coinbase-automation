package coinbase

import coinbase.Currency.Currency
import coinbase.PaymentMethod.PaymentMethod
import com.softwaremill.sttp._
import Client._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object Currency extends Enumeration {
  type Currency = Value
  val BTC, EUR, ETH = Value
}

object PaymentMethod extends Enumeration {
  type PaymentMethod = Value
  val SEPA, FIAT = Value
}


object Buys {

  def placeBuyOrder(amount: Float, currency: Currency, paymentMethod: PaymentMethod): Future[Response[String]] = {
    val path = "accounts/:account_id/buys"
    val request = sttp.post(uri"$baseUri$path")
        .contentType("application/json")
        .auth.bearer(bearerToken)
        .body(Seq(("amount", amount.toString),("currency", currency.toString),("payment_method", paymentMethod.toString)): _*)

    request.send()
  }

}

