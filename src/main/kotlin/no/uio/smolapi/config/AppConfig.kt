package no.uio.smolapi.config

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(basePackages = arrayOf("no.uio.smolapi"))
open class AppConfig {
}