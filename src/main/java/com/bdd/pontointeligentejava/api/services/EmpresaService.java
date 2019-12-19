package com.bdd.pontointeligentejava.api.services;

import java.util.Optional;

import com.bdd.pontointeligentejava.api.entities.Empresa;

public interface EmpresaService {
	
	/**
	 * Retorna um empresa dado um CNPJ
	 * @param cnpj
	 * @return Optional<Empresa> 
	 */
	Optional<Empresa> buscarPorCnpj(String cnpj);
	
	/**
	 * Cadastra uma nova empresa na base de dados 	
	 * @param empresa
	 * @return Empresa
	 */
	Empresa persistir(Empresa empresa);

}
