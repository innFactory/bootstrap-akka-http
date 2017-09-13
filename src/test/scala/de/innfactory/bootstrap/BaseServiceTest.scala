package de.innfactory.bootstrap

import akka.http.scaladsl.testkit.ScalatestRouteTest
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport
import de.innfactory.akka.AuthService
import de.innfactory.akka.jwt.AutoValidator
import de.innfactory.bootstrap.http.HttpService
import de.innfactory.bootstrap.services.DummyService
import de.innfactory.bootstrap.utils.Persistence
import de.innfactory.bootstrap.utils.InMemoryPostgresStorage._
import org.scalatest.{Matchers, WordSpec}


trait BaseServiceTest extends WordSpec with Matchers with ScalatestRouteTest with FailFastCirceSupport {


  dbProcess.getProcessId

  implicit val persistence = new Persistence
  val dummyService = new DummyService()
  val jwtValidator = new AutoValidator
  val authService = new AuthService(jwtValidator)
  val httpService = new HttpService(authService, dummyService)

}
