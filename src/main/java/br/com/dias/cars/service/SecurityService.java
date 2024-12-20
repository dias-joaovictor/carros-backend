package br.com.dias.cars.service;

import br.com.dias.cars.dto.AuthUserDTO;
import br.com.dias.cars.exception.AuthenticationException;
import br.com.dias.cars.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SecurityService {
    private final UsuarioService usuarioService;
    private final TokenService tokenService;
    private final BCryptPasswordEncoder passwordEncoder;

    public String authenticate(AuthUserDTO authUserDTO) throws AuthenticationException {
        Optional<Usuario> byEmail = usuarioService.findByEmail(authUserDTO.email());
        if (byEmail.isEmpty()) {
            throw new AuthenticationException("Usuario ou senha incorretos");
        }

        Usuario usuario = byEmail.get();
        if (passwordEncoder.matches(authUserDTO.password(), usuario.getPassword())) {
            return tokenService.generateToken(usuario);
        } else {
            throw new AuthenticationException("Invalid username or password");
        }
    }
}
