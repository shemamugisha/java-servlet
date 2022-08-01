package com.shema.servlet.db;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.shema.servlet.user.models.User;

import lombok.Getter;

@Getter
public class Db {
    private static Map<String, User> users = new LinkedHashMap<>();

    public static void addUser(User user) {
        if (users.get(user.getUsername()) != null) {
            throw new RuntimeException("User already Exits");
        }
        users.put(user.getUsername(), user);
    }

    public static User findUser(String username) {
        return users.get(username);
    }

    public static List<User> getUsers() {
        return new ArrayList<>(users.values());
    }

}
