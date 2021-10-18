package leon.patmore.documentreader.repository.mongo

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "mongo")
data class MongoProperties(val databaseName: String, val collectionName: String)
