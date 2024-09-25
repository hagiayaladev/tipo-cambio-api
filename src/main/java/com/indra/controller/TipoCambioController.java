package com.indra.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.indra.model.TipoCambio;
import com.indra.service.ITipoCambioService;

@RestController
@RequestMapping("/api/v1/tipo-cambio")
public class TipoCambioController {
	
	@Autowired
    private ITipoCambioService tipoCambioService;
	
	@GetMapping("/aplicar")
    public ResponseEntity<Map<String, Object>> aplicarTipoCambio(
            @RequestParam Double monto,
            @RequestParam String monedaOrigen,
            @RequestParam String monedaDestino) {

        Optional<TipoCambio> tipoCambioOpt = tipoCambioService.obtenerTipoCambio(monedaOrigen, monedaDestino);

        if (tipoCambioOpt.isPresent()) {
            TipoCambio tipoCambio = tipoCambioOpt.get();
            Double montoConvertido = monto * tipoCambio.getTipoCambio();

            Map<String, Object> response = new HashMap<>();
            response.put("monto", monto);
            response.put("montoConTipoCambio", montoConvertido);
            response.put("monedaOrigen", monedaOrigen);
            response.put("monedaDestino", monedaDestino);
            response.put("tipoCambio", tipoCambio.getTipoCambio());

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(Map.of("error", "Tipo de cambio no encontrado"));
        }
    }

    @PostMapping("/actualizar")
    public ResponseEntity<TipoCambio> actualizarTipoCambio(
            @RequestParam String monedaOrigen,
            @RequestParam String monedaDestino,
            @RequestParam Double nuevoTipoCambio) {

        TipoCambio tipoCambioActualizado = tipoCambioService.actualizarTipoCambio(monedaOrigen, monedaDestino, nuevoTipoCambio);
        return ResponseEntity.ok(tipoCambioActualizado);
    }
}
