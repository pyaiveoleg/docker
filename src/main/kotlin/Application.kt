package com.example

import io.ktor.application.call
import io.ktor.application.Application
import io.ktor.http.ContentType
import io.ktor.response.respondText
import io.ktor.routing.routing
import io.ktor.routing.get
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.jsoup.Jsoup

private const val PORT = 8080

fun Application.main() {
    val server = embeddedServer(Netty, PORT) {
        routing {
            get("/") {
                val doc = Jsoup.connect("http://bash.org/?random").get()
                val quote = doc.select("p.qt").first().text()
                call.respondText("Random quote from bash.org: $quote", ContentType.Text.Html)
            }
        }
    }
    server.start(wait = true)
}