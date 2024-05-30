package no.uio.smolapi.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.slf4j.Logger
import org.slf4j.LoggerFactory

data class QueryRequest(val query: String)

@RestController
@RequestMapping("/api")
class HomeController {
    private val log : Logger = LoggerFactory.getLogger(this.javaClass);

    @Operation(summary = "Retrieve information from the knowledge graph")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Query executed successfully"),
        ApiResponse(responseCode = "400", description = "Invalid query"),
        ApiResponse(responseCode = "500", description = "Internal server error")
    ])
    @GetMapping("/select")
    fun selectData(@RequestBody request: QueryRequest) {
        log.info("Received query: ${request.query}")
    }
}