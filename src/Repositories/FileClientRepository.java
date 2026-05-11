package Repositories;

import Entities.Client;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileClientRepository implements ClientRepository {
    private String baseFolder;

    public FileClientRepository(String baseFolder) {
        this.baseFolder = baseFolder;
        File folder = new File(baseFolder,"clients");
        if(!folder.exists()){
            folder.mkdir();
        }
    }

    @Override
    public void save(Client client) {
        String fileName = client.getName().replace(" ","")+client.getId()+".csv";
        File file = new File(baseFolder + File.separator + "clients",fileName);

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))){
            bw.write(client.toString());
        } catch (IOException e){
            System.out.println("Error to save client: "+e.getMessage());
        }
    }

    @Override
    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        File folder = new File(baseFolder,"clients");
        File[] files = folder.listFiles();
        if(files != null){
            for(File file: files){
                Client client = convertFileToClient(file);
                if(client != null){
                    clients.add(client);
                }
            }
        }
        return clients;
    }

    @Override
    public Client findById(long id) {
        File file = getFileById(id);
        return (file != null) ? convertFileToClient(file) : null;
    }

    @Override
    public void update(Client client) {
        File oldFile = getFileById(client.getId());

        if(oldFile != null && oldFile.exists()){
            oldFile.delete();
        }

        save(client);
    }

    @Override
    public void delete(long id) {
        File file = getFileById(id);
        if(file != null && file.exists()){
            if(file.delete()){
                System.out.println("User deleted");
            }else{
                System.out.println("User not found");
            }
        }
    }

    @Override
    public long getNextId() {
        File folder = new File(baseFolder, "clients");
        File[] files = folder.listFiles();

        if (files == null || files.length == 0) {
            return 1000;
        }

        long maxId = 1000;
        for (File f : files) {
            String name = f.getName().replace(".csv", "");

            String onlyNumbers = name.replaceAll("\\D", "");

            if (!onlyNumbers.isEmpty()) {
                try {
                    long currentId = Long.parseLong(onlyNumbers);
                    if (currentId > maxId) {
                        maxId = currentId;
                    }
                } catch (NumberFormatException e) {

                }
            }
        }
        return maxId + 1;

    }

    private Client convertFileToClient(File file){
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line = br.readLine();
            if(line != null){
                String[] data = line.split(",");

                long id = Long.parseLong(data[0]);
                String name = data[1];
                String email = data[2];
                String phone = data[3];

                return new Client(id,name,email,phone);
            }
        } catch (IOException e){
            System.out.println("Error to convert file: "+e.getMessage());
        }
        return null;

    }

    private File getFileById(Long id){
        File folder = new File(baseFolder,"clients");
        File[] files = folder.listFiles();
        if(files != null){
            for(File file: files){
                if(file.getName().endsWith(id+".csv"))
                    return file;
            }
        }
        return null;
    }
}
