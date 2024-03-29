package com.estudo.prova.configuration;

import com.estudo.prova.entities.Usuario;
import com.estudo.prova.repositories.UsuarioRepository;
import com.estudo.prova.service.TokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {


    private final UsuarioRepository usuarioRepository;

    private final TokenService tokenService;

    public TokenAuthenticationFilter(TokenService tokenService, UsuarioRepository repository) {
        this.tokenService = tokenService;
        this.usuarioRepository = repository;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String tokenFromHeader = getTokenFromHeader(httpServletRequest);

        boolean tokenValid = tokenService.isValidToken(tokenFromHeader);
        if(tokenValid) {
            this.authenticate(tokenFromHeader);
        }


        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void authenticate(String tokenFromHeader){
        Long id = Long.valueOf(tokenService.getTokenId(tokenFromHeader));

        Optional<Usuario> optional = usuarioRepository.findById(id); // precisa ser via id por causa do lazy

        if(optional.isPresent() ){
            Usuario usuario = optional.get();
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
    }

    private String getTokenFromHeader(HttpServletRequest request){
        String token = request.getHeader("Authorization");

        if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }

        return token.substring(7, token.length());
    }


}
