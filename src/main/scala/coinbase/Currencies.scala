package coinbase

import Client._
import upickle.default._

import scala.concurrent.Future

object Currencies {
  def currencies: Future[Seq[Currency]] =
    getAsync("currencies")(read[Seq[Currency]])

}
