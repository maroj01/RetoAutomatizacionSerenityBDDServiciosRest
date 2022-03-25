package co.com.sofka.question;

import co.com.sofka.models.RespuestaPlaceHolder;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;


public class ListaLibros implements Question {


    @Override
    public Integer answeredBy(Actor actor) {

        return SerenityRest.lastResponse()
                .jsonPath()
                .getList("data", RespuestaPlaceHolder.class).size();    }

    public static ListaLibros listBooks() {
        return new ListaLibros();
    }
}