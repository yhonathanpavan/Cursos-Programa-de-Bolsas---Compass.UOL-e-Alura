package br.com.alura.forum.controller;

import br.com.alura.forum.controller.dto.DetalhesDoTopicoDto;
import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.controller.form.AtualizacaoTopicoForm;
import br.com.alura.forum.controller.form.TopicoForm;
import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController //Com essa anotação, eu omito o "Response Body" de cada método. Pois o Spring assume que todos os métodos irão usá-lo.
@RequestMapping("/topicos")
public class TopicosController{

    @Autowired //Injeção de dependência
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    @Cacheable(value = "listaDeTopicos") //Essa string funciona como um "id" para esse cache
    public Page<TopicoDto> lista(@RequestParam(required = false) String nomeCurso, @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable paginacao){ //Passo o parametro pela URL.../?nomeCurso=Spring+Boot&page=..&size=...
        //Pageable paginacao = PageRequest.of(pagina, qtd, Sort.Direction.DESC, ordenacao); //Criando uma paginação Interface pageable do spring data recebe classe pageRequest //Direction pra dizer a direção, se será crescente ou dec...

        if(nomeCurso == null){
            Page<Topico> topicos = topicoRepository.findAll(paginacao); //Tipo page tem dentro dele uma lista, mas com informações de qtd de paginas, pagina atual etc.
            return TopicoDto.converter(topicos);
        } else {
            Page<Topico> topicos = topicoRepository.findByCurso_Nome(nomeCurso, paginacao);
            return TopicoDto.converter(topicos);
        }
    }
    //O Spring usa uma biblioteca chamada Jackson, o Spring pega essa lista, passa pro jackson, jackson converte pra Json e depois converteu, e devolveu como String

    //@RequestMapping(value="/topicos", method = RequestMethod.POST)
    @PostMapping
    @Transactional
    @CacheEvict(value = "listaDeTopicos", allEntries = true) //Quero que limpe o cache da lista de topicos e quero que limpe todos os registros
    public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder){ //Digo que esse parâmetro eu quero pegar do corpo da requisição
        Topico topico = form.converter(cursoRepository);
        topicoRepository.save(topico);
        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDto(topico));
    }

    @GetMapping("/{id}") //A parte dinâmica do path deve ser declarada entre chaves.
    public ResponseEntity<DetalhesDoTopicoDto> detalhar(@PathVariable Long id){ //Isto é, preciso avisar para o Spring que o parâmetro Long id não virá numa interrogação e sim no barra ("/"), na URL. Para dizer isso pro Spring, existe uma anotação, @PathVariable, que se trata de uma variável do Path, da URL. E aí o Spring por padrão perceberá que o nome do parâmetro do método se chama id (@PathVariable Long id) e a parte dinâmica da URL se chama id ("/{id}"), então ele vai associar, saberá que é para pegar o que veio na URL e jogar no parâmetro.
        Optional<Topico> topico = topicoRepository.findById(id); //Tipo Optional pois pode ter um registro ou não...
        if(topico.isPresent()){
            return ResponseEntity.ok(new DetalhesDoTopicoDto(topico.get()));
        }
        return ResponseEntity.notFound().build(); //Retorna 404
    }

    @PutMapping("/{id}") //Put seria pra sobreescrever o recurso inteiro (atualizar)
    @Transactional //Pra avisar pro spring que é pra commitar a transação no final do método
    @CacheEvict(value = "listaDeTopicos", allEntries = true)
    public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicoForm form){ //O segundo parametro, em request body, é o que eu passo pelo JSON
        Optional<Topico> optional = topicoRepository.findById(id); //Tipo Optional pois pode ter um registro ou não...
        if(optional.isPresent()){
            Topico topico = form.atualizar(id, topicoRepository);
            return ResponseEntity.ok(new TopicoDto(topico));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listaDeTopicos", allEntries = true)
    public ResponseEntity<?> remover(@PathVariable Long id){
        Optional<Topico> optional = topicoRepository.findById(id); //Tipo Optional pois pode ter um registro ou não...
        if(optional.isPresent()){
            topicoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }


}
