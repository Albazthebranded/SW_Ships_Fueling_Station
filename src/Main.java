import Client.Client;
import Ships.*;
import utils.D4C.D4C;
import utils.D4C.D4CCommands;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        D4C D4C = new D4C();
        String action;

        D4C.operate(D4CCommands.INTRO);
        D4C.operate(D4CCommands.CHECKACCESS);
        D4C.operate(D4CCommands.LEAVECHECK);

        while (true) {

            action = scan.nextLine();

            if (!action.equalsIgnoreCase("client") && !action.equalsIgnoreCase("employee")
                    && !action.equalsIgnoreCase("ISB") && !action.equalsIgnoreCase("leave")) {
                D4C.operate(D4CCommands.ERROR);
                continue;
            } else if (action.equalsIgnoreCase("leave")) {
                D4C.operate(D4CCommands.LEAVE);
            } else if (action.equalsIgnoreCase("client")) {
                D4C.operate(D4CCommands.CLIENT);
                Client client = new Client(D4C.getClientName(), D4C.getClientShipName(), D4C.getClientShipBrand());

                Ship ship = switch (client.getShipBrand()) {
                    case MONCALAMARICRUISER -> new MonCalamariCruiser(client.getShipName());
                    case NUBIAN -> new Nubian(client.getShipName());
                    case STARDESTROYER -> new StarDestroyer(client.getShipName());
                    case TIEFIGHTER -> new TIEFighter(client.getShipName());
                    case XWING -> new XWing(client.getShipName());
                };

                D4C.calculateFuelCost(ship);
                D4C.saveClientData(client, ship);
                D4C.operate(D4CCommands.PROCESSCLIENT);

            } else if (action.equalsIgnoreCase("employee")) {

                D4C.operate(D4CCommands.EMPLOYEE);
                D4C.operate(D4CCommands.CHECKPRICES);
                D4C.operate(D4CCommands.WISHPLEASANTWORK);

            } else if (action.equalsIgnoreCase("ISB")) {

                D4C.operate(D4CCommands.ISB);
                D4C.operate(D4CCommands.GRANTISBACCESSTOFILES);

            }
            System.exit(0);
        }
    }
}