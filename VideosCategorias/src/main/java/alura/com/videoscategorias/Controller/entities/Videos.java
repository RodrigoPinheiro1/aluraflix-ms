package alura.com.videoscategorias.Controller.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Videos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    private String url;

    @Enumerated(EnumType.STRING)
    private StatusVideo statusVideo;

    @ManyToOne(cascade = CascadeType.ALL)
    private Categorias categorias;
    private Boolean enable;


    public Videos(String titulo, String descricao, String url, StatusVideo statusVideo, Categorias categorias, Boolean enable) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.url = url;
        this.statusVideo = statusVideo;
        this.categorias = categorias;
        this.enable = enable;
    }
}
