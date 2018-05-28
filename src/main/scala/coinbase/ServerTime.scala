package coinbase

import Client.getAsync

import scala.concurrent.Future

object ServerTime {
  def time: Future[Time] = {
    getAsync[Time]("time")
  }

}
