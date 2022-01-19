import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;



public class adminForm extends JFrame {
 
 JMenuBar menuBar;
 JMenu user, manage;
 JMenuItem tambahStaff, Dashboard, Logoff, SwitchUsr;
 JDesktopPane pane;
 staffForm sForm;
 dashboardForm df;
 
 public void init() {
  //MenuBar
  menuBar = new JMenuBar();
  
  
  //Menu
  user = new JMenu("User");
  manage = new JMenu("Manage");
  
  //MenuItem
  Logoff = new JMenuItem("Log Off");
  SwitchUsr = new JMenuItem("Switch User");
  tambahStaff = new JMenuItem("Tambah Staff");
  Dashboard = new JMenuItem("Dashboard");
  
  //add Menu item
  user.add(Logoff);
  user.add(SwitchUsr);
  manage.add(Dashboard);
  manage.add(tambahStaff);
 
  
  //Add menu to MenuBar
  menuBar.add(user);
  menuBar.add(manage);
  
  
  //DesktopPane
  pane = new JDesktopPane();
 }
 
 public void listener() {
	 tambahStaff.addActionListener(new ActionListener() {
	   
	   @Override
	   public void actionPerformed(ActionEvent e) {
	    sForm = new staffForm();
	    pane.add(sForm);
	    try {
	    	 df.dispose();
		} catch (Exception e2) {
			// TODO: handle exception
		}
	   
	   }
	  });
	 
	 Logoff.addActionListener(new ActionListener() {
	  
	  @Override
	  public void actionPerformed(ActionEvent e) {
	   int response = JOptionPane.showConfirmDialog(null, "Are you sure want to close the program?");
	   if ( response == JOptionPane.YES_OPTION) {
	    dispose();
	   }
	   
	   
	  }
	 });
	 
	 SwitchUsr.addActionListener(new ActionListener() {
	  
	  @Override
	  public void actionPerformed(ActionEvent e) {
	   int response = JOptionPane.showConfirmDialog(null, "Are you sure want to switch user ?");
	   if (response == JOptionPane.YES_OPTION) {
	    dispose();
	    new login();
	   }
	
	  }
	 });
	 
	 Dashboard.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			df = new dashboardForm();
			pane.add(df);
			try {
				sForm.dispose();
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
		}
	});
 
}
 public adminForm() {
  frame();
 }
 public void frame() {
  init();
  listener();
  setVisible(true);
  setSize(1500,1000);
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  setLocationRelativeTo(null);
  setResizable(false);
  setTitle("Admin Form");
  setJMenuBar(menuBar);
  add(pane);
 }

}