package alura.com.videoscategorias.Controller.controllers;

import alura.com.videoscategorias.Controller.dto.CategoriasDto;
import alura.com.videoscategorias.Controller.dto.VideoDto;
import alura.com.videoscategorias.Controller.entities.Categorias;
import alura.com.videoscategorias.Controller.entities.StatusVideo;
import alura.com.videoscategorias.Controller.entities.Videos;
import alura.com.videoscategorias.Controller.repository.CategoriasRepository;
import alura.com.videoscategorias.Controller.repository.VideosRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CategoriasControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CategoriasRepository categoriasRepository;
    private Categorias categorias;
    @MockBean
    private CategoriasDto categoriasDto;

    private final URI uri = URI.create("/categorias");
    private final URI uriId = URI.create("/categorias/");

    private String idString;

    private final String idNotExist ="000";


    @BeforeAll
    void put() {
        categorias = new Categorias("terror", "preto", true);
        categoriasRepository.save(categorias);
        idString = String.valueOf(categorias.getId());
    }

    @AfterAll
    void deleteAfter() {
        categoriasRepository.delete(categorias);
    }

    @BeforeEach
    void setUp() {
        categoriasDto = new CategoriasDto("terror", "preto", true);

    }

    @Test
    void cadastro() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoriasDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void atualiza() throws Exception {


        categoriasDto.setId(categorias.getId());
        categoriasDto.setCor("atualizada cor");
        categoriasDto.setTitulo("titulo atualizado");
        mockMvc.perform(MockMvcRequestBuilders.put(uriId + idString)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoriasDto)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    void shouldReturn404WhenUpdatingIdThatNotExist() throws Exception {

        categoriasDto.setId(categorias.getId());
        categoriasDto.setCor("atualizada cor");
        categoriasDto.setTitulo("titulo atualizado");
        mockMvc.perform(MockMvcRequestBuilders.put(uriId + idNotExist)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoriasDto)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void paginacao() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get(uri))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(uriId + idString))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    void shouldReturn404WhenSearchAIdthatNotExist() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(uriId+idNotExist))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void delete() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete(uriId + idString))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

    }
    @Test
    void shouldReturn404WhenDeleteAIdthatNotExist() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(uriId+idNotExist))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}