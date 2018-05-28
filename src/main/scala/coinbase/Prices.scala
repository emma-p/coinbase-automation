package coinbase

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import upickle.default._

import scala.concurrent.Future

import Client._

object Prices {
  def buyPrice(from: Currency, to: Currency): Future[(Double, Currency)] =
    getAsync(s"prices/$from-$to/buy")(read[(Double, Currency)])

  def sellPrice(from: Currency, to: Currency): Future[(Double, Currency)] =
    getAsync(s"prices/$from-$to/sell")(read[(Double, Currency)])

  def spotPrice(from: Currency, to: Currency, date: Option[LocalDate]): Future[(Double, Currency)] = {
    val dateParam = date.map(d => s"?date=${d.format(DateTimeFormatter.ISO_LOCAL_DATE)}").getOrElse("")
    getAsync(s"prices/$from-$to/spot$dateParam")(read[(Double, Currency)])
  }
}
