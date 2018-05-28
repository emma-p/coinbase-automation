package coinbase

import coinbase.Client._
import com.softwaremill.sttp._
import upickle.default._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


object Accounts {

  def listAccounts: Future[Seq[Account]] = {
    val path = "accounts"
    sttp.get(uri"$baseUri$path").send().map(res =>
      res.body.right.map{body =>
        val data = ujson.read(body)("data")
        read[Seq[Account]](data)
      }.getOrElse(Seq())
    )
  }

  def showAccount(currency: Currency): Future[Account] = {
    val path = s"accounts/$currency"
    sttp.get(uri"$baseUri$path").send().map{res =>
      res.body.right.map { body =>
        val data = ujson.read(body)("data")
        read[Account](data)
      }.right.get
    }
  }
}
