package com.shema.servlet.user.interfaces;

import com.shema.servlet.helpers.Message;
import com.shema.servlet.user.models.User;

public interface IUser {
  public abstract Message<User> register() throws Exception;

  public void fromUser(User user);
}