package org.dallvoro.third_task;

import java.util.Optional;
import java.util.function.Consumer;

public class Clothes {
    private final String clothesColor;
    private final String clothesType;
    private final String clothesQuality;

    public Clothes(String clothesColor, String clothesType, String clothesQuality) {
        this.clothesColor = clothesColor;
        this.clothesType = clothesType;
        this.clothesQuality = clothesQuality;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Optional.ofNullable(clothesQuality).ifPresent(str -> sb.append(str).append(" "));
        Optional.ofNullable(clothesColor).ifPresent(str -> sb.append(str).append(" "));
        Optional.ofNullable(clothesType).ifPresent(sb::append);
        return sb.toString();
    }
}
