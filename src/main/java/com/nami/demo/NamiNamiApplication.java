package com.nami.demo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@OpenAPIDefinition(
    info = @Info(
            title = "Ñami Ñami API",
            version = "1.0.0",
            description = """
        API del sistema Ñami Ñami: una plataforma tecnológica que impulsa la digitalización 
        de restaurantes locales en Lima Metropolitana mediante una arquitectura modular, 
        escalable y con privacidad desde el diseño.
    
        Permite gestionar usuarios, restaurantes, menús, pedidos y reportes, además de 
        integrar un motor de búsqueda inteligente que prioriza calidad, proximidad y disponibilidad.
        """,
            contact = @Contact(
                name = "Equipo Ñami Ñami",
                email = "contacto@naminami.pe",
                url = "https://naminami.pe"
            ),
            license = @License(
                name = "MIT License",
                url = "https://opensource.org/licenses/MIT"
            )
    ),
    servers = {
        @Server(url = "http://localhost:8080/api/v1", description = "Servidor local (desarrollo)"),
        @Server(url = "https://api-nami.onrender.com/api/v1", description = "Servidor de producción")
    }
)
@SecurityScheme(
    name = "bearerAuth",
    description = "JWT auth",
    scheme = "bearer",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    in = SecuritySchemeIn.HEADER
)
@SpringBootApplication
public class NamiNamiApplication{
	public static void main(String[] args) {
        SpringApplication.run(NamiNamiApplication.class, args);
	}
}
