package br.com.alura.forum.config.security;

import br.com.alura.forum.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity //Habilitando o módulo de segurança
@Configuration //Para no startar do projeto o spring carregar e ler as configurações que estiverem dentro da classe
public class SecurityConfigurations extends WebSecurityConfigurerAdapter{

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    @Bean //Agora o spring sabe que esse método retorna um AuthenticationManager -> De tal forma, agora é possível fazer a injeção com @Autowired
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    //Serve pra configurar a parte de autenticação (Controles de acesso, Login)
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
    }

    //Serve para configurações de Autoricação (URLs, quem pode acessar cada URL, perfil de acesso)
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .antMatchers(HttpMethod.GET, "/topicos").permitAll() //No antMatchers eu digo qual url eu quero filtrar e o que é pra fazer(permitir ou bloquear)
        .antMatchers(HttpMethod.GET, "/topicos/*").permitAll()
        .antMatchers(HttpMethod.POST, "/auth").permitAll()
        .antMatchers(HttpMethod.GET, "/actuator/**").permitAll() //Actuator é o monitoramento
        .anyRequest().authenticated() //Qualquer outra requisição, precisa estar autenticado.
        .and().csrf().disable() //cross-site request forgery (que é um tipo de ataque hacker que acontece em aplicações web)
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //Com isso, aviso para o Spring security que no nosso projeto, quando eu fizer autenticação, não é para criar sessão, porque vamos usar token.
        .and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class); //Nosso filtro virá antes do filtro do spring de autenticar. "Antes de qualquer coisa, rode nosso filtro."
    }

    //Para configurações de serviços estáticos (Arquivos CSS, JS, imagens, etc.)
    @Override
    public void configure(WebSecurity web) throws Exception { //É aqui também que configuro o swagger (Pois quero que o spring security ignore)
        web.ignoring()
                .antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**"); //Todas as URLs que quero ignorar (Não bloauear o acesso)
    }

    /* Visualizando o hash da senha 123456
    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("123456"));
    }
     */
}

