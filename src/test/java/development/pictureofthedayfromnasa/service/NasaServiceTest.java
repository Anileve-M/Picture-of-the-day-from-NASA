package development.pictureofthedayfromnasa.service;

import development.pictureofthedayfromnasa.httpclient.NasaClient;
import development.pictureofthedayfromnasa.httpclient.NasaImageClient;
import development.pictureofthedayfromnasa.model.NasaResponse;
import feign.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NasaServiceTest {

    @Mock
    private NasaClient nasaClient;

    @Mock
    private NasaImageClient nasaImageClient;

    @InjectMocks
    private NasaService nasaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetImage_ReturnsImageBytes() throws Exception {
        NasaResponse mockResponse = new NasaResponse();
        mockResponse.setMedia_type("image");
        mockResponse.setUrl("http://example.com/image.jpg");

        Response mockImageResponse = mock(Response.class);
        Response.Body mockBody = mock(Response.Body.class);
        byte[] mockImageBytes = new byte[]{1, 2, 3};

        when(nasaClient.getMedia()).thenReturn(mockResponse);
        when(nasaImageClient.getImage(new URI("http://example.com/image.jpg"))).thenReturn(mockImageResponse);
        when(mockImageResponse.body()).thenReturn(mockBody);
        when(mockBody.asInputStream()).thenReturn(new ByteArrayInputStream(mockImageBytes));

        byte[] result = nasaService.getImage();
        assertArrayEquals(mockImageBytes, result);
    }

    @Test
    public void testGetImage_ThrowsIOException() throws Exception {
        NasaResponse mockResponse = new NasaResponse();
        mockResponse.setMedia_type("image");
        mockResponse.setUrl("http://example.com/image.jpg");

        Response mockImageResponse = mock(Response.class);
        Response.Body mockBody = mock(Response.Body.class);

        when(nasaClient.getMedia()).thenReturn(mockResponse);
        when(nasaImageClient.getImage(new URI("http://example.com/image.jpg"))).thenReturn(mockImageResponse);
        when(mockImageResponse.body()).thenReturn(mockBody);
        when(mockBody.asInputStream()).thenThrow(new IOException("Stream error"));

        assertThrows(RuntimeException.class, () -> {
            nasaService.getImage();
        });
    }
}

