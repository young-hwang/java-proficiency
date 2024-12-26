package me.web.httpserver.servlet.annotation;

import me.web.httpserver.HttpRequest;
import me.web.httpserver.HttpResponse;
import me.web.httpserver.HttpServlet;
import me.web.httpserver.PageNotFoundException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class AnnotationServletV1 implements HttpServlet {
    private final List<Object> controllers;

    public AnnotationServletV1(List<Object> controllers) {
        this.controllers = controllers;
    }

    @Override
    public void service(HttpRequest request, HttpResponse response) {
        for (Object controller : controllers) {
            Method[] methods = controller.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(Mapping.class)) {
                    String value = method.getAnnotation(Mapping.class).value();
                    if (value.equals(request.getPath())) {
                        invoke(controller, method, request, response);
                        return;
                    }
                }
            }
        }
        throw new PageNotFoundException("request = " + request.getPath() + " not found");
    }

    private static void invoke(Object controller, Method method, HttpRequest request, HttpResponse response) {
        try {
            method.invoke(controller, request, response);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
