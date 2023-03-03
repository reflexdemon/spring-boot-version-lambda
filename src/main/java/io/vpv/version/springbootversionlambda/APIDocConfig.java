package io.vpv.version.springbootversionlambda;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Spring BootTree",
                description = "" +
                        "This is a simple tool that actually lists all the spring boot versions and its dependencies.",
                contact = @Contact(
                        name = "spring-boot-version",
                        url = "https://boottree.vpv.io",
                        email = "contact@vpv.io"
                ),
                license = @License(
                        name = "MIT Licence",
                        url = "https://github.com/reflexdemon/spring-boot-version-lambda/blob/main/LICENSE")),
        servers = @Server(url = "http://localhost:8080")
)
public class APIDocConfig {
}
