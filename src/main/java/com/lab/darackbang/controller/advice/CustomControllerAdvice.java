package com.lab.darackbang.controller.advice;

import com.lab.darackbang.exception.MemberNotFoundException;
import com.lab.darackbang.exception.ProductNotFoundException;
import com.lab.darackbang.exception.NotFoundException;
import com.lab.darackbang.exception.RoleAccessDeniedException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
@RequiredArgsConstructor
public class CustomControllerAdvice {


    //컬렉션에서 요소를 가져오려고 할 때, 해당 요소가 없는 경우에 발생하는 예외
    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<?> handleNoSuchElementException(NoSuchElementException exception) {
        String message = exception.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("msg","값을 찾을수 없습니다."));

    }

    //요청 인자가 유효하지 않을 때 발생
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        String message = exception.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(Map.of("msg","/요청 인자가 유효하지 않습니다."));
    }

    //산술 연산 중에 발생하는 예외
    @ExceptionHandler(ArithmeticException.class)
    protected ResponseEntity<?> handleNoSuchElementFoundException(ArithmeticException exception) {
        String message = exception.getMessage();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("msg","산술 연산 중에오류가 발생했습니다."));
    }

    //메서드 호출 시 인자로 전달되는 값 자체가 잘못된 경우
    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<?> handleIllegalArgument(IllegalArgumentException exception) {
        String message = exception.getMessage();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("msg",message));
    }


    @ExceptionHandler(ProductNotFoundException.class)
    protected ResponseEntity<?> handleProductNotFoundException(ProductNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("msg","상품 정보를 찾을 수 없습니다."));
    }

    @ExceptionHandler(MemberNotFoundException.class)
    protected ResponseEntity<?> handleMemberNotFoundException(MemberNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("msg","사용자 정보를 찾을 수 없습니다."));
    }

    @ExceptionHandler(RoleAccessDeniedException.class)
    protected ResponseEntity<?> RoleAccessDeniedException(RoleAccessDeniedException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("msg","접근 권한이 없습니다."));
    }


    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<?> handleNotFoundException(NotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("msg","정보를 찾을 수 없습니다."));
    }

}
