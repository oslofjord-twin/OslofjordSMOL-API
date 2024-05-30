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
import org.apache.jena.query.QueryExecution
import org.apache.jena.query.QueryExecutionFactory

data class QueryRequest(val parameters: List<List<String>>, val name: String? = null)

@SpringBootApplication
@RestController
@RequestMapping("/api")
open class HomeController {
    private val log : Logger = LoggerFactory.getLogger(this.javaClass);
    private val fusekiHost = System.getenv("HASURA_HOST") ?: "localhost"
    private val fusekiEndpoint = "http://$fusekiHost:3030/ds"

    private fun queryGenerator(parameters : List<List<String>>, name: String?): String {
        var graphQlData = """
        PREFIX ast: <http://www.smolang.org/oslofjordDT#>
        PREFIX owl: <http://www.w3.org/2002/07/owl#>
        PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
        PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
    
        SELECT DISTINCT
          ?name
          
        """.trimIndent()

        for (parameter in parameters) {
            val param = parameter.get(0)
            graphQlData += "  ?$param\n"
        }

       graphQlData += """
        WHERE {
          ?species rdfs:subClassOf ?b1 .
          ?b1 owl:hasValue ?name ;
              owl:onProperty ast:Name .
        """.trimIndent()
        
        if (name != null) {
            graphQlData += """
        FILTER (?name = "$name"^^xsd:string)
        
        """.trimIndent()
        }

        var i = 2
        for (parameter in parameters) {
            val param = parameter.get(0)
            val value = parameter.get(1)
            graphQlData += """
        OPTIONAL {
          ?species rdfs:subClassOf ?b$i .
          ?b$i owl:hasValue ?$param ;
               owl:onProperty ast:$value .
        }
        
        """.trimIndent()
            i++
        }

        graphQlData += "}\n\n"

        return graphQlData
    }

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
        log.info("Received parameters for query: ${request.parameters}")

        val query = queryGenerator(request.parameters, request.name)
        log.info("Generated query: $query")
        log.info("Executing query to $fusekiEndpoint/query")

        val q = QueryExecution.service("$fusekiEndpoint/query", query)
        val results = q.execSelect()

        while (results.hasNext()) {
            val soln = results.nextSolution()
            log.info(soln.toString())
        }

        return ResponseEntity.ok("Query executed successfully")
    }
}