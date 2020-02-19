package com.dong.utils;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;




@org.springframework.web.bind.annotation.ControllerAdvice
@ResponseBody
public class ExceptionAdvice {

//    private static Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
//        logger.error("参数验证失败", e);
        System.out.println("参数验证失败");
        BindingResult result = e.getBindingResult();
        List<String> resultList = new ArrayList<String>();
        for (ObjectError error : result.getAllErrors()) {
            String code = error.getCode();
            String message = error.getDefaultMessage();
            String description = String.format("%s:%s", code, message);
            resultList.add(message);
        }
        return "test-exception";
        /*return new CommonResult().failure(resultList, ResultStatusEnum.PARAMETER_INVALID.getCode(),
                ResultStatusEnum.PARAMETER_INVALID.getDescription()).toJSON();*/
    }
}
