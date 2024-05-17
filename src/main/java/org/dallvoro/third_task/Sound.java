package org.dallvoro.third_task;

public class Sound {
    private final String soundName;

    public Sound(String soundName) {
        this.soundName = soundName;
    }

    @Override
    public String toString() {
        return soundName;
    }
}
