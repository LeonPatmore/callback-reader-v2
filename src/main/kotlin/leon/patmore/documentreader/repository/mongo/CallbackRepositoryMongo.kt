package leon.patmore.documentreader.repository.mongo

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.mongodb.client.model.Filters.and
import com.mongodb.client.model.Filters.eq
import com.mongodb.reactivestreams.client.MongoCollection
import leon.patmore.documentreader.repository.CallbackRepository
import org.bson.Document
import org.bson.conversions.Bson
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class CallbackRepositoryMongo(
    private val mongoCollection: MongoCollection<Document>,
    private val objectMapper: ObjectMapper
) : CallbackRepository {
    override fun save(jsonNode: JsonNode): Mono<Void> {
        val asMap = objectMapper.convertValue(jsonNode, MAP_TYPE_REFERENCE) as Map<String, Any>
        return Mono.from(mongoCollection.insertOne(Document(asMap))).then()
    }

    override fun query(params: Map<String, String>): Flux<JsonNode> {
        return Flux.from(mongoCollection.find(mapToMongoQuery(params))).map { objectMapper.readTree(it.toJson()) }
    }

    companion object {
        private val MAP_TYPE_REFERENCE = object : TypeReference<HashMap<String, Any>>() {}

        private fun mapToMongoQuery(params: Map<String, String>): Bson {
            return and(params.map { eq(it.key, it.value) })
        }
    }
}
