package unit;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Ingredient;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class TestBurgerReceipt extends BaseBurgerTest {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {database.availableBuns().get(0), List.of(
                        database.availableIngredients().get(0),
                        database.availableIngredients().get(1),
                        database.availableIngredients().get(2)
                ), "(==== black bun ====)\n" +
                    "= sauce hot sauce =\n" +
                    "= sauce sour cream =\n" +
                    "= sauce chili sauce =\n" +
                    "(==== black bun ====)"},
                {database.availableBuns().get(1), List.of(
                        database.availableIngredients().get(2),
                        database.availableIngredients().get(1)
                ), "(==== white bun ====)\n" +
                    "= sauce chili sauce =\n" +
                    "= sauce sour cream =\n" +
                    "(==== white bun ====)"},
                {database.availableBuns().get(1), List.of(
                        database.availableIngredients().get(1),
                        database.availableIngredients().get(2)
                ), "(==== white bun ====)\n" +
                    "= sauce sour cream =\n" +
                    "= sauce chili sauce =\n" +
                    "(==== white bun ====)"},
                {database.availableBuns().get(2), List.of(
                        database.availableIngredients().get(0),
                        database.availableIngredients().get(2)
                ), "(==== red bun ====)\n" +
                    "= sauce hot sauce =\n" +
                    "= sauce chili sauce =\n" +
                    "(==== red bun ====)"},
                {database.availableBuns().get(2), List.of(
                        database.availableIngredients().get(2),
                        database.availableIngredients().get(0)
                ), "(==== red bun ====)\n" +
                    "= sauce chili sauce =\n" +
                    "= sauce hot sauce =\n" +
                    "(==== red bun ====)"}
        });
    }

    private final Bun bun;
    private final List<Ingredient> ingredients;
    private final String expected;

    public TestBurgerReceipt(Bun bun, List<Ingredient> ingredients, String expected) {
        this.bun = bun;
        this.ingredients = ingredients;
        this.expected = expected;
    }

    @Test
    public void testReceipt() {
        assertTrue(buildBurger(bun, ingredients).getReceipt().startsWith(expected));
    }
}
