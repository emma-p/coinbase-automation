import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import upickle.default._

package object coinbase {

  sealed trait Currency
  case object BTC extends Currency
  case object EUR extends Currency
  case object ETH extends Currency

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
    import LocalDateTimeReadWriter._
    implicit def readerWriter: ReadWriter[Account] = macroRW
  }

  object PaymentMethod extends Enumeration {
    type PaymentMethod = Value
    val SEPA, FIAT = Value
  }
}
