/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csgo.gameintegration;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Christoffer Andersson & Herman Söderlund
 */
public class CSGO_Tournament extends Application
{
    
    @Override
    public void start(Stage primaryStage)
    {
        
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>()
        {
            
            @Override
            public void handle(ActionEvent event)
            {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static CSGOServer server;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        EventManager.init();
        
        EventManager.addListener((int event, Object newValue, Object lastValue, Object[] userData) ->
        {
            System.out.println(EventManager.getEventName(event) + " : " + newValue + " : " + lastValue);
        }, EventManager.PLAYER_ALL_OnLeft, EventManager.PLAYER_ALL_OnJoined, EventManager.PLAYER_ALL_OnSmoked, EventManager.PLAYER_ALL_OnNotSmoked, EventManager.PLAYER_ALL_OnFlashed, EventManager.PLAYER_ALL_OnNotFlashed, EventManager.PLAYER_ALL_OnKill, EventManager.PLAYER_ALL_OnHeadshot);
        
        try
        {
            //launch(args);
            server = new CSGOServer();
        }
        catch (IOException ex)
        {
            Logger.getLogger(CSGO_Tournament.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
