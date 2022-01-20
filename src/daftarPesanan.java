
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class daftarPesanan extends JInternalFrame {

	JLabel titleLbl,titleRightLbl;
	JPanel leftPanel, rightJPanel, titlePnl, titleRightPnl, waitingTblScrollPnl, completedTblScrollPnl, processBtnPnl, searchBtnPnl, btnPnl, searchTxtPnl;
	JTable waitingTbl, completedTbl;
	JButton processBtn, searchBtn;
	JTextField searchTxt;
	DefaultTableModel waitingTblData, completedTblData;
	JScrollPane waitingTblScroll, completedTblScroll;
	private Connect con = Connect.getConnection();
	
	public void loadDataWaiting() {
		
		
		//Select From Order
		String query = "SELECT * FROM order_tbl WHERE Status = 0";
		con.rs = con.executeQuery(query);
		int package_typeID = 0;
		try {
			while (con.rs.next()) {
			
				int order_id = con.rs.getInt("OrderID");
				int customer_id = con.rs.getInt("CustomerID");
				package_typeID = con.rs.getInt("Package_TypeID");
				int weight = con.rs.getInt("Weight");
				int status = con.rs.getInt("Status");				
				String package_typeName = "";
				
				if (package_typeID == 1) {
					package_typeName = "Paket Satu";
				} else if (package_typeID == 2) {
					package_typeName = "Paket Dua";
				}
				
				Vector<Object> waitingTbllist = new Vector<>();
				waitingTbllist.add(order_id);
				waitingTbllist.add(customer_id);
				waitingTbllist.add(package_typeName);
				waitingTbllist.add(weight);
				
				waitingTblData.addRow(waitingTbllist);
		

			}	
			
				

				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void loadDataCompleted() {
		
		//Select From Order
				String query = "SELECT * FROM order_tbl WHERE Status = 1";
				con.rs = con.executeQuery(query);
				int package_typeID = 0;
				try {
					while (con.rs.next()) {
					
						int order_id = con.rs.getInt("OrderID");
						int customer_id = con.rs.getInt("CustomerID");
						package_typeID = con.rs.getInt("Package_TypeID");
						int weight = con.rs.getInt("Weight");	
						String Status = "Sudah Dikerjakan";
						String package_typeName = "";
						
						if (package_typeID == 1) {
							package_typeName = "Paket Satu";
						} else if (package_typeID == 2) {
							package_typeName = "Paket Dua";
						}
						
					
						Vector<Object> completedTbllist = new Vector<>();
						completedTbllist.add(order_id);
						completedTbllist.add(customer_id);
						completedTbllist.add(package_typeName);
						completedTbllist.add(weight);
						completedTbllist.add(Status);
						
						completedTblData.addRow(completedTbllist);
				

					}	
					
						

						
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
	public void listener() {
		processBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRowIndex = waitingTbl.getSelectedRow();;
				if (selectedRowIndex == -1) {
					JOptionPane.showMessageDialog(null, "Please select an order!");
				} else {
				
					int response = JOptionPane.showConfirmDialog(null, "Are you sure want to process the order ?");
					if (response == JOptionPane.YES_OPTION) {
						int order_id = (int) waitingTblData.getValueAt(selectedRowIndex, 0);
						String queryUpdate = "UPDATE order_tbl SET Status = ? WHERE OrderID = ? ";
						PreparedStatement psUpdate = con.prepareStatement(queryUpdate);
						try {
							psUpdate.setInt(1, 1);
							psUpdate.setInt(2, order_id);
							psUpdate.executeUpdate();
							JOptionPane.showMessageDialog(null, "Order successfully processed");
							waitingTblData.setRowCount(0);
							completedTblData.setRowCount(0);
							loadDataCompleted();
							loadDataWaiting();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					} else {
						
					}
		
				
		
				}
				
				
			}
		});
		
		TableRowSorter<TableModel> sorter = new TableRowSorter<>(waitingTblData);
		waitingTbl.setRowSorter(sorter);
		
		searchTxt.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
			searchBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					String text = searchTxt.getText();	
					if (text.trim().length() == 0) {
	                    sorter.setRowFilter(null);
	                } else {
	                	sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
	                }
					
					
				}
			});
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				searchBtn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String text = searchTxt.getText();	
						if (text.trim().length() == 0) {
		                    sorter.setRowFilter(null);
		                } else {
		                	sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
		                }
						
						
					}
				});
				
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				searchBtn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String text = searchTxt.getText();	
						if (text.trim().length() == 0) {
		                    sorter.setRowFilter(null);
		                } else {
		                	sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
		                }
						
						
					}
				});
				
			}
		});
	
	}
	
	public void init() {
		
		Vector<Object> headerWaiting = new Vector<>();
		headerWaiting.add("Order ID");
		headerWaiting.add("Customer ID");
		headerWaiting.add("Package");
		headerWaiting.add("Weight");
		
		Vector<Object> headerCompleted = new Vector<>();
		headerCompleted.add("Order ID");
		headerCompleted.add("Customer ID");
		headerCompleted.add("Package");
		headerCompleted.add("Weight");
		headerCompleted.add("Status");
		
		
		//Button
		processBtn = new JButton("Process");
		processBtn.setPreferredSize(new Dimension(150,40));
		
		searchBtn = new JButton("Search");
		searchBtn.setPreferredSize(new Dimension(150,40));
		
		//DefaultTableModel
		waitingTblData = new DefaultTableModel(headerWaiting, 0);
		completedTblData = new DefaultTableModel(headerCompleted, 0);
		
		
		//Tabel
		waitingTbl = new JTable(waitingTblData) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		
		completedTbl = new JTable(completedTblData) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		
		
		//Scroll
		waitingTblScroll = new JScrollPane(waitingTbl);
		waitingTblScroll.setPreferredSize(new Dimension(500,700));
		completedTblScroll = new JScrollPane(completedTbl);
		completedTblScroll.setPreferredSize(new Dimension(500,700));
		
		//Label
		titleLbl = new JLabel("Waiting Order");
		titleLbl.setFont(new Font("Arial", Font.BOLD, 25));
		
		titleRightLbl = new JLabel("Completed Order");
		titleRightLbl.setFont(new Font("Arial", Font.BOLD, 25));
		
		//Text
		searchTxt = new JTextField();
		searchTxt.setPreferredSize(new Dimension(200,40));
		
		//Panel
		
			//Main Panel
			leftPanel = new JPanel(new BorderLayout());
			leftPanel.setBorder(BorderFactory.createLineBorder(Color.black));
			rightJPanel = new JPanel(new BorderLayout());
			rightJPanel.setBorder(BorderFactory.createLineBorder(Color.black));
			//
		
			titlePnl = new JPanel();
			titleRightPnl = new JPanel();
		
			waitingTblScrollPnl = new JPanel();
			completedTblScrollPnl = new JPanel();
			
			processBtnPnl = new JPanel();
			searchBtnPnl = new JPanel();
			
			btnPnl = new JPanel();
			
			searchTxtPnl = new JPanel();
		
		//
		
			//Add to Panel
			titlePnl.add(titleLbl);
			titleRightPnl.add(titleRightLbl);
			
			waitingTblScrollPnl.add(waitingTblScroll);
			completedTblScrollPnl.add(completedTblScroll);
			
			processBtnPnl.add(processBtn);
			processBtnPnl.setPreferredSize(new Dimension(160,100));
			
			searchBtnPnl.add(searchBtn);
			searchBtnPnl.setPreferredSize(new Dimension(150,100));
			
			searchTxtPnl.add(searchTxt);
			searchTxtPnl.setPreferredSize(new Dimension(200,100));
			
			btnPnl.add(processBtnPnl);
			btnPnl.add(searchBtnPnl);
			btnPnl.add(searchTxtPnl);
			
			
			//Add to Main Panel
			leftPanel.add(titlePnl, BorderLayout.NORTH);
			leftPanel.add(waitingTblScrollPnl,BorderLayout.CENTER);
			leftPanel.add(btnPnl, BorderLayout.SOUTH);
			rightJPanel.add(titleRightPnl, BorderLayout.NORTH);
			rightJPanel.add(completedTblScrollPnl, BorderLayout.CENTER);
		
	}
	
	public void frame() {
		init();
		listener();
		loadDataWaiting();
		loadDataCompleted();
		setVisible(true);
		setSize(1600,900);
		setClosable(true);
		setIconifiable(true);
		JPanel mainPanel = new JPanel(new GridLayout(1,2));
		add(mainPanel);
		mainPanel.add(leftPanel);
		mainPanel.add(rightJPanel);
		
	}
	
	public daftarPesanan() {
		frame();
	}

}
