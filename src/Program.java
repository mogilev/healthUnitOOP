import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class Program implements Serializable {

    private static final long serialVersionUID = 1L;

    public static void main(String[] args) {
        HealthUnit healthUnit = new HealthUnitClass();
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if(input.isBlank()) {
                scanner.close();
                System.exit(0);
            }
            String commands[] = input.split(" ");
            switch(commands[0]) {
                case "RP":
                    commandRP(healthUnit, commands);
                    break;
                case "RU":
                    commandRU(healthUnit, commands);
                    break;
                case "RF":
                    commandRF(healthUnit, commands);
                    break;
                case "AF":
                    commandAF(healthUnit, commands);
                    break;
                case "DF":
                    commandDF(healthUnit, commands);
                    break;
                case "LP":
                    commandLP(healthUnit);
                    break;
                case "LU":
                    commandLU(healthUnit);
                    break;
                case "LF":
                    commandLF(healthUnit);
                    break;
                case "MF":
                    commandMF(healthUnit, commands);
                    break;
                case "MC":
                    commandMC(healthUnit, scanner, commands);
                    break;
                case "CC":
                    commandCC(healthUnit, commands);
                    break;
                case "LCU":
                    commandLCU(healthUnit, commands);
                    break;
                case "LCF":
                    commandLCF(healthUnit, commands);
                    break;
                case "LSP":
                    commandLSP(healthUnit, commands);
                    break;
                case "LMS":
                    commandLMS(healthUnit, commands);
                    break;
                case "G":
                    commandG(healthUnit);
                    break;
                case "L":
                    commandL(healthUnit);
                    break;
                default:
                    System.out.println("Invalid instruction");
            }
        }
    }


    private static void commandRP(HealthUnit healthUnit, String[] commands) {
        String professionalCat = commands[1];
        String professionalName = commands[2];
        if(healthUnit.hasProfessional(professionalCat, professionalName)) {
            System.out.println("Professional already exists.");
        }
        else if (!healthUnit.categoryExists(professionalCat)){
            System.out.println("Non-existent category.");
        }
        else {
            healthUnit.createProfessional(professionalCat, professionalName);
            System.out.println("Professional successfully added.");
        }
    }

    private static void commandRU(HealthUnit healthUnit, String[] commands) {
        String clientName = commands[1];
        String clientAgeGroup = commands[2];
        if(healthUnit.hasClient(clientName)) {
            System.out.println("User already exists.");
        }
        else if(!healthUnit.ageGroupExists(clientAgeGroup)) {
            System.out.println("Non-existent age group");
        }
        else {
            healthUnit.createClient(clientName, clientAgeGroup);
            System.out.println("User successfully added.");
        }
    }

    private static void commandRF(HealthUnit healthUnit, String[] commands) {
        String familyName = commands[1];
        if(healthUnit.familyExists(familyName)) {
            System.out.println("Family already exists.");
        }
        else {
            healthUnit.createFamily(familyName);
            System.out.println("Family successfully added.");
        }
    }

    private static void commandAF(HealthUnit healthUnit, String[] commands) {
        String clientName = commands[1];
        String familyName = commands[2];
        if(!healthUnit.hasClient(clientName)) {
            System.out.println("Non-existent user.");
        }
        else if(!healthUnit.familyExists(familyName)) {
            System.out.println("Non-existent family.");
        }
        else if(healthUnit.hasFamily(clientName)) {
            System.out.println("User belongs to family");
        }
        else {
            healthUnit.joinFamily(clientName, familyName);
            System.out.println("User associated to family.");
        }
    }

    private static void commandDF(HealthUnit healthUnit, String[] commands) {
        String clientName = commands[1];
        if(healthUnit.hasClient(clientName) == false) {
            System.out.println("Non-existent user.");
        }
        else if(!healthUnit.hasFamily(clientName)) {
            System.out.println("User does not belong to a family");
        }
        else {
            healthUnit.leaveFamily(clientName);
            System.out.println("User removed from family");
        }
    }

    private static void commandLP(HealthUnit healthUnit) {
        if (healthUnit.getProfessionalList().isEmpty()) {
            System.out.println("No professionals registered");
        }
        else {
            healthUnit.listAllProfessionals();
        }
    }

    private static void commandLU(HealthUnit healthUnit) {
        if (healthUnit.getClientList().isEmpty()) {
            System.out.println("No users registered");
        }
        else {
            healthUnit.listAllClients();
        }
    }


    private static void commandLF(HealthUnit healthUnit) {
        if (healthUnit.getFamilyList().isEmpty()) {
            System.out.println("No families registered.");
        }
        else {
            healthUnit.listAllFamilies();
        }
    }

    private static void commandMF(HealthUnit healthUnit, String[] commands) {
        String familyName = commands[1];
        if(healthUnit.familyExists(familyName) == false) {
            System.out.println("Non-existent family.");
        }
        else {
            healthUnit.showFamilyMember(familyName);
        }
    }

    private static void commandMC(HealthUnit healthUnit, Scanner scanner, String[] commands) {
        String clientName = commands[1];
        if(!healthUnit.hasClient(clientName)) {
            System.out.println("Non-existent user.");
        }
        else {
            String[] parameters = scanner.nextLine().split(" ");
            String service = parameters[0];
            if (!healthUnit.serviceExists(service)) {
                System.out.println("Non-existent service.");
            }
            else {
                String[] parameters1 = scanner.nextLine().split(" ");
                String category = parameters1[0];
                String nomeProfissional = parameters1[1];
                if (!healthUnit.categoryExists(category)) {
                    System.out.println("Non-existent category.");
                }
                else if (!healthUnit.hasProfessional(category, nomeProfissional)) {
                    System.out.println("Non-existent professional.");
                }
                else {
                    if (!healthUnit.serviceRulesCheck(service, category)) {
                        System.out.println("Invalid category.");
                    }
                    else {
                        healthUnit.createAppointment(clientName, service, nomeProfissional, category);
                        System.out.println("Appointment successfully scheduled.");
                    }
                }
            }
        }
    }

    private static void commandCC(HealthUnit healthUnit, String[] commands) {
        String clientName = commands[1];
        if(healthUnit.hasClient(clientName) == false) {
            System.out.println("Non-existent user.");
        }
        else if(!healthUnit.clientHasAppointments(clientName)){
            System.out.println("User has no scheduled appointments."); }
        else{
            healthUnit.cancelAppointment(clientName);
            System.out.println("Appointments successfully canceled.");
        }
    }

    private static void commandLCU(HealthUnit healthUnit, String[] commands) {
        String clientName = commands[1];
        if(!healthUnit.hasClient(clientName)) {
            System.out.println("Non-existent user.");
        }
        else {
            if(!healthUnit.clientHasAppointments(clientName)) {
                System.out.println("User has no scheduled appointments.");
            }
            healthUnit.listClientAppointments(clientName);
        }
    }

    private static void commandLCF(HealthUnit healthUnit, String[] commands) {
        String familyName = commands[1];
        if(!healthUnit.familyExists(familyName)) {
            System.out.println("Non-existent family.");
        }
        else {
            if (!healthUnit.familyHasAppointments(familyName)) {
                System.out.println("Family has no scheduled appointments.");
            }
            healthUnit.listFamilyAppointments(familyName);
        }
    }


    private static void commandLSP(HealthUnit healthUnit, String[] commands) {
        String professionalCat = commands[1];
        String professionalName = commands[2];
        if(!healthUnit.hasProfessional(professionalCat, professionalName)) {
            System.out.println("Non-existent professional.");
        }
        else {
            if (!healthUnit.professionalHasAppointments(professionalCat, professionalName)) {
                System.out.println("Health professional has no scheduled appointments");
            }
            else {
                healthUnit.listProfessionalAppointments(professionalCat, professionalName);
            }
        }
    }

    private static void commandLMS(HealthUnit healthUnit, String[] commands) {
        String service = commands[1];
        if (!healthUnit.serviceExists(service)) {
            System.out.println("Non-existent service.");
        }
        else {
            if (!healthUnit.serviceHasAppointments(service)) {
                System.out.println("Service without appointments.");
            }
            else {
                healthUnit.listServiceAppointments(service);
            }
        }
    }

    private static void commandG(HealthUnit healthUnit) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("file.save");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(healthUnit);
            objectOutputStream.close();
            fileOutputStream.close();
            System.out.println("Health unit saved");
        } catch(IOException ioe) {
            ioe.printStackTrace();
            System.out.println("An error occurred while recording");
        }
    }

    private static void commandL(HealthUnit healthUnit) {
        try {
            FileInputStream fileInputStream = new FileInputStream("file.save");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            healthUnit = (HealthUnit)objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            System.out.println("Health unit loaded.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred while loading");
            return;
        }
    }


}	