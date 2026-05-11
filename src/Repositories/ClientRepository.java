package Repositories;

import Entities.Client;

import java.util.List;

public interface ClientRepository {
    //create
    void save(Client client);

    //read
    List<Client> findAll();

    //read
    Client findById(long id);

    //update
    void update(Client client);

    //delete
    void delete(long id);

    //controle de ID
    long getNextId();
}
