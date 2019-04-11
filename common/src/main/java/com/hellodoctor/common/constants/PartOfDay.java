package com.hellodoctor.common.constants;

/**
 * @author Khoa
 * @created 4/10/2019
 */
public enum PartOfDay {
    MORNING("MORNING", 8, 12),
    AFTERNOON("AFTERNOON", 13, 17),
    EVENING("EVENING", 18, 22);

    String name;
    int startHour;
    int endHour;


    PartOfDay(String name, int startHour, int endHour) {
        this.name = name;
        this.startHour = startHour;
        this.endHour = endHour;
    }

    public static PartOfDay getPartOfDayByString(String value) {
        for (PartOfDay s : PartOfDay.values()) {
            if (s.name == value) {
                return s;
            }
        }
        return MORNING;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }
}
