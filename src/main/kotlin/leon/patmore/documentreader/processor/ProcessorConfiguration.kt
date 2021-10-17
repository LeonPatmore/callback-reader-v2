package leon.patmore.documentreader.processor

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.MongoTemplate

@Configuration
class ProcessorConfiguration {

    @Bean
    fun processor(mongoTemplate: MongoTemplate): Processor {
        return Processor(mongoTemplate)
    }
}
