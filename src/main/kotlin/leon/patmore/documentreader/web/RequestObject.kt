package leon.patmore.documentreader.web

import com.fasterxml.jackson.databind.JsonNode

data class RequestObject(
    val body: JsonNode,
    val headers: Map<String, String>,
    val params: Map<String, String>
)
