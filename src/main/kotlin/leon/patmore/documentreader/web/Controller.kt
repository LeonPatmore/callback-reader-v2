package leon.patmore.documentreader.web

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import leon.patmore.documentreader.repository.CallbackRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/object")
class Controller(
    private val callbackRepository: CallbackRepository,
    private val objectMapper: ObjectMapper
) {

    @PostMapping
    private fun insert(
        @RequestBody jsonNode: JsonNode,
        @RequestParam params: Map<String, String>,
        @RequestHeader headers: Map<String, String>
    ): Mono<Void> {
        return Mono.fromCallable { RequestObject(jsonNode, headers, params) }
            .map { objectMapper.valueToTree<JsonNode>(it) }
            .flatMap { callbackRepository.save(it) }
    }

    @GetMapping
    private fun query(@RequestParam params: Map<String, String>): Flux<JsonNode> {
        return callbackRepository.query(params)
    }
}
