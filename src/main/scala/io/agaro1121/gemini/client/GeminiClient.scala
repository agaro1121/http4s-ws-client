package io.agaro1121.gemini.client

import io.agaro1121.gemini.models.MarketDataResponse
import cats.effect._
import fs2.Stream
import org.http4s.client.jdkhttpclient._
import io.circe.parser._
import org.http4s.Uri
import io.agaro1121.gemini.serialization.circe._

trait GeminiClient[F[_]] {
  def marketData(symbol: String): Resource[F, Stream[F, MarketDataResponse]]
}

final class GeminiWsClient(underlying: WSClient[IO]) extends GeminiClient[IO] {
  private val marketDataApi: String = "wss://api.gemini.com/v1/marketdata"

  def marketData(symbol: String): Resource[IO, Stream[IO, MarketDataResponse]] =
    underlying
      .connectHighLevel(WSRequest(Uri.unsafeFromString(s"$marketDataApi/$symbol")))
      .map { connection =>
        connection.receiveStream
          .collect {
            case WSFrame.Text(textBody, _) => textBody
          }
          .evalMap { textBody =>
            IO.fromEither(decode[MarketDataResponse](textBody))
          }
      }

}
