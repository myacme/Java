package com.ljx.net.bean;


/**
 * @author ljx
 * @version 1.0.0
 * @create 2024/7/10 下午4:43
 */
public class Message {

    private String message;
    private String sender;

    public Message(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    @Override
    public String toString() {
        return sender + "：" + message;
    }
}
