package com.indra.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indra.model.TipoCambio;
import com.indra.repository.TipoCambioRepository;
import com.indra.service.ITipoCambioService;

@Service
public class TipoCambioServiceImpl implements ITipoCambioService{

	@Autowired
    private TipoCambioRepository tipoCambioRepository;

    @Override
    public Optional<TipoCambio> obtenerTipoCambio(String monedaOrigen, String monedaDestino) {
        return tipoCambioRepository.findByMonedaOrigenAndMonedaDestino(monedaOrigen, monedaDestino);
    }

    @Override
    public TipoCambio actualizarTipoCambio(String monedaOrigen, String monedaDestino, Double nuevoTipoCambio) {
        Optional<TipoCambio> tipoCambioOpt = tipoCambioRepository.findByMonedaOrigenAndMonedaDestino(monedaOrigen, monedaDestino);
        TipoCambio tipoCambio = tipoCambioOpt.orElse(new TipoCambio(null, monedaOrigen, monedaDestino, nuevoTipoCambio));
        tipoCambio.setTipoCambio(nuevoTipoCambio);
        return tipoCambioRepository.save(tipoCambio);
    }

}
