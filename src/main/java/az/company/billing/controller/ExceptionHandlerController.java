package az.company.billing.controller;

import az.company.billing.entities.Exception;
import az.company.billing.exceptions.GeneralException;
import az.company.billing.exceptions.NoDataFoundException;
import az.company.billing.exceptions.UnexpectedValueException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ExceptionHandlerController {


    @ExceptionHandler(GeneralException.class)
    public Exception handleGeneralException(GeneralException e) {

        return Exception
                .builder()
                .code(300)
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(NoDataFoundException.class)
    public Exception handleNoDataFoundException(NoDataFoundException e) {

        return Exception
                .builder()
                .code(301)
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(UnexpectedValueException.class)
    public Exception handleUnexpectedValueException(UnexpectedValueException e) {
        return Exception
                .builder()
                .code(302)
                .message(e.getMessage())
                .build();
    }
}
