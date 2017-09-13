package de.innfactory.bootstrap

import akka.actor.ActorSystem
import akka.event.{Logging, LoggingAdapter}
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import de.innfactory.akka.AuthService
import de.innfactory.akka.jwt.AutoValidator
import de.innfactory.bootstrap.http.HttpService
import de.innfactory.bootstrap.services.DummyService
import de.innfactory.bootstrap.utils.{Configuration, FlywayService, Persistence}

import scala.concurrent.ExecutionContext

object Main extends App with Configuration {
  // $COVERAGE-OFF$Main Application Wrapper
  implicit val actorSystem = ActorSystem()
  implicit val executor: ExecutionContext = actorSystem.dispatcher
  implicit val log: LoggingAdapter = Logging(actorSystem, getClass)
  implicit val materializer: ActorMaterializer = ActorMaterializer()

  val flywayService = new FlywayService(jdbcUrl, dbUser, dbPassword)
  flywayService.migrateDatabaseSchema

  implicit val persistence = new Persistence
  val jwtValidator = new AutoValidator
  val authService = new AuthService(jwtValidator)
  val dummyService = new DummyService()

  val httpService = new HttpService(authService, dummyService)

  Http().bindAndHandle(httpService.routes, httpHost, httpPort)
  // $COVERAGE-ON$
}
