package coinbase

import java.time.LocalDateTime
import java.time.format.Parsed

import org.scalatest.{FlatSpec, Matchers}
import ujson.Js
import upickle.default._

class packageTest extends FlatSpec with Matchers {
  "localDateTimeReader" should "parse a local date time" in {
    import LocalDateTimeReadWriter._
    read[LocalDateTime](Js.Str("2018-05-30T13:56:16Z")) should be(LocalDateTime.of(2018,5,30,13,56,16))
  }

}
