package order.service;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;

import javax.inject.Inject;

@Controller("/")
public class OrderController {

    @Client("http://localhost:8081")
    @Inject
    RxHttpClient priceHttpClient;

    @Client("http://localhost:8082")
    @Inject
    RxHttpClient productHttpClient;

    @Get(produces = MediaType.TEXT_PLAIN)
    public String index() {
        return "Hello World";
    }

    @Get(value = "/distributed", produces = MediaType.TEXT_PLAIN)
    public String distributedIndex() {
        String first = priceHttpClient.toBlocking().retrieve("/");
        String second = productHttpClient.toBlocking().retrieve("/");
        return first + second;
    }
}
