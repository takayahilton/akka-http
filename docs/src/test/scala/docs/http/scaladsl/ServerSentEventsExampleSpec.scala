/*
 * Copyright (C) 2014-2019 Lightbend Inc. <https://www.lightbend.com>
 */

package docs.http.scaladsl

import akka.http.scaladsl.server.{ Directives, RoutingSpec }
import docs.CompileOnlySpec

final class ServerSentEventsExampleSpec extends RoutingSpec with Directives with CompileOnlySpec {

  "stream example" in compileOnlySpec {
    //#event-stream-marshalling-example
    import akka.NotUsed
    import akka.stream.scaladsl.Source

    import akka.http.scaladsl.Http
    import akka.http.scaladsl.unmarshalling.Unmarshal
    import akka.http.scaladsl.model.sse.ServerSentEvent

    //#event-stream-marshalling-example

    //#event-stream-unmarshalling-example
    import akka.http.scaladsl.unmarshalling.sse.EventStreamUnmarshalling._

    Http()
      .singleRequest(Get("http://localhost:8000/events"))
      .flatMap(Unmarshal(_).to[Source[ServerSentEvent, NotUsed]])
      .foreach(_.runForeach(println))
    //#event-stream-unmarshalling-example
  }
}
