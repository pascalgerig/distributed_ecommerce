package product.service;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;

import javax.inject.Inject;

@Controller("/")
public class ProductController {

    @Client("http://localhost:8080")
    @Inject
    RxHttpClient orderHttpClient;

    @Client("http://localhost:8081")
    @Inject
    RxHttpClient priceHttpClient;

    @Get(produces = MediaType.TEXT_PLAIN)
    public String index() {
        return "Hello World";
    }

    @Get(value = "/distributed", produces = MediaType.TEXT_PLAIN)
    public String distributedIndex() {
        String first = orderHttpClient.toBlocking().retrieve("/");
        String second = priceHttpClient.toBlocking().retrieve("/");
        return first + second;
    }
}
