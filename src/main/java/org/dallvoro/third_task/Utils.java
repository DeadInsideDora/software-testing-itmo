package org.dallvoro.third_task;

import java.util.List;

public class Utils {
    public static String join(List<?> objects) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < objects.size(); ++i) {
            if (i != 0) {
                sb.append(" и ");
            }
            sb.append(objects.get(i).toString());
        }
        return sb.toString();
    }
}
