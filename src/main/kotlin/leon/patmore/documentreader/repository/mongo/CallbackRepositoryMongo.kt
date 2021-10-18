package leon.patmore.documentreader.repository.mongo

import com.fasterxml.jackson.databind.JsonNode
import leon.patmore.documentreader.repository.CallbackRepository
import reactor.core.publisher.Mono

class CallbackRepositoryMongo : CallbackRepository {
    override fun save(jsonNode: JsonNode): Mono<Void> {
        TODO("Not yet implemented")
    }
}
