package novi.nl.library.integrationTests;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class IntegrationTests(@Autowired val restTemplate:TestRestTemplate) {
//
////    @Test
////    void testStatusCode200() {
////        //Assert blog page title, content and status code`()
////        val entity = restTemplate.getForEntity<String>("/")
////        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
////        assertThat(entity.body).contains("<h1>Blog</h1>")
////    }
//
//}