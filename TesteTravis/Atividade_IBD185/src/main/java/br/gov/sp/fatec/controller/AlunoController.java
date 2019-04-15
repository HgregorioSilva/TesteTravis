package br.gov.sp.fatec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.model.Aluno;
import br.gov.sp.fatec.repository.AlunoRepository;

@RestController
@RequestMapping	(value="/aluno")

public class AlunoController {
	
	@Autowired
	private AlunoRepository alunoRepo;
	
	@RequestMapping(value = "/getAlunoRa")
	public ResponseEntity<List<Aluno>> getAlunoRA(@RequestParam(value="ra") String ra, @RequestParam(value="sobrenome") String sobrenome){
		
		List<Aluno> listaAluno = alunoRepo.findByRaContainsIgnoreCaseOrSobrenomeContainsIgnoreCase(ra, sobrenome);
		
		if (listaAluno == null) {
			return new	ResponseEntity<List<Aluno>>(HttpStatus.NOT_FOUND);
			
		}
		return new	ResponseEntity<List<Aluno>>(listaAluno, HttpStatus.OK);
	}
	
	
	@RequestMapping(value ="/getByNome")
	//@JsonView(View.All.Class)
	public ResponseEntity<Aluno> get(@RequestParam(value="nomeAluno", defaultValue="1") String nomeAluno){
		System.out.println(nomeAluno);
		Aluno aluno = alunoRepo.findByNomeAluno(nomeAluno);
		
		System.out.println(aluno.getNomeAluno());
		
		if(aluno == null) {
			return new	ResponseEntity<Aluno>(HttpStatus.NOT_FOUND);
		}
		return new	ResponseEntity<Aluno>(aluno, HttpStatus.OK);

	}

}
