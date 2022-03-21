package br.com.alura.forum.config.swagger;

import br.com.alura.forum.modelo.Usuario;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;

@Configuration
public class SwaggerConfigurations {

    @Bean //Pro spring saber que estou exportando
    public Docket forumApi() { //Docket é um objeto do swagger

        return new Docket(DocumentationType.SWAGGER_2) //Quando dou um new docket, preciso dizer o tipo da documentação
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.alura.forum")) //A partir de qual pacote ele vai ler as classes do projeto
                .paths(PathSelectors.ant("/**")) //Quais endpoints, paths/endereços que é pro springFoxSwagger fazer uma análise
                .build()
                .ignoredParameterTypes(Usuario.class) //Quero que ele ignore todas as urls que trabalham com nossa classe Usuário (até pq exibiria a nossa senha)
                .globalOperationParameters( //Com isso conseguimos aicionar parâmetros globais (Um parâmetro que quero que o swagger apresente em todos os endpoints)
                        Arrays.asList( //o metodo recebe uma lista com os parâmetros
                                new ParameterBuilder() //damos new pra passar os parametros
                                        .name("Authorization") //setei o nome
                                        .description("Header para Token JWT") //descrição pra aparecer na documentação
                                        .modelRef(new ModelRef("string")) //Qual o tipo do parâmetro (No caso uma string)
                                        .parameterType("header") //O tipo de parâmetro que será um cabeçalho (Não é um parâmetro que vai no corpo da requisição)
                                        .required(false) //O parâmetro é opcional
                                        .build())); //build pra construir o objeto pra gente

    }

}
