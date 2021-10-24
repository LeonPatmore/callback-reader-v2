package leon.patmore.documentreader

import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.junit.jupiter.Container

open class MongoDbIntegratingTest {
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