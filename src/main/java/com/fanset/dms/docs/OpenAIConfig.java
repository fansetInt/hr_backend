package com.fanset.dms.docs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Fanset International",
                        email = "info@fanset.net"
                ),
                description = "Documentation For Device Management System",
                title = "DMS - Fanset International",
                version = "1.0",
                license = @License(
                        name = "MIT LICENCE",
                        url = "fanset.net"

                ),
                termsOfService = "This is for authorised personals only"

        ),
        servers = {
                @Server(
                        description = "Local URL",
                        url = "http://localhost:8080"
                ),
                @Server(
                        description = "Prod URL",
                        url = "http://161.35.71.73:4000"
                )
        },
        security = {
                @SecurityRequirement(
                        name = "bearerAuth"
                )
        }
)

@SecurityScheme(
        name = "bearerAuth",
        description = "Jwt token auth",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)

public class OpenAIConfig {
}
