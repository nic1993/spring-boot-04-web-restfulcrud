package com.atguigu.springboot04webrestfulcrud.exception;

public class ConnectFailException extends RuntimeException{
    public ConnectFailException() {
        super("连接失败!");
    }
}
