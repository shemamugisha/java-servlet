package com.shema.servlet.helpers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.*;

import com.google.gson.ExclusionStrategy;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import java.util.stream.Collectors;


public class Json {
    private final Gson gson = new GsonBuilder().addSerializationExclusionStrategy(new ExclusionStrategy() {
        @Override
        public boolean shouldSkipField(FieldAttributes fieldAttributes) {
            final Expose expose = fieldAttributes.getAnnotation(Expose.class);
            return expose != null && !expose.serialize();
        }

        @Override
        public boolean shouldSkipClass(Class<?> aClass) {
              return false;
        }
        
    }).addDeserializationExclusionStrategy(new ExclusionStrategy() {
        @Override
        public boolean shouldSkipField(FieldAttributes fieldAttributes) {
            final Expose expose = fieldAttributes.getAnnotation(Expose.class);
            return expose != null && !expose.deserialize();
        }

        @Override
        public boolean shouldSkipClass(Class<?> aClass) {
            return false;
        }
    }).create();

    public String toJson(Object object) {
        if (object == null) return null;
        String json = null;
        try {
            json = this.gson.toJson(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public <T> T fromJson(String json, Class<T> classOfT) {
        try {
            return this.gson.fromJson(json, classOfT);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T> T parseBodyJson(HttpServletRequest req, Class<T> classOfT) throws IOException {
        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        return this.fromJson(requestBody, classOfT);
    }
}
