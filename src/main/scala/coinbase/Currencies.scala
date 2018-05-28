package coinbase

import scala.concurrent.Future

import com.softwaremill.sttp._
import Client._
import scala.concurrent.ExecutionContext.Implicits.global

object Currencies {
  def exchangeRates(currency: Currency): Future[Int] = {
    val path = s"exchange-rates?currency=$currency"
    sttp.get(uri"$baseUri$path").send().map(res =>
      ???
    )
  }
}
