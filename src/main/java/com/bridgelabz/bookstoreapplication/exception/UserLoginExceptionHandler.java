package com.bridgelabz.bookstoreapplication.exception;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.bridgelabz.bookstoreapplication.DTO.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class UserLoginExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<ObjectError> errorList = exception.getBindingResult().getAllErrors();
        List<String> errMesg = errorList.stream().map(objErr -> objErr.getDefaultMessage()).collect(Collectors.toList());
        ResponseDTO responseDTO = new ResponseDTO("Exception while processing REST Request",errMesg);
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JWTDecodeException.class)
    public ResponseEntity<ResponseDTO> handleJWTDecodeException(JWTDecodeException exception) {
        ResponseDTO responseDTO = new ResponseDTO("Invalid Token Provided",exception.getMessage());
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserLoginException.class)
    public ResponseEntity<ResponseDTO> handleUserLoginException(UserLoginException exception) {
        ResponseDTO responseDTO = new ResponseDTO("Exception while processing REST Request",exception.getMessage());
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookException.class)
    public ResponseEntity<ResponseDTO> handleBookException(BookException exception) {
        ResponseDTO responseDTO = new ResponseDTO("Exception while processing REST Request",exception.getMessage());
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CartException.class)
    public ResponseEntity<ResponseDTO> handleCartException(CartException exception) {
        ResponseDTO responseDTO = new ResponseDTO("Exception while processing REST Request",exception.getMessage());
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }

}
