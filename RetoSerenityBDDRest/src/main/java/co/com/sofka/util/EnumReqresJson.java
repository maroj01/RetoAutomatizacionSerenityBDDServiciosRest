package co.com.sofka.util;

public enum EnumReqresJson {
    EMAIL("[email]"),
    PASSWORD("[password]");

    private final String value;

    EnumReqresJson(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
