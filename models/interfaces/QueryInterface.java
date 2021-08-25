package models.interfaces;

import java.io.*;
import java.util.ArrayList;

public interface QueryInterface {
    // private final static String DB_PATH = "/home/deboraferreira/Área de Trabalho/poo-projetinho/projeto-final-poo/db/";
    final static String DB_PATH = "/home/administrator/Documentos/poo/projeto-final-poo/db/";
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
    
    // Abre um arquivo table.txt para leitura
    private static BufferedReader accessReader(String table){
        try{
            BufferedReader br = new BufferedReader(new FileReader(DB_PATH + table + ".txt"));
            return br;    
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }  
    
    // Abre um arquivo table.txt para escrita. 
    // Caso a boolean rewrite seja false, o arquivo será aberto para rescrita, caso contrario, manterá a informação já escrita.
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

    // Encontra uma instancia no table.txt onde this.parameter == value
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

    // Encontra uma instancia no table.txt com id == value
    public static String find(String table, int value){
        try{
            BufferedReader br = QueryInterface.accessReader(table);
            br.readLine();
            while (br.ready()){
                String line = br.readLine();
                int line_id = Integer.parseInt(line.split(" \\| ")[0]);
                if (line_id == value){
                    return line;
                }
            }
            br.close();          
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    // Retorna um ArrayList com todas as instancias de table.txt
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

    // Retorna um ArrayList com todas as instancias de table.txt onde this.parameter == value.
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

    // Salva uma instancia stringified em table.txt
    public static boolean save(String table, String line){
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
    
    // Deleta uma instancia em table.txt
    public static boolean delete(String table, int id){
        ArrayList<String> backup = new ArrayList<>();
        try{
            BufferedReader br = QueryInterface.accessReader(table);
            backup.add(br.readLine());
            while(br.ready()){
                String existing_line = br.readLine();
                int line_id = Integer.parseInt(existing_line.split(" \\| ")[0]);
                if (line_id != id){
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
    
    // Retorna a ultima instancia da table.txt
    public static String last(String table){
        try{
            BufferedReader bw = QueryInterface.accessReader(table);
            bw.readLine();
            String response = null;
            while(bw.ready()){
                response = bw.readLine();
            }
            bw.close();
            return response;
        }catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }
}
