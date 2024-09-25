package com.indra.service;

import java.util.Optional;

import com.indra.model.TipoCambio;

public interface ITipoCambioService {
	 Optional<TipoCambio> obtenerTipoCambio(String monedaOrigen, String monedaDestino);
	 TipoCambio actualizarTipoCambio(String monedaOrigen, String monedaDestino, Double nuevoTipoCambio);
}
