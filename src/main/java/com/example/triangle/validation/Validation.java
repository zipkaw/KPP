package com.example.triangle.validation;

import com.example.triangle.handleException.Handle;
import com.example.triangle.serviceException.Service;

public class Validation {
    public static boolean isDigit(String s) throws Handle {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;

        }
    }

    public static Integer parsing(String value1, String value2, String value3) {
        String message = "";
        int flag = 0;
        if (Validation.isDigit(value1)) {
            flag += 1;
        } else {

            message += "error 400: BAD_REQUEST : invalid data, your first number is not Integer ; ";
        }

        if (Validation.isDigit(value2)) {
            flag += 1;

        } else {

            message += "error 400: BAD_REQUEST : invalid data, your second number is not Integer  ; ";
        }

        if (Validation.isDigit(value3)) {
            flag += 1;

        } else {

            message += "error 400: BAD_REQUEST : invalid data, your third number is not Integer  ; ";
        }
        if (flag != 3) {
            throw new Handle(message);
        }
        return flag;
    }

    public static boolean existing(boolean tr) {
        String message = "";
        if (!tr) {
            message += "error 500: INTERNAL_SERVER_ERROR : TRIANGLE DOES NOT EXIST  ; ";
            throw new Service(message);
        } else return true;
        //return null;
    }
}
