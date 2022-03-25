package co.com.sofka.stepdefinition.reqres;

import co.com.sofka.models.RespuestaReqres;
import co.com.sofka.question.RespuestaServicioR;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.assertj.core.api.Assertions;

import java.util.HashMap;

import static co.com.sofka.task.HacerPost.hacerPost;
import static co.com.sofka.util.EnumReqresJson.EMAIL;
import static co.com.sofka.util.EnumReqresJson.PASSWORD;
import static co.com.sofka.util.LecturaJson.leer;
import static co.com.sofka.util.Log4jValues.LOG4J_PROPERTIES_FILE_PATH;
import static co.com.sofka.util.Log4jValues.USER_DIR;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class ReqresStepDefinition {

    private static final String PATH_JSON = System.getProperty("user.dir") + "\\src\\test\\resources\\files\\reqres\\reqresRegistro.json";
    private static final String PATH_JSON_ERROR = System.getProperty("user.dir") + "\\src\\test\\resources\\files\\reqres\\reqresRegistroError.json";

    private static final String URL_BASE = "https://reqres.in";
    private static final String RESOURCE = "/api/register";
    private final HashMap<String, Object> headers = new HashMap<>();
    private final Actor actor = Actor.named("Adriana");
    private static final Logger LOGER = Logger.getLogger(ReqresStepDefinition.class);
    private final String ERROR = "Missing password";

    @Given("que el usuario se encuentra en la aplicación")
    public void ingresarAplicacion() {
        try {
            PropertyConfigurator.configure(USER_DIR.getValue() + LOG4J_PROPERTIES_FILE_PATH.getValue());
            actor.can(CallAnApi.at(URL_BASE));
            headers.put("Content-Type", "application/json");

        } catch (Exception e) {
            LOGER.error(e.getMessage(), e);
            Assertions.fail(e.getMessage());
        }

    }

    @When("desea realizar el registro con email {string} y password {string}")
    public void registrarEmailPassword(String email, String password) {

        try {
            String json = leer(PATH_JSON);
            String bodyRequest = json.replace(EMAIL.getValue(), email).replace(PASSWORD.getValue(), password);
            actor.attemptsTo(
                    hacerPost()
                            .usingTheResource(RESOURCE)
                            .withHeaders(headers)
                            .andBodyRequest(bodyRequest)
            );
        } catch (Exception e) {
            LOGER.error(e.getMessage(), e);
            Assertions.fail(e.getMessage());
        }
    }

    @Then("el registro es exitoso")
    public void verificarRegistroExitoso() {
        actor.should(
                seeThatResponse("El código es el esperado",
                        response -> response.statusCode(200))
        );
        RespuestaReqres respuestaReqres = new RespuestaServicioR().answeredBy(actor);
        actor.should(
                seeThat("El ID se creó exitosamente",
                        act -> respuestaReqres.getId(), notNullValue())
        );
    }

    @When("el usuario intenta realizar el registro  solo con el email {string}")
    public void realizarRegistroEmail(String email) {
        try {
            String json = leer(PATH_JSON_ERROR);
            String jsonEmail = json.replace(EMAIL.getValue(), email);

            actor.attemptsTo(
                    hacerPost()
                            .usingTheResource(RESOURCE)
                            .withHeaders(headers)
                            .andBodyRequest(jsonEmail)
            );
        } catch (Exception e) {
            LOGER.error(e.getMessage(), e);
            Assertions.fail(e.getMessage());
        }
    }

    @Then("el registro no es exitoso")
    public void verificarRegistroNoExitoso() {
        actor.should(
                seeThatResponse("El código es el esperado",
                        response -> response.statusCode(400))
        );
        RespuestaReqres respuestaReqres = new RespuestaServicioR().answeredBy(actor);
        actor.should(
                seeThat("Se presentó el error esperado",
                        act -> respuestaReqres.getError(),equalTo(ERROR))
        );
    }
}
