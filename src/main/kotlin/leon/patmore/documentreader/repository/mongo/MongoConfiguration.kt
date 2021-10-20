package leon.patmore.documentreader.repository.mongo

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.mongodb.reactivestreams.client.MongoCollection
import com.mongodb.reactivestreams.client.MongoDatabase
import org.bson.Document
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory

@Configuration
@EnableConfigurationProperties(MongoProperties::class)
class MongoConfiguration {

    @Bean
    fun mongoDatabase(mongoProperties: MongoProperties, reactiveMongoDatabaseFactory: ReactiveMongoDatabaseFactory): MongoDatabase {
        return reactiveMongoDatabaseFactory.getMongoDatabase(mongoProperties.databaseName).block()!!
    }

    @Bean
    fun mongoCollection(mongoDatabase: MongoDatabase, mongoProperties: MongoProperties): MongoCollection<Document> {
        return mongoDatabase.getCollection(mongoProperties.collectionName)
    }

    @Bean
    fun callbackRepositoryMongo(mongoCollection: MongoCollection<Document>,
    objectMapper: ObjectMapper): CallbackRepositoryMongo {
        return CallbackRepositoryMongo(mongoCollection, objectMapper)
    }
}
