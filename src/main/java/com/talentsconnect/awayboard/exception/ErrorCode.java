package com.talentsconnect.awayboard.exception;

public enum ErrorCode {

    EMPLOYEE_EXIST_TO_TEAM("A001", "Employee Already Exist to the team."),
    UNABLE_FIND_ONE_OF_THE_TEAMS("A002", "Unable to find one of the teams." ),
    EMPLOYEE_ALREADY_EXIT("A003", "Employee Already Exist"),
    INVALID_EMPLOYEE_ID("A004", "Employee id is not valid.");
    String code;
    String message;

    ErrorCode(String code, String message){
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
