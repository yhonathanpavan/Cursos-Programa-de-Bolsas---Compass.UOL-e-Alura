package br.com.alura.forum.config.security;

import br.com.alura.forum.modelo.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    //Injetando algo que está em application.properties
    @Value("${forum.jwt.expiration}") //Spring vai em application propeties, vai ver quem é essa propriedade, pegar o valor dela e injetar na string
    private String expiration;

    @Value("${forum.jwt.secret}")
    private String secret;


    public String gerarToken(Authentication authentication) {
                         //Casting pq essa função devolve um Object e preciso de um Usuario
        Usuario logado = (Usuario) authentication.getPrincipal(); //Esse método recupera o usuário que está logado
        Date hoje = new Date();
        Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));

        return Jwts.builder() //Para criar o token JWT, devemos utilizar a classe Jwts
                .setIssuer("API do fórum da Alura") //Quem é (A Aplicação) que está gerando o token
                .setSubject(logado.getId().toString()) //Quem é o dono do token (Quem é o usuário autenticado a quem esse token pertence)
                .setIssuedAt(hoje) //Data de geração do token
                .setExpiration(dataExpiracao) //Data de expiração
                .signWith(SignatureAlgorithm.HS256, secret) // Pela especificação JSON webtoken, o token tem que ser criptografado. Preciso dizer para ele quem é o algoritmo de criptografia e a senha da minha aplicação, que é usada para fazer a assinatura e gerar o REST da criptografia do token.
                .compact(); //um compact para ele compactar e transformar isso em uma string.
    }

    public boolean isTokenValid(String token) {

        try {

            Jwts.parser()     //Passo um token, ele discriptografa e ve se ta ok
                    .setSigningKey(this.secret) //Passo a chave (secret) que é a chave que ele usa pra criptografar e discriptografar
                    .parseClaimsJws(token); //Esse  método devolve um Jws<Claims> que é um objeto onde consigo recuperar o token e todas as informações que setei dentro do token
            //Quando é feito essa chamada e há um token válido, ele retorna um objeto. Se não, retorna exception

            return true;
        }catch(Exception e){
            return false;
        }
    }

    public Long getIdUsuario(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token)
                .getBody();//Devolve o corpo, o objeto do token em si.

       return Long.parseLong(claims.getSubject()); //Pra pegar o Id de volta
    }
}
