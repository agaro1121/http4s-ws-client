package io.agaro1121.gemini.models

import enumeratum.EnumEntry.Lowercase
import enumeratum._

sealed trait Side extends EnumEntry with Lowercase

object Side extends Enum[Side] {
  override def values: IndexedSeq[Side] = findValues

  case object Bid extends Side

  case object Ask extends Side

}

sealed abstract class Reason(override val entryName: String) extends EnumEntry

object Reason extends Enum[Reason] with Lowercase {
  override def values: IndexedSeq[Reason] = findValues

  case object Place extends Reason("place")

  case object Trade extends Reason("trade")

  case object Cancel extends Reason("cancel")

  case object Initial extends Reason("initial")

}

sealed trait MarketDataEvent

final case class Change(price: Double,
                        side: Side,
                        reason: Reason,
                        remaining: Double,
                        delta: Double,
                        `type`: String) extends MarketDataEvent

sealed trait MakerSide extends EnumEntry

object MakerSide extends Enum[MakerSide] {
  override def values: IndexedSeq[MakerSide] = findValues

  case object Bid extends MakerSide

  case object Ask extends MakerSide

  case object Auction extends MakerSide

}

final case class Trade(
                        price: Double,
                        amount: Double,
                        makerSide: MakerSide
                      ) extends MarketDataEvent

final case class BlockTrade(
                             price: Double,
                             amount: Double
                           ) extends MarketDataEvent

final case class Auction(
                          `type`: String,
                          auction_open_ms: Long,
                          auction_time_ms: Long,
                          first_indicative_ms: Long,
                          last_cancel_time_ms: Long
                        ) extends MarketDataEvent