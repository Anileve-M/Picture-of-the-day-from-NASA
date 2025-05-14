package development.pictureofthedayfromnasa.service;

import development.pictureofthedayfromnasa.httpclient.NasaClient;
import development.pictureofthedayfromnasa.httpclient.NasaImageClient;
import development.pictureofthedayfromnasa.model.NasaResponse;
import feign.Response;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

@Service
@AllArgsConstructor
public class NasaService {
    private final NasaClient nasaClient;
    private final NasaImageClient nasaImageClient;

    public byte[] getImage() {
        var nasaResponse = nasaClient.getMedia();
        Response response;
        try {
            response = nasaImageClient.getImage(getNasaImageUri(nasaResponse));
        } catch (URISyntaxException e) {
            return null;
        }

        Response.Body body = response.body();
        try {
            InputStream inputStream = body.asInputStream();
            return inputStream.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private URI getNasaImageUri(NasaResponse nasaResponse) throws URISyntaxException {
        String nasaImageUri = nasaResponse.getMedia_type().equals("image") ? nasaResponse.getUrl() :
                "http://img.yotube.com/vi/" + nasaResponse.getUrl().split("watch\\?v=")[1] +
                        "/maxresdefault.jpg";
        return new URI(nasaImageUri);
    }
}