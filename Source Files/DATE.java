import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DATE {

	private JFrame frame;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				        if ("Nimbus".equals(info.getName())) {
				            UIManager.setLookAndFeel(info.getClassName());
				            break;
				        }
				    }
					DATE window = new DATE();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DATE() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame("Date Calculator");
		frame.setResizable(false);
		frame.setBounds(100, 100, 410, 294);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
				
			
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Dialog", Font.BOLD, 14));
		comboBox.setToolTipText("Select the month");
		comboBox.setBounds(120, 33, 165, 26);
		frame.getContentPane().add(comboBox);
		
		JComboBox<Integer> comboBox_1 = new JComboBox<Integer>();
		comboBox_1.setFont(new Font("DejaVu Sans", Font.BOLD, 14));
		comboBox_1.setToolTipText("Select the Year");
		comboBox_1.setBounds(308, 34, 88, 25);
		frame.getContentPane().add(comboBox_1);
		
		JComboBox<Integer> comboBox_2 = new JComboBox<Integer>();
		comboBox_2.setToolTipText("Select the Day");
		comboBox_2.setFont(new Font("DejaVu Sans", Font.BOLD, 14));
		comboBox_2.setBounds(16, 33, 92, 25);
		frame.getContentPane().add(comboBox_2);
		
		for(int i=1900;i<=2200;i++) comboBox_1.addItem(i);
		for(int i=0;i<DateFunctions.array.length;i++) comboBox.addItem(DateFunctions.array[i]);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = comboBox.getSelectedIndex();
				int year = (Integer)comboBox_1.getSelectedItem();
				comboBox_2.removeAllItems();
			    if(DateFunctions.IsLeapYear(year) && index==1) for(int i=1;i<=29;i++) comboBox_2.addItem(i);
			    else for(int i=1;i<=DateClass.monthDays[index];i++) comboBox_2.addItem(i);
			} 
		});
		
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int month= comboBox.getSelectedIndex();
				int year= (Integer)comboBox_1.getSelectedItem();
				if(DateFunctions.IsLeapYear(year) && month==1){
					comboBox_2.removeAllItems();
					for(int i=1;i<=29;i++) comboBox_2.addItem(i);
				}
			}
		});
		
		String todate=DateFunctions.getCurrentDate();
		String points[]=todate.split("/");
		comboBox.setSelectedIndex(Integer.parseInt(points[1])-1);
		comboBox_1.setSelectedItem(Integer.parseInt(points[2]));
		comboBox_2.setSelectedItem(Integer.parseInt(points[0]));
		
		JLabel lblDayCalculator = new JLabel("........................................Day Calculator.................................");
		lblDayCalculator.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		lblDayCalculator.setBounds(6, 6, 438, 15);
		frame.getContentPane().add(lblDayCalculator);
		
		JButton btnNewButton = new JButton("Get the Day of the week");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int year = (Integer)comboBox_1.getSelectedItem();
					int index = comboBox.getSelectedIndex();
					int daynum=(Integer)comboBox_2.getSelectedItem();
					String mess="";
					DateClass x=new DateClass(daynum,index+1,year),y=new DateClass(DateFunctions.getCurrentDate());
					
					if(x.equals(y)) mess="Today is "+DateFunctions.GetDay(x);
					else {
						String day=DateFunctions.GetDay(x);
						if(DateClass.before(x)) mess="It was a "+day;
						else mess="It will be a "+day;
					}
					JOptionPane.showMessageDialog(null,mess,"Day of the Week",1);
				}catch(Exception err) {
					err.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		btnNewButton.setBounds(16, 69, 380, 30);
		frame.getContentPane().add(btnNewButton);
		
		JComboBox<Integer> comboBox_3 = new JComboBox<Integer>();
		comboBox_3.setToolTipText("Select the Day");
		comboBox_3.setFont(new Font("DejaVu Sans", Font.BOLD, 14));
		comboBox_3.setBounds(16, 138, 92, 25);
		frame.getContentPane().add(comboBox_3);
		
		JComboBox<String> comboBox_4 = new JComboBox<String>();
		comboBox_4.setToolTipText("Select the month");
		comboBox_4.setFont(new Font("Dialog", Font.BOLD, 14));
		comboBox_4.setBounds(120, 138, 165, 26);
		frame.getContentPane().add(comboBox_4);
		
		JComboBox<Integer> comboBox_5 = new JComboBox<Integer>();
		comboBox_5.setToolTipText("Select the Year");
		comboBox_5.setFont(new Font("DejaVu Sans", Font.BOLD, 14));
		comboBox_5.setBounds(308, 139, 88, 25);
		frame.getContentPane().add(comboBox_5);
		
		JLabel lbldayDifferenceCalculator = new JLabel("..............................Day Difference Calculator.........................");
		lbldayDifferenceCalculator.setFont(new Font("DejaVu Sans", Font.PLAIN, 14));
		lbldayDifferenceCalculator.setBounds(6, 111, 438, 15);
		frame.getContentPane().add(lbldayDifferenceCalculator);
		
		JComboBox<Integer> comboBox_6 = new JComboBox<Integer>();
		comboBox_6.setToolTipText("Select the Day");
		comboBox_6.setFont(new Font("DejaVu Sans", Font.BOLD, 14));
		comboBox_6.setBounds(16, 175, 92, 25);
		frame.getContentPane().add(comboBox_6);
		
		JComboBox<String> comboBox_7 = new JComboBox<String>();
		comboBox_7.setToolTipText("Select the month");
		comboBox_7.setFont(new Font("Dialog", Font.BOLD, 14));
		comboBox_7.setBounds(120, 175, 165, 26);
		frame.getContentPane().add(comboBox_7);
		
		JComboBox<Integer> comboBox_8 = new JComboBox<Integer>();
		comboBox_8.setToolTipText("Select the Year");
		comboBox_8.setFont(new Font("DejaVu Sans", Font.BOLD, 14));
		comboBox_8.setBounds(308, 176, 88, 25);
		frame.getContentPane().add(comboBox_8);
		
		for(int i=1900;i<=2200;i++) comboBox_5.addItem(i);
		for(int i=1900;i<=2200;i++) comboBox_8.addItem(i);
		for(int i=0;i<DateFunctions.array.length;i++) comboBox_4.addItem(DateFunctions.array[i]); 
		for(int i=0;i<DateFunctions.array.length;i++) comboBox_7.addItem(DateFunctions.array[i]); 
				
		comboBox_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = comboBox_4.getSelectedIndex();
				int year = (Integer)comboBox_5.getSelectedItem();
				comboBox_3.removeAllItems();
			    if(DateFunctions.IsLeapYear(year) && index==1) 
			    	for(int i=1;i<=29;i++) comboBox_3.addItem(i);
			    else for(int i=1;i<=DateClass.monthDays[index];i++) comboBox_3.addItem(i);
			} 
		});
		
		comboBox_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = comboBox_7.getSelectedIndex();
				int year = (Integer)comboBox_8.getSelectedItem();
				comboBox_6.removeAllItems();
			    if(DateFunctions.IsLeapYear(year) && index==1) 
			    	for(int i=1;i<=29;i++) comboBox_6.addItem(i);
			    else for(int i=1;i<=DateClass.monthDays[index];i++) comboBox_6.addItem(i);
			} 
		});
		
		comboBox_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int month= comboBox_4.getSelectedIndex();
				int year= (Integer)comboBox_5.getSelectedItem();
				if(DateFunctions.IsLeapYear(year) && month==1){
					comboBox_3.removeAllItems();
					for(int i=1;i<=29;i++) comboBox_3.addItem(i);
				}
			}
		});
		
		comboBox_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int month= comboBox_7.getSelectedIndex();
				int year= (Integer)comboBox_8.getSelectedItem();
				if(DateFunctions.IsLeapYear(year) && month==1){
					comboBox_6.removeAllItems();
					for(int i=1;i<=29;i++) comboBox_6.addItem(i);
				}
			}
		});
		
		comboBox_4.setSelectedIndex(0);
		comboBox_7.setSelectedIndex(Integer.parseInt(points[1])-1);
		comboBox_5.setSelectedItem(Integer.parseInt(points[2]));
		comboBox_8.setSelectedItem(Integer.parseInt(points[2]));
		comboBox_3.setSelectedIndex(0);
		comboBox_6.setSelectedItem(Integer.parseInt(points[0]));
		
		JButton btnGetTheDifference = new JButton("Get the Difference betwen Days");
		btnGetTheDifference.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int x1= (Integer)comboBox_3.getSelectedItem(), y1= comboBox_4.getSelectedIndex()+1, z1= (Integer)comboBox_5.getSelectedItem();
					int x2= (Integer)comboBox_6.getSelectedItem(), y2= comboBox_7.getSelectedIndex()+1, z2= (Integer)comboBox_8.getSelectedItem();
					DateClass d1=new DateClass(x1,y1,z1), d2=new DateClass(x2,y2,z2);
					if(d1.equals(d2)) JOptionPane.showMessageDialog(null,"Both of the dates are the same","Difference between Days",1);
					else {
						long date=DateClass.getDifference(d1,d2);
						DateClass x=DateClass.getDateDifference(d1,d2);
						String message="Number of days  = "+date+" \nThere's a difference between "+x.yyyy+" years "+x.MM+" months and "+x.dd+" days";
						JOptionPane.showMessageDialog(null,message,"Difference between Days",1);
					}
				}catch(Exception exc) {
					exc.printStackTrace();
				}
			}
		});
		btnGetTheDifference.setFont(new Font("DejaVu Sans", Font.BOLD, 13));
		btnGetTheDifference.setBounds(16, 213, 380, 30);
		frame.getContentPane().add(btnGetTheDifference);
	}
}
