package br.gov.sp.fatec.controller;

import br.gov.sp.fatec.model.Curso;
import br.gov.sp.fatec.model.Aluno;
import br.gov.sp.fatec.repository.CursoRepository;
import br.gov.sp.fatec.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/curso")

public class CursoController {

	@Autowired
	private CursoRepository cursoRepo;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	//@JsonView(View.CursoCompleto.class)
	@JsonView(View.CursoCompleto.class)
	public ResponseEntity<Curso> save(@RequestBody Curso curso, HttpServletRequest req, HttpServletResponse res) {
		curso = cursoRepo.save(curso);

		res.addHeader("Location", req.getServerName() + ":" + req.getServerPort() + req.getContextPath()
				+ "/curso/getById?id=" + curso.getId());
		return curso == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(curso, HttpStatus.OK);
	}

	@RequestMapping(value = "/findByNameCurso")
    @JsonView(View.CursoCompleto.class)
    public ResponseEntity<Curso> findByNameCurso(@RequestParam(value="name") String name) {
        Curso curso = cursoRepo.findByNomeCurso(name);
        return curso == null ?
                new ResponseEntity<Curso>(HttpStatus.NOT_FOUND):
        		new ResponseEntity<Curso>(curso, HttpStatus.OK);
    }

	@RequestMapping(value = "/findByID")
	@JsonView(View.CursoNomePeriodo.class)
	public ResponseEntity<Curso> findByID(@RequestParam(value = "id") Long id) {
		Optional<Curso> curso = cursoRepo.findById(id);
		return curso.isPresent() ? 
				new ResponseEntity<Curso>(curso.get(), HttpStatus.OK):
					new ResponseEntity<Curso>(HttpStatus.NOT_FOUND);
	}

}
