/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.BillModel;
import Models.BillModel.Bill;
import Views.BillView;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Thang Le
 */
public class BillController extends Controller {
     private static BillController _instance = null;
    
    public static BillController getInstance(BillView view) {
        _instance = new BillController(view);
        return _instance;
    }
    
    private final BillView view;
    private final BillModel model;
    private List<Bill> bills = null;//save
    
    private BillController(BillView view) {
        this.view = view;
        this.model = BillModel.getInstance();
    }
    
    @Override
    public void insert(Object object){
        
    }
    @Override
    public void delete(Object object){
      int id = (int)object;
        _deleteBills(id);
        CompletableFuture.runAsync(() -> { //runAsync no return value
            try
            {
                    model.delete(id);
            }catch (IOException ex) {
                Logger.getLogger(BillController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    @Override
    public void update(Object object){
        
       
    }
    
    @Override
    public void loadFull()
    {
        CompletableFuture<List<Bill>>  future;                
        future = CompletableFuture.supplyAsync(() -> {//open thread
            try {
                    bills = model.getBill();
                return bills;
            } catch (IOException ex) {
                Logger.getLogger(BillController.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        });
        future.thenAccept(listBills -> view.loadView(listBills));
    }
    
    @Override
    public Object Filter(String keyWord, Object opt)
    {
        if(bills == null) return null;
        if(keyWord.isEmpty() || keyWord.trim().isEmpty())
            return bills;
        else
        {
            return bills.stream().filter(item -> 
                    item.table.toLowerCase().contains(keyWord) || 
                    item.username.toLowerCase().contains(keyWord)).collect(Collectors.toList());
        }
    }
    
    private void _deleteBills(int index)
    {
        for (Bill item : bills)
        {
            if (item.id==index)
            {
                bills.remove(item);
                break;
            }
                
        }
    }
}
