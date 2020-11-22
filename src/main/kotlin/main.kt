package com.example

import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.response.respondText
import io.ktor.routing.routing
import io.ktor.routing.get
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.jsoup.Jsoup

fun main() {
    val server = embeddedServer(Netty, 8080) {
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
