package cl.desafiotecnico.gestor_tareas.controller;

import cl.desafiotecnico.gestor_tareas.dto.JwtResponse;
import cl.desafiotecnico.gestor_tareas.dto.LoginRequest;
import cl.desafiotecnico.gestor_tareas.utils.JWTUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Autenticación", description = "Endpoints para la autenticación de usuarios y generación de tokens JWT")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtil jwtUtil;

    @Operation(
            summary = "Iniciar sesión",
            description = "Autentica a un usuario utilizando su correo y contraseña. Si la autenticación es exitosa, retorna un token JWT."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario autenticado exitosamente, retorna un token JWT"),
            @ApiResponse(responseCode = "401", description = "Credenciales de inicio de sesión inválidas")
    })
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public JwtResponse login(@RequestBody LoginRequest loginRequest) {
        try {

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.email(),
                            loginRequest.password()
                    )
            );

            String token = jwtUtil.generateJwtToken(loginRequest.email());
            return new JwtResponse(token);
        } catch (AuthenticationException ex) {
            throw new RuntimeException("Credenciales de inicio de sesión inválidas", ex);
        }
    }
}
