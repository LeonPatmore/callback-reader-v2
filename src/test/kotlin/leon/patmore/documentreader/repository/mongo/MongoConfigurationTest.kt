package leon.patmore.documentreader.repository.mongo

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.junit.jupiter.Testcontainers
import reactor.test.StepVerifier

@SpringBootTest(classes = [MongoConfiguration::class])
@Testcontainers
@EnableAutoConfiguration
class MongoConfigurationTest(
    @Autowired private val callbackRepositoryMongo: CallbackRepositoryMongo,
    @Autowired private val objectMapper: ObjectMapper
) {

    companion object {
        private val MONGO_DB_CONTAINER = MongoDBContainer("mongo:5.0.3")
    }

    @Test
    fun whenSave_doesNotError() {
        StepVerifier.create(callbackRepositoryMongo.save(objectMapper.readTree("""{"hi": "bye"}""")))
            .verifyComplete()
    }
}
