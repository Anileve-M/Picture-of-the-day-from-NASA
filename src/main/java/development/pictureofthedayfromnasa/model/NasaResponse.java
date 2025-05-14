package development.pictureofthedayfromnasa.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class NasaResponse {
    private String copyright;
    private Date date;
    private String explanation;
    private String hdurl;
    @JsonProperty("media_type")
    private String media_type;
    @JsonProperty("service_version")
    private String service_version;
    private String title;
    private String url;
}