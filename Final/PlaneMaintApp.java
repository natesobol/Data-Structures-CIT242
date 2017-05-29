// Programmer: Nate Sobol
// Title: CIT242Final

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Formatter;

public class PlaneMaintApp implements PlaneConstants {
    private static PlaneDAO PlaneDAO = null;
    private static Scanner sc = null;

    public static void main(String args[]) {
        System.out.println("Welcome to the Plane application\n");

        PlaneDAO = DAOFactory.getPlaneDAO();
        sc = new Scanner(System.in);

        displayMenu();

        String action = "";
        while (!action.equalsIgnoreCase("exit")) {
            action = Validator.getString(sc, "Enter a command: ");
            System.out.println();

            if (action.equalsIgnoreCase("list")) {
                displayAllPlanes();
            } else if (action.equalsIgnoreCase("add")) {
                addPlane();
            } else if (action.equalsIgnoreCase("del") || action.equalsIgnoreCase("delete")) {
                deletePlane();
            } else if (action.equalsIgnoreCase("help") || action.equalsIgnoreCase("menu")) {
                displayMenu();
            } else if (action.equalsIgnoreCase("exit")) {
                System.out.println("Bye!\n");
            } else {
                System.out.println("Error! Not a valid command.\n");
            }
        }
    }

    public static void displayMenu() {
        System.out.println("COMMAND MENU");
        System.out.println("list    - List all Planes");
        System.out.println("add     - Add a Plane");
        System.out.println("del     - Delete a Plane");
        System.out.println("help    - Show this menu");
        System.out.println("exit    - Exit this application\n");
    }

    public static void displayAllPlanes() {
        System.out.println("Here's the list of planes");
		System.out.println("---------------------------------------------------");
        ArrayList<Plane> Planes = PlaneDAO.getPlanes();
		System.out.println("Returned from  getPlanes...");
        if (Planes == null) {
            System.out.println("Error! Unable to get Planes.\n");
        } else {
            Plane p = null;
            StringBuilder sb = new StringBuilder();

            for (int k = 0; k < Planes.size(); k++) {
                p = Planes.get(k);

                sb.append(
						  "Company: " + StringUtils.padWithSpaces(p.getCompany(), SPEED_SIZE + 1) +
						  "Speed: " + StringUtils.padWithSpaces(p.getSpeed(), SPEED_SIZE + 1) +
						  "Model: " + StringUtils.padWithSpaces(p.getModel(), MODEL_SIZE + 1) +
						  "FuelCap: " + StringUtils.padWithSpaces(p.getFuelCap(), FUELCAP_SIZE + 5) +
						  "WingSpan: " + StringUtils.padWithSpaces(p.getWingSpan(), WINGSPAN_SIZE + 2) +
						  "PassCap: " + StringUtils.padWithSpaces(p.getPassCap(), PASSCAP_SIZE  = 2 + 2) +
						  "gender: " + p.getEngineCount() + "\n");
            }

            System.out.println(sb.toString());
        }
    }

    public static void addPlane() {
        int Company = Validator.getInt(sc, "Enter Plane Company: ", 0, 1000);
		String Company_str = String.format("%03d", Company);
		int Speed = Validator.getString(sc, "Enter Plane Speed: ", true);
        String Model = Validator.getLine(sc, "Enter Plane Model: ");
		int FuelCap = Validator.getLine(sc, "Enter Plane FuelCap: ");
        int WingSpan = Validator.getInt(sc, "Enter Plane Wing Span: ");
        int PassCap = Validator.getDouble(sc, "Enter Pass Cap: ");
        int EngineCount = Validator.getChar(sc, "Enter the Engine count of Plane: ");

		
        Plane Plane = new Plane();

        Plane.setCompany(Company_str);
        Plane.setSpeed(Speed);
        Plane.setModel(Model);
		Plane.setFuelCap(FuelCap);
        Plane.setWingSpan(WingSpan);
        Plane.setPassCap(PassCap);
        Plane.setEngineCount(EngineCount);
        boolean success = PlaneDAO.addPlane(Plane);

        System.out.println();
        if (success) {
            System.out.println(Model + " was added to the database.\n");
        } else {
            System.out.println("Error! Unable to add Plane\n");
        }
    }

    public static void deletePlane() {
        int Company = Validator.getInt(sc, "Enter Plane Company to delete: ", 0, 1000);
		String Company_str = String.format("%03d", Company);
        Plane p = PlaneDAO.getPlane(Company_str);

        System.out.println();
        if (p != null) {
            boolean success = PlaneDAO.deletePlane(p);
            if (success) {
                System.out.println(p.getModel() + " was deleted from the database.\n");
            } else {
                System.out.println("Error! Unable to add Plane\n");
            }
        } else {
            System.out.println("No Plane matches that Company.\n");
        }
    }
}
