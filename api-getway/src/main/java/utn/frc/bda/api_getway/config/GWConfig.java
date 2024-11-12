package utn.frc.bda.api_getway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtGrantedAuthoritiesConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class GWConfig {

    @Bean
    public RouteLocator configurarRutas(RouteLocatorBuilder builder,
                                        @Value("${tpi-agencia-api-gw.microservicio-agencia}") String uriAgencia,
                                        @Value("${tpi-agencia-api-gw.microservicio-notificaciones}") String uriNotificaciones) {
        return builder.routes()
                .route(p -> p.path("api/v1/agencia/**").filters(f-> f.stripPrefix(3)).uri(uriAgencia))
                .route(p -> p.path("api/v1/notificaciones/**").filters(f-> f.stripPrefix(3)).uri(uriNotificaciones))
                .build();
    }

    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity http) {

        http.authorizeExchange(exchanges -> exchanges

                        .pathMatchers("/api/v1/agencia/pruebas/new").hasRole("EMPLEADO")
                        .pathMatchers("/api/v1/notificaciones/promocion").hasRole("EMPLEADO")
                        .pathMatchers("/api/v1/agencia/pruebas/posicion").hasRole("VEHICULO")
                        .pathMatchers("/api/v1/agencia/reportes/**").hasRole("ADMIN")


                .anyExchange()
                .authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                .csrf(ServerHttpSecurity.CsrfSpec::disable);
        return http.build();
    }

    @Bean
    public ReactiveJwtAuthenticationConverter jwtAuthenticationConverter() {
        ReactiveJwtAuthenticationConverter jwtAuthenticationConverter = new ReactiveJwtAuthenticationConverter();
        JwtGrantedAuthoritiesConverter grantedAuhoritiesConverter = new JwtGrantedAuthoritiesConverter();

        grantedAuhoritiesConverter.setAuthoritiesClaimName("authorities");

        grantedAuhoritiesConverter.setAuthorityPrefix("ROLE_");

        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(
                new ReactiveJwtGrantedAuthoritiesConverterAdapter(grantedAuhoritiesConverter));
        return jwtAuthenticationConverter;
    }
}
