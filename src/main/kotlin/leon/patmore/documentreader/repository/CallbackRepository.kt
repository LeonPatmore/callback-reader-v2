package leon.patmore.documentreader.repository

import com.fasterxml.jackson.databind.JsonNode
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface CallbackRepository {

    fun save(jsonNode: JsonNode): Mono<Void>

    fun query(params: Map<String, String>): Flux<JsonNode>
}
