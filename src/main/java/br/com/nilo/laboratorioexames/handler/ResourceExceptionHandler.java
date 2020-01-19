package br.com.nilo.laboratorioexames.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.nilo.laboratorioexames.domain.DetalhesErro;

@ControllerAdvice
public class ResourceExceptionHandler {
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<DetalhesErro> handleDataIntegrityViolationException
							(DataIntegrityViolationException e, HttpServletRequest request) {
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(400l);
		erro.setTitulo("Requisição Inválida");
		erro.setMensagemDesenvolvedor("");
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
}
