package com.shema.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shema.servlet.helpers.Message;
import com.shema.servlet.helpers.Response;



@WebServlet("")
public class Main extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Response.send(res, new Message<>("Welcome To Medical Retriever Auth Api", null), HttpServletResponse.SC_OK);
    }
    
}
