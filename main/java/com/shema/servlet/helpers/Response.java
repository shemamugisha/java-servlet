package com.shema.servlet.helpers;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

public class Response {

    public static void send(HttpServletResponse res, Message payload, int status) {
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        try {
            res.setStatus(status);
            if (payload != null) {
                OutputStream outputStream = res.getOutputStream();
                outputStream.write(new Json().toJson(payload).getBytes());
                outputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }    
}
