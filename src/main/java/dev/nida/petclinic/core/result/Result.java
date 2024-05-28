package dev.nida.petclinic.core.result;

/**
 * @author Nida Başer
 * May 2024
 */

import lombok.Getter;

@Getter
public class Result {

    private boolean status;

    private String message;

    private String httpCode;

    public Result(boolean status, String message, String httpCode) {

        this.status = status;

        this.message = message;

        this.httpCode = httpCode;

    }

}