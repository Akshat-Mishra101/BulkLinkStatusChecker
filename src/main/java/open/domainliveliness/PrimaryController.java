package open.domainliveliness;

import java.io.File;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

public class PrimaryController {
    @FXML
    TextField proxy;
    @FXML
    TextField timeout;
    
    @FXML
    Label infolabel;
    @FXML
    ProgressBar pb;
    String path="";
    @FXML
    TextField tf1;
    @FXML
    ListView lv;
   @FXML
   private void open()
   {
   FileChooser fc=new FileChooser();
   try{
   File fil=fc.showOpenDialog(null);
   path=fil.getAbsolutePath();
   tf1.setText(path);
      }
   catch(Exception e){System.out.println(e);
   }
   }
   @FXML
   public void start()
   {
      
   LiveThread lt=new LiveThread(path,lv,proxy.getText(),Integer.parseInt(timeout.getText()));   
   Thread rtx=new Thread(lt);
   rtx.start();
   pb.progressProperty().bind(lt.progressProperty());
   infolabel.textProperty().bind(lt.messageProperty());
   }
}
