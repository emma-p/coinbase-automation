package coinbase

import scala.concurrent.Future
import upickle.default._
import Client._
import scala.concurrent.ExecutionContext.Implicits.global

object ExchangeRates {
  //Defaults to USD if no currency specified
  def exchangeRates(currency: Option[Currency]): Future[Seq[Rate]] = {
    val c = currency.map(c => s"?currency=${c.id}").getOrElse("")
    val path = s"exchange-rates$c"
    getAsync(path)(read[Seq[Rate]])
  }
}
