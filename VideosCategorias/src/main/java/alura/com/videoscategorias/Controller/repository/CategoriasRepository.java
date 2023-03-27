package alura.com.videoscategorias.Controller.repository;

import alura.com.videoscategorias.Controller.entities.Categorias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoriasRepository extends JpaRepository<Categorias, Long> {


}
