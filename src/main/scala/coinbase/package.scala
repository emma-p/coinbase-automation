import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import upickle.default._
import coinbase.LocalDateTimeReadWriter._
import ujson.Js

package object coinbase {

  case class Currency(id: String, name: String, minTradingSize: Double)

  object Currency {
    implicit val r = reader[Js.Value].map[Currency](
      json => Currency(json("id").str, json("name").str, json("min_size").str.toDouble)
    )
  }

  sealed trait AccountType
  case object Wallet extends AccountType
  case object Fiat extends AccountType
  case object Vault extends AccountType

  object AccountType {
    implicit def reader: Reader[AccountType] = macroR
  }

  case class Balance(amount: Float, currency: Currency)

  object Balance {
    implicit def reader: Reader[Balance] = macroR
  }

  object LocalDateTimeReadWriter {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
    implicit val rw = readwriter[String].bimap[LocalDateTime](
      dt => dt.format(formatter),
      str => LocalDateTime.parse(str, formatter)
    )
  }

  case class Account(id: String,
                     name: String,
                     primary: Boolean,
                     accountType: AccountType,
                     currency: Currency,
                     balance: Balance,
                     createdAt: LocalDateTime,
                     updatedAt: LocalDateTime,
                     resource: String,
                     resourcePath: String
                    )

  object Account {
    implicit def reader: Reader[Account] = macroR
  }

  object PaymentMethod extends Enumeration {
    type PaymentMethod = Value
    val SEPA, FIAT = Value
  }

  case class ExchangeRate(currency: String, rates: Map[String, Double])

  object ExchangeRate {
    implicit val r = reader[Js.Value].map[ExchangeRate](
      json => ExchangeRate(json("currency").str, json("rates").obj.mapValues(_.str.toDouble).toMap)
    )
  }

  case class Time(iso: LocalDateTime, epoch: Long)

  object Time {
    implicit val r: Reader[Time] = reader[Js.Value].map[Time](
      json => Time(read[LocalDateTime](json("iso")), json("epoch").num.toLong)
    )
  }
}
