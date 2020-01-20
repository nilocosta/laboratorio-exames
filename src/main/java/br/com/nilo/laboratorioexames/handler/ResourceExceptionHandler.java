package br.com.nilo.laboratorioexames.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import br.com.nilo.laboratorioexames.domain.DetalhesErro;
import br.com.nilo.laboratorioexames.services.exceptions.IdCanNotBeNullException;
import br.com.nilo.laboratorioexames.services.exceptions.LaboratorioNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<DetalhesErro> handleDataIntegrityViolationException(DataIntegrityViolationException e,
			HttpServletRequest request) {

		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(400l);
		erro.setTitle("Requisição Inválida");
		erro.setDeveloperMessage(e.getMessage());
		erro.setTimestamp(System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}

	@ExceptionHandler(LaboratorioNotFoundException.class)
	public ResponseEntity<DetalhesErro> LaboratorioNotFoundException(LaboratorioNotFoundException e,
			HttpServletRequest request) {

		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(404l);
		erro.setTitle("O Laboratório não foi encontrado");
		erro.setDeveloperMessage(e.getMessage());
		erro.setTimestamp(System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(IdCanNotBeNullException.class)
	public ResponseEntity<DetalhesErro> IdCanNotBeNullException(IdCanNotBeNullException e,
			HttpServletRequest request) {

		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(404l);
		erro.setTitle("O campo Id não pode ser null");
		erro.setDeveloperMessage("Defina um campo id no corpo da requisição");
		erro.setTimestamp(System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<DetalhesErro> MethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e,
			HttpServletRequest request) {

		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(404l);
		erro.setTitle("Argumento Inválido");
		erro.setDeveloperMessage("O argumento não é suportado para esse serviço.");
		erro.setTimestamp(System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
}
