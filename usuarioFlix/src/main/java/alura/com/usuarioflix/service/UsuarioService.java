package alura.com.usuarioflix.service;

import alura.com.usuarioflix.Dto.UsuarioDto;
import alura.com.usuarioflix.http.VideoClient;
import alura.com.usuarioflix.model.Permissao;
import alura.com.usuarioflix.model.Usuario;
import alura.com.usuarioflix.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private VideoClient videoClient;

    public void confirmarVideo(Long id) {

        Usuario usuario = usuarioRepository.getReferenceById(id);

        usuario.setPermissao(Permissao.ACEITO_ASSISTIR);
        usuarioRepository.save(usuario);

        videoClient.atualizaVideo(usuario.getVideoId());

    }

    @Autowired
    private UsuarioRepository usuarioRepository;
    public UsuarioDto cadastro(UsuarioDto dto) {

        Usuario usuario = modelMapper.map(dto, Usuario.class);

        usuario.setNome(dto.getNome());
        usuario.setDataNascimento(dto.getDataNascimento());
        usuario.setPermissao(Permissao.ACEITO);
        usuarioRepository.save(usuario);

        return modelMapper.map(usuario,UsuarioDto.class);

    }

    public UsuarioDto atualizar(UsuarioDto dto, Long id) {

        Usuario usuario = modelMapper.map(dto, Usuario.class);

        usuario.setId(id);
        usuario.setDataNascimento(dto.getDataNascimento());
        usuario.setNome(dto.getNome());

        usuarioRepository.save(usuario);
        return modelMapper.map(usuario,UsuarioDto.class);
    }


}
