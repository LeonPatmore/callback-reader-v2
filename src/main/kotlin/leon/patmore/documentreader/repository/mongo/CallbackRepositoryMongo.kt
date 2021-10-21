package leon.patmore.documentreader.repository.mongo

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.mongodb.reactivestreams.client.MongoCollection
import leon.patmore.documentreader.repository.CallbackRepository
import org.bson.Document
import reactor.core.publisher.Mono

class CallbackRepositoryMongo(
    private val mongoCollection: MongoCollection<Document>,
    private val objectMapper: ObjectMapper
) : CallbackRepository {
    override fun save(jsonNode: JsonNode): Mono<Void> {
        val asMap = objectMapper.convertValue(jsonNode, MAP_TYPE_REFERENCE) as Map<String, Any>
        return Mono.from(mongoCollection.insertOne(Document(asMap))).then()
    }

    companion object {
        private val MAP_TYPE_REFERENCE = object : TypeReference<HashMap<String, Any>>() {}
    }
}
