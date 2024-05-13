package unit;

import org.junit.Test;
import org.mockito.Mockito;
import praktikum.Burger;
import praktikum.Ingredient;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
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

        assertThat(burger.getReceipt(), containsString(Stream.of(
                "(==== white bun ====)",
                "= sauce chili sauce =",
                "= sauce sour cream =",
                "= sauce hot sauce =",
                "(==== white bun ====)").collect(
                StringBuilder::new,
                (acc, str) -> acc.append(String.format("%s%n", str)),
                StringBuilder::append
        ).toString()));

        burger.moveIngredient(1, 0);
        assertThat(burger.getReceipt(), containsString(Stream.of(
                "(==== white bun ====)",
                "= sauce sour cream =",
                "= sauce chili sauce =",
                "= sauce hot sauce =",
                "(==== white bun ====)").collect(
                StringBuilder::new,
                (acc, str) -> acc.append(String.format("%s%n", str)),
                StringBuilder::append
        ).toString()));

        burger.removeIngredient(2);
        assertThat(burger.getReceipt(), containsString(Stream.of(
                "(==== white bun ====)",
                "= sauce sour cream =",
                "= sauce chili sauce =",
                "(==== white bun ====)").collect(
                StringBuilder::new,
                (acc, str) -> acc.append(String.format("%s%n", str)),
                StringBuilder::append
        ).toString()));
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
