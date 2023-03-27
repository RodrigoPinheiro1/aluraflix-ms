package alura.com.videoscategorias.Controller.repository;

import alura.com.videoscategorias.Controller.dto.StatusDto;
import alura.com.videoscategorias.Controller.entities.Videos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public interface VideosRepository extends JpaRepository<Videos, Long> {


    Page<Videos> findByTitulo(String titulo, Pageable pageable);


    @Query("SELECT v from Videos v where v.titulo = :titulo ") //jpql
    Page<Videos> acharPorNome (String titulo, Pageable pageable);
    @Query("select v from Videos v  join v.categorias c where c.titulo = :titulo") //jpql
   // delivered query (spring) findByCategorias_Titulo;
    Page<Videos> acharNomeTituloDeCategorias (String titulo, Pageable pageable) ;

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Videos v set v.statusVideo= :statusVideo where v = :videos")
    void atualizaStatus(StatusDto statusVideo,Videos videos);
}
