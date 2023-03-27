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



}
