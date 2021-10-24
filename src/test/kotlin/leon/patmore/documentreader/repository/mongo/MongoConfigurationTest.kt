package leon.patmore.documentreader.repository.mongo

import com.fasterxml.jackson.databind.ObjectMapper
import leon.patmore.documentreader.MongoDbIntegratingTest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.testcontainers.junit.jupiter.Testcontainers
import reactor.test.StepVerifier
import java.util.Collections

@SpringBootTest(classes = [MongoConfiguration::class])
@Testcontainers
@EnableAutoConfiguration
class MongoConfigurationTest(
    @Autowired private val callbackRepositoryMongo: CallbackRepositoryMongo,
    @Autowired private val objectMapper: ObjectMapper
) : MongoDbIntegratingTest() {

    @Test
    fun whenSave_doesNotError() {
        StepVerifier
            .create(callbackRepositoryMongo.save(objectMapper.readTree("""{"hi": "bye"}""")))
            .verifyComplete()

        StepVerifier.create(callbackRepositoryMongo.query(Collections.singletonMap("hi", "bye")))
            .expectNextCount(1)
            .verifyComplete()
    }
}
