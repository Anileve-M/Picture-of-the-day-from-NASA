package development.pictureofthedayfromnasa.httpclient;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.URI;

@FeignClient(name = "nasa-image-client", url = "${nasa.image.url}")
public interface NasaImageClient {
    @GetMapping
    Response getImage(URI baseUrl);
}