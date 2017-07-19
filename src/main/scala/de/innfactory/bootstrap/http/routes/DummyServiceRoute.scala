package de.innfactory.bootstrap.http.routes

import akka.http.scaladsl.server.Directives._
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport
import de.innfactory.bootstrap.services.DummyService
import io.circe.generic.auto._
import io.circe.syntax._

import scala.concurrent.ExecutionContext

class DummyServiceRoute(val dummyService: DummyService)(implicit executionContext: ExecutionContext) extends FailFastCirceSupport {

  import dummyService._

  val route = pathPrefix("dummy") {
    path("") {
      pathEndOrSingleSlash {
        get {
          complete(getAll.map(_.asJson))
        }
      }
    }
  }

}
