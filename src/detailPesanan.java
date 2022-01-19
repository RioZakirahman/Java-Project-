import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
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

public class detailPesanan extends JInternalFrame {
	
	DefaultTableModel viewTblData, detailTblData;
	JTable viewTbl,detailTbl;
	JScrollPane viewTblScroll, detailTblScroll;
	JPanel leftPanel, rightPanel, viewTblScrollPnl, detailTblScrollPnl, searchBtnPnl, searchTxtPnl, visibleBtnPnl, northPanel, searchPnl;
	JLabel titleLbl;
	JButton searchBtn , visibleBtn, visibleBtn2, visibleBtn3;
	JTextField searchTxt;
	
	private Connect con = Connect.getConnection();
	
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
		detailHeader.add("No_telp");
		detailHeader.add("Address");
		detailHeader.add("Payment_Type");
		detailHeader.add("Order_Date");
		detailHeader.add("Return_Date");
		detailHeader.add("Weight");
		detailHeader.add("Status");
		
		//Button
		searchBtn = new JButton("Search");
		searchBtn.setPreferredSize(new Dimension(150,40));
		visibleBtn = new JButton();
		visibleBtn.setVisible(false);;
		visibleBtn2 = new JButton();
		visibleBtn2.setVisible(false);
		visibleBtn3 = new JButton();
		visibleBtn3.setVisible(false);

		//Label
		titleLbl = new JLabel("Detail Pesanan");
		
		//Text
		searchTxt = new JTextField();
		searchTxt.setPreferredSize(new Dimension(200,40));
		
		//Dtm
		viewTblData = new DefaultTableModel(viewHeader,0);
		detailTblData = new DefaultTableModel(detailHeader,0);
		
		//Table
		viewTbl = new JTable(viewTblData) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		detailTbl = new JTable(detailTblData) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		
		//Scroll
		viewTblScroll = new JScrollPane(viewTbl);
		viewTblScroll.setPreferredSize(new Dimension(500,700));
		detailTblScroll = new JScrollPane(detailTbl);
		detailTblScroll.setPreferredSize(new Dimension(1000,700));
		
		//Panel
		leftPanel = new JPanel();
		rightPanel = new JPanel();
		searchBtnPnl = new JPanel();
		searchTxtPnl = new JPanel();
		searchTxtPnl.setPreferredSize(new Dimension(200,140));
		visibleBtnPnl = new JPanel();
		northPanel = new JPanel(new GridLayout(1,4));
		searchBtnPnl.setPreferredSize(new Dimension(150,140));
		searchPnl = new JPanel();


		
		viewTblScrollPnl = new JPanel();
		detailTblScrollPnl = new JPanel();
		
		
		//Add comp to panel
		viewTblScrollPnl.add(viewTblScroll);
		detailTblScrollPnl.add(detailTblScroll);
		searchBtnPnl.add(searchBtn);
		searchTxtPnl.add(searchTxt);
		searchPnl.add(searchBtnPnl);
		searchPnl.add(searchTxtPnl);
		visibleBtnPnl.add(visibleBtn);
		
		leftPanel.add(viewTblScrollPnl);
		rightPanel.add(detailTblScrollPnl);

