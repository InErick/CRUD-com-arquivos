package Entities;

public class Client {
    private long id;
    private String name;
    private String email;
    private String phone;

    public Client(long id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "ID: "+id+", Name: "+name+", Email: "+email+", Phone: "+phone;
    }
}
