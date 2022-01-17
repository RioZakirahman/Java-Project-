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
	 JMenu manage;
	 JMenuItem tambahStaff;
	 JDesktopPane pane;
	// staffForm sForm;
	
	 
	 public void init() {
	  //MenuBar
	  menuBar = new JMenuBar();
	  
	  
	  //Menu
	  manage = new JMenu("Manage");
	  
	  //MenuItem
	  tambahStaff = new JMenuItem("Tambah Staff");
	  
	  //add Menu item
	  manage.add(tambahStaff);
	  
	  //Add menu to MenuBar
	  menuBar.add(manage);
	  
	  
	  //DesktopPane
	  pane = new JDesktopPane();
	 }
	 
	 public void listener() {
	 tambahStaff.addActionListener(new ActionListener() {
	   
	   @Override
	   public void actionPerformed(ActionEvent e) {
	//    sForm = new staffForm();
	//    pane.add(sForm);
	   
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