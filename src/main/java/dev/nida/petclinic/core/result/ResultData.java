package dev.nida.petclinic.core.result;

/**
 * @author Nida Başer
 * May 2024
 */

public class ResultData <T> extends Result {

    private T data;

    public ResultData(boolean status, String message, String httpCode, T data) {

        super(status, message, httpCode);

        this.data = data;

    }

}