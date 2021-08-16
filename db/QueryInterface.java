package db;

import java.io.*;

import models.abstracts.User;
public class QueryInterface {
    private final static String DB_PATH = "/home/administrator/Documentos/poo/projeto-final-poo/db/";
    public static void createDatabaseArchives(){
        File users = new File(DB_PATH+"users.txt"); 
        File classes = new File(DB_PATH+"classes.txt"); 
        try{  
            users.createNewFile();
            classes.createNewFile();  
            
        } catch (IOException e){  
        e.printStackTrace();
        }         
    }
    public static void trySaveUser(User user){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(DB_PATH+"users.txt", true));
            bw.write("nome:" + user.getName() + ";cpf:" + user.getCpf() + ";email:" + user.getEmail() + ";password:" + user.getPassword() + ";registration:" + user.getRegistration());
            bw.newLine();
            bw.close();
            System.out.println("Usuário " + user.getName() + "(" + user.getEmail() + ") criado com sucesso");
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public static User tryAuthenticate(String email, String password){
        try{
            BufferedReader br = new BufferedReader(new FileReader(DB_PATH+"users.txt"));
            while (br.ready()){
                String line = br.readLine();
                String[]parameters = line.split(";");
                String email_test = parameters[2].split(":")[1];
                String password_test = parameters[3].split(":")[1];
                if (email_test.equals(email) && password_test.equals(password)){
                    String name = parameters[0].split(":")[1];
                    String cpf = parameters[1].split(":")[1];
                    String registration = parameters[4].split(":")[1];
                    User user = new User(name, cpf, email, password, registration);
                    br.close();
                    return user;
                }
            }
            br.close();          
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
