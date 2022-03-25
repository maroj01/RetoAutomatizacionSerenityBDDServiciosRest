package co.com.sofka.question;

import co.com.sofka.models.RespuestaReqres;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class RespuestaServicioR implements Question {
    @Override
    public RespuestaReqres answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(RespuestaReqres.class);
    }
}