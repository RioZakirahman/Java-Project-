import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class detailPesanan extends JInternalFrame {
	
	DefaultTableModel viewTblData, detailTblData;
	JTable viewTbl,detailTbl;
	JScrollPane viewTblScroll, detailTblScroll;
	JPanel leftPanel, rightPanel, viewTblScrollPnl, detailTblScrollPnl;
	JLabel titleLbl;
	
	
	public detailPesanan() {
		frame();
	}
	
	public void init() {
		
		//Vector
		Vector<Object> viewHeader = new Vector<>();
		viewHeader.add("Order ID");
		viewHeader.add("Staff ID");
		viewHeader.add("Customer Name");
		viewHeader.add("Package");
		viewHeader.add("Total Harga");
		
		Vector<Object> detailHeader = new Vector<>();
		detailHeader.add("Order ID");
		detailHeader.add("Customer ID");
		detailHeader.add("Transaction ID");
		detailHeader.add("Package_TypeID");
		detailHeader.add("Staff ID");
		detailHeader.add("Order_Date");
		detailHeader.add("Return_Date");
		detailHeader.add("Weight");
		detailHeader.add("Status");
		
		//Label
		titleLbl = new JLabel("Title");
		
		
		//Dtm
		viewTblData = new DefaultTableModel(viewHeader,0);
		detailTblData = new DefaultTableModel(detailHeader,0);
		
		//Table
		viewTbl = new JTable(viewTblData);
		detailTbl = new JTable(detailTblData);
		
		//Scroll
		viewTblScroll = new JScrollPane(viewTbl);
		viewTblScroll.setPreferredSize(new Dimension(500,700));
		detailTblScroll = new JScrollPane(detailTbl);
		detailTblScroll.setPreferredSize(new Dimension(800,700));
		
		//Panel
		leftPanel = new JPanel();
		rightPanel = new JPanel();

		
		viewTblScrollPnl = new JPanel();
		detailTblScrollPnl = new JPanel();
		
		
		//Add comp to panel
		viewTblScrollPnl.add(viewTblScroll);
		detailTblScrollPnl.add(detailTblScroll);
		
		leftPanel.add(viewTblScrollPnl);
		rightPanel.add(detailTblScrollPnl);
		

		
		
	}
	
	public void frame() {
		init();
		JPanel mainPanel = new JPanel();
		setVisible(true);
		setSize(1400,900);
		setClosable(true);
		setIconifiable(true);
		mainPanel.add(leftPanel);
		mainPanel.add(rightPanel);
		add(mainPanel);
	
	}

}
