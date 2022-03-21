package br.com.alura.forum.config.security;

import br.com.alura.forum.modelo.Usuario;
import br.com.alura.forum.repository.UsuarioRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter { //É um filtro do sping que é chamado uma única vez a cada requisição

    private TokenService tokenService;
    private UsuarioRepository repository;

    //Não consigo injetar os atributos acima, então preciso passar por um construtor
    public AutenticacaoViaTokenFilter(TokenService tokenService, UsuarioRepository repository) {
        this.tokenService = tokenService;
        this.repository = repository;
    }



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //Recuperar o token do cabeçalho
        String token = recuperarToken(request);
       //Valida se o token do cabeçalho é o mesmo que tenho aqui
        boolean valido = tokenService.isTokenValid(token);

        if(valido){
            autenticarCliente(token);
        }
        filterChain.doFilter(request, response); //Linha pra falar pro spring "Ja rodei o que tinha que rodar no filtro, segue o fluxo da requisição"
    }

    private void autenticarCliente(String token) {
        Long idUsuario = tokenService.getIdUsuario(token); //Peguei o id do token
        Usuario usuario = repository.findById(idUsuario).get(); //Recuperei o usuario passando o id
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities()); //getAuthorities seria pra pegar os perfis desse usuário
        SecurityContextHolder.getContext().setAuthentication(authentication); //chamei a classe do spring que força a autenticação
    }

    private String recuperarToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization"); //Quero recuperar o request do cabeçalho Authorization
        if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }
        return token.substring(7, token.length()); //começando em 7 pra tirar a palavra "Bearer "
    }
}
