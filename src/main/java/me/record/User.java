package me.record;

import java.util.List;

public record User(String username, String password, List<String> roles, String[] data) {

}
