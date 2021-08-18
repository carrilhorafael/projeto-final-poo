package db;

import java.io.*;
import java.util.ArrayList;

public abstract class QueryInterface {
    private final static String DB_PATH = "/home/administrator/Documentos/poo/projeto-final-poo/db/";
    // public static void createDatabaseArchives(){
    //     File users = new File(DB_PATH+"users.txt"); 
    //     File classes = new File(DB_PATH+"classes.txt"); 
    //     try{  

    //         users.createNewFile();
    //         classes.createNewFile();  
            
    //     } catch (IOException e){  
    //     e.printStackTrace();
    //     }         
    // }
    public static BufferedReader accessReader(String table){
        try{
            BufferedReader br = new BufferedReader(new FileReader(DB_PATH + table + ".txt"));
            return br;    
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }  
    public static BufferedWriter accessWriter(String table) throws IOException{
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(DB_PATH + table));
            return bw;    
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }
    public static String find_by(String table, String parameter, String value){
        try{
            BufferedReader br = QueryInterface.accessReader(table);
            String[] parameters = br.readLine().split(" | ");
            int i;
            for(i = 0; i < parameters.length; i++){
                if (parameters[i].equals(parameter)){
                    break;
                }
            }
            while (br.ready()){
                String line = br.readLine();
                String line_data = line.split(" | ")[i];
                if (line_data == value){
                    return line;
                }
            }
            br.close();          
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }
    public static ArrayList<String> where(String table, String parameter, String value){
        try{
            BufferedReader br = QueryInterface.accessReader(table);
            ArrayList<String> response = new ArrayList<>();
            String[] parameters = br.readLine().split(" | ");
            int i;
            for(i = 0; i < parameters.length; i++){
                if (parameters[i].equals(parameter)){
                    break;
                }
            }
            while (br.ready()){
                String line = br.readLine();
                String line_data = line.split(" | ")[i];
                if (line_data == value){
                    response.add(line);
                }
            }
            br.close();
            return response;          
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
