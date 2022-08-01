package com.shema.servlet.user.models;
import java.util.regex.Pattern;
import com.shema.servlet.helpers.Message;
import com.shema.servlet.db.Db;
import com.shema.servlet.user.interfaces.IUser;

public class Admin extends User implements IUser {
    @Override
    public Message<User> register() throws Exception {
        if (!Pattern.matches("^\\d{8}$", getPassword())) {
            throw new Exception("Password must be 8 digits");
        }


        encrptPass();
        Db.addUser(this);

        return new Message<User>("Admin success", Db.findUser(getUsername()));
    }
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
