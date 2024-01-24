package Ships;

public class XWing extends Ship {

    public XWing(String shipName) {
        this.shipName = shipName;
        this.shipType = ShipTypes.FIGHTER;
        this.fuelType = FuelTypes.RHYDONIUM;
        this.fuelTankSize = 4000;
        this.shipBrand = ShipBrand.XWING;
    }
}
