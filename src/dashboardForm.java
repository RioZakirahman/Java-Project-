import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class dashboardForm extends JInternalFrame {
	JTextField revenueTxt, transactionTxt;
	JLabel titleLbl, revenueLbl, transactionLbl;
	JPanel titleLblPnl, centerPnl, revenueLblPnl, transactionLblPnl, revenueTxtPnl, transactionTxtPnl, southPnl, reportScrollPanePnl;
	private Connect con = Connect.getConnection();
	JTable reportTable;
	DefaultTableModel reportTableModel;
	JScrollPane reportScrollPane;
	
	public void loadTableData() {
		String querySelect = "SELECT * FROM order_tbl";
		int staffID = 0;
		con.rs = con.executeQuery(querySelect);
		try {
			while (con.rs.next()) {
				staffID = con.rs.getInt("staffID");
			}
			for (int i = 1; i < staffID+1; i++) {
				String queryCount = "SELECT staffID,COUNT(OrderID) FROM Order_tbl WHERE staffID  =?";
				PreparedStatement ps = con.prepareStatement(queryCount);
				ps.setInt(1, i);
				con.rs = ps.executeQuery();
				while (con.rs.next()) {
					int staff_id = con.rs.getInt("staffID");
					int total_order = con.rs.getInt("COUNT(OrderID)");
					
					Vector<Object> listData = new Vector<>();
					listData.add(staff_id);
					listData.add(total_order);
					
					reportTableModel.addRow(listData);
					
				}
			}

				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String queryRevenue = "SELECT SUM(Total_Price) FROM transaction";
		con.rs = con.executeQuery(queryRevenue);
		
		try {
			while (con.rs.next()) {
				int total_price = con.rs.getInt("SUM(Total_Price)");
				String total = String.valueOf(total_price);
				
				revenueTxt.setText(total);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String queryOrder = "SELECT COUNT(OrderID) FROM order_tbl";
		con.rs = con.executeQuery(queryOrder);
		
		try {
			while (con.rs.next()) {
				int countOrder = con.rs.getInt("COUNT(OrderID)");
				String count_order = String.valueOf(countOrder);
				transactionTxt.setText(count_order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void init() {
		
		Vector<Object> header = new Vector<>();
		header.add("Staff ID");
		header.add("Total Transaction");
		
		//Default table model
		reportTableModel = new DefaultTableModel(header,0);

		
		//Table
		reportTable = new JTable(reportTableModel);
		reportTable.setOpaque(false);
	
		
		//Scroll
		reportScrollPane = new JScrollPane(reportTable);
		reportScrollPane.setOpaque(false);

		
		
		//TextField		
		revenueTxt = new JTextField();
		revenueTxt.setEditable(false);
		revenueTxt.setPreferredSize(new Dimension(500,50));
		Border border = BorderFactory.createLineBorder(Color.black, 3);
		revenueTxt.setBorder(border);
		revenueTxt.setFont(new Font("Arial", Font.BOLD, 25));
		revenueTxt.setHorizontalAlignment(JTextField.CENTER);
		
		transactionTxt = new JTextField();
		transactionTxt.setEditable(false);
		transactionTxt.setPreferredSize(new Dimension(500,50));
		transactionTxt.setBorder(border);
		transactionTxt.setFont(new Font("Arial", Font.BOLD, 25));
		transactionTxt.setHorizontalAlignment(JTextField.CENTER);
		
		//Label
		titleLbl = new JLabel("Dashboard");
		titleLbl.setFont(new Font("Arial", Font.BOLD, 25));
		titleLbl.setForeground(Color.black);
	
	
		revenueLbl = new JLabel("Total revenue");
		revenueLbl.setFont(new Font("Arial", Font.BOLD, 25));
		revenueLbl.setForeground(Color.black);
		revenueLbl.setPreferredSize(new Dimension(180,50));
		
		transactionLbl = new JLabel("Total Order");
		transactionLbl.setFont(new Font("Arial", Font.BOLD, 25));
		transactionLbl.setForeground(Color.black);
		transactionLbl.setPreferredSize(new Dimension(180,50));
		
		
		//Panel
		titleLblPnl = new JPanel();
		titleLblPnl.setBorder(BorderFactory.createRaisedBevelBorder()); 
//		titleLblPnl.setBackground(Color.GREEN);
		
		
		revenueLblPnl = new JPanel();
		revenueLblPnl.setOpaque(false);
		revenueLblPnl.add(revenueLbl);
		
		revenueTxtPnl = new JPanel();
		revenueTxtPnl.setOpaque(false);
		revenueTxtPnl.add(revenueTxt);
		
		transactionLblPnl = new JPanel();
		transactionLblPnl.setOpaque(false);
		transactionLblPnl.add(transactionLbl);
		
		transactionTxtPnl= new JPanel();
		transactionTxtPnl.setOpaque(false);
		transactionTxtPnl.add(transactionTxt);
		
		reportScrollPanePnl = new JPanel();
		reportScrollPanePnl.add(reportScrollPane);
		reportScrollPanePnl.setOpaque(false);
		reportScrollPanePnl.setPreferredSize(new Dimension(700,500));

		
		
		//Center Pnl
		centerPnl = new JPanel(new GridLayout(2,2, 0,-100));
		centerPnl.setBackground(Color.white);
		centerPnl.add(revenueLblPnl);
		centerPnl.add(revenueTxtPnl);
		centerPnl.add(transactionLblPnl);
		centerPnl.add(transactionTxtPnl);
		
		//South Panel
		southPnl = new JPanel();
		southPnl.setBackground(Color.white);
		southPnl.add(reportScrollPanePnl);
		
		
		
		//Add to Panel
		titleLblPnl.add(titleLbl);
	}
	
	public void frame() {
		init();
		loadTableData();
		setVisible(true);
		setSize(1200,900);
		setClosable(true);
		setIconifiable(true);
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setOpaque(false);
		mainPanel.add(titleLblPnl, BorderLayout.NORTH);
		mainPanel.add(centerPnl, BorderLayout.CENTER);
		mainPanel.add(southPnl, BorderLayout.SOUTH);
		add(mainPanel);
	}
	
	public dashboardForm() {
		frame();
	}

}
