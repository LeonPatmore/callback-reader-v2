package leon.patmore.documentreader.insert

import leon.patmore.documentreader.repository.CallbackRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ProcessorConfiguration {

    @Bean
    fun processor(callbackRepository: CallbackRepository): InsertProcessor {
        return InsertProcessor(callbackRepository)
    }
}
