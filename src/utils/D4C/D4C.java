package utils.D4C;

import Client.Client;
import Ships.*;

import java.io.*;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

import static utils.AccessLvl.EMPLOYEE;
import static utils.AccessLvl.ISB;

public class D4C {

    public void operate(D4CCommands command) throws InterruptedException {
        switch (command) {
            case INTRO -> {
                System.out.println("Welcome to our humble Starship fueling Station!");
                lag(2000);
                System.out.println("I am D4-C, Client Relations protocol droid, at your service.");
                lag(2000);
            }
            case CHECKACCESS -> {
                System.out.println("If I may inquire, are you a client or an employee?");
                lag(2000);
            }
            case LEAVECHECK -> {
                System.out.println("To leave our station, please type \"leave\".");
                lag(1000);
            }
            case ERROR -> {
                System.out.println("Can you repeat that, please?");
                lag(1000);
            }
            case CHECKPRICES -> {
                System.out.printf("The fuel prices are as follows: \nHypermatter: %d Galactic credits per litre \nRhydonium: %d Galactic credits per litre \nMalastarian: %d Galactic credits per litre",
                        FuelTypes.HYPERMATTER.getFuelCost(), FuelTypes.RHYDONIUM.getFuelCost(), FuelTypes.MALASTARIAN.getFuelCost());
                lag(1000);
            }
            case PROCESSCLIENT -> {
                if (askClientToPay()) {
                    System.out.println("Thank you for choosing our fueling station! Please visit us again!");
                    System.exit(0);
                } else {
                    System.out.println("Please exit the premises. There are other clients waiting in line.");
                    System.exit(0);
                }
            }
            case LEAVE -> {
                System.out.println("Thank you for considering our fueling station!");
                System.exit(0);
            }
            case CLIENT -> {
                System.out.println("Greetings, valued customer!");
                lag(1000);
            }
            case EMPLOYEE -> {
                System.out.println("Greetings, sir!");
                lag(1000);
                askEmployeeForPassword();
            }
            case WISHPLEASANTWORK -> {
                lag(1000);
                System.out.println("\nI wish you a pleasant work day!");
                System.exit(0);
            }
            case ISB -> {
                System.out.println("Greetings, officer.");
                lag(1000);
                askISBForPassword();
            }
            case GRANTISBACCESSTOFILES -> {
                System.out.println("As per protocol, I am granting you access to all customers' files for your perusal." +
                        "\nI hope our information proves useful to the Empire. Maker knows I don't want to be deactivated.");
                lag(1000);
                grantISBData();
            }
        }
    }

    private void lag(int time) throws InterruptedException {
        Thread.sleep(time);
    }

    public String getClientName() {
        Scanner scan = new Scanner(System.in);
        System.out.println("May I have your name, please?");
        String clientName = scan.nextLine();
        return clientName;
    }

    public ShipBrand getClientShipBrand() throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        int shipType;

        while (true) {
            System.out.println("What kind of ship do you want to fuel today? Press 1 for Mon Calamari Cruiser," +
                    "\n2 for Star Destroyer, 3 for Nubian, 4 for TIE Fighter and 5 for X-Wing");
            try {
                shipType = Integer.parseInt(scan.nextLine());

                if (shipType != 1 && shipType != 2 && shipType != 3 && shipType != 4 && shipType != 5) {
                    operate(D4CCommands.ERROR);
                    continue;
                }

                switch (shipType) {
                    case 1 -> {
                        return ShipBrand.MONCALAMARICRUISER;
                    }
                    case 2 -> {
                        return ShipBrand.STARDESTROYER;
                    }
                    case 3 -> {
                        return ShipBrand.NUBIAN;
                    }
                    case 4 -> {
                        return ShipBrand.TIEFIGHTER;
                    }
                    case 5 -> {
                        return ShipBrand.XWING;
                    }
                }
            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println("Wrong input, try again please.");
            }
        }
    }

    public String getClientShipName() {
        Scanner scan = new Scanner(System.in);
        System.out.println("And what is the name of your vessel?");
        String shipName = scan.nextLine();
        return shipName;
    }

    public void calculateFuelCost(Ship ship) {
        ship.calculateFuelingCost();
        System.out.printf("The cost of refueling your ship, %s, a %s %s, would be %d Galactic Credits.", ship.getName(), ship.getShipBrand(), ship.getShipType(), ship.calculateFuelingCost());
    }

    public void saveClientData(Client client, Ship ship) {
        try (FileWriter writer = new FileWriter("clientData.txt", true)) {
            String[] clientData = {client.getName(), ship.getName(), String.valueOf(ship.getShipType()), String.valueOf(ship.calculateFuelingCost())};
            writer.write(Arrays.toString(clientData));
            writer.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean askClientToPay() throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        System.out.println("\nWould you like to refuel at that cost?");
        lag(1000);
        System.out.println("If yes, please type \"yes\". Otherwise you will be asked to leave. There are other customers.");
        String action = scan.nextLine();
        return action.equalsIgnoreCase("yes");
    }

    private void askEmployeeForPassword() throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        boolean isPWCorrect = false;

        while (!isPWCorrect) {
            System.out.println("May I please have the password so I can verify your identity?");
            String password = scan.nextLine();
            if (password.equals(EMPLOYEE.getStingValue())) {
                isPWCorrect = true;
            } else {
                System.out.println("Wrong password!");
                lag(2000);
            }
            System.out.println("Welcome, sir!");
            lag(1000);
        }
    }

    private void askISBForPassword() throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        boolean isPWCorrect = false;

        while (!isPWCorrect) {
            System.out.println("WARNING: Impersonating an ISB Officer is a capital offense according to Imperial Law!\nPlease enter your ISB password to verify your identity.");
            String password = scan.nextLine();
            if (password.equals(ISB.getStingValue())) {
                isPWCorrect = true;
            } else {
                System.out.println("\u001B[31mWRONG PASSWORD !!! WARNING: IMPERIAL AUTHORITIES ARE ON THEIR WAY!\u001B[0m");
                System.exit(0);
            }
            System.out.println("Welcome, Officer!");
            lag(1000);
        }
    }

    private void grantISBData() {
        try (FileReader reader = new FileReader("clientData.txt")) {
            int data = reader.read();
            while (data != -1) {
                System.out.print((char) data);
                data = reader.read();
            }
            reader.close();
            System.out.println("\nHave a pleasant day!");
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
