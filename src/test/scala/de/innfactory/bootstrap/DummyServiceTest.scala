package de.innfactory.bootstrap

import org.scalatest.concurrent.ScalaFutures

class DummyServiceTest extends BaseServiceTest with ScalaFutures {

  trait Context {
    val route = httpService.dummyRouter.route
  }

  "Dummy service" should {

    "retrieve dummy list" in new Context {
      Get("/dummy/") ~> route ~> check {
        responseAs[Seq[String]].isEmpty should be(true)
      }
    }
  }

}
