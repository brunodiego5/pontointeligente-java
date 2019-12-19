package com.bdd.pontointeligentejava.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.bdd.pontointeligentejava.api.entities.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long>{
	
	@Transactional(readOnly = true) /*porque só é consulta*/
	Empresa findByCnpj(String cnpj);
	

}
