package infrastructure.service

import domain.model.MockRoute
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.generator(mocks: List<MockRoute>) {
    mocks.forEach {
        val mock = it
        route(mock.request.path, HttpMethod(mock.request.method)) {
            handle {
                call.respondText(
                    mock.response.body,
                    ContentType.parse(mock.response.header.contentType),
                    HttpStatusCode(mock.response.statusCode.toInt(), "")
                )
            }
        }
    }
}
