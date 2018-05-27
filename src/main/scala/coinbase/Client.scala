package coinbase

import com.softwaremill.sttp.akkahttp.AkkaHttpBackend

object Client {
  val baseUri = "https://api.coinbase.com/v2/"
  implicit val sttpBackend = AkkaHttpBackend()

  val bearerToken = ???

}
