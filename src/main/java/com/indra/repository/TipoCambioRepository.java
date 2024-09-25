package com.indra.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.indra.model.TipoCambio;

@Repository
public interface TipoCambioRepository extends JpaRepository<TipoCambio, Long>{
	Optional<TipoCambio> findByMonedaOrigenAndMonedaDestino(String monedaOrigen, String monedaDestino);
}
