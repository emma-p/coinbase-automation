package coinbase

import com.softwaremill.sttp._
import com.softwaremill.sttp.akkahttp.AkkaHttpBackend
import upickle.default.{Reader, read}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object Client {
  val baseUri = "https://api.coinbase.com/v2/"
  implicit val sttpBackend = AkkaHttpBackend()

  //TODO remove get and deal with failure cases
  def getAsync[A: Reader](path: String): Future[A] =
    sttp.get(uri"$baseUri$path").send().map { res =>
      res.body.toOption.map { body =>
        val data = ujson.read(body)("data")
        read(data)
      }.get
    }


}
