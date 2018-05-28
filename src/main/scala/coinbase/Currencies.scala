package coinbase

import Client._

import scala.concurrent.Future

object Currencies {
  def currencies: Future[Seq[Currency]] =
    getAsync[Seq[Currency]]("currencies")

}
