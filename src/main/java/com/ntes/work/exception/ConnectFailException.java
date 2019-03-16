package com.ntes.work.exception;

public class ConnectFailException extends RuntimeException{
    public ConnectFailException() {
        super("连接失败!");
    }
}
