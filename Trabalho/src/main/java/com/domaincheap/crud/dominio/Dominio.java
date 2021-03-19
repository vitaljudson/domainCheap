package com.domaincheap.crud.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/** Esta classe contém os atributos e métodos de um domínio.*/
@Entity
public class Dominio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	@NotBlank(message = "O campo domínio é obrigatório.")
	@Size(min = 2, message = "Um dominio deve ter pelo menos dois caracteres.")
	private String dom;

	@NotBlank(message = "O campo nome é obrigatório.")
	@Size(min = 2, message = "Um nome deve ter pelo menos dois caracteres.")
	private String nome;
	
	@NotBlank(message = "O campo email é obrigatório.")
	private String email;

	@NotBlank(message = "O campo telefone é obrigatório.")
	@Size(min = 11, message = "O campo telefone deve ter 11 números, incluindo DDD.")
	@Size(max = 11, message = "O campo telefone deve ter 11 números, incluindo DDD.")
	private String telefone;
	
	@NotBlank(message = "O campo endereço é obrigatório.")
	private String endereco;
	
	@NotBlank(message = "O campo senha é obrigatório.")
	@Size(min = 8, message = "O campo senha deve ter no mínimo 8 caracteres.")
	private String senha;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDom() {
		return dom;
	}
	public void setDom(String dom) {
		this.dom = dom;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dominio other = (Dominio) obj;
		if (id != other.id)
			return false;
		return true;
	}
}