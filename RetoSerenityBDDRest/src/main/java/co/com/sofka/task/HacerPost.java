package co.com.sofka.task;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import java.util.HashMap;

public class HacerPost implements Task {


    private String resource;
    private HashMap<String, Object> headers = new HashMap<>();
    private String bodyRequest;

    public HacerPost usingTheResource(String resource) {
        this.resource = resource;
        return this;
    }

    public HacerPost withHeaders(HashMap<String, Object> headers) {
        this.headers = headers;
        return this;
    }

    public HacerPost andBodyRequest(String bodyRequest) {
        this.bodyRequest = bodyRequest;
        return this;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to(resource)
                        .with(
                                requestSpecification -> requestSpecification.relaxedHTTPSValidation()
                                        .headers(headers)
                                        .body(bodyRequest)
                        )
        );
    }

    public static HacerPost hacerPost() {
        return new HacerPost();
    }
}
