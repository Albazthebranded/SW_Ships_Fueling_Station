package Ships;

public enum FuelTypes {
    HYPERMATTER(450000), RHYDONIUM(200), MALASTARIAN(250);

    public final int cost;

    FuelTypes(int cost) {
        this.cost = cost;
    }

    public int fuelCostPerLitre(FuelTypes type) {
        switch (type) {
            case RHYDONIUM -> {
                return RHYDONIUM.cost;
            }
            case HYPERMATTER -> {
                return HYPERMATTER.cost;
            }
            case MALASTARIAN -> {
                return MALASTARIAN.cost;
            }
        }
        return 0;
    }

    public int getFuelCost() {
        return this.cost;
    }
}
