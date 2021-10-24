package leon.patmore.documentreader.insert

import com.fasterxml.jackson.databind.JsonNode
import leon.patmore.documentreader.repository.CallbackRepository
import reactor.core.publisher.Mono

class InsertProcessor(private val callbackRepository: CallbackRepository) {

    fun process(jsonNode: JsonNode): Mono<Void> {
        return callbackRepository.save(jsonNode)
    }
}
