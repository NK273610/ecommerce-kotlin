package com.order.ecommerce.errorHandling;

import com.order.ecommerce.errorHandling.exception.OrderNotFoundException;
import com.order.ecommerce.errorHandling.exception.ProductNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<ErrorData> onMethodArgumentNotValidException(
            MethodArgumentNotValidException exp, HttpServletRequest request) {
        ErrorDetails errorDetails = ErrorDetails.BAD_REQUEST;
        ErrorData error = new ErrorData(
                errorDetails.getCode(),
                errorDetails.getTitle(),
                exp.getBindingResult().getFieldErrors().get(0).getDefaultMessage());

        return new ResponseEntity<ErrorData>(error, errorDetails.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public ResponseEntity<ErrorData> onMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException exp, HttpServletRequest request) {
        ErrorDetails errorDetails = ErrorDetails.BAD_REQUEST_PARAM;
        ErrorData error = new ErrorData(
                errorDetails.getCode(),
                errorDetails.getTitle(),
                exp.getParameter().getParameterName() + " is not valid");

        return new ResponseEntity<ErrorData>(error, errorDetails.getHttpStatus());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public ResponseEntity<ErrorData> onMissingServletRequestParamException(
            MissingServletRequestParameterException exp, HttpServletRequest request) {
        ErrorDetails errorDetails = ErrorDetails.BAD_REQUEST_PARAM_MISSING;
        ErrorData error = new ErrorData(
                errorDetails.getCode(),
                errorDetails.getTitle(),
                exp.getParameterName() + " is not provided");

        return new ResponseEntity<ErrorData>(error, errorDetails.getHttpStatus());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ResponseEntity<ErrorData> handleInvalidJson(Exception exp,
                                                        HttpServletRequest request) {
        ErrorDetails errorDetails = ErrorDetails.BAD_REQUEST;
        ErrorData error = new ErrorData(
                errorDetails.getCode(),
                errorDetails.getTitle(),
                errorDetails.getMessage());

        return new ResponseEntity<ErrorData>(error, errorDetails.getHttpStatus());
    }

    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseBody
    public ResponseEntity<ErrorData> handlerInvalidOrder(OrderNotFoundException exp,
                                                       HttpServletRequest request) {
        ErrorDetails errorDetails = ErrorDetails.NO_ORDER_PRESENT;
        ErrorData error = new ErrorData(
                errorDetails.getCode(),
                errorDetails.getTitle(),
                errorDetails.getMessage());

        return new ResponseEntity<ErrorData>(error, errorDetails.getHttpStatus());
    }

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseBody
    public ResponseEntity<ErrorData> handlerInvalidOrder(ProductNotFoundException exp,
                                                         HttpServletRequest request) {
        ErrorDetails errorDetails = ErrorDetails.NO_PRODUCT_PRESENT;
        ErrorData error = new ErrorData(
                errorDetails.getCode(),
                errorDetails.getTitle(),
                errorDetails.getMessage());

        return new ResponseEntity<ErrorData>(error, errorDetails.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ErrorData> handleAllException(Exception exp,
                                                        HttpServletRequest request) {
        ErrorDetails errorDetails = ErrorDetails.INTERNAL_SERVER_ERROR;
        ErrorData error = new ErrorData(
                errorDetails.getCode(),
                errorDetails.getTitle(),
                errorDetails.getMessage());

        return new ResponseEntity<ErrorData>(error, errorDetails.getHttpStatus());
    }
}
