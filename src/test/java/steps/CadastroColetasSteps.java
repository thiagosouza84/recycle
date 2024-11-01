package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Então;
import org.junit.Assert;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CadastroColetasSteps {

    private String token;
    private HttpResponse<String> response;

    @Dado("que eu tenha as credenciais de usuário e senha:")
    public void que_eu_tenha_as_credenciais_de_usuario_e_senha(Map<String, String> credenciais) throws Exception {
        String usuario = credenciais.get("usuario");
        String senha = credenciais.get("senha");

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("username", usuario);
        requestBody.put("password", senha);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(requestBody);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://seu-endereco/api/auth"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            Map<String, String> responseBody = objectMapper.readValue(response.body(), Map.class);
            token = responseBody.get("token");
        }
    }

    @Quando("eu enviar uma requisição de login para o endpoint \"/auth\"")
    public void eu_enviar_uma_requisicao_de_login_para_o_endpoint() {
        Assert.assertNotNull("Token de autenticação não deve ser nulo", token);
    }

    @Então("o status code da resposta do agendamento deve ser {int}")
    public void o_status_code_da_resposta_deve_ser_no_agendamento(int statusCodeEsperado) {
        Assert.assertEquals(statusCodeEsperado, response.statusCode());
    }

    @Então("o corpo da resposta deve conter um token de autenticação")
    public void o_corpo_da_resposta_deve_conter_um_token_de_autenticacao() {
        Assert.assertNotNull("Token não retornado na resposta", token);
    }

    @Dado("que eu esteja autenticado com o token")
    public void que_eu_esteja_autenticado_com_o_token() {
        Assert.assertNotNull("Token não está disponível para autenticação", token);
    }

    @Dado("que eu tenha os seguintes dados do agendamento:")
    public void que_eu_tenha_os_seguintes_dados_do_agendamento(Map<String, String> dadosAgendamento) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(dadosAgendamento);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://seu-endereco/api/agendamento"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        response = client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    @Quando("eu enviar a requisição para o endpoint \"/agendamento\" de agendamento de coletas")
    public void eu_enviar_a_requisicao_para_o_endpoint_de_agendamento_de_coletas() {
        // Nenhuma ação necessária, pois a requisição foi enviada no step anterior
    }

    @Então("o status code da resposta deve ser {int}")
    public void o_status_code_da_resposta_deve_ser(int statusCodeEsperado) {
        Assert.assertEquals(statusCodeEsperado, response.statusCode());
    }

    @Quando("eu enviar uma requisição de login para o endpoint {string}")
    public void euEnviarUmaRequisiçãoDeLoginParaOEndpoint(String arg0) {
    }
}
