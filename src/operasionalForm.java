import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class operasionalForm extends JFrame {

	JMenuBar menu;
	JMenu user, pesanan;
	JMenuItem logoff,switchUser, daftarPesanan;
	JDesktopPane pane;
	
	private daftarPesanan dp = new daftarPesanan();
	
	public void init() {
		
		//DesktopPane
		pane = new JDesktopPane();
		
		//MenuBar
		menu = new JMenuBar();
		
		//Menu
		user = new JMenu("User");
		pesanan = new JMenu("Pesanan");
		
		//Menu Item
		logoff = new JMenuItem("Log off");
		switchUser = new JMenuItem("Switch User");
		daftarPesanan = new JMenuItem("Daftar Pesanan");
		
		//add menuItem to menu
		user.add(logoff);
		user.add(switchUser);
		
		pesanan.add(daftarPesanan);
		
		//Add to MenuBar
		menu.add(user);
		menu.add(pesanan);
		
		
	}
	
	public void listener() {
		logoff.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int response = JOptionPane.showConfirmDialog(null, "Are you sure want to close the program?");
				if ( response == JOptionPane.YES_OPTION) {
					dispose();
				
				}
				
			}
		});
		
		switchUser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int response = JOptionPane.showConfirmDialog(null, "Are you sure want to switch user ?");
				if (response == JOptionPane.YES_OPTION) {
					dispose();
					new login();
				}
				
			}
		});
		
		daftarPesanan.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dp.dispose();
				dp = new daftarPesanan();
				pane.add(dp);
				
			}
		});
	}
	
	public operasionalForm() {
		frame();
	}
	
	
	public void frame() {
		init();
		listener();
		setVisible(true);
		setSize(1700,1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Operasional Form");
		setJMenuBar(menu);
		add(pane);
		
	}
	
}
