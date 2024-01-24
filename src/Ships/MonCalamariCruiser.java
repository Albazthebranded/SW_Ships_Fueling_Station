package Ships;

public class MonCalamariCruiser extends Ship {

    public MonCalamariCruiser(String shipName) {
        this.shipName = shipName;
        this.shipType = ShipTypes.CAPITAL;
        this.fuelType = FuelTypes.HYPERMATTER;
        this.fuelTankSize = 50;
        this.shipBrand = ShipBrand.MONCALAMARICRUISER;
    }
}
