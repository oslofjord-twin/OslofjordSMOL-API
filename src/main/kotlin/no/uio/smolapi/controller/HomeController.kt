package no.uio.smolapi.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.parameters.RequestBody as SwaggerRequestBody
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.http.ResponseEntity

data class QueryRequest(val query: String)

@SpringBootApplication
@RestController
@RequestMapping("/api")
open class HomeController {
    private val log : Logger = LoggerFactory.getLogger(this.javaClass);

    @GetMapping("/status")
    fun status(): ResponseEntity<String> {
        log.info("Service is up and running")
        return ResponseEntity.ok("Service is up and running")
    }

    @Operation(summary = "Retrieve information from the knowledge graph")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Query executed successfully"),
        ApiResponse(responseCode = "400", description = "Invalid query"),
        ApiResponse(responseCode = "500", description = "Internal server error")
    ])
    @PostMapping("/select")
    fun selectData(@SwaggerRequestBody(description = "Request for data retrieval") @RequestBody request: QueryRequest): ResponseEntity<String> {
        log.info("Received query: ${request.query}")

        return ResponseEntity.ok("Query executed successfully")
    }
}