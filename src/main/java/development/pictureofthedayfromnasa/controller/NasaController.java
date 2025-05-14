package development.pictureofthedayfromnasa.controller;

import development.pictureofthedayfromnasa.service.NasaService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class NasaController {
    private final NasaService nasaService;

    @GetMapping(value = "/nasa")
    public ResponseEntity<byte[]> getContent() {
        byte[] content = nasaService.getImage();
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(content);
    }
}