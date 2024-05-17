package org.dallvoro.third_task;

import java.util.List;

public class Human {
    private final String type;
    private final String clothes;

    public Human(List<Clothes> clothes) {
        this("человек", clothes);
    }

    protected Human(String type, List<Clothes> clothes) {
        this.type = type;
        this.clothes = Utils.join(clothes);
    }

    public String breakInRoom(Place place) {
        return print("ворвался в %s", place.toString());
    }

    public String pushPeople(List<Human> humans) {
        return print("растолкал %s", Utils.join(humans));
    }

    public String blockPath(String how, String who) {
        return print("%s преградить %s путь", how, who);
    }

    public String sound(List<Sound> sounds) {
        return print("издает %s", Utils.join(sounds));
    }

    private String print(String format, Object... args) {
        String result = String.format(String.format("%s %s", this, format), args);
        System.out.println(result);
        return result;
    }

    @Override
    public String toString() {
        String result = type;
        if (!clothes.isEmpty()) {
            result = String.format("%s в %s", result, clothes);
        }
        return result;
    }
}
