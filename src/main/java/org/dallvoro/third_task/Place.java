package org.dallvoro.third_task;

import java.util.List;

public class Place {
    private final String placeName;
    private boolean closed;

    public Place(String placeName, boolean closed) {
        this.placeName = placeName;
        this.closed = closed;
    }

    public String soundInPlace(List<Sound> sound) {
        String start = "в " + this;
        String res = String.format("%s раздался %s", start, Utils.join(sound));
        System.out.println(res);
        return res;
    }

    public void open() {
        if (!closed) {
            throw new IllegalStateException("Дверь уже открыта");
        }
        closed = false;
    }

    public void close() {
        if (closed) {
            throw new IllegalStateException("Дверь уже закрыта");
        }
        closed = true;
    }

    public boolean isClosed() {
        return closed;
    }

    @Override
    public String toString() {
        return (closed ? "закрытая" : "открытая") + (placeName != null ? " " + placeName : "");
    }
}
