import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import upickle.default._
import coinbase.LocalDateTimeReadWriter._

package object coinbase {

  case class Currency(id: String, name: String, minTradingSize: Double)

  object Currency {
    implicit def readerWriter: ReadWriter[Currency] = macroRW
  }

  sealed trait AccountType
  case object Wallet extends AccountType
  case object Fiat extends AccountType
  case object Vault extends AccountType

  object AccountType {
    implicit def readerWriter: ReadWriter[AccountType] = macroRW
  }

  case class Balance(amount: Float, currency: Currency)

  object Balance {
    implicit def readerWriter: ReadWriter[Balance] = macroRW
  }

  object LocalDateTimeReadWriter {
    implicit val rw = readwriter[String].bimap[LocalDateTime](
      dt => dt.format(DateTimeFormatter.ISO_INSTANT),
      str => LocalDateTime.parse(str, DateTimeFormatter.ISO_INSTANT)
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
    implicit def readerWriter: ReadWriter[Account] = macroRW
  }

  object PaymentMethod extends Enumeration {
    type PaymentMethod = Value
    val SEPA, FIAT = Value
  }

  case class Rate(currency: Currency, value: Double)

  object Rate {
    implicit def readerWriter: ReadWriter[Rate] = macroRW
  }

  case class Time(iso: LocalDateTime, epoch: Long)

  object Time {
    implicit def readerWriter: ReadWriter[Time] = macroRW
  }
}
