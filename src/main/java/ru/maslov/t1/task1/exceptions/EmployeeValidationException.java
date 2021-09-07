package ru.maslov.t1.task1.exceptions;

/**
 * Исключение для описания ошибок валидации, возникающая при считывании входных данных
 */
public class EmployeeValidationException extends RuntimeException {
    public EmployeeValidationException() {
    }

    public EmployeeValidationException(String message) {
        super(message);
    }

    public EmployeeValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeeValidationException(Throwable cause) {
        super(cause);
    }
}
