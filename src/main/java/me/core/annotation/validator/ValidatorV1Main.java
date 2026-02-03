package me.core.annotation.validator;

import static me.util.MyLogger.log;

public class ValidatorV1Main {
    public static void main(String[] args) {
        User user = new User("user", 0);
        Team team = new Team("", 1);

        try {
            log("== user 검증 ===");
            validateUser(user);
        } catch (Exception e) {
            log(e);
        }

        try {
            log("== team 검증 ===");
            validateTeam(team);
        } catch (Exception e) {
            log(e);
        }
    }

    private static void validateUser(User user) {
        if (user.getName() == null || user.getName().isEmpty()) {
            throw new RuntimeException("Empty name");
        }

        if (user.getAge() < 1 || user.getAge() > 100) {
            throw new RuntimeException("Invalid age");
        }
    }

    private static void validateTeam(Team team) {
        if (team.getName() == null || team.getName().isEmpty()) {
            throw new RuntimeException("Empty name");
        }

        if (team.getMemberCount() < 1 || team.getMemberCount() > 100) {
            throw new RuntimeException("Invalid age");
        }
    }
}
