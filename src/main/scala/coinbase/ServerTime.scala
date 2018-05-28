package coinbase

import Client.getAsync

import scala.concurrent.Future
import upickle.default._

object ServerTime {
  def time: Future[Time] = {
    getAsync("time")(read[Time])
  }

}
