package alura.com.videoscategorias.Controller.Service.implement;

import alura.com.videoscategorias.Controller.Service.CategoriaService;
import alura.com.videoscategorias.Controller.dto.CategoriasDto;
import alura.com.videoscategorias.Controller.entities.Categorias;
import alura.com.videoscategorias.Controller.repository.CategoriasRepository;
import alura.com.videoscategorias.exceptions.ProductNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@org.springframework.stereotype.Service
public class CategoriasCategoriaServiceImpl implements CategoriaService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoriasRepository categoriasRepository;

    @Override
    public CategoriasDto cadastro(CategoriasDto dto) {

        Categorias categorias = modelMapper.map(dto, Categorias.class);
        categoriasRepository.save(categorias);
        return modelMapper.map(categorias, CategoriasDto.class);
    }

    @Override
    public CategoriasDto atualiza(Long id, CategoriasDto dto) {

        findById(id);

        Categorias categorias = modelMapper.map(dto, Categorias.class);

        categorias.setId(id);
        categorias.setTitulo(dto.getTitulo());
        categorias.setCor(dto.getCor());
        categoriasRepository.save(categorias);

        return modelMapper.map(categorias, CategoriasDto.class);
    }

    @Override
    public Page<CategoriasDto> paginacao(Pageable pageable) {
        return categoriasRepository.findAll(pageable).map(categorias -> modelMapper.map(categorias, CategoriasDto.class));
    }

    public CategoriasDto findById(Long id) {
        Categorias categorias = categoriasRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        return modelMapper.map(categorias, CategoriasDto.class);
    }

    @Override
    public void delete(Long uuid) {
        findById(uuid);
        categoriasRepository.deleteById(uuid);
    }
}
