package br.com.dias.cars.service;

import br.com.dias.cars.model.Usuario;
import br.com.dias.cars.repository.UsuarioRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import static org.springframework.web.context.WebApplicationContext.SCOPE_REQUEST;

@Getter
@Setter
@Service
@Scope(value = SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
@RequiredArgsConstructor
public class UsuarioContext {

    private final UsuarioRepository usuarioRepository;

    private volatile Long usuarioId;

    public Usuario getUsuarioCurrent() {
        return usuarioRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
}
