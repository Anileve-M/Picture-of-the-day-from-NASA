package development.pictureofthedayfromnasa.httpclient;

import development.pictureofthedayfromnasa.model.NasaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "media", url = "${nasa.url}")
public interface NasaClient {
    @GetMapping
    NasaResponse getMedia();
}