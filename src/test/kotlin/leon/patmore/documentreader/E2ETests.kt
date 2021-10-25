package leon.patmore.documentreader

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ArrayNode
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest(classes = [DocumentReaderApplication::class])
@Testcontainers
@AutoConfigureWebTestClient
class E2ETests(
    @Autowired private val webTestClient: WebTestClient,
    @Autowired private val objectMapper: ObjectMapper
) : MongoDbIntegratingTest() {

    @Test
    fun testSaveAndGetByBody() {
        webTestClient.post()
            .uri("/object")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue("{\"hi\":\"bye\"}")
            .exchange()
            .expectStatus()
            .isOk

        webTestClient.get()
            .uri("/object?body.hi=bye")
            .exchange()
            .expectStatus()
            .isOk
            .expectBody()
            .consumeWith {
                val body = objectMapper.readTree(it.responseBody)
                assert(body.isArray)
                assertEquals(1, (body as ArrayNode).size())
            }
    }

    @Test
    fun testSaveAndGetByHeader() {
        val headerName = "test-header"
        val headerValue = "abc123"

        webTestClient.post()
            .uri("/object")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue("{\"hi\":\"bye\"}")
            .header(headerName, headerValue)
            .exchange()
            .expectStatus()
            .isOk

        webTestClient.get()
            .uri("/object?headers.$headerName=$headerValue")
            .exchange()
            .expectStatus()
            .isOk
            .expectBody()
            .consumeWith {
                val body = objectMapper.readTree(it.responseBody)
                assert(body.isArray)
                assertEquals(1, (body as ArrayNode).size())
            }
    }
}
