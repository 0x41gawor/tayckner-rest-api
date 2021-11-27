package pl.gawor.tayckner.taycknerbackend.service.facade.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Color {
    public static boolean validate(String input) {
        if (input.length() > 7) return false;
        Pattern pattern = Pattern.compile("#([0-9a-f]{3}|[0-9a-f]{6}|[0-9a-f]{8})");
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
}
