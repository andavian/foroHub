package com.andavian.forohub.controller;

import com.andavian.forohub.domain.usuario.DatosAutenticacionUsuarios;
import com.andavian.forohub.domain.usuario.Usuario;
import com.andavian.forohub.infra.security.DatosJWTtoken;
import com.andavian.forohub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<DatosJWTtoken> autenticarUsuarios(
            @RequestBody @Valid DatosAutenticacionUsuarios datosAutenticacionUsuarios) {
        // Crear el token de autenticación
        Authentication authToken = new UsernamePasswordAuthenticationToken(
                datosAutenticacionUsuarios.login(),
                datosAutenticacionUsuarios.clave());

        // Autenticar al usuario
        var usuarioAutenticado = authenticationManager.authenticate(authToken);

        // Generar el token JWT
        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());

        // Retornar la respuesta con el token
        return ResponseEntity.ok(new DatosJWTtoken(JWTtoken));
    }
}

