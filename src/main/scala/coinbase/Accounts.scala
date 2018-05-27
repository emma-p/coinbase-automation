package coinbase

import Client._
import akka.http.scaladsl.model.DateTime
import coinbase.AccountType.AccountType
import coinbase.Currency.Currency
import com.softwaremill.sttp._

import scala.concurrent.Future
import upickle.default._
import scala.concurrent.ExecutionContext.Implicits.global

object AccountType extends Enumeration {
  type AccountType = Value
  val Wallet, Fiat, Vault = Value

}

case class Balance(amount: Float, currency: Currency)

case class Account(id: String,
                   name: String,
                   primary: Boolean,
                   accountType: AccountType,
                   currency: Currency,
                   balance: Balance,
                   createdAt: DateTime,
                   updatedAt: DateTime,
                   resource: String,
                   resourcePath: String
                  )

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
