package leon.patmore.documentreader

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
class E2ETests(@Autowired private val webTestClient: WebTestClient) : MongoDbIntegratingTest() {

    @Test
    fun whenSave_doesNotError() {
        webTestClient.post()
            .uri("/object")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue("{\"hi\":\"bye\"}")
            .exchange()
            .expectStatus()
            .isOk

        webTestClient.get()
            .uri("/object?hi=bye")
            .exchange()
            .expectStatus()
            .isOk
            .expectBody()
            .jsonPath("results")
            .isArray
    }
}
