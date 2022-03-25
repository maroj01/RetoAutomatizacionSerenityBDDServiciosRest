package co.com.sofka.task;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

public class HacerGet implements Task {

    private String resource;

    public HacerGet usingTheResource(String resource) {
        this.resource = resource;
        return this;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                Get.resource(resource)
        );

    }

    public static HacerGet hacerGet() {
        return new HacerGet();
    }

}
