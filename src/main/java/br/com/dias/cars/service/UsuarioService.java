package br.com.dias.cars.service;

import br.com.dias.cars.model.Usuario;
import br.com.dias.cars.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    public Optional<Usuario> findByEmail(String email) {
        Usuario byEmail = usuarioRepository.findByEmail(email);
        if (byEmail == null) {return Optional.empty(); }
        else { return Optional.of(byEmail); }
    }
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario getById(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
}
