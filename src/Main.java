import Client.Client;
import Ships.*;
import utils.D4C.D4C;
import utils.D4C.D4CCommands;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        D4C D4C = new D4C();
        String clientAction;

        D4C.operate(D4CCommands.INTRO);
        D4C.operate(D4CCommands.CHECKACCESS);
        D4C.operate(D4CCommands.LEAVECHECK);

        while (true) {

            clientAction = scan.nextLine();

            serveClient(clientAction);

            if (!clientAction.equalsIgnoreCase("client") && !clientAction.equalsIgnoreCase("employee")
                    && !clientAction.equalsIgnoreCase("ISB") && !clientAction.equalsIgnoreCase("leave")) {
                continue;
            }

            System.exit(0);
        }
    }

    public static void serveClient(String clientAction) throws InterruptedException {
        D4C D4C = new D4C();

        switch (clientAction.toUpperCase()) {
            case ("LEAVE") -> D4C.operate(D4CCommands.LEAVE);
            case ("CLIENT") -> {
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
            }
            case ("EMPLOYEE") -> {
                D4C.operate(D4CCommands.EMPLOYEE);
                D4C.operate(D4CCommands.CHECKPRICES);
                D4C.operate(D4CCommands.WISHPLEASANTWORK);
            }
            case ("ISB") -> {
                D4C.operate(D4CCommands.ISB);
                D4C.operate(D4CCommands.GRANTISBACCESSTOFILES);

            }
            default -> {
                D4C.operate(D4CCommands.ERROR);
            }
        }
    }
}