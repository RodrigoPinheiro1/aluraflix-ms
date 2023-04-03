package alura.com.videoscategorias.Controller.controllers;

import alura.com.videoscategorias.Controller.dto.VideoDto;
import alura.com.videoscategorias.Controller.entities.Categorias;
import alura.com.videoscategorias.Controller.entities.StatusVideo;
import alura.com.videoscategorias.Controller.entities.Videos;
import alura.com.videoscategorias.Controller.repository.CategoriasRepository;
import alura.com.videoscategorias.Controller.repository.VideosRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class VideosControllerTest {


    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private VideosRepository videosRepository;

    @MockBean
    private VideoDto videoDto;


    private String StringId;

    private Long idVideos;

    @MockBean
    private Videos video;


    @MockBean
    private Categorias categorias;

    private final URI uri = URI.create("/videos");
    private final URI uriId = URI.create("/videos/");
    private final URI uriIdNoutFound = URI.create("/videos/0");
    private Long categoriasId;

    @BeforeAll
    void put() {
        categorias = new Categorias("terror", "preto", true);

        video = new Videos("terror o filme", "descricao", "url", StatusVideo.CRIADO, categorias, true);

        videosRepository.save(video);
        idVideos = video.getId();
        categoriasId = categorias.getId();

        StringId = String.valueOf(categorias.getId());

    }


    @BeforeEach
    void setUp() {


        videoDto = new VideoDto("terror o filme", "descricao", "url", StatusVideo.CRIADO, categoriasId, true);

    }


    @Test
    void cadastrarVideo() throws Exception {

        videoDto.setCategoriasId(categoriasId);

        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(videoDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    void atualizarVideo() throws Exception {

        videoDto.setId(idVideos);
        videoDto.setTitulo("atualizar");
        videoDto.setUrl("url nova");
        videoDto.setDescricao("aaa");

        mockMvc.perform(MockMvcRequestBuilders.put(uriId + StringId)
                        .content(objectMapper.writeValueAsString(videoDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }


    @Test
    void getById() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get(uriId + StringId))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void getByIdNotFound() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get(uriIdNoutFound))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }


    @Test
    void getAll() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get(uri))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void deleteById() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete(uriId + StringId))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

    }
}