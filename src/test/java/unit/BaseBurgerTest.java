package unit;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Database;
import praktikum.Ingredient;

public class BaseBurgerTest {
    protected static final Database database = new Database();

    public Burger buildBurger(Bun bun, List<Ingredient> ingredients) {
        var burger = new Burger();
        burger.setBuns(bun);
        ingredients.forEach(burger::addIngredient);
        return burger;
    }

    public Burger buildBurger(Bun bun, Integer... ingredientIndex) {
        var ingredients = Arrays.stream(ingredientIndex)
                .map(i -> database.availableIngredients().get(i))
                .collect(Collectors.toList());
        return buildBurger(bun, ingredients);
    }

    public Burger buildBurger(Integer bunIndex, Integer... ingredientIndex) {
        return buildBurger(database.availableBuns().get(bunIndex), ingredientIndex);
    }
}
