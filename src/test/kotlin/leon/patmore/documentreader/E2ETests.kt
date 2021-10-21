package leon.patmore.documentreader

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest(classes = [DocumentReaderApplication::class])
@EnableAutoConfiguration
@Testcontainers
class E2ETests {

    companion object {
        private val MONGO_DB_CONTAINER = MongoDBContainer("mongo:5.0.3")
    }

//    @Test
//    fun whenSave_doesNotError() {
//        StepVerifier.create(callbackRepositoryMongo.save(objectMapper.readTree("""{"hi": "bye"}""")))
//            .verifyComplete()
//    }
}
