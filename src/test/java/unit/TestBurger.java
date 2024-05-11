package unit;

import org.junit.Test;
import org.mockito.Mockito;
import praktikum.Burger;
import praktikum.Ingredient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class TestBurger extends BaseBurgerTest {
    @Test
    public void testPrice () {
        var burger = buildBurger(0, 0, 1, 2);

        var expectedPrice = database.availableBuns().get(0).getPrice()*2
                + database.availableIngredients().get(0).getPrice()
                + database.availableIngredients().get(1).getPrice()
                + database.availableIngredients().get(2).getPrice();

        assertEquals(expectedPrice, burger.getPrice(), 0);

        burger.moveIngredient(1, 0);

        assertEquals(expectedPrice, burger.getPrice(), 0);

        burger.removeIngredient(0);

        assertEquals(expectedPrice-database.availableIngredients().get(1).getPrice(), burger.getPrice(), 0);
    }

    @Test
    public void testReceipt() {
        var burger = buildBurger(1, 2, 1, 0);

        assertTrue(burger.getReceipt().startsWith("(==== white bun ====)\n" +
                "= sauce chili sauce =\n" +
                "= sauce sour cream =\n" +
                "= sauce hot sauce =\n" +
                "(==== white bun ====)"));

        burger.moveIngredient(1, 0);
        assertTrue(burger.getReceipt().startsWith("(==== white bun ====)\n" +
                "= sauce sour cream =\n" +
                "= sauce chili sauce =\n" +
                "= sauce hot sauce =\n" +
                "(==== white bun ====)"));

        burger.removeIngredient(2);
        assertTrue(burger.getReceipt().startsWith("(==== white bun ====)\n" +
                "= sauce sour cream =\n" +
                "= sauce chili sauce =\n" +
                "(==== white bun ====)"));
    }

    @Test
    public void testNegativePrice() {
        var ingredient = Mockito.mock(Ingredient.class);
        when(ingredient.getPrice()).thenReturn(-200f);

        var burger = new Burger();

        burger.setBuns(database.availableBuns().get(0));
        burger.addIngredient(ingredient);

        assertEquals(0, burger.getPrice(), 0.0);
    }
}
