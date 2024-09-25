package fruityvice;

public class Nutritions {
    private int calories;
    private float fat;
    private float sugar;
    private float carbohydrates;
    private float protein;

    public Nutritions(int calories, float fat, float sugar, float carbohydrates, float protein) {
        this.calories = calories;
        this.fat = fat;
        this.sugar = sugar;
        this.carbohydrates = carbohydrates;
        this.protein = protein;
    }

    public int getCalories() {
        return calories;
    }

    public float getFat() {
        return fat;
    }

    public float getSugar() {
        return sugar;
    }

    public float getCarbohydrates() {
        return carbohydrates;
    }

    public float getProtein() {
        return protein;
    }

}
