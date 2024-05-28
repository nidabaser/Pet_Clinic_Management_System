package dev.nida.petclinic.core.config;

import dev.nida.petclinic.core.exception.DataExistsException;
import dev.nida.petclinic.core.exception.NotFoundException;
import dev.nida.petclinic.core.result.Result;
import dev.nida.petclinic.core.utilities.ResultHelper;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Nida Başer
 * May 2024
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    public ResponseEntity<Result> handleNotFoundException(NotFoundException e){

        return new ResponseEntity<>(ResultHelper.notFoundError(e.getMessage()), HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(DataExistsException.class)
    public ResponseEntity<Result> handleDataExistsException(DataExistsException e){

        return new ResponseEntity<>(ResultHelper.dataAlreadyExistError(e.getMessage()), HttpStatus.BAD_REQUEST);

    }
}