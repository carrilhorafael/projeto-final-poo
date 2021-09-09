package models.interfaces;

import java.io.*;
import java.util.ArrayList;

public interface ActiveRecord {
    // private final static String DB_PATH = "/home/deboraferreira/Área de Trabalho/poo-projetinho/projeto-final-poo/db/";
    final static String DB_PATH = "/media/administrator/'HDD Linux'/pessoais/projeto-final-poo/db/";
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
            BufferedReader br = accessReader(table);
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
            BufferedReader br = accessReader(table);
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
            BufferedReader br = accessReader(table);
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
            BufferedReader br = accessReader(table);
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
            BufferedWriter bw = accessWriter(table, true);
            bw.write(line);
            bw.newLine();
            bw.close();
            update("ids", 1, "next_" + table + "_id", Integer.toString(Integer.parseInt(line.split(" | ")[0]) + 1));
            return true;
        }catch(IOException e){
            e.printStackTrace();
        }
        return false;
    }
    
    // Editar uma instancia em table.txt
    public static boolean update(String table, int id, String parameters, String value){
        ArrayList<String> backup = new ArrayList<>();
        try{
            BufferedReader br = accessReader(table);
            String first_line = br.readLine();
            backup.add(first_line);
            String[] first_line_params = first_line.split(" \\| ");
            int i;
            for(i = 0; i < first_line_params.length; i++){
                if(first_line_params[i].equals(parameters)){
                    break;
                }
            }
            while(br.ready()){
                String existing_line = br.readLine();
                String[] existing_line_array = existing_line.split(" \\| "); 
                int line_id = Integer.parseInt(existing_line_array[0]);
                if (line_id == id){
                    String new_line = "";
                    for (int j = 0; j < existing_line_array.length; j++){
                        if (i == j) 
                            new_line = new_line + value;
                        else 
                            new_line = new_line + existing_line_array[j];
                        if (j < existing_line_array.length - 1){
                            new_line = new_line + " | ";
                        }
                    }
                    backup.add(new_line);
                }else{
                    backup.add(existing_line);
                }
            }
            br.close();
            BufferedWriter bw = accessWriter(table, false);
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
        }catch(IOException e){
            e.printStackTrace();
            return false;
        }
    }

    // Deleta uma instancia em table.txt
    public static boolean delete(String table, int id){
        ArrayList<String> backup = new ArrayList<>();
        try{
            BufferedReader br = accessReader(table);
            backup.add(br.readLine());
            while(br.ready()){
                String existing_line = br.readLine();
                int line_id = Integer.parseInt(existing_line.split(" \\| ")[0]);
                if (line_id != id){
                    backup.add(existing_line);
                }
            }
            
            br.close();
            BufferedWriter bw = accessWriter(table, false);
                
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
            BufferedReader bw = accessReader(table);
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

    // public static String constructNewLine();
}
