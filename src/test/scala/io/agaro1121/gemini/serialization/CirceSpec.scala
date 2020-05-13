package io.agaro1121.gemini.serialization

import org.scalatest._
import io.circe.parser._
import io.agaro1121.gemini.models._
import io.agaro1121.gemini.serialization.circe._

class circeSpec extends FreeSpec {

    "Serialization" - {
        "should parse Json with Circe" in {
            val sample: String = 
                """
                {
                    "type" : "update",
                    "eventId" : 11059590610,
                    "timestamp" : 1589234043,
                    "timestampms" : 1589234043652,
                    "socket_sequence" : 85,
                    "events" : [
                        {
                        "type" : "change",
                        "side" : "ask",
                        "price" : "8606.82",
                        "remaining" : "0",
                        "delta" : "-0.00650898",
                        "reason" : "cancel"
                        }
                    ]
                }
                """

            val result = decode[MarketDataResponse](sample)

            assert(result.isRight, result)
        }
    }

}