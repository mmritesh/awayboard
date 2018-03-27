package com.talentsconnect.awayboard.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by ritesh on 27/3/18.
 */

@Getter
@Setter
public class ServiceResponse<T> {
    private T data;
    private String message;
    private boolean error;

    public ServiceResponse(T data, String message, boolean error) {
        setData(data);
        setMessage(message);
        setError(error);
    }

    public ServiceResponse(T data) {
        setData(data);
        setMessage("Success");
        setError(error);
    }
}
