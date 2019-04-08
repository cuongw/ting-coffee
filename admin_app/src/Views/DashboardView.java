/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controllers.DashboardController;
import Models.DashboardModel.Report;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DefaultDrawingSupplier;
import org.jfree.chart.plot.DrawingSupplier;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.xy.StackedXYAreaRenderer;
import org.jfree.chart.renderer.xy.XYAreaRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Thang Le
 */
public class DashboardView extends View {
    private JMonthChooser date;
    private JYearChooser year;
    private JButton btn;
    private JFreeChart lineChart;
    private DashboardController controller;
    private List<Report> list=new ArrayList<>();
    String []items={"Today","Last 7 day","Last month","Last year"};
    JComboBox cb;
    public DashboardView()
    {
        controller=DashboardController.getInstance(this);
    }
    
    //---------------------------------------------------------------------------------------------------------
    @Override
    public void insert(Object objects){
    }
    
    @Override
    public void delete(int row){
    }
    
    @Override
    public void update(int row, Object objects){
        
    }
    
    @Override
    public void loadView(Object objects){

    }
    
    public void setList(Object objects,int d)
    {
        list.clear();
        List<Report> categories = (List<Report>)(Object)objects;
        for(Report item :categories)
        {
            list.add(item);
        }
        //update chart
        CategoryPlot plot = (CategoryPlot) lineChart.getPlot();
        plot.setDataset(createDataset(d));

    }
    //----------------------------------------------------------------------------------------------------------
    
    public void Load(JPanel main,JPanel info,JPanel footer)
    {
        LoadMain(main);
        LoadInfo(info);
        LoadFooter(footer);
        controller.loadReportWeek();
        cb.setSelectedIndex(1);
    }
    
    public void LoadMain(JPanel main)
    {
        main.removeAll();
        main.setLayout(new BoxLayout(main, BoxLayout.X_AXIS));
        
        lineChart = ChartFactory.createAreaChart(
         "Dashboard",
         "Day","Total",
         null,
         PlotOrientation.VERTICAL,
         true,true,false);
        ChartPanel chartPanel = new ChartPanel( lineChart );
        chartPanel.setPreferredSize( new Dimension( main.getWidth() , main.getHeight() ) );
        lineChart.getPlot().setBackgroundPaint( new Color(228,249,245) );

        //show detail value on top chart
        CategoryItemRenderer renderer = ((CategoryPlot)lineChart.getPlot()).getRenderer();
        renderer.setBaseItemLabelGenerator(new org.jfree.chart.labels.StandardCategoryItemLabelGenerator());
        renderer.setBaseItemLabelsVisible(true);
        ItemLabelPosition position = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, 
                org.jfree.ui.TextAnchor.TOP_CENTER);
        renderer.setBasePositiveItemLabelPosition(position);
        
        main.add(Box.createRigidArea(new Dimension(5,0)));
        main.add(chartPanel);
        
        main.revalidate();
    }
    
    public void LoadInfo(JPanel info)
    {
        info.removeAll();
        info.setPreferredSize( new Dimension( 5,5) );
        info.revalidate();
    }
    
    public void LoadFooter(JPanel footer)
    {
        footer.removeAll(); // remove all components
        footer.setLayout(new BoxLayout(footer,BoxLayout.X_AXIS));
        footer.setPreferredSize(new Dimension(footer.getWidth(),50));
        
        
         /*Birthday*/
        JPanel Monthgroup=new JPanel();
        Monthgroup.setLayout(new BoxLayout(Monthgroup,BoxLayout.X_AXIS));
        //Monthgroup.setMaximumSize(new Dimension(400, 30));
        Monthgroup.setMaximumSize(new Dimension(300, 30));
        Monthgroup.setBackground(new Color(228,249,245));
        
        /*
        date=new JMonthChooser();
        date.setAlignmentX(Component.CENTER_ALIGNMENT);
        year=new JYearChooser();
        year.setAlignmentX(Component.CENTER_ALIGNMENT);
        
         JLabel dateLabel=new JLabel("Month : ");
         dateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);*/
        
       cb=new JComboBox(items);
       btn=new JButton("View");
        btn.setForeground(new Color(0,107,68));
        btn.add(Box.createRigidArea(new Dimension(50, 20)));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        
        Monthgroup.add(Box.createRigidArea(new Dimension(5,0)));
        Monthgroup.add(cb);
        /*Monthgroup.add(Box.createRigidArea(new Dimension(33,0)));
        Monthgroup.add(date);
        Monthgroup.add(Box.createRigidArea(new Dimension(10,0))); 
        Monthgroup.add(year);*/
        Monthgroup.add(Box.createRigidArea(new Dimension(33,0))); 
        Monthgroup.add(btn);
        
        footer.add(Monthgroup);
        
        /*end Birthday*/
        footer.revalidate();
        footer.repaint();
        
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*Date curday=new Date();
                int curYear=curday.getYear()+1900;
                int curMonth=curday.getMonth();                
                String day="";
                if(year.getYear()<curYear)
                {
                    
                        if(date.getMonth()+1<10)
                            day=year.getYear()+"-0"+(date.getMonth()+1)+"-01";
                        else
                            day=year.getYear()+"-"+(date.getMonth()+1)+"-01";
                        
                        controller.loadReport(day);
                }
                if(year.getYear()==curYear)
                {
                    if(date.getMonth()<=curMonth)
                    {
                        if(date.getMonth()+1<10)
                            day=year.getYear()+"-0"+(date.getMonth()+1)+"-01";
                        else
                            day=year.getYear()+"-"+(date.getMonth()+1)+"-01";
                        controller.loadReport(day);
                    }
                    else
                        JOptionPane.showMessageDialog(null, "Chưa có báo cáo vì ngày hiện tại nhỏ hơn ngày xem báo cáo");
                }
                if(year.getYear()>curYear)
                {
                    JOptionPane.showMessageDialog(null, "Chưa có báo cáo vì ngày hiện tại nhỏ hơn ngày xem báo cáo");
                }*/
                Date curday=new Date();
                int day=curday.getDate();
                int month=curday.getMonth()+1;
                int year=curday.getYear();
                if(cb.getSelectedIndex()==0) 
                   controller.loadReportToday();
                if(cb.getSelectedIndex()==1) 
                   controller.loadReportWeek();
                if(cb.getSelectedIndex()==2) 
                    controller.loadReportMonth();
                if(cb.getSelectedIndex()==3) 
                    controller.loadReportYear();
                
            }
        });
    }
    
    DefaultCategoryDataset createDataset(int d)
    {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
        if(d==0 || d==1)
        {
            for(Report item : list)
            {
            
                dataset.addValue( item.price , "USD" , item._date );
            }
        }
        if(d==2)
        {
            for(Report item : list)
            {
                String []month=item._date.split("-");
                dataset.addValue( item.price , "USD" , month[0]+"-"+month[1] );
            }
        }
        if(d==3)
        {
            for(Report item : list)
            {
                String []year=item._date.split("-");
                dataset.addValue( item.price , "USD" , year[0] );
            }
        }
        
        return dataset;
    }

}
