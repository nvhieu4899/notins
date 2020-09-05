package go.nvhieucs.notins.exceptionhandler;


import org.json.HTTP;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public Map<String,String> handleUpload()
    {
        Map<String,String> jsonResponse = new HashMap<>();
        jsonResponse.put("Message","Files upload failed");
        return jsonResponse;
    }
}
