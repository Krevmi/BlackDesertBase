package by.krevm.blackdesertbase;

public class Dish {
    private IngredientFromParse ing1, ing2, ing3, ing4, ing5, res1, res2;

    public IngredientFromParse getIng1() {
        return ing1;
    }

    public IngredientFromParse getIng2() {
        return ing2;
    }

    public IngredientFromParse getIng3() {
        return ing3;
    }

    public IngredientFromParse getIng4() {
        return ing4;
    }

    public IngredientFromParse getIng5() {
        return ing5;
    }

    public IngredientFromParse getRes1() {
        return res1;
    }

    public IngredientFromParse getRes2() {
        return res2;
    }

    public int getAm1() {
        return am1;
    }

    public int getAm2() {
        return am2;
    }

    public int getAm3() {
        return am3;
    }

    public int getAm4() {
        return am4;
    }

    public int getAm5() {
        return am5;
    }

    private int am1, am2, am3, am4, am5;

    public Dish(IngredientFromParse ing1, IngredientFromParse ing2, IngredientFromParse ing3, IngredientFromParse ing4,
                IngredientFromParse ing5, IngredientFromParse res1, IngredientFromParse res2,
                int am1, int am2, int am3, int am4, int am5) {
        this.ing1 = ing1;
        this.ing2 = ing2;
        this.ing3 = ing3;
        this.ing4 = ing4;
        this.ing5 = ing5;
        this.res1 = res1;
        this.res2 = res2;
        this.am1 = am1;
        this.am2 = am2;
        this.am3 = am3;
        this.am4 = am4;
        this.am5 = am5;
    }
}
