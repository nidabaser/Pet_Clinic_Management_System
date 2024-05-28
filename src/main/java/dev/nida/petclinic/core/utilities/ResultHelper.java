package dev.nida.petclinic.core.utilities;

import dev.nida.petclinic.core.result.Result;
import dev.nida.petclinic.core.result.ResultData;

/**
 * @author Nida Başer
 * May 2024
 */

public class ResultHelper {

    public static <T>ResultData<T> created(T data){

        return new ResultData<>(true, Msg.CREATED, "201", data);
    }

    public static <T> ResultData<T> validateError(T data){

        return new ResultData<>(false, Msg.VALIDATE_ERROR, "400", data);
    }

    public static <T> ResultData<T> success(T data){

        return new ResultData<>(true, Msg.OK, "200", data);
    }

    public static Result ok(){

        return new Result(true, Msg.OK, "200");
    }

    public static Result notFoundError(String msg){

        return new Result(false, msg, "404");
    }

    public static Result dataAlreadyExistError(String msg){

        return new Result(false, msg, "400");
    }

}