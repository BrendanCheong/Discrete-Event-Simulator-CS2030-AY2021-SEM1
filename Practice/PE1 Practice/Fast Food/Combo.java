public class Combo extends Item {

    private final int[] comboValues;
    private final Menu menu;
    private final boolean isValid;

    public Combo(int[] comboValues, int index, Menu menu, boolean valid) {
        super("Combo", index, 0);
        this.comboValues = comboValues;
        this.menu = menu;
        this.isValid = valid;
    }

    @Override
    public boolean isEqual(String input) {
        return this.getName().equals(input);
    }

    public Combo validate() {
        // validate that the combo is correct
        if (this.menu.getMenu().size() < 3) {
            System.out.println(
                String.format("Invalid combo input %d %d %d",
                this.comboValues[0], this.comboValues[1], this.comboValues[2]));
            return new Combo(this.comboValues, super.getIndex(), this.menu, false);
        }
        boolean firstCombo = this.menu.getMenu().get(comboValues[0]).isEqual("Burger");
        boolean secondCombo = this.menu.getMenu().get(comboValues[1]).isEqual("Snack");
        boolean thirdCombo = this.menu.getMenu().get(comboValues[2]).isEqual("Drink");
        if (!firstCombo || !secondCombo || !thirdCombo) {
            System.out.println(
                String.format("Invalid combo input %d %d %d",
                comboValues[0], comboValues[1], comboValues[2]));
             return new Combo(this.comboValues, super.getIndex(), this.menu, false);
        }
        return new Combo(this.comboValues, super.getIndex(), this.menu, true);
    }

    public String getCombo() {
        String base = "";
        for (int comboIndex : this.comboValues) {
            base += this.menu.getMenu().get(comboIndex).toString() + "\n";
        }
        return base;
    }

    public int getTotal() {
        int total = 0;
        for (int comboIndex : this.comboValues) {
            total += this.menu.getMenu().get(comboIndex).getPrice();
        }
        return total;
    }

    @Override
    public String toString() {
        return String.format("#%d %s %d %d %d", super.getIndex(),
            super.getName(), this.comboValues[0], this.comboValues[1], this.comboValues[2]);
    }

}
