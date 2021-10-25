package leon.patmore.documentreader

import com.mongodb.reactivestreams.client.MongoCollection
import org.bson.Document
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.junit.jupiter.Container
import reactor.core.publisher.Mono
import reactor.test.StepVerifier

open class MongoDbIntegratingTest {

    @Autowired
    private lateinit var mongoCollection: MongoCollection<Document>

    @BeforeEach
    fun cleanup() {
        StepVerifier.create(Mono.from(mongoCollection.deleteMany(Document())))
            .expectNextCount(1)
            .verifyComplete()
    }

    companion object {
        @Container
        private val MONGO_DB_CONTAINER = MongoDBContainer("mongo:5.0.3")

        @JvmStatic
        @DynamicPropertySource
        fun setProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.data.mongodb.uri", MONGO_DB_CONTAINER::getReplicaSetUrl)
        }
    }
}
