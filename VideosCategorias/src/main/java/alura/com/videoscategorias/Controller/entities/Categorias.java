package alura.com.videoscategorias.Controller.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Categorias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String cor;

    private Boolean ativo;

    public Categorias(String titulo, String cor, Boolean ativo) {
        this.titulo = titulo;
        this.cor = cor;
        this.ativo = ativo;
    }


}
