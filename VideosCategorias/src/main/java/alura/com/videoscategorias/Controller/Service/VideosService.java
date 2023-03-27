package alura.com.videoscategorias.Controller.Service;

import alura.com.videoscategorias.Controller.dto.StatusDto;
import alura.com.videoscategorias.Controller.dto.VideoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VideosService {

    VideoDto cadastrarVideo (VideoDto dto);

    VideoDto atualizarVideo (VideoDto dto, Long id);

    Page<VideoDto> findAll (String titulo,Pageable pageable);

    VideoDto findById (Long id);

    void atualizaStatus(Long id);
}
