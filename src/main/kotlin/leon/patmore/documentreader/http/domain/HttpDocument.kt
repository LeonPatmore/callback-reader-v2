package leon.patmore.documentreader.http.domain

import com.fasterxml.jackson.databind.JsonNode

data class HttpDocument(private val body: JsonNode, private val headers: Map<String, String>)
