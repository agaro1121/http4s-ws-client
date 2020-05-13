package io.agaro1121.gemini.serialization

import cats.implicits._
import enumeratum.Circe._
import io.circe.Decoder
import io.circe.generic.semiauto._

import io.agaro1121.gemini.models._
import io.agaro1121.gemini.models.MarketDataResponse.{Heartbeat, Update}


object circe extends MarketDataResponseDecoderInstances

trait MarketDataResponseDecoderInstances {
  implicit val updateDecoder: Decoder[Update] = deriveDecoder
  implicit val heartbeatDecoder: Decoder[Heartbeat] = deriveDecoder
  implicit val marketDataResponseDecoder: Decoder[MarketDataResponse] =
    List[Decoder[MarketDataResponse]](
      Decoder[Update].widen, //has to be first LOL
      Decoder[Heartbeat].widen
    ).reduceLeft(_ or _)
  implicit val sideDecoder: Decoder[Side] = decoder(Side)
  implicit val makerSideDecoder: Decoder[MakerSide] = decoder(MakerSide)
  implicit val reasonDecoder: Decoder[Reason] = decoder(Reason)
  implicit val changeDecoder: Decoder[Change] = deriveDecoder
  implicit val tradeDecoder: Decoder[Trade] = deriveDecoder
  implicit val blockTradeDecoder: Decoder[BlockTrade] = deriveDecoder
  implicit val auctionDecoder: Decoder[Auction] = deriveDecoder
  implicit val marketDataEvent: Decoder[MarketDataEvent] = List[Decoder[MarketDataEvent]](
    Decoder[Change].widen,
    Decoder[Trade].widen,
    Decoder[BlockTrade].widen,
    Decoder[Auction].widen
  ).reduceLeft(_ or _)
}
