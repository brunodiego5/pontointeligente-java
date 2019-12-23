package com.bdd.pontointeligentejava.api.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.bdd.pontointeligentejava.api.entities.Lancamento;
import com.bdd.pontointeligentejava.api.repositories.LancamentoRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class LancamentoServiceTest {
	
	@MockBean
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private LancamentoService lancamentoService;
	
	
	@BeforeEach
	public void setUp() {
		BDDMockito.given(this.lancamentoRepository.findByFuncionarioId(Mockito.anyLong(), Mockito.any(PageRequest.class)))
			.willReturn(new PageImpl<Lancamento>(new ArrayList<Lancamento>()));
		BDDMockito.given(this.lancamentoRepository.findById(Mockito.anyLong())).willReturn(Optional.of(new Lancamento()));
		BDDMockito.given(this.lancamentoRepository.save(Mockito.any(Lancamento.class))).willReturn(new Lancamento());
	}
	
	@Test
	public void testBuscarLancamentoPorFuncionarioId( ) {
		Page<Lancamento> lancamento = this.lancamentoService.buscarPorFuncionarioId(1L, PageRequest.of(0, 10));
		
		assertNotNull(lancamento);
	}
	
	@Test
	public void testBuscarLancamentoId( ) {
		Optional<Lancamento> lancamento = this.lancamentoService.buscarPorId(1L);
		
		assertTrue(lancamento.isPresent());
	}
	
	@Test
	public void testPersistirLancamento( ) {
		Lancamento lancamento = this.lancamentoService.persistir(new Lancamento());
		
		assertNotNull(lancamento);
	}

}
