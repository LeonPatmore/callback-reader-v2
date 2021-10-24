package leon.patmore.documentreader.web

import com.fasterxml.jackson.databind.JsonNode

data class ResponseObject(private val results: List<JsonNode>)