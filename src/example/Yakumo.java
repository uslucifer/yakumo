/*
 * задача класса получать новые множества и реагировать на имеющиеся
 */
package example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aleksei Nezhnov
 */
public class Yakumo {
    Hashtable <String,Hashtable<String,String>> main_library;
    Hashtable <String, String> reaction;
    String filename = "data\\main_lib.dat";
    String echo; // эхо, последнее множество на входе
    boolean reaction_flag;
    private String session;
    boolean last_multiude; 
   public Yakumo() 
    {
        main_library = new Hashtable <String,Hashtable<String, String>>();
        reaction = new Hashtable <String, String>();
        reaction_flag = true;
        last_multiude = false;
        session="";
        echo ="";
    }
    void addMultitude(String multitude)
    {// добавляет библиотеку новым множеством
        String key = multitude.length()+"";// формируем ключ
        echo = multitude;//  формируем эхо множество
        if(main_library.containsKey(key))
        {
            Hashtable<String,String> mul = main_library.get(key);
            if(!mul.containsKey(multitude))// если множество неизвестно
            {
                mul.put(multitude,"serial");
                main_library.put(key, mul);
                reaction_flag = false; // реакция на неизвестное множество - интерес
            }else{// если множество найдено
                reaction_flag = true; // реакция на неизвестное множество - отсутствие интереса
            }
        }
        else
        {//создаем новый ключ
            Hashtable<String,String> mul = new  Hashtable<String,String>();
            mul.put(multitude,"serial");
            main_library.put(key, mul);
        }   
        if(!last_multiude)// дописывать если сессия не завершается
        {
            if(session=="")
            {
                session=getSession()+echo;//  если первое множество в сессии
            }
            else
            {
                session=getSession()+"."+echo;
            }
        
        }
    }
    void saveLibary()
    {   try {
        // сохраняет объект библиотеки в файл
            last_multiude = true;
                addMultitude(getSession());
                
        FileOutputStream file_output = new FileOutputStream(filename);
                ObjectOutputStream output_object = new ObjectOutputStream(file_output);
                output_object.writeObject(main_library);
       
               
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Yakumo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Yakumo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void loadLibrary()
    {   try {
        // загружает состояние библиотеки из файла
        FileInputStream input_stream = new FileInputStream(filename);
                ObjectInputStream input_object = new ObjectInputStream(input_stream);
                main_library =  (Hashtable<String, Hashtable<String, String>>) input_object.readObject();
                
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Yakumo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Yakumo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Yakumo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    int getSize()
    {
        int size =0;
        for(Enumeration<String> tt = main_library.keys();tt.hasMoreElements(); )
            size = size + main_library.get(tt.nextElement()).size();
    return size;
    }

    /**
     * @return the session
     */
    public String getSession() {
        return session;
    }
    boolean getReaction_flag()
    {
        return reaction_flag;
    }
    String getEcho()
    {
        return echo;
    }
}
