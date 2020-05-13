package io.agaro1121.gemini.models


sealed trait MarketDataResponse {
  def socket_sequence: Int
}

object MarketDataResponse {

  final case class Heartbeat(
                              socket_sequence: Int,
                              `type`: String
                            ) extends MarketDataResponse

  final case class Update(
                           socket_sequence: Int,
                           `type`: String,
                           eventId: Long,
                           events: Vector[MarketDataEvent],
                           timestamp: Option[Long],
                           timestampms: Option[Long]
                         ) extends MarketDataResponse

}
