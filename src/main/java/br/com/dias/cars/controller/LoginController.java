package br.com.dias.cars.controller;

import br.com.dias.cars.dto.AuthUserDTO;
import br.com.dias.cars.exception.AuthenticationException;
import br.com.dias.cars.payload.AuthPayload;
import br.com.dias.cars.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class LoginController {
    private final SecurityService securityService;

    @PostMapping
    public ResponseEntity<?> authenticate(@RequestBody AuthUserDTO authUserDTO){
        try{
            String authenticate = securityService.authenticate(authUserDTO);
            return ResponseEntity.ok(new AuthPayload(authenticate));
        }catch (AuthenticationException ex){
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(Map.of("message", "Usuario ou senha invalidos"));
        }
    }
}
