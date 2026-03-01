package com.codtech.tests

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class ReqResLoadTest extends Simulation {

  // Base URL
  val baseUrl = "https://reqres.in"

  // HTTP Configuration
  val httpConf = http
    .baseUrl(baseUrl)
    .acceptHeader("application/json")

  // Scenario Definition
  val scn = scenario("ReqRes API Load Test")
    .exec(
      http("Get Users Page 2")
        .get("/api/users?page=2")
        .check(status.is(200))
    )

  // Number of virtual users
  // Default 10 users, can override via CMD using -Dvu=<number>
  private val vu = Integer.getInteger("vu", 10)

  // Load Simulation
  setUp(
    scn.inject(atOnceUsers(vu))
  ).protocols(httpConf)
}