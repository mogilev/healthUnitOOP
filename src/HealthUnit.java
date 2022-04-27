import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public interface HealthUnit {

    boolean hasProfessional(String professionalCat, String professionalName); // Verifica se já existe profissional com determinado nome e categoria profissional

    boolean categoryExists(String professionalCat); // Verifica se existe determinada categoria profissional

    int createProfessional(String professionalCat, String professionalName); // Adiciona novo profissional

    int createClient(String clientName, String clientAgeGroup); // Adiciona novo utente

    int createFamily(String familyName); // Adiciona nova família

    boolean hasClient(String clientName); // Verifica se já existe esse cliente pelo seu nome

    boolean ageGroupExists(String clientAgeGroup); // Verifica se existe determinada faixa etária

    boolean familyExists(String familyName); // Verifica se existe determinada família

    boolean hasFamily(String clientName); // Verifica se determinado cliente tem família associada

    int joinFamily(String clientName, String familyName); // Associa um utente a uma família

    int leaveFamily(String clientName); // Retira a associação de um utente a família

    boolean serviceExists(String serviceName); // Verifica se existe determinado serviço médico

    boolean clientHasAppointments (String clientName); // Verifica se determinado cliente tem cuidados de saúde marcados

    boolean familyHasAppointments (String clientName); // Verifica se determinada família tem cuidados de saúde marcados

    boolean professionalHasAppointments (String professionalCat, String professionalName); // Verifica se determinado profissional tem cuidados de saúde marcados

    boolean serviceHasAppointments (String service); // Verifica se determinado tipo de serviço tem cuidados agendados

    boolean serviceRulesCheck(String service, String category); // Confirma se determinado cuidado de saúde pode ser prestado pela categoria profissional indicada, de acordo com as regras impostas na tabela 4

    int createAppointment(String clientName,String serviceName,String healthProfessionalName, String healthProfessionalCategory ); // Marca um cuidado de saúde a realizar

    int cancelAppointment(String clientName); // Cancela todos os cuidados de saúde agendados para determinado utente

    void listAllFamilies(); // Devolve uma listagem de todas as famílias existentes, por ordem alfabética

    void listAllClients(); // Devolve uma listagem de todos os utentes existentes, por ordem alfab+etica dentro de cada família

    void listAllProfessionals(); // Devolve uma listagem de todos os profissionais, por ordem alfabética dentro de cada categoria 

    void listClientAppointments(String clientName); // Devolve uma listagem dos cuidados de saude marcados a um determinado utente, por ordem de serviço e categoria

    void listFamilyAppointments(String familyName); // Devolve listagem dos cuidados de saúde marcados a utentes que pertencem a determinada família, por ordem de serviço e categoria

    void listProfessionalAppointments(String professionalCat, String professionalName); // Devolve listagem dos cuidados de saúde marcados para determinado profissional, por ordem alfabética dentro de cada tipo de serviço.

    void listServiceAppointments(String service); // Devolve listagem dos cuidados de saúde marcados em determinado serviço, por ordem alfabética do profisisonal para cada categoria

    void showFamilyMember(String familyName); // Devolve listagem dos utentes que pertencem a determinada família, por ordem alfabética


    // Manuseamento das listas:

    // Profissionais:

    List<HealthProfessional> getProfessionalList(); // Devolve o conjunto de profissionais existentes

    // Clientes:

    List<Client> getClientList(); // Devolve o conjunto de utentes existentes

    // Consultas:

    List<Appointment> getAppointmentList(); // Devolve o conjunto de cuidados de saúde marcados

    // Famílias:

    List<Family> getFamilyList(); // Devolve o conjunto de famílias existentes

}