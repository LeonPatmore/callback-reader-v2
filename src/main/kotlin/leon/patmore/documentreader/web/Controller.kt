package leon.patmore.documentreader.web

import com.fasterxml.jackson.databind.JsonNode
import leon.patmore.documentreader.insert.InsertProcessor
import leon.patmore.documentreader.repository.CallbackRepository
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/object")
class Controller(private val insertProcessor: InsertProcessor,
private val callbackRepository: CallbackRepository) {

    @PostMapping
    private fun insert(@RequestBody jsonNode: JsonNode): Mono<Void> {
        return insertProcessor.process(jsonNode)
    }

    @GetMapping
    private fun query(@RequestParam params: Map<String, String>) : Flux<JsonNode> {
        return callbackRepository.query(params)
    }

}