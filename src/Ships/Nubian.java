package Ships;

public class Nubian extends Ship {

    public Nubian(String shipName) {
        this.shipName = shipName;
        this.shipType = ShipTypes.TRANSPORT;
        fuelType = FuelTypes.MALASTARIAN;
        fuelTankSize = 20000;
        this.shipBrand = ShipBrand.NUBIAN;
    }
}
