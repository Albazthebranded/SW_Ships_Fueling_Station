package Ships;

public class StarDestroyer extends Ship {

    public StarDestroyer(String shipName) {
        this.shipName = shipName;
        this.shipType = ShipTypes.CAPITAL;
        this.fuelType = FuelTypes.HYPERMATTER;
        this.fuelTankSize = 100;
        this.shipBrand = ShipBrand.STARDESTROYER;
    }
}
