package alura.com.videoscategorias.Controller.Service;

import alura.com.videoscategorias.Controller.dto.CategoriasDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoriaService {


    void delete(Long uuid);

    CategoriasDto cadastro(CategoriasDto dto);

    CategoriasDto atualiza(Long uuid, CategoriasDto dto);

    Page<CategoriasDto> paginacao(Pageable pageable);

    CategoriasDto findById(Long id);


}
