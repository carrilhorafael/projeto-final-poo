package db;

import java.io.*;
import java.util.ArrayList;

public abstract class QueryInterface {
    private final static String DB_PATH = "/home/deboraferreira/Área de Trabalho/poo-projetinho/projeto-final-poo/db/";
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

    private static BufferedReader accessReader(String table){
        try{
            BufferedReader br = new BufferedReader(new FileReader(DB_PATH + table + ".txt"));
            return br;    
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }  

    private static BufferedWriter accessWriter(String table, boolean rewriter) throws IOException{
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(DB_PATH + table + ".txt", rewriter));
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
            String[] parameters = br.readLine().split(" \\| ");
            int i;
            for(i = 0; i < parameters.length; i++){
                if (parameters[i].equals(parameter)){
                    break;
                }
            }
            while (br.ready()){
                String line = br.readLine();
                String line_data = line.split(" \\| ")[i];
                if (line_data.equals(value)){
                    return line;
                }
            }
            br.close();          
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<String> all(String table){
        try{
            BufferedReader br = QueryInterface.accessReader(table);
            ArrayList<String> response = new ArrayList<>();
            br.readLine();
            while (br.ready()){
                String line = br.readLine();
                response.add(line);                
            }
            br.close();
            return response;
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<String> where(String table, String parameter, String value){
        try{
            BufferedReader br = QueryInterface.accessReader(table);
            ArrayList<String> response = new ArrayList<>();
            String[] parameters = br.readLine().split(" \\| ");
            int i;
            for(i = 0; i < parameters.length; i++){
                if (parameters[i].equals(parameter)){
                    break;
                }
            }
            while (br.ready()){
                String line = br.readLine();
                String line_data = line.split(" \\| ")[i];
                if (line_data.equals(value)){
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

    public static boolean create(String table, String line){
        try{
            BufferedWriter bw = QueryInterface.accessWriter(table, true);
            bw.write(line);
            bw.newLine();
            bw.close();
            return true;
        }catch(IOException e){
            e.printStackTrace();
        }
        return false;
    }

    public static boolean delete(String table, String line){
        ArrayList<String> backup = new ArrayList<>();
        try{
            BufferedReader br = QueryInterface.accessReader(table);
            while(br.ready()){
                String existing_line = br.readLine();
                if (!existing_line.equals(line)){
                    backup.add(existing_line);
                }
            }
            
            br.close();
            BufferedWriter bw = QueryInterface.accessWriter(table, false);
                
            backup.forEach(existing_line -> {
                try{
                    bw.write(existing_line);
                    bw.newLine();
                }catch(IOException e){
                    e.printStackTrace();
                }
            });
            bw.close();
            return true;
        }
        catch(IOException e){
            e.printStackTrace();
            return false;
        }
    }
}
