package unit;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Database;
import praktikum.Ingredient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class TestBurgerPrice extends BaseBurgerTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {database.availableBuns().get(0), List.of(
                        database.availableIngredients().get(0),
                        database.availableIngredients().get(1),
                        database.availableIngredients().get(2)
                ), 800},
                {database.availableBuns().get(1), List.of(
                        database.availableIngredients().get(2),
                        database.availableIngredients().get(1)
                ), 900},
                {database.availableBuns().get(1), List.of(
                        database.availableIngredients().get(1),
                        database.availableIngredients().get(2)
                ), 900},
                {database.availableBuns().get(2), List.of(
                        database.availableIngredients().get(0),
                        database.availableIngredients().get(2)
                ), 1000},
                {database.availableBuns().get(2), List.of(
                        database.availableIngredients().get(2),
                        database.availableIngredients().get(0)
                ), 1000}
        });
    }

    private final Bun bun;
    private final List<Ingredient> ingredients;
    private final float expected;

    public TestBurgerPrice(Bun bun, List<Ingredient> ingredients, float expected) {
        this.bun = bun;
        this.ingredients = ingredients;
        this.expected = expected;
    }

    @Test
    public void test () {
        assertEquals(expected, buildBurger(bun, ingredients).getPrice(), 0.0);
    }
}
