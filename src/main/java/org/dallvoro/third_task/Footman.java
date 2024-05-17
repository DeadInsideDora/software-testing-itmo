package org.dallvoro.third_task;

import java.util.List;

public class Footman extends Human {
    public Footman(List<Clothes> clothes) {
        super("лакей", clothes);
    }

    public String blockPath() {
        return super.blockPath("тщетно пытавшийся", "им");
    }
}
