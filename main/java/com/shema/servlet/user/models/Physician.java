package com.shema.servlet.user.models;

import java.util.regex.Pattern;


import com.shema.servlet.helpers.Message;
import com.shema.servlet.db.Db;
import com.shema.servlet.user.interfaces.IUser;

public class Physician extends User implements IUser {
    @Override
    public Message<User> register() throws Exception {
        if (!Pattern.matches("^\\d{6}$", getPassword())) {
            throw new Exception("Password must be 6 digits");
        }


        encrptPass();
        Db.addUser(this);

        return new Message<User>("Physician success", Db.findUser(getUsername()));
    }
    

    @Override
    public void fromUser(User user) {
        setFirstName(user.getFirstName());
        setLastName(user.getLastName());
        setAge(user.getAge());
        setRole(user.getRole());
        setPhone(user.getPhone());
        setUsername(user.getUsername());
        setPassword(user.getPassword());
        setGender(user.getGender());
    }
}
