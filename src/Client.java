
public interface Client {

    String getName(); // Devolve o nome do utente

    String getAgeGroupName(); // Devolve, em texto, a faixa etária do utente

    AgeGroup getAgeGroup(); // Devolve a faixa etária do utente

    Family getFamily(); // Devolve a família a que o utente pertence

    String getFamilyName(); // Devolve o nome da família a que o utente pertence

    Family setFamily(Family family); // Atribui família(objecto) ao utente

}