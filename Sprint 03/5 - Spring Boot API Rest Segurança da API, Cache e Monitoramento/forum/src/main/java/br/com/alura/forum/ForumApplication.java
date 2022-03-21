package br.com.alura.forum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//Essa é a classe que usamos pra rodar nosso projeto

@SpringBootApplication
@EnableSpringDataWebSupport //Com essa notação a gente habilita o suporte pro Spring pega da requisição dos parâmetros da URL os campos de paginação e repassar pro spring data
@EnableCaching //Habilito o cache
@EnableSwagger2 //Anotação pra habilitar swagger no projeto
public class ForumApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForumApplication.class, args);
	}

}
