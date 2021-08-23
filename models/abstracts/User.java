package models.abstracts;


public abstract class User {
    private String id, name, cpf, email, password, registration, state, birthdate, nationality;
    private int quantUser = 0;

    public User(String name, String cpf, String email, String password, String registration, String birthdate, String state, String nationality){
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        this.registration = registration;
        this.state = state;
        this.birthdate = birthdate;
        this.nationality = nationality;
        this.id = Integer.toString(quantUser + 1);
        quantUser++;
    }
    
    public User(String id, String name, String cpf, String email, String password, String registration, String birthdate, String state, String nationality){
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        this.registration = registration;
        this.state = state;
        this.birthdate = birthdate;
        this.nationality = nationality;
        this.id = id;
    }

    public String stringify(){
        return this.id + " | " + this.name + " | " + this.cpf + " | " + this.email + " | " + this.password + " | " + this.registration + " | " + this.birthdate + " | " + this.state + " | " + this.nationality + " | " + this.getClass().toString().split("\\.")[2];
    }
    
    public boolean authenticate (String password){
        return this.password.equals(password); 
    }

    // Getters
    public String getId(){
        return id;
    }
    public String getCpf() {
        return cpf;
    }
    public String getEmail() {
        return email;
    }
    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }
    public String getRegistration() {
        return registration;
    }
}
