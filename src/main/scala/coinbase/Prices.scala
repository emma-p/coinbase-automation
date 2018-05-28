package coinbase

import java.time.LocalDate
import java.time.format.DateTimeFormatter

import scala.concurrent.Future

import Client._

object Prices {
  def buyPrice(from: Currency, to: Currency): Future[(Double, Currency)] =
    getAsync[(Double, Currency)](s"prices/$from-$to/buy")

  def sellPrice(from: Currency, to: Currency): Future[(Double, Currency)] =
    getAsync[(Double, Currency)](s"prices/$from-$to/sell")

  def spotPrice(from: Currency, to: Currency, date: Option[LocalDate]): Future[(Double, Currency)] = {
    val dateParam = date.map(d => s"?date=${d.format(DateTimeFormatter.ISO_LOCAL_DATE)}").getOrElse("")
    getAsync[(Double, Currency)](s"prices/$from-$to/spot$dateParam")
  }
}
