package leon.patmore.documentreader.web

import com.fasterxml.jackson.databind.JsonNode

data class RequestObject(private val body: JsonNode,
                         private val headers: Map<String, String>,
                         private val params: Map<String, String>)
