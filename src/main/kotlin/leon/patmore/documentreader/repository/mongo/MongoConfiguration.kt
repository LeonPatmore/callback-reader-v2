package leon.patmore.documentreader.repository.mongo

import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import org.bson.Document
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.MongoDatabaseFactory

@Configuration
@EnableConfigurationProperties(MongoProperties::class)
class MongoConfiguration {

    @Bean
    fun mongoDatabase(mongoProperties: MongoProperties, mongoDatabaseFactory: MongoDatabaseFactory): MongoDatabase {
        return mongoDatabaseFactory.getMongoDatabase(mongoProperties.databaseName)
    }

    @Bean
    fun mongoCollection(mongoDatabase: MongoDatabase, mongoProperties: MongoProperties): MongoCollection<Document> {
        return mongoDatabase.getCollection(mongoProperties.collectionName)
    }

    @Bean
    fun callbackRepositoryMongo(): CallbackRepositoryMongo {
        return CallbackRepositoryMongo()
    }
}
