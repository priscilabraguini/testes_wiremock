package teste_Wiremock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

import io.restassured.RestAssured;

public class testeApiWiremock {

	@Rule
	public WireMockRule wireMockRule = new WireMockRule(8089);

	@Test
	public void testeWiremock() {
		stubFor(post("/api/teste").withHeader("Content-Type", containing("json")).willReturn(
				ok().withHeader("Content-Type", "application/json").withBody("Usuario cadastrado com sucesso!")));

		RestAssured.given().header("Content-Type", "application/json").when().post("http://localhost:8089/api/teste")
				.then().assertThat().log().all().body(Matchers.equalTo("Usuario cadastrado com sucesso!"));

	}

}
