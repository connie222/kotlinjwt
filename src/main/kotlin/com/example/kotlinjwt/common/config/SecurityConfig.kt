package com.example.kotlinjwt.common.config

import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.authentication.AuthenticationWebFilter


@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
class SecurityConfig {

    @Bean
    fun configureSecurity(
        http: ServerHttpSecurity
    ): SecurityWebFilterChain {
        return http
            .cors().and()
            .csrf().disable()
            .logout().disable()
            .authorizeExchange()
            .anyExchange().permitAll()
            .and()
            .build()

    }
}