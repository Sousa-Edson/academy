package br.com.academy.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.academy.dao.AlunoDao;
import br.com.academy.model.Aluno;

@Controller
public class AlunoController {
	@Autowired
	private AlunoDao alunorepositoio;

	@GetMapping("/inserirAluno")
	public ModelAndView InserirAluno(Aluno aluno) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("aluno/formAluno");
		mv.addObject("aluno", new Aluno());
		return mv;
	}

	@PostMapping("InsertAlunos")
	public ModelAndView inserirAluno(@Valid Aluno aluno, BindingResult br) {
		ModelAndView mv = new ModelAndView();
		if (br.hasErrors()) {
			mv.setViewName("aluno/formAluno");
			mv.addObject("aluno");
		} else {
			mv.setViewName("redirect:/alunos-adicionados");
			alunorepositoio.save(aluno);
		}
		return mv;
	}

	@GetMapping("alunos-adicionados")
	public ModelAndView listagemAlunos() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("aluno/listAlunos");
		mv.addObject("alunosList", alunorepositoio.findAll());
		return mv;
	}

	@GetMapping("/alterar/{id}")
	public ModelAndView alterar(@PathVariable("id") Integer id) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("aluno/alterar");
		Aluno aluno = alunorepositoio.getOne(id);
		mv.addObject("aluno", aluno);
		return mv;

	}

	@PostMapping("alterar")
	public ModelAndView alterar(Aluno aluno) {
		ModelAndView mv = new ModelAndView();
		alunorepositoio.save(aluno);
		mv.setViewName("redirect:/alunos-adicionados");
		return mv;
	}

	@GetMapping("/excluir/{id}")
	public String excluirAluno(@PathVariable("id") Integer id) {
		alunorepositoio.deleteById(id);
		return ("redirect:/alunos-adicionados");

	}

	@GetMapping("filtro-alunos")
	public ModelAndView filtroAlunos() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("aluno/filtroAlunos");
		return mv;
	}

	@GetMapping("alunos-ativos")
	public ModelAndView listaAlunosAtivos() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("aluno/alunos-ativos");
		mv.addObject("alunosAtivos", alunorepositoio.findByStatusAtivos());
		return mv;
	}

	@GetMapping("alunos-inativos")
	public ModelAndView listaAlunosInativos() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("aluno/alunos-inativos");
		mv.addObject("alunosInativos", alunorepositoio.findByStatusInativos());
		return mv;
	}

	@PostMapping("pesquisar-aluno")
	public ModelAndView pesquisarAluno(@RequestParam(required = false) String nome) {
		ModelAndView mv = new ModelAndView();
		List<Aluno> listaAlunos;
		if (nome == null || nome.trim().isEmpty()) {
			listaAlunos = alunorepositoio.findAll();
		} else {
			listaAlunos = alunorepositoio.findByNomeContainingIgnoreCase(nome);
		}
		mv.setViewName("aluno/pesquisa-resultado");
		mv.addObject("listaDeAlunos", listaAlunos);
//		mv.addObject("aluno", new Aluno());
		return mv;
	}

}
