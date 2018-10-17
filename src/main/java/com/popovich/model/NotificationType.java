package main.java.com.popovich.model;

public enum NotificationType {
    MAIL(), HTTP();

    private String extra_params;

    NotificationType(String extra_params) {
        this.extra_params = extra_params;
    }

    public String getExtra_params() {
        return extra_params;
    }
}
