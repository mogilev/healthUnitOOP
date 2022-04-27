import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HealthUnitClass implements HealthUnit, Serializable {

    private static final long serialVersionUID = 1L;
    private List<Client> clientList;
    private List<HealthProfessional> healthProfessionalList;
    private List<Appointment> appointmentList;
    private List<Family> familyList;


    public HealthUnitClass() {
        this.clientList = new ArrayList<Client>();
        this.healthProfessionalList = new ArrayList<HealthProfessional>();
        this.appointmentList = new ArrayList<Appointment>();
        this.familyList = new ArrayList<Family>();
    }

    @Override
    public boolean hasProfessional(String professionalCat, String professionalName) {
        for(HealthProfessional healthProfessional : this.getProfessionalList()) {
            if(healthProfessional.getName().equals(professionalName) && healthProfessional.getCategoryName().contentEquals(professionalCat)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean categoryExists(String professionalCat) {
        for (Category category : Category.values()) {
            if (category.name().equals(professionalCat)) {
                return true;
            }	}
        return false;}

    public int createProfessional(String professionalCat, String professionalName) {
        Category category = Category.valueOf(professionalCat);
        HealthProfessional healthProfessional = (HealthProfessional) new HealthProfessionalClass(category, professionalName);
        healthProfessionalList.add((HealthProfessional) healthProfessional);
        return 0;
    }

    @Override
    public boolean hasClient(String clientName) {
        for(Client client : this.getClientList()) {
            if(client.getName().contentEquals(clientName)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean ageGroupExists(String clientAgeGroup) {
        for (AgeGroup ageGroup : AgeGroup.values()) {
            if (ageGroup.name().equals(clientAgeGroup)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean familyExists(String familyName) {
        for(Family family : this.getFamilyList()) {
            if(family.getFamilyName().contentEquals(familyName)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasFamily(String clientName) {
        for(Client client : this.getClientList()) {
            if(client.getName().contentEquals(clientName)){
                if (client.getFamily() != null){
                    return true;
                }
            }
        }
        return false;
    }

    public int joinFamily(String clientName, String familyName) {
        for(Client client : this.getClientList()) {
            if (client.getName().contentEquals(clientName)){
                for (Family family : this.getFamilyList()){
                    if (family.getFamilyName().contentEquals(familyName)){
                        client.setFamily(family);
                    }
                }
            }
        }
        return 0;
    }

    public int leaveFamily(String clientName){
        for(Client client : this.getClientList()) {
            if (client.getName().contentEquals(clientName)){
                client.setFamily(null);
            }
        }
        return 0;
    }

    @Override
    public int createClient(String clientName, String clientAgeGroup) {
        AgeGroup ageGroup = AgeGroup.valueOf(clientAgeGroup);
        Client client = (Client) new ClientClass(clientName, ageGroup);
        clientList.add((Client) client);
        return 0;
    }

    @Override
    public int createFamily(String familyName) {
        Family family = (Family) new FamilyClass(familyName);
        familyList.add(family);
        return 0;
    }

    @Override
    public List<HealthProfessional> getProfessionalList() {
        return this.healthProfessionalList;
    }

    @Override
    public List<Client> getClientList() {
        return this.clientList;
    }

    @Override
    public List<Appointment> getAppointmentList() {
        return this.appointmentList;
    }

    @Override
    public List<Family> getFamilyList() {
        return this.familyList;
    }

    @Override
    public boolean serviceExists(String serviceName) {
        for (Service service : Service.values()) {
            if (service.name().contentEquals(serviceName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean clientHasAppointments(String clientName) {
        for(Appointment appointment : this.getAppointmentList()) {
            if(appointment.getClientName().contentEquals(clientName)){
                return true;
            }
        }
        return false;
    }

    @Override
    public int createAppointment(String clientName, String serviceName, String healthProfessionalName, String healthProfessionalCategory ) {
        for (Client client : this.clientList) {
            if (client.getName().contentEquals(clientName)) {
                for (HealthProfessional healthProfessional : this.healthProfessionalList) {
                    if(healthProfessional.getName().contentEquals(healthProfessionalName) && healthProfessional.getCategoryName().contentEquals(healthProfessionalCategory)) {
                        Service service = Service.valueOf(serviceName);
                        Appointment appointment = (Appointment) new AppointmentClass(client, service, healthProfessional);
                        appointmentList.add((Appointment) appointment);
                    }
                }
            }
        }
        return 0;
    }

    @Override
    public int cancelAppointment(String clientName) {
        List<Appointment> toRemoveList = new ArrayList<Appointment>();
        for (Appointment appointment : this.getAppointmentList()) {
            if (appointment.getClientName().contentEquals(clientName))
                toRemoveList.add(appointment);
        }
        appointmentList.removeAll(toRemoveList);
        return 0;
    }

    @Override
    public void listAllFamilies() {
        Collections.sort(familyList,
                new Comparator<Family>(){
                    public int compare(Family f1, Family f2) {
                        return f1.getFamilyName().compareTo(f2.getFamilyName());
                    }
                });
        this.getFamilyList().forEach((family) -> {
            System.out.println(family.getFamilyName());
        });
    }

    @Override
    public void showFamilyMember(String familyName) {
        List<Client> clients = new ArrayList<Client>();
        for (Family family : this.getFamilyList()){
            if (family.getFamilyName().contentEquals(familyName)){
                for (Client client : this.getClientList()){
                    if (client.getFamily() == family){
                        clients.add(client);
                    }
                }
            }
        }
        Collections.sort(clients,
                new Comparator<Client>() {
                    public int compare(Client c1, Client c2) {
                        if(c1.getAgeGroup().equals(c2.getAgeGroup())){
                            return c1.getName().compareTo(c2.getName());
                        }
                        else {
                            return c1.getAgeGroup().compareTo(c2.getAgeGroup());
                        }
                    }
                });
        if (clients.isEmpty()) {
        }
        else {
            for (Client client : clients){
                System.out.println(client.getAgeGroupName() + " " + client.getName());
            }
        }
    }

    @Override
    public void listAllClients() {
        Collections.sort(clientList,
                new Comparator<Client>() {
                    public int compare(Client c1, Client c2) {
                        int NameCompare = c1.getName().compareTo(c2.getName());
                        int AgeCompare = c1.getAgeGroup().compareTo(c2.getAgeGroup());
                        if (AgeCompare == 0) {
                            return ((NameCompare == 0) ? AgeCompare : NameCompare);
                        } else {
                            return AgeCompare;
                        }
                    }
                });
        for (Client client :  this.getClientList()){
            String nome = client.getName();
            if (this.hasFamily(nome)){
                System.out.println(client.getFamilyName() + " " + client.getAgeGroup() + " " + client.getName());
            }
            else{
                System.out.println(client.getAgeGroup() + " " + client.getName());
            }
        }
    }


    public void listAllProfessionals() {
        Collections.sort(this.healthProfessionalList,
                new Comparator<HealthProfessional>() {
                    public int compare(HealthProfessional h1, HealthProfessional h2) {
                        int NameCompare = h1.getName().compareTo(h2.getName());
                        int CatCompare = h1.getCategory().compareTo(h2.getCategory());
                        if (CatCompare == 0) {
                            return ((NameCompare == 0) ? CatCompare : NameCompare);
                        } else {
                            return CatCompare;
                        }
                    }
                });
        for (HealthProfessional healthprofessional :  this.getProfessionalList()){
            System.out.println(healthprofessional.getCategoryName() + " " + healthprofessional.getName());
        }
    }


    @Override
    public boolean serviceRulesCheck(String service, String category) {
        if (service.contentEquals("Consulta")&& category.contentEquals("Medicina")){
            return true;
        }
        else if (service.contentEquals("PequenaCirurgia")) {
            if (category.contentEquals("Medicina") || category.contentEquals("Enfermagem") || category.contentEquals("Auxiliar")) {
                return true;
            }
        }
        else if (service.contentEquals("Enfermagem")) {
            if (category.contentEquals("Enfermagem") || category.contentEquals("Auxiliar")) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void listClientAppointments(String clientName) {
        List<Appointment> appointmentsList = new ArrayList<Appointment>();
        for (Appointment appointment :  this.getAppointmentList()) {
            if (appointment.getClientName().contentEquals(clientName)) {
                appointmentsList.add(appointment);
            }
        }
        Collections.sort(appointmentsList,
                new Comparator<Appointment>() {
                    public int compare(Appointment a1, Appointment a2) {
                        int NameCompare = a1.getClientName().compareTo(a2.getClientName());
                        int ServCompare = a1.getService().compareTo(a2.getService());
                        if (ServCompare == 0) {
                            return ((NameCompare == 0) ? ServCompare : NameCompare);
                        } else {
                            return ServCompare;
                        }
                    }
                });
        if (appointmentsList.isEmpty()) {
        }
        else {
            for (Appointment appointment : appointmentsList){
                System.out.println(appointment.getService().toString() + " " + appointment.getHealthProfessional().getCategoryName() + " " + appointment.getHealthProfessional().getName());
            }
        }
    }

    @Override
    public boolean familyHasAppointments(String familyName) {
        for(Appointment appointment : this.getAppointmentList()) {
            if(appointment.getClient().getFamilyName().contentEquals(familyName)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void listFamilyAppointments(String familyName) {
        List<Appointment> appointmentsList = new ArrayList<Appointment>();
        for (Appointment appointment :  this.getAppointmentList()) {
            if (appointment.getClient().getFamilyName().contentEquals(familyName)) {
                appointmentsList.add(appointment);
            }
        }
        Collections.sort(appointmentsList,
                new Comparator<Appointment>() {
                    public int compare(Appointment a1, Appointment a2) {
                        int NameCompare = a1.getClientName().compareTo(a2.getClientName());
                        int ServCompare = a1.getService().compareTo(a2.getService());
                        if (ServCompare == 0) {
                            return ((NameCompare == 0) ? ServCompare : NameCompare);
                        } else {
                            return ServCompare;
                        }
                    }
                });
        if (appointmentsList.isEmpty()) {
        }
        else {
            for (Appointment appointment : appointmentsList){
                System.out.println(appointment.getService().toString() + " " + appointment.getHealthProfessional().getCategoryName() + " " + appointment.getHealthProfessional().getName());
            }
        }
    }

    @Override
    public boolean professionalHasAppointments(String professionalCat, String professionalName) {
        for(Appointment appointment : this.getAppointmentList()) {
            if(appointment.getHealthProfessional().getName().contentEquals(professionalName) && appointment.getHealthProfessional().getCategoryName().contentEquals(professionalCat)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void listProfessionalAppointments(String professionalCat, String professionalName) {
        List<Appointment> appointmentsList = new ArrayList<Appointment>();
        for (Appointment appointment :  this.getAppointmentList()) {
            if (appointment.getHealthProfessional().getName().contentEquals(professionalName) && appointment.getHealthProfessional().getCategoryName().contentEquals(professionalCat)) {
                appointmentsList.add(appointment);
            }
        }
        Collections.sort(appointmentsList,
                new Comparator<Appointment>() {
                    public int compare(Appointment a1, Appointment a2) {
                        int NameCompare = a1.getClientName().compareTo(a2.getClientName());
                        int ServCompare = a1.getService().compareTo(a2.getService());
                        if (ServCompare == 0) {
                            return ((NameCompare == 0) ? ServCompare : NameCompare);
                        } else {
                            return ServCompare;
                        }
                    }
                });
        if (appointmentsList.isEmpty()) {
        }
        else {
            for (Appointment appointment : appointmentsList){
                System.out.println(appointment.getService().toString() + " " + appointment.getClientName());
            }
        }
    }

    @Override
    public boolean serviceHasAppointments(String service) {
        for (Appointment appointment :  this.getAppointmentList()) {
            if (appointment.getService().toString().contentEquals(service)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void listServiceAppointments(String service) {
        List<Appointment> appointmentsList = new ArrayList<Appointment>();
        for (Appointment appointment :  this.getAppointmentList()) {
            if (appointment.getService().toString().contentEquals(service)) {
                appointmentsList.add(appointment);
            }
        }
        Collections.sort(appointmentsList,
                new Comparator<Appointment>() {
                    public int compare(Appointment a1, Appointment a2) {
                        int NameCompare = a1.getHealthProfessional().getName().compareTo(a2.getHealthProfessional().getName());
                        int CatCompare = a1.getHealthProfessional().getCategory().compareTo(a2.getHealthProfessional().getCategory());
                        if (CatCompare == 0) {
                            return ((NameCompare == 0) ? CatCompare : NameCompare);
                        } else {
                            return CatCompare;
                        }
                    }
                });
        if (appointmentsList.isEmpty()) {
        }
        else {
            for (Appointment appointment : appointmentsList){
                System.out.println(appointment.getHealthProfessional().getCategoryName() + " " + appointment.getHealthProfessional().getName() + " " + appointment.getClientName());
            }
        }
    }


}