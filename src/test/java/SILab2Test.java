import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SILab2Test {

    @Test
    void testEveryStatement() {
        RuntimeException ex;

        Item item1 = new Item("Chocolate", 15, 400, -1);
        Item item2 = new Item("Chocolate", 15, 400, 1);
        Item item3 = new Item("", 15, 400, -1);

        List<Item> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);
        items.add(item3);

        // 1
        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(null, null));
        assertTrue(ex.getMessage().contains("allItems list can't be null!"));

        // 2
        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(items, "0"));
        assertTrue(ex.getMessage().contains("Invalid item!"));

        // 4
        items.remove(item3);
        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(items, "123456789012345"));
        assertTrue(ex.getMessage().contains("Invalid card number!"));

        // 5
        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(items, "123456789012345a"));
        assertTrue(ex.getMessage().contains("Invalid character in card number!"));

        // 3
        assertEquals(5940.0,SILab2.checkCart(items, "1234567890123456"));
    }

    @Test
    void multiplecondition(){
        // (T || X || X)
        Item item = new Item("Chocolate", 1, 301,1);
        assertEquals(-30, SILab2.checkCart(List.of(item), "1234567890123456"));

        // (F || T || X)
        item = new Item("Chocolate", 1, 299, 1);
        assertEquals(-30, SILab2.checkCart(List.of(item), "1234567890123456"));

        // (F || F || T)
        item = new Item("Chocolate", 11, 299, 0);
        assertEquals(3259, SILab2.checkCart(List.of(item), "1234567890123456"));

        // (F || F || F)
        item = new Item("Chocolate", 9, 299, 0);
        assertEquals(2691, SILab2.checkCart(List.of(item), "1234567890123456"));
    }
}