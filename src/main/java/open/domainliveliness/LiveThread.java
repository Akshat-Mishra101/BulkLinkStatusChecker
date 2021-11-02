/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package open.domainliveliness;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.ListView;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author joey
 */
public class LiveThread extends Task<Void>
{   String path;
    ListView lv;
    String proxy;
    int timeout;
    LiveThread(String path,ListView lv,String proxy,int timeout)
    {
     this.path=path;
     this.lv=lv;
     this.proxy=proxy;
     this.timeout=timeout;
    }

    @Override
    protected Void call() throws Exception {
        updateMessage("Started");
        updateProgress(-1,100);
        FileWriter fw=new FileWriter("data.txt");
        Files.write(Paths.get("data.txt"), ("LINK,STATUS"+"\r\n").getBytes(), StandardOpenOption.APPEND);
        fw.close();
        Scanner sc=new Scanner(new File(path));
        while(sc.hasNext()){
            String line="";
            try{
             
      
            line=sc.nextLine();
            updateMessage("Processing: "+line);
            Document doc=Jsoup.connect(proxy.trim()+((line).contains("http")?line:"https://"+line)).timeout(timeout).get();
            String lvx=line;
            Platform.runLater(()->{
            lv.getItems().add("IS ALIVE: "+lvx);
            });
           
            }
            catch(Exception e){
            // dead link
             Files.write(Paths.get("data.txt"), (line+","+e+"\r\n").getBytes(), StandardOpenOption.APPEND);
             String lvx=line;
              Platform.runLater(()->{
            lv.getItems().add("IS DEAD: "+lvx+" REASON:"+e);
            });
            }
            
            
        }
            updateProgress(100,100); 
        return null;
    }
    
    
}
