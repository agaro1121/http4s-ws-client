package io.agaro1121.gemini

import java.net.http.HttpClient

import cats.effect.{ExitCode, IO, IOApp}
import org.http4s.client.jdkhttpclient._

import io.agaro1121.gemini.client.GeminiWsClient

object App extends IOApp {
  override def run(args: List[String]): IO[ExitCode] = {

    val geminiClient: IO[GeminiWsClient] = for {
      http <- IO(HttpClient.newHttpClient())
      wsClient = JdkWSClient[IO](http)
      geminiClient = new GeminiWsClient(wsClient)
    } yield geminiClient


    geminiClient.flatMap(client =>
      client.marketData("BTCUSD")
        .use { stream =>
          stream.evalTap(m => IO(println(m))).compile.drain
        }
    ).as(ExitCode.Success)

  }

}
