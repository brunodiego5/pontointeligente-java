package com.bdd.pontointeligentejava.api.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.bdd.pontointeligentejava.api.entities.Lancamento;

public interface LancamentoService {
	
	
	/**
	 * Retorna uma lista paginada de lancamentos de um determinado funcionario.
	 * @param funcionarioId
	 * @param pageRequest 
	 * @return Page<Lancamento>
	 */
	Page<Lancamento> buscarPorFuncionarioId(Long funcionarioId, PageRequest pageRequest);
	
	
	/**
	 * Retorna um lancamento por id
	 * @param id
	 * @return Optional<Lancamento>
	 */
	Optional<Lancamento> buscarPorId(Long id);
	
	/**
	 * Persiste(salva) um lancamento na base de dados
	 * @param lancamento
	 * @return Lancamento
	 */
	Lancamento persistir(Lancamento lancamento);
	
	/**
	 * Remove um lancamento da base de dados	
	 * @param id
	 */
	void remover(Long id);
	

}
