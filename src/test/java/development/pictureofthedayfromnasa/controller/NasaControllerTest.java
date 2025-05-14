package development.pictureofthedayfromnasa.controller;

import development.pictureofthedayfromnasa.service.NasaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class NasaControllerTest {
    @Mock
    private NasaService nasaService;

    @InjectMocks
    private NasaController nasaController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(nasaController).build();
    }

    @Test
    public void testGetContent_ReturnsImage() throws Exception {
        byte[] mockImage = new byte[]{1, 2, 3};
        when(nasaService.getImage()).thenReturn(mockImage);
        mockMvc.perform(get("/nasa"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.IMAGE_JPEG))
                .andExpect(content().bytes(mockImage));
    }
}
