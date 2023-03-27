package alura.com.videoscategorias.Controller.Service.implement;

import alura.com.videoscategorias.Controller.Service.VideosService;
import alura.com.videoscategorias.Controller.dto.StatusDto;
import alura.com.videoscategorias.Controller.dto.VideoDto;
import alura.com.videoscategorias.Controller.entities.Categorias;
import alura.com.videoscategorias.Controller.entities.StatusVideo;
import alura.com.videoscategorias.Controller.entities.Videos;
import alura.com.videoscategorias.Controller.repository.CategoriasRepository;
import alura.com.videoscategorias.Controller.repository.VideosRepository;
import alura.com.videoscategorias.exceptions.ProductNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VideosServiceImpl implements VideosService {

    @Autowired
    private VideosRepository videosRepository;
    @Autowired
    private CategoriasCategoriaServiceImpl categoriaService;

    @Autowired
    private CategoriasRepository categoriasRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public VideoDto cadastrarVideo(VideoDto dto) {

        Videos videos = modelMapper.map(dto, Videos.class);
        categoriaService.findById(videos.getCategorias().getId());
        Categorias categorias = categoriasRepository.getReferenceById(videos.getCategorias().getId());
        videos.setCategorias(categorias);
        videos.setStatusVideo(StatusVideo.CRIADO);
        videos.setEnable(true);
        videosRepository.save(videos);

        return modelMapper.map(videos, VideoDto.class);
    }


    @Override
    public VideoDto atualizarVideo(VideoDto dto, Long id) {

        findById(id);
        Videos videos = modelMapper.map(dto, Videos.class);

        categoriaService.findById(videos.getCategorias().getId());
        Categorias categorias = categoriasRepository.getReferenceById(videos.getCategorias().getId());

        videos.setId(id);
        videos.setDescricao(dto.getDescricao());
        videos.setTitulo(dto.getTitulo());
        videos.setUrl(dto.getUrl());
        videos.setCategorias(categorias);
        videosRepository.save(videos);

        return modelMapper.map(videos, VideoDto.class);
    }

    @Override
    public VideoDto findById(Long id) {

        Videos videos = videosRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        return modelMapper.map(videos, VideoDto.class);
    }

    @Override
    public void atualizaStatus(Long id) {


        Videos videos = videosRepository.getReferenceById(id);
        videos.setStatusVideo(StatusVideo.ASSISTIDO);
        videosRepository.save(videos);

    }

    @Override
    public Page<VideoDto> findAll(String titulo, Pageable pageable) {

        if (titulo == null) {
            return videosRepository.findAll(pageable).map(videos -> modelMapper.map(videos, VideoDto.class));
        }
        return videosRepository.acharPorNome(titulo, pageable).map(videos -> modelMapper.map(videos, VideoDto.class));
    }

    public void deleteById(Long id) {

        VideoDto videosDto = findById(id);
        Videos videos = modelMapper.map(videosDto, Videos.class);
        videos.setEnable(false);
        videosRepository.save(videos);
    }
}

