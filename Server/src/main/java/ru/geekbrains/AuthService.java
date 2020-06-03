package ru.geekbrains;

import java.util.List;

public class AuthService {

    private static class Entry {
        private String login;
        private String pass;
        private String nick;

        public Entry(String login, String pass, String nick) {
            this.login = login;
            this.pass = pass;
            this.nick = nick;
        }
    }

    private final List<Entry> entries = List.of(
            new Entry("login1", "pass1", "nick1"),
            new Entry("login2", "pass2", "nick2"),
            new Entry("login3", "pass3", "nick3")

    );


    public void start() {
        System.out.println("Сервис авторизации запущен");
    }

    public void stop() {
        System.out.println("Сервис авторизации остановлен");
    }


    public String getNickByLoginPass(String login, String pass) {
        for (Entry entry : entries){
            if (entry.login.equals(login) && entry.pass.equals(pass)){
                return entry.nick;
            }
        }
        return null;
    }
}