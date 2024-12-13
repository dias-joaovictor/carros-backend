package br.com.dias.cars.service;

import br.com.dias.cars.model.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.time.Instant;

import static java.time.temporal.ChronoUnit.HOURS;

@Service
public class TokenService {
    private static final Algorithm algorithm = Algorithm.HMAC512("MY-SUPER-SECRET-1234");
    private static final String USUARIO_ID = "usuarioId";
    private static final String EMAIL = "email";
    private static final String NOME = "nome";
    private static final String CARGO = "cargo";
    private static final String AVATAR = "avatar";

    private final UsuarioService usuarioService;
    private final JWTVerifier verifier;

    public TokenService(final UsuarioService usuarioService) {
        this.usuarioService = usuarioService;

        verifier = JWT.require(algorithm)
                .withIssuer("ACME.COM") // Ensure the issuer matches
                .build();
    }

    public String generateToken(Usuario usuario) {
        return JWT.create()
                .withIssuer("ACME.COM")
                .withSubject(usuario.getId().toString())
                .withExpiresAt(Instant.now().plus(10, HOURS))
                .withIssuedAt(Instant.now())
                .withClaim(USUARIO_ID, usuario.getId())
                .withClaim(EMAIL, usuario.getEmail())
                .withClaim(NOME, usuario.getNome())
                .withClaim(CARGO, usuario.getCargo())
                .withClaim(AVATAR, usuario.getAvatar())
                .sign(algorithm);
    }

    public Usuario getUsuarioFromToken(String token){
        DecodedJWT decoded = verifier.verify(token);
        return usuarioService.getById(decoded.getClaim(USUARIO_ID).asLong());
    }

}
