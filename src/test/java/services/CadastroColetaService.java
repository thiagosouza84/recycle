package services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.ColetaModel;
import model.EntregaModel;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class CadastroColetaService {

    final ColetaModel coletaModel = new ColetaModel();
    public final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    public Response response;
    String baseUrl = "http://localhost:8080";
    String  idDelivery;
    JSONObject jsonSchema;
    String schemasPath = "src/test/resources/schemas/";
    private final ObjectMapper mapper = new ObjectMapper();


    public void setFieldsColeta(String field, String value) {
        switch (field) {
            case "numeroColeta" -> coletaModel.setNumeroColeta(Integer.parseInt(value));
            case "descricao" -> coletaModel.setDescricao(value);
            //case "endereco" -> coletaModel.setEndereco(value);
            case "dataColeta" -> coletaModel.setDataColeta(value);
            default -> throw new IllegalStateException("Campo invalido" + field);
        }
    }

    public void createColeta(String endPoint) {
        String url = baseUrl + endPoint;
        String bodySend = gson.toJson(coletaModel);
        response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(bodySend)
                .when()
                .post(url)
                .then()
                .extract()
                .response();
    }

}
