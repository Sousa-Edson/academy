package br.com.academy.enums;

public enum Curso {
	ADMINISTRACAO("Administração"), INFORMATICA("Informatica"), CONTABILIDADE("Contabilidade"),
	PROGRAMACAO("Programação"), ENFERMAGEM("Enfermagem");

	private String curso;

	private Curso(String curso) {
		this.curso = curso;
	}

}
