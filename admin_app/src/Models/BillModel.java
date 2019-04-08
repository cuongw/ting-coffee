/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Constants.Query;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Thang Le
 */
public class BillModel {
    private static BillModel _instance = null;
    
    public static BillModel getInstance() {
        if(_instance == null)
            _instance = new BillModel();
        return _instance;
    }
    
    private final Gson json;//convert json
    private final MySqlConnection mySqlConnection;
    
    private BillModel() {
        json = new Gson();
        mySqlConnection = MySqlConnection.getInstance();
    }
    
     public List<Bill> getBill() throws IOException
    {
        String rawJson=mySqlConnection.executeQuery(Query.getBill, null);
        if(rawJson==null)
            return null;
        Bill[] bills=json.fromJson(rawJson, Bill[].class); // convert json to foodcategory[]
        List<Bill> listBill = new LinkedList<>(Arrays.asList(bills));
        return listBill;
        
    }
     
     public void delete(int id) throws IOException
     {
         String raw=mySqlConnection.executeNoneQuery(Query.delBill, new Object[] { id });
         if (raw=="1") JOptionPane.showMessageDialog(null, "Deleted successfully!");
     }
    
    public class Bill
    {
        @SerializedName("ID") 
        public int id;
        @SerializedName("IDTable") 
        public int idtable;
        @SerializedName("Name") 
        public String table;
        @SerializedName("DateCheckIn") 
        public String checkin;
        @SerializedName("DateCheckOut") 
        public String checkout;
        @SerializedName("Discount") 
        public Double discount;
        @SerializedName("TotalPrice") 
        public double price;
        @SerializedName("Status") 
        public int status;
        @SerializedName("Username") 
        public String username;
        
        
        public Bill(int id,int idtable,String checkin,String checkout,Double discount,Double price, String name)
        {
            this.id = id;
            this.idtable = idtable;
            this.checkin=checkin;
            this.checkout=checkout;
            this.discount=discount;
            this.price=price;
            this.username=name;
        }
        
        public Bill(int id,String table,String checkin,String checkout,Double discount,Double price,int status, String name)
        {
            this.id = id;
            this.table = table;
            this.checkin=checkin;
            this.checkout=checkout;
            this.discount=discount;
            this.price=price;
            this.status=status;
            this.username=name;
        }
        
        public Bill(){}
    }
}
