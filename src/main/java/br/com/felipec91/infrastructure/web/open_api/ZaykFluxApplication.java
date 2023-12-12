package br.com.felipec91.infrastructure.web.open_api;


import jakarta.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Zaykflux API",
                description = "Conjunto de endpoints da aplicação Web Zaykflux. Utilizados tanto por inquilinos/clientes - registrados e autorizados - para operações relacionadas a abertura de tickets e posterior acompanhamento, quanto para atendentes e ou gestores - que tratam, monitoram e gerenciam o atendimento de seus clientes",
                version = "1.0.0",
                contact = @Contact(
                        name = "Maintainer",
                        email = "feelipecampos91@gmail.com",
                        url = "https://github.com/FelipeC91/Zaykflux-api"
                )
        )
)
public class ZaykFluxApplication extends Application {
}
