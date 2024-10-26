package contacts;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contact {

    private String phone;
    private final LocalDateTime timeCreated;
    private LocalDateTime lastUpdated;

    public Contact() {
        timeCreated = LocalDateTime.now();
        lastUpdated = LocalDateTime.now();
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (isPhoneValid(phone)) {
            this.phone = phone;
        } else {
            System.out.println("Wrong number format!");
            this.phone = "[no number]";
        }

        setLastUpdated(LocalDateTime.now());
    }

    private boolean isPhoneValid(String phone) {
        Pattern pattern = Pattern
                .compile("(\\+?\\(?[a-zA-Z0-9]+\\)?)?([ -])?(\\(?[a-zA-Z0-9]{2,}\\)?([ -])?)*");

        Matcher matcher = pattern.matcher(phone);

        if (matcher.matches()) {
            int count = 0;
            int open = 0;
            int close = 0;
            int index = 0;
            for (char c : phone.toCharArray()) {
                if (c == '(') {
                    open = index;
                    count++;
                    if (count > 1)
                        return false;
                }
                if (count > 0 && c == ')') {
                    close = index;
                    if (phone.substring(open, close).contains(" ")) {
                        return false;
                    }
                }
                index++;
            }
            return true;
        } else {
            return false;
        }
    }
}