package br.com.academy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.academy.model.Aluno;

public interface AlunoDao extends JpaRepository<Aluno, Integer> {
	@Query("select p from Aluno p where p.status = 'ATIVO' ")
	public List<Aluno> findByStatusAtivos();

	@Query("select i from Aluno i where i.status = 'INATIVO' ")
	public List<Aluno> findByStatusInativos();

	public List<Aluno> findByNomeContainingIgnoreCase(String nome);
}
