
public interface Appointment {

    public String getClientName(); // Devolve o nome do utente

    public Client getClient(); // Devolve o utente

    public Service getService(); // Devolve o tipo de serviço prestado ao utente

    public HealthProfessional getHealthProfessional(); // Devolve o profissional de saúde responsável pela prestação do serviço

}