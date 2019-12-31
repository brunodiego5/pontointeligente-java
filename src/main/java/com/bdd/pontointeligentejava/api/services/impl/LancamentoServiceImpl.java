package com.bdd.pontointeligentejava.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.bdd.pontointeligentejava.api.entities.Lancamento;
import com.bdd.pontointeligentejava.api.repositories.LancamentoRepository;
import com.bdd.pontointeligentejava.api.services.LancamentoService;

@Service
public class LancamentoServiceImpl implements LancamentoService {
	
	private static final Logger log = LoggerFactory.getLogger(LancamentoServiceImpl.class);
	
	@Autowired
	private LancamentoRepository lancamentoRepository;

	public Page<Lancamento> buscarPorFuncionarioId(Long funcionarioId, PageRequest pageRequest) {
		log.info("Buscando lancamentos para o funcionario id {}", funcionarioId);
		
		return this.lancamentoRepository.findByFuncionarioId(funcionarioId, pageRequest);
	}

	@Cacheable("lancamentoPorId") /*a primeira vez busca no banco e add o resultado no cache, as proximas a busca lê o cache (3600 s) */
	public Optional<Lancamento> buscarPorId(Long id) {
		log.info("Buscando um lancamento pelo id {}", id);
		// return this.lancamentoRepository.findOne(id); <= para spring 1.5
		return this.lancamentoRepository.findById(id);
	}

	@CachePut("lancamentoPorId") /*mantem o cache atualizado*/
	public Lancamento persistir(Lancamento lancamento) {
		log.info("Persistindo o lançamento {} ", lancamento);
		return this.lancamentoRepository.save(lancamento);
	}

	public void remover(Long id) {
		log.info("removendo o lancamento id {} ", id);
		//this.lancamentoRepository.delete(id); <= para spring 1.5
		this.lancamentoRepository.deleteById(id);
		
	}

}
