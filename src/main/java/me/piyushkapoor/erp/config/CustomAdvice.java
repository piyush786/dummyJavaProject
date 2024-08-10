package me.piyushkapoor.erp.config;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomAdvice {
  

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Map<String, String> handleException(MethodArgumentNotValidException ex) {
    var err = new HashMap<String, String>();
   BindingResult br = ex.getBindingResult();
    List<FieldError> error = br.getFieldErrors();

    err.put("error",  error.get(0).getDefaultMessage());
    err.put("status","failure");
    err.put("timeStamp", LocalDateTime.now().toString());
    return err;
  }

  @ExceptionHandler(Exception.class)
  public Map<String, String> handleException(Exception ex) {
    var err = new HashMap<String, String>();
    System.out.println(ex);
    err.put("error",  ex.getMessage());
    err.put("status","failure");
    err.put("timeStamp", LocalDateTime.now().toString());
    return err;
  }

}
