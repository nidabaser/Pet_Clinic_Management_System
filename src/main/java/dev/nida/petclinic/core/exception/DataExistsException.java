package dev.nida.petclinic.core.exception;

/**
 * @author Nida Başer
 * May 2024
 */

public class DataExistsException extends RuntimeException {

    public DataExistsException(String message){

        super(message);

    }

}