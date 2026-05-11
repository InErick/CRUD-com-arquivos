package Services;

import Entities.Client;
import Repositories.ClientRepository;

import java.util.List;

public class ClientService {
    private ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public void create(String name, String email, String phone){
        if(name.isBlank() || email.isBlank() || phone.isBlank()){
            System.out.println("Name, email or phone is invalid");
            return;
        }

        List<Client> clients = repository.findAll();
        for(Client c: clients){
            if(c.getEmail().equalsIgnoreCase(email)){
                System.out.println("This emails already used");
                return;
            }
        }

        long id = repository.getNextId();
        Client newClient = new Client(id, name, email, phone);
        repository.save(newClient);
        System.out.println("Client created success");
    }

    public List<Client> listAll(){
        return repository.findAll();
    }

    public Client getById(long id){
        Client c = repository.findById(id);
        if(c == null){
            System.out.println("Id not found");
        }
        return c;
    }

    public void remove(long id){
        if(repository.findById(id) != null){
            repository.delete(id);
        }else{
            System.out.println("Id not found");
        }
    }

    public void updateInfo(long id,String newName, String newEmail, String newPhone){
        Client existing = repository.findById(id);
        if(existing != null){
            Client updatedClient = new Client(id, newName, newEmail, newPhone);
            repository.update(updatedClient);
            System.out.println("Client update succesfuly");
        }else{
            System.out.println("Id not found");
        }
    }
}
