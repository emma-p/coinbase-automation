package coinbase

import com.softwaremill.sttp._
import Client._
import coinbase.PaymentMethod.PaymentMethod
import upickle.default._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global




object Buys {


//  def placeBuyOrder(amount: Float, currency: Currency, paymentMethod: PaymentMethod): Future[Response[String]] = {
//    val path = "accounts/:account_id/buys"
//    val request = sttp.post(uri"$baseUri$path")
//        .contentType("application/json")
//        .auth.bearer(bearerToken)
//        .body(Seq(("amount", amount.toString),("currency", currency.toString),("payment_method", paymentMethod.toString)): _*)
//
//    request.send()
//  }

}