		northPanel.add(searchPnl);
		northPanel.add(visibleBtnPnl);
		northPanel.add(visibleBtn2);
		northPanel.add(visibleBtn3);
		
		
	}
	
	public void listener() {
		viewTbl.addMouseListener(new MouseListener() {
			int selectedRowIndex;
			@Override
			public void mouseReleased(MouseEvent e) {
				
				detailTblData.setRowCount(0);
				selectedRowIndex = viewTbl.getSelectedRow();
					int payment_typeID = 0;
					int customerID = 0;
					int transactionID = 0;
					int paymentID = 0;
					String no_telp = "";
					String address = "";
					int order_id = 0;
					if (selectedRowIndex != -1) {
						order_id = (int) viewTblData.getValueAt(selectedRowIndex, 0);
					}
					
					String query = "SELECT * FROM order_tbl WHERE OrderID = " + order_id;
					con.rs = con.executeQuery(query);
					
					try {
						while (con.rs.next()) {
							
							String payment_typeName = "";
							int orderID = con.rs.getInt("OrderID");
							customerID = con.rs.getInt("CustomerID");
							transactionID = con.rs.getInt("TransactionID");
							int package_typeID = con.rs.getInt("Package_TypeID");
							int staffID = con.rs.getInt("staffID");
							String order_date = con.rs.getString("Order_Date");
							String return_date = con.rs.getString("Return_Date");
							int weight = con.rs.getInt("Weight");
							int statusNo = con.rs.getInt("Status");
							String status = "";
							if (statusNo == 0) {
								status = "Belum Dikerjakan";
							} else {
								status = "Sudah Dikerjakan";
							}
							
							//Select Customer
							String queryCustomer = "SELECT * FROM customer WHERE CustomerID =?";
							PreparedStatement psCustomer = con.prepareStatement(queryCustomer);
							psCustomer.setInt(1, customerID);
							con.rs = psCustomer.executeQuery();
							while (con.rs.next()) {
								no_telp = con.rs.getString("No_telp");
								address = con.rs.getString("Address");
							}
							
							//Select Transaction
							String queryTransaction = "SELECT * FROM transaction WHERE TransactionID = ?";
							PreparedStatement psTransaction = con.prepareStatement(queryTransaction);
							psTransaction.setInt(1, transactionID);
							con.rs = psTransaction.executeQuery();
							while (con.rs.next()) {
							paymentID = con.rs.getInt("PaymentID");
							}
							
							//Select Payment
							String queryPayment = "SELECT * FROM payment WHERE PaymentID = ?";
							PreparedStatement psPayment = con.prepareStatement(queryPayment);
							psPayment.setInt(1, paymentID);
							con.rs = psPayment.executeQuery();
							while (con.rs.next()) {
								payment_typeID = con.rs.getInt("PaymentTypeID");
								
							}
							//Select PaymentTYpe
							String queryPaymentType = "SELECT * FROM paymenttype WHERE PaymentTypeID = ? ";
							PreparedStatement psPaymentType = con.prepareStatement(queryPaymentType);
							psPaymentType.setInt(1, payment_typeID);
							con.rs = psPaymentType.executeQuery();
							while (con.rs.next()) {
								payment_typeName = con.rs.getString("PaymentTypeName");
							}
							
							Vector<Object> data = new Vector<>();
					
							data.add(no_telp);
							data.add(address);
							data.add(payment_typeName);
							data.add(order_date);
							data.add(return_date);
							data.add(weight);
							data.add(status);
							
							detailTblData.addRow(data);
							
							
							
						}
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
					
					
				
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			
			
			
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	
		TableRowSorter<TableModel> sorter = new TableRowSorter<>(viewTblData);
		viewTbl.setRowSorter(sorter);
		
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
	
	public void loadData(ResultSet rs) {
		try {
			while (rs.next()) {
				int order_id = rs.getInt("order_tbl.OrderID");
				int customer_id = rs.getInt("order_tbl.CustomerID");
				int package_id = rs.getInt("Package_typeID");
				int staff_id= rs.getInt("order_tbl.staffID");
				String customer_name = rs.getString("customer.Customer_Name");
				String package_type_name = rs.getString("packagetype.Package_TypeName");
				int total_price= rs.getInt("transaction.Total_Price");
				
				Vector<Object> listData = new Vector<>();
				listData.add(order_id);
				listData.add(staff_id);
				listData.add(customer_name);
				listData.add(package_type_name);
				listData.add(total_price);
			
				viewTblData.addRow(listData);
				
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public void frame() {
		init();
		listener();
		String querySelect = "SELECT * FROM order_tbl JOIN customer ON order_tbl.CustomerID = customer.CustomerID JOIN packagetype ON order_tbl.Package_TypeID = packagetype.Package_TypeID JOIN staff ON order_tbl.staffID = staff.staffID JOIN transaction ON order_tbl.TransactionID = transaction.TransactionID ";
		loadData(con.executeQuery(querySelect));
		JPanel mainPanel = new JPanel(new BorderLayout());
		setVisible(true);
		setSize(1600,900);
		setClosable(true);
		setIconifiable(true);
		mainPanel.add(leftPanel, BorderLayout.WEST);
		mainPanel.add(rightPanel, BorderLayout.CENTER);
		mainPanel.add(northPanel, BorderLayout.SOUTH);
		add(mainPanel);
	
	}

}
