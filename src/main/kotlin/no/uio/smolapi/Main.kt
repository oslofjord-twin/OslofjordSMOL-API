package no.uio.no.uio.smolapi

import no.uio.smolapi.controller.HomeController
import org.springframework.boot.SpringApplication

fun main(args: Array<String>) {
    SpringApplication.run(HomeController::class.java, *args)
}