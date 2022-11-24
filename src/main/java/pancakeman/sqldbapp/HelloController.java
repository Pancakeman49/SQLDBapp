package pancakeman.sqldbapp;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.stage.PopupWindow;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;

public class HelloController {

    private final Tab LoadingTab = new Tab("Loading");
    public ListView listOfTables;
    @FXML
    private Label label;
    @FXML
    private Pane controlPane;
    @FXML
    private TextField sqlQuery;
    @FXML
    private Button sqlButton;
    @FXML
    private TabPane tabPane;


    public HelloController(){

    }
    public void SQLButtonClick() {
        //create the task that will be run
        Task<String> getTabWithTable = new Task<String>() {
            @Override
            protected String call() throws Exception {
                Thread.sleep(100);
                System.out.println("Runing");

                NewTab newTab = new NewTab();
                String string = "";
                try {
                    string = newTab.GetWithTableNewTab(sqlQuery.getText());
                }
                catch (Exception e){
                    e.printStackTrace();
                }

                System.out.println("Returned");
                return string;
            }
        };
        //add tab to tabpane when succeeded
        getTabWithTable.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                RemoveLoadingTab();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                AddHTMLToTabWithWebView(getTabWithTable.getValue());
            }
        });
        //start thread
        Thread th = new Thread(getTabWithTable);
        th.setDaemon(true);
        AddLoadingTab();
        th.start();
        System.out.println("Started");
    }

    public void GetTablesBtnClick(){
        JDBC sqlQueryEngine = new JDBC();
        List<String> tableNames = sqlQueryEngine.GetTablesFromDB();
        listOfTables.getItems().addAll(tableNames);
    }



    private void AddLoadingTab(){
        tabPane.getTabs().add(LoadingTab);
    }
    private void RemoveLoadingTab(){
        tabPane.getTabs().remove(LoadingTab);
    }
    private void AddHTMLToTabWithWebView(String s){
        WebView webView = new WebView();
        webView.getEngine().loadContent(s);
        Tab tab = new Tab("Table");
        tab.setContent(webView);
        tab.setClosable(true);
        tabPane.getTabs().add(tab);
    }





}