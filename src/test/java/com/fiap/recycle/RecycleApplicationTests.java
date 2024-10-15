package com.fiap.recycle;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RecycleApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void contextLoads() throws Exception {
		// Testa se o contexto carrega corretamente
		assertThat(restTemplate).isNotNull();
	}

	@Test
	public void testHelloEndpoint() throws Exception {
		// Testa se o endpoint /hello retorna o valor esperado
		String url = "http://localhost:" + port + "/hello";
		String response = this.restTemplate.getForObject(url, String.class);
		assertThat(response).isEqualTo("Hello, Recycle!");
	}
}
