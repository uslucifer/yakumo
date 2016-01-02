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
    Hashtable <String,Vector<String>> main_library;
    Hashtable <String, String> reaction;
    String filename = "data\\main_lib.dat";
   public Yakumo() 
    {
       main_library = new Hashtable<String,Vector<String>>();
    }
    void addMultitude(String multitude)
    {// добавляет библиотеку новым множеством
        String key = multitude.length()+"";// формируем ключ
        if(main_library.containsKey(key))
        {
            Vector<String> v_multitude = main_library.get(key);
            if(!v_multitude.contains(multitude))// если множество неизвестно
            {
                v_multitude.add(multitude);
                main_library.put(key, v_multitude);
            }else{
            }
        }else{
            Vector<String> v_multitude = new Vector<String>();
                v_multitude.add(multitude);
            main_library.put(key, v_multitude);
        }
        
    }
    void saveLibary()
    {   try {
        // сохраняет объект библиотеки в файл
                
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
                main_library =    (Hashtable<String, Vector<String>>) input_object.readObject();
                
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
   
}
