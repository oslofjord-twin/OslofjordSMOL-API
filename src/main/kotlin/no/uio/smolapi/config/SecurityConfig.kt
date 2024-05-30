package no.uio.smolapi.config
//
//import org.springframework.context.annotation.Bean
//import org.springframework.security.config.annotation.web.builders.HttpSecurity
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
//import org.springframework.security.web.SecurityFilterChain
//
//
//@EnableWebSecurity
//open class SecurityConfig {
////    @Bean
////    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
////        http.authorizeHttpRequests { authorizeRequests ->
////            authorizeRequests
//////                .requestMatchers("/public/**", "/login").permitAll()
////                .anyRequest().permitAll()
////        }
////        return http.build()
////    }
//    @Bean
//    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
//        http.authorizeHttpRequests { authz ->
//                authz
//                    .requestMatchers("/authentication/**").permitAll()
//                    .requestMatchers("/h2/**").permitAll()
//                    .anyRequest().authenticated()
//        }
//
//        return http.build()
//    }
//}