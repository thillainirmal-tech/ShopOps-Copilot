package dev.thillainirmal.shopops.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:shop-web;MODE=PostgreSQL;DB_CLOSE_DELAY=-1",
        "spring.datasource.username=sa", "spring.datasource.password=",
        "shop.security.api-key=integration-test-key-32-characters", "shop.ai.enabled=false",
        "spring.ai.model.chat=none"
})
@AutoConfigureMockMvc
class WebIntegrationTest {
    @Autowired MockMvc mvc;

    @Test void landingIsPublicAndCatalogueIsAuthenticated() throws Exception {
        mvc.perform(get("/")).andExpect(status().isOk());
        mvc.perform(get("/api/products")).andExpect(status().isForbidden());
        mvc.perform(get("/api/products").header("X-API-Key", "integration-test-key-32-characters"))
                .andExpect(status().isOk()).andExpect(jsonPath("$").isArray()).andExpect(jsonPath("$.length()").value(3));
    }
}
