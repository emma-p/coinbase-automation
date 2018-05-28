package coinbase

import com.softwaremill.sttp._
import com.softwaremill.sttp.akkahttp.AkkaHttpBackend
import ujson.Transformable
import upickle.default._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object Client {
  val baseUri = "https://api.coinbase.com/v2/"
  implicit val sttpBackend = AkkaHttpBackend()

  val bearerToken = ???

  def getAsync[A](path: String)(reader: Transformable => A): Future[A] =
    sttp.get(uri"$baseUri$path").send().map(res =>
      res.body.right.map { body =>
        val data = ujson.read(body)("data")
        reader(data)
      }.right.get
    )

}
