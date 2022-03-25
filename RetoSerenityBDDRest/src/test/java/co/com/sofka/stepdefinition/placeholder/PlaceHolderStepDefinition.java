package co.com.sofka.stepdefinition.placeholder;

import co.com.sofka.stepdefinition.reqres.ReqresStepDefinition;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.assertj.core.api.Assertions;

import static co.com.sofka.question.ListaLibros.listBooks;
import static org.hamcrest.CoreMatchers.equalTo;
import static co.com.sofka.question.RespuestaServicioPh.respuestaServicioPh;
import static co.com.sofka.task.HacerGet.hacerGet;
import static co.com.sofka.util.Log4jValues.LOG4J_PROPERTIES_FILE_PATH;
import static co.com.sofka.util.Log4jValues.USER_DIR;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;

public class PlaceHolderStepDefinition {

    private static final String URL_BASE = "https://jsonplaceholder.typicode.com";
    private static final String RESOURCE = "/posts/";
    private final Actor actor = Actor.named("Adriana");
    private static final Logger LOGER = Logger.getLogger(ReqresStepDefinition.class);


    @Given("que me encuentro en el sitio web como administrador")
    public void ingresarSitioWeb() {

        try {
            PropertyConfigurator.configure(USER_DIR.getValue() + LOG4J_PROPERTIES_FILE_PATH.getValue());
            actor.can(CallAnApi.at(URL_BASE));

        } catch (Exception e) {
            LOGER.error(e.getMessage(), e);
            Assertions.fail(e.getMessage());
        }
    }

    @When("se obtiene el título del libro con ID {string}")
    public void obtenerTituloLibro(String id) {
        try {

            actor.attemptsTo(
                    hacerGet()
                            .usingTheResource(RESOURCE + id)
            );
        } catch (Exception e) {
            LOGER.error(e.getMessage(), e);
            Assertions.fail(e.getMessage());
        }

    }

    @Then("el título contiene mas de {int} letras")
    public void contenerLetras(int longitudMinimaTitulo) {
        actor.should(
                seeThat(
                        respuestaServicioPh()
                                .establecerLongitud(longitudMinimaTitulo), equalTo(true))
        );
    }

    @When("consulto la cantidad de libros prestados")
    public void consultarCantidadLibrosPrestados() {
        try {
            actor.attemptsTo(
                    hacerGet()
                            .usingTheResource(RESOURCE)
            );
        } catch (Exception e) {
            LOGER.error(e.getMessage(), e);
            Assertions.fail(e.getMessage());
        }
    }

    @Then("mostrará que el total es de {int} registros")
    public void mostrarTotalRegistros(Integer cantidadRegistros) {
        actor.should(
                seeThat(
                        listBooks(), equalTo(cantidadRegistros)));
    }
}
