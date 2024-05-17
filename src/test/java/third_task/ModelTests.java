package third_task;

import org.dallvoro.third_task.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ModelTests {
    private Clothes firstClothes;
    private Clothes secondClothes;
    private List<Human> people;
    private List<Sound> sounds;


    @BeforeAll
    void init() {
        firstClothes = new Clothes("синий", "балахон", "выцветший");
        secondClothes = new Clothes(null, "пояс Круксванского университета", null);

        sounds = new ArrayList<>();
        sounds.add(new Sound("шум"));
        sounds.add(new Sound("крик"));

        people = new ArrayList<>();
        people.add(new Human(List.of(firstClothes, secondClothes)));
        people.add(new Human(List.of(secondClothes)));

        people.add(new Footman(List.of(firstClothes)));
        people.add(new Footman(List.of(secondClothes)));
        people.add(new Footman(List.of()));
    }

    @Test
    void closedPlaceCannotClosed() {
        Place place = new Place(null, true);
        assertThrows(IllegalStateException.class, place::close);
    }

    @Test
    void openedPlaceCannotOpen() {
        Place place = new Place(null, false);
        assertThrows(IllegalStateException.class, place::open);
    }

    @Test
    void testOpenPlace() {
        Place place = new Place(null, true);
        assertTrue(place.isClosed());
        place.open();
        assertFalse(place.isClosed());
    }

    @Test
    void testClosePlace() {
        Place place = new Place(null, false);
        assertFalse(place.isClosed());
        place.close();
        assertTrue(place.isClosed());
    }

    @Test
    void testToStringPlace() {
        Place place = new Place("комната", false);
        assertEquals("открытая комната", place.toString());
        place.close();
        assertEquals("закрытая комната", place.toString());
    }

    @Test
    void testSoundInPlace() {
        Place place = new Place("комната университета", false);
        assertEquals("в открытая комната университета раздался шум и крик", place.soundInPlace(sounds));
    }

    @Test
    void testOnlyTypeInClothes() {
        assertEquals("пояс Круксванского университета", secondClothes.toString());
    }

    @Test
    void testColorAndTypeClothes() {
        assertEquals("красный носок", new Clothes("красный", "носок", null).toString());
    }

    @Test
    void testJoin() {
        assertEquals("выцветший синий балахон и пояс Круксванского университета", Utils.join(List.of(firstClothes, secondClothes)));
    }

    @Test
    void testFootmanBlockPath() {
        assertEquals("лакей тщетно пытавшийся преградить им путь", new Footman(List.of()).blockPath());
    }

    @Test
    void testHumanSound() {
        Human human = new Human(List.of());
        assertEquals("человек издает шум и крик", human.sound(sounds));
        assertEquals("человек издает шум", human.sound(List.of(sounds.get(0))));
        assertEquals("человек издает крик", human.sound(List.of(sounds.get(1))));
    }

    @Test
    void testFootmanSound() {
        Footman footman = new Footman(List.of());
        assertEquals("лакей издает шум и крик", footman.sound(sounds));
        assertEquals("лакей издает шум", footman.sound(List.of(sounds.get(0))));
        assertEquals("лакей издает крик", footman.sound(List.of(sounds.get(1))));
    }

    @Test
    void testHumanPush() {
        assertEquals("человек в выцветший синий балахон " +
                        "и пояс Круксванского университета растолкал человек в выцветший синий балахон " +
                        "и пояс Круксванского университета и человек в пояс Круксванского университета " +
                        "и лакей в выцветший синий балахон и лакей в пояс Круксванского университета и лакей",
                people.get(0).pushPeople(people));
        assertEquals("человек в выцветший синий балахон и пояс Круксванского университета растолкал человек " +
                "в пояс Круксванского университета и лакей в выцветший синий балахон", people.get(0).pushPeople(people.subList(1, 3)));
    }

    @Test
    void testBreakInRoom1() {
        assertEquals("человек в выцветший синий балахон и пояс Круксванского университета " +
                        "ворвался в открытая 1 комнатная квартира",
                people.get(0).breakInRoom(new Place("1 комнатная квартира", false)));
    }

    @Test
    void testBreakInRoom2() {
        assertEquals("лакей в пояс Круксванского университета ворвался в закрытая университет", people.get(3).breakInRoom(new Place("университет", true)));
    }
}
