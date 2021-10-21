package leon.patmore.documentreader.repository.mongo

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import reactor.test.StepVerifier
import java.util.Collections

@SpringBootTest(classes = [MongoConfiguration::class])
@Testcontainers
@EnableAutoConfiguration
class MongoConfigurationTest(
    @Autowired private val callbackRepositoryMongo: CallbackRepositoryMongo,
    @Autowired private val objectMapper: ObjectMapper
) {

    companion object {
        @Container
        private val MONGO_DB_CONTAINER = MongoDBContainer("mongo:5.0.3")

        @JvmStatic
        @DynamicPropertySource
        fun setProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.data.mongodb.uri", MONGO_DB_CONTAINER::getReplicaSetUrl)
        }
    }

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
