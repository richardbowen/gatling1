package org.baeldung.simulation

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class RecordedSimulation extends Simulation {

	val httpProtocol = http
		.baseUrl("http://computer-database.gatling.io")
		.inferHtmlResources(BlackList(""".*\.css""", """.*\.js """, """.*\.ico"""), WhiteList())
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("en-US,en;q=0.5")
		.upgradeInsecureRequestsHeader("1")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:124.0) Gecko/20100101 Firefox/124.0")

	val headers_3 = Map("Origin" -> "http://computer-database.gatling.io")



	val scn = scenario("RecordedSimulation")
		.exec(http("request_0")
			.get("/"))
		.pause(6)
		.exec(http("request_1")
			.get("/computers?f=amstrad"))
		.pause(4)
		.exec(http("request_2")
			.get("/computers/412"))
		.pause(7)
		.exec(http("request_3")
			.post("/computers/412")
			.headers(headers_3)
			.formParam("name", "Amstrad CPC 6128")
			.formParam("introduced", "")
			.formParam("discontinued", "")
			.formParam("company", "37"))
		.pause(1)
		.exec(http("request_4")
			.get("/computers/381"))
		.pause(2)
		.exec(http("request_5")
			.get("/computers"))
		.pause(4)
		.exec(http("request_6")
			.get("/computers?f=apple"))
		.pause(1)
		.exec(http("request_7")
			.get("/computers/11"))
		.pause(3)
		.exec(http("request_8")
			.get("/computers"))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}