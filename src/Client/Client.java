package Client;

import Ships.*;

public class Client {

    private final String name;
    private final String shipName;
    private final ShipBrand shipBrand;

    public Client(String name, String shipName, ShipBrand shipBrand) {
        this.name = name;
        this.shipName = shipName;
        this.shipBrand = shipBrand;
    }

    public String getName() {
        return this.name;
    }

    public String getShipName() {
        return this.shipName;
    }

    public ShipBrand getShipBrand() {
        return this.shipBrand;
    }
}
