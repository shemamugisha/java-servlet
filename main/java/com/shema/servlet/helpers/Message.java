package com.shema.servlet.helpers;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Message<T> {
    String message;
    T payload;
}
