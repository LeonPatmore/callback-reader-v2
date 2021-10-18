package leon.patmore.documentreader.repository

import com.fasterxml.jackson.databind.JsonNode
import reactor.core.publisher.Mono

interface CallbackRepository {

    fun save(jsonNode: JsonNode): Mono<Void>
}
