package com.shema.servlet.user.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shema.servlet.helpers.Message;
import com.shema.servlet.helpers.Json;
import com.shema.servlet.helpers.Response;
import com.shema.servlet.user.dtos.UserTypes;
import com.shema.servlet.user.models.Admin;
import com.shema.servlet.user.models.Patient;
import com.shema.servlet.user.models.Pharmacists;
import com.shema.servlet.user.models.Physician;
import com.shema.servlet.user.models.User;

@WebServlet("/register")
public class SignupServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        User user = new Json().parseBodyJson(req, User.class);  
        
        try {
            Message<User> results = null;
            if (user.getRole() ==  UserTypes.ADMIN) {
                Admin admin = new Admin();
                admin.fromUser(user);
                results = admin.register();
           }  else if (user.getRole() == UserTypes.PATIENT) {
                Patient patient = new Patient();
                patient.fromUser(user);
                results = patient.register();
            } else if (user.getRole() == UserTypes.PHARMACISTS) {
                Pharmacists pharmacists = new Pharmacists();
                pharmacists.fromUser(user);
                results = pharmacists.register();
            } else if (user.getRole() == UserTypes.PHYSICIAN) {
                Physician physician = new Physician();
                physician.fromUser(user);
                results = physician.register();
            }
            
            Response.send(res, results, HttpServletResponse.SC_CREATED);


        } catch (Exception e) {
            e.printStackTrace();
            Response.send(res, new Message<>(e.getMessage(), null), HttpServletResponse.SC_BAD_REQUEST);
        }
    }
    
}
