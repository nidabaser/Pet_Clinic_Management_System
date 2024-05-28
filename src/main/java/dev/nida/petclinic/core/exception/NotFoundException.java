package dev.nida.petclinic.core.exception;

/**
 * @author Nida Başer
 * May 2024
 */

public class NotFoundException extends RuntimeException{

    public NotFoundException(String message){

        super(message);

    }

}
