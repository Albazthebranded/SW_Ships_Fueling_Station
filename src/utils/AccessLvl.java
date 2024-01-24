package utils;

public enum AccessLvl {
    CLIENT("0"), EMPLOYEE("PASSWORD"), ISB("o2@Ug*221H");

    private final String access;

    AccessLvl(String access) {
        this.access = access;
    }

    public String getStingValue() {
        return this.access;
    }
}
