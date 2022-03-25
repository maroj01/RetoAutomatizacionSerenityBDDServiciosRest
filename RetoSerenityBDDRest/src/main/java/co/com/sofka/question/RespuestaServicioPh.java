package co.com.sofka.question;

import co.com.sofka.models.RespuestaPlaceHolder;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class RespuestaServicioPh implements Question<Boolean> {

    private int longitudTitulo;

    public RespuestaServicioPh establecerLongitud(int longitudTitulo) {
        this.longitudTitulo = longitudTitulo;
        return this;
    }

    @Override
    public Boolean answeredBy(Actor actor) {

        String title = SerenityRest.lastResponse().as(RespuestaPlaceHolder.class).getTitle();
        return title.replace(" ", "").length() > longitudTitulo;
    }

    public static RespuestaServicioPh respuestaServicioPh() {
        return new RespuestaServicioPh();
    }

}
