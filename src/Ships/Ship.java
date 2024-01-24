package Ships;

public abstract class Ship {

    protected String shipName;
    protected ShipTypes shipType;
    protected FuelTypes fuelType;
    protected ShipBrand shipBrand;
    protected int fuelTankSize;

    public Ship() {
    }

    public int calculateFuelingCost() {
        return this.fuelTankSize * this.fuelType.fuelCostPerLitre(fuelType);
    }

    public String getName() {
        return this.shipName;
    }

    public ShipTypes getShipType() {
        return this.shipType;
    }

    public ShipBrand getShipBrand() {
        return this.shipBrand;
    }
}
