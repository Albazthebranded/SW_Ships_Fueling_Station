package Ships;

public class TIEFighter extends Ship {

    public TIEFighter(String shipName) {
        this.shipName = shipName;
        this.shipType = ShipTypes.FIGHTER;
        this.fuelType = FuelTypes.RHYDONIUM;
        this.fuelTankSize = 2000;
        this.shipBrand = ShipBrand.TIEFIGHTER;
    }
}

