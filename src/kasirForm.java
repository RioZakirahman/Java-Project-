

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;



public class kasirForm extends JFrame {
	
	JMenuBar menuBar;
	JMenu User, Pesanan;
	JMenuItem listPesanan, detailPesanan, Logoff, SwitchUsr;
	JDesktopPane pane;
	pesananForm pf;
	detailPesanan dp;
	
	public void init() {
		//MenuBar
		menuBar = new JMenuBar();
		
		//Menu
		User = new JMenu("User");
		Pesanan = new JMenu("Pesanan");
		
		//Menu Item
		listPesanan = new JMenuItem("Form Pesanan");
		detailPesanan = new JMenuItem("Detail Pesanan");
		Logoff = new JMenuItem("Log off");
		SwitchUsr = new JMenuItem("Switch User");
		
		//add Menu item
		Pesanan.add(listPesanan);
		Pesanan.add(detailPesanan);
		User.add(Logoff);
		User.add(SwitchUsr);
		
		//Add menu to MenuBar
		menuBar.add(User);
		menuBar.add(Pesanan);
		
		
		//DesktopPane
		pane = new JDesktopPane();
	}
	
	public void listener() {
		listPesanan.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				pf = new pesananForm();
				pane.add(pf);
				try {
					dp.dispose();
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
				
			}
		});
		
		detailPesanan.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dp = new detailPesanan();
				pane.add(dp);
				try {
					pf.dispose();
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
		
	}
	
	public kasirForm() {
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
		setTitle("Kasir Form");
		setJMenuBar(menuBar);
		add(pane);
	}

}
