/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.DashboardModel;
import Models.DashboardModel.Report;
import Views.DashboardView;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thang Le
 */
public class DashboardController extends Controller {
    private static  DashboardController _instance = null;
    
    
    public static DashboardController getInstance(DashboardView view)
    {
        _instance = new DashboardController(view);
        return _instance;
    }
    
    private final DashboardView view;
    private final DashboardModel model;
    
    private List<Report> reports = null; // save food categories
    
     private DashboardController(DashboardView view)
    {
        this.view = view;
        this.model = DashboardModel.getInstance();
    }
     @Override
    public void insert(Object object){

        
    }
    @Override
    public void delete(Object object){

    }
    @Override
    public void update(Object object){
        

    }
    
    @Override
    public void loadFull()
    {
        
    }
    @Override
    public Object Filter(String keyWord, Object opt)
    {
        return null;
    }
    public void loadReportWeek()
    {
        //String day=(String) obj;
        CompletableFuture<List<Report>>  future;                
        future = CompletableFuture.supplyAsync(() -> {//open thread
            try {

                reports = model.getReportWeek();
                return reports;
            } catch (IOException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        });
        future.thenAccept(listReport -> view.setList(listReport,1));
    }
    
     public void loadReportMonth()
    {
        //String day=(String) obj;
        CompletableFuture<List<Report>>  future;                
        future = CompletableFuture.supplyAsync(() -> {//open thread
            try {

                reports = model.getReportMonth();
                return reports;
            } catch (IOException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        });
        future.thenAccept(listReport -> view.setList(listReport,2));
    }
     public void loadReportToday()
    {
        //String day=(String) obj;
        CompletableFuture<List<Report>>  future;                
        future = CompletableFuture.supplyAsync(() -> {//open thread
            try {

                reports = model.getReportToday();
                return reports;
            } catch (IOException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        });
        future.thenAccept(listReport -> view.setList(listReport,0));
    }
     public void loadReportYear()
    {
        //String day=(String) obj;
        CompletableFuture<List<Report>>  future;                
        future = CompletableFuture.supplyAsync(() -> {//open thread
            try {

                reports = model.getReportYear();
                return reports;
            } catch (IOException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        });
        future.thenAccept(listReport -> view.setList(listReport,3));
    }
}
