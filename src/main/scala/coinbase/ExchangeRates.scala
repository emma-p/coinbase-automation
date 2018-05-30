package coinbase

import scala.concurrent.Future
import Client._

object ExchangeRates {
  //Defaults to USD if no currency specified
  def exchangeRates(currency: Option[Currency]): Future[ExchangeRate] = {
    val c = currency.map(c => s"?currency=${c.id}").getOrElse("")
    val path = s"exchange-rates$c"
    getAsync[ExchangeRate](path)
  }
}
