




import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;


public class pesananForm extends JInternalFrame {

	JTable orderTbl;
	DefaultTableModel data;
	JScrollPane orderTblScroll;
	JPanel titleLblPnl,leftPnl,rightPnl,mainLeftPnl,orderTblPnl, form1Pnl, form2Pnl, nameLblPnl, nameTxtPnl,notelpPnl,notelpTxtPnl, alamatPnl,alamatTxtPnl, orderDatePnl, tanggalPesananPnl, jenisPaketLblPnl, jenisPaketPnl, beratPakaianLblPnl, beratPakaianPnl,jenisPembayaranLblPnl,jenisPembayaranPnl, pricePnl, priceTxtPnl, returnDateLblPnl, returnDatePnl, visibleLblPnl, visibleTxtPnl, addBtnPnl,deleteBtnPnl, southPnl, centerPnl, orderTblScrollPnl, updateBtnPnl;
	JLabel titleLbl,nameLbl, notelp, alamat, tanggalPesanan, jenisPaketLbl, beratPakaianLbl, jenisPembayaranLbl, price, returnDateLbl, visibleLbl;
	JTextField nameTxt, notelpTxt, priceTxt, visibleTxt;
	JTextArea alamatTxt;
	JDateChooser orderDate, returnDate;
	JComboBox<String> jenisPaket, jenisPembayaran, beratPakaian;
	JButton addBtn,updateBtn, deleteBtn;
	private int packageTypeID;
	private int total_price;
	private int packageWeight;
	private Connect con = Connect.getConnection();
	int paymentID = getCountRowPayment(con.executeQuery("SELECT max(PaymentID) FROM payment"));;
	int customerID = getCountRowCustomer(con.executeQuery("SELECT max(CustomerID) FROM customer"));
	int transactionID = getCountRowTransaction(con.executeQuery("SELECT max(TransactionID) FROM transaction"));;
	public void init() {
		
		
		//Table

		Vector<Object> header = new Vector<>();
		header.add("Order ID");
		header.add("Staff ID");
		header.add("Customer Name");
		header.add("Package");
		header.add("Total Harga");
		
		data = new DefaultTableModel(header,0);
		orderTbl = new JTable(data);
		orderTblScroll = new JScrollPane(orderTbl);
		orderTblScroll.setPreferredSize(new Dimension(500,700));
		
		String querySelect = "SELECT * FROM order_tbl JOIN customer ON order_tbl.CustomerID = customer.CustomerID JOIN packagetype ON order_tbl.Package_TypeID = packagetype.Package_TypeID JOIN staff ON order_tbl.staffID = staff.staffID JOIN transaction ON order_tbl.TransactionID = transaction.TransactionID ";
		loadData(con.executeQuery(querySelect));
		
		//Button
		addBtn = new JButton("Add");
		addBtn.setPreferredSize(new Dimension(150,40));
		deleteBtn = new JButton("Delete");
		deleteBtn.setPreferredSize(new Dimension(150,40));
		updateBtn = new JButton("Update");
		updateBtn.setPreferredSize(new Dimension(150,40));

		
		//ComboBox
		Vector<String> jenisPaketlist = new Vector<>();
		jenisPaketlist.add("Paket Satu");
		jenisPaketlist.add("Paket Dua");
		
		Vector<String> beratPakaianlist = new Vector<>();
		beratPakaianlist.add("0 kg");
		beratPakaianlist.add("1 kg");
		beratPakaianlist.add("2 kg");
		beratPakaianlist.add("3 kg");
		beratPakaianlist.add("4 kg");
		beratPakaianlist.add("5 kg");
		
		Vector<String> jenisPembayaranlist = new Vector<>();
		jenisPembayaranlist.add("CASH");
		jenisPembayaranlist.add("OVO");
		jenisPembayaranlist.add("DANA");
		
		
		
		jenisPaket= new JComboBox<>(jenisPaketlist);
		jenisPaket.setPreferredSize(new Dimension(150,30));
	
	
		
		beratPakaian = new JComboBox<>(beratPakaianlist);
		beratPakaian.setPreferredSize(new Dimension(150,30));
	
		
		jenisPembayaran = new JComboBox<>(jenisPembayaranlist);
		jenisPembayaran.setPreferredSize(new Dimension(150,30));
	
		
		//Label
		titleLbl = new JLabel("Form Pesanan");
		titleLbl.setFont(new Font("Arial", Font.BOLD, 25));
		titleLbl.setPreferredSize(new Dimension(180,50));
		nameLbl = new JLabel("Nama Customer");
		notelp = new JLabel("No telp");
		alamat = new JLabel("Alamat");
		tanggalPesanan = new JLabel("Tanggal Pemesanan");
		jenisPaketLbl = new JLabel("Jenis Paket");
		beratPakaianLbl = new JLabel("Berat Pakaian");
		jenisPembayaranLbl = new JLabel("Jenis Pembayaran");
		price = new JLabel("Total Harga");
		returnDateLbl = new JLabel("Tanggal Pengembalian");


		
		//TextField
		nameTxt = new JTextField();
		nameTxt.setPreferredSize(new Dimension(150,30));
		notelpTxt = new JTextField();
		notelpTxt.setPreferredSize(new Dimension(150,30));
		alamatTxt = new JTextArea();
		alamatTxt.setPreferredSize(new Dimension(150,50));
		priceTxt = new JTextField();
		priceTxt.setPreferredSize(new Dimension(150,30));
		priceTxt.setEditable(false);
		visibleTxt = new JTextField();
		visibleTxt.setVisible(false);
		
		//date
		orderDate = new JDateChooser();
		orderDate.setDateFormatString("YYYY-MM-DD");
		orderDate.setPreferredSize(new Dimension(150,30));
		
		
		returnDate = new JDateChooser();
		returnDate.setDateFormatString("YYYY-MM-DD");
		returnDate.setPreferredSize(new Dimension(150,30));
		
		//Panel
		nameTxtPnl = new JPanel();
		leftPnl = new JPanel(new GridLayout());
		rightPnl = new JPanel();
		centerPnl = new JPanel(new GridLayout(1,2));
		mainLeftPnl = new JPanel(new GridLayout(1,2,3,0));
		form1Pnl = new JPanel(new GridLayout(5,2));
		form2Pnl = new JPanel(new GridLayout(5,2));
		nameLblPnl = new JPanel();
		notelpTxtPnl = new JPanel();
		notelpPnl = new JPanel();
		alamatPnl = new JPanel();
		alamatTxtPnl = new JPanel();
		orderDatePnl = new JPanel();
		tanggalPesananPnl = new JPanel();
		jenisPaketLblPnl = new JPanel();
		jenisPaketPnl = new JPanel();
		beratPakaianLblPnl = new JPanel();
		beratPakaianPnl = new JPanel();
		jenisPembayaranLblPnl = new JPanel();
		jenisPembayaranPnl = new JPanel();
		pricePnl = new JPanel();
		priceTxtPnl = new JPanel();
		returnDateLblPnl = new JPanel();
		returnDatePnl = new JPanel();
		visibleTxtPnl = new JPanel();
		visibleLblPnl = new JPanel();
		addBtnPnl = new JPanel();
		deleteBtnPnl = new JPanel();
		southPnl = new JPanel();
		orderTblScrollPnl = new JPanel();
		titleLblPnl = new JPanel();
		updateBtnPnl = new JPanel();
		
		//set Panel
		beratPakaianLblPnl.setOpaque(false);
		beratPakaianPnl.setOpaque(false);
		jenisPembayaranLblPnl.setOpaque(false);
		jenisPembayaranPnl.setOpaque(false);
		
		//Add Panel
		titleLblPnl.add(titleLbl);
		nameTxtPnl.add(nameTxt);
		nameLblPnl.add(nameLbl);
		notelpTxtPnl.add(notelpTxt);
		notelpPnl.add(notelp);
		alamatPnl.add(alamat);
		alamatTxtPnl.add(alamatTxt);
		orderDatePnl.add(orderDate);
		tanggalPesananPnl.add(tanggalPesanan);
		jenisPaketLblPnl.add(jenisPaketLbl);
		jenisPaketPnl.add(jenisPaket);
		beratPakaianLblPnl.add(beratPakaianLbl);
		beratPakaianPnl.add(beratPakaian);
		jenisPembayaranLblPnl.add(jenisPembayaranLbl);
		jenisPembayaranPnl.add(jenisPembayaran);
		pricePnl.add(price);
		priceTxtPnl.add(priceTxt);
		returnDateLblPnl.add(returnDateLbl);
		returnDatePnl.add(returnDate);

		addBtnPnl.add(addBtn);
		deleteBtnPnl.add(deleteBtn);
	
		updateBtnPnl.add(updateBtn);
		orderTblScrollPnl.add(orderTblScroll);
		
		
		
		//South Panel
		southPnl.add(addBtn);
		southPnl.add(deleteBtn);
		southPnl.add(updateBtnPnl);

		
		//Center Panel
		centerPnl.add(leftPnl);
		centerPnl.add(rightPnl);
		
		leftPnl.add(mainLeftPnl);
		rightPnl.add(orderTblScrollPnl);
		
		//
		mainLeftPnl.add(form1Pnl);
		mainLeftPnl.add(form2Pnl);
		
		
		//inside mainLeftPnl
		form1Pnl.add(nameLblPnl);
		form1Pnl.add(nameTxtPnl);
		form1Pnl.add(notelpPnl);
		form1Pnl.add(notelpTxtPnl);
		form1Pnl.add(alamatPnl);
		form1Pnl.add(alamatTxtPnl);
		form1Pnl.add(tanggalPesananPnl);
		form1Pnl.add(orderDatePnl);
		form1Pnl.add(jenisPaketLblPnl);
		form1Pnl.add(jenisPaketPnl);
		form2Pnl.add(beratPakaianLblPnl);
		form2Pnl.add(beratPakaianPnl);
		form2Pnl.add(jenisPembayaranLblPnl);
		form2Pnl.add(jenisPembayaranPnl);
		form2Pnl.add(pricePnl);
		form2Pnl.add(priceTxtPnl);
		form2Pnl.add(returnDateLblPnl);
		form2Pnl.add(returnDatePnl);

		
		
		
	}
	
	public pesananForm() {
		frame();
	}
	
	public Integer loadTransaction(ResultSet rs) {
	int payment_id = 0;
		try {
			
			while (rs.next()) {
				payment_id = rs.getInt("PaymentID");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return payment_id;
	}
	
	public void DeleteData(ResultSet rs) {
		try {
			while (rs.next()) {
				paymentID = paymentID + 1;
				customerID = customerID + 1;
				transactionID = transactionID + 1;
				int order_id = rs.getInt("OrderID");
				int customer_id = rs.getInt("CustomerID");
				int transaction_id = rs.getInt("TransactionID");
				

				int payment_id = loadTransaction(con.executeQuery("SELECT * FROM transaction WHERE TransactionID = " + transaction_id));
				
				
				//Delete From Table Order
//				String deleteQuery = "DELETE FROM order_tbl WHERE OrderID =?";
//				PreparedStatement psOrder = con.prepareStatement(deleteQuery);
//				
//				try {
//					psOrder.setInt(1, order_id);
//					psOrder.executeUpdate();
//					System.out.println(psOrder.toString());
//
//					
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}

					
				
//				//Delete From Table Transaction
//				String deleteTransaction = "DELETE FROM transaction WHERE TransactionID=?";
//				PreparedStatement psTransaction = con.prepareStatement(deleteTransaction);
//				
//				try {
//					psTransaction.setInt(1, transaction_id);
//					psTransaction.executeUpdate();
//					System.out.println(psTransaction.toString());
//				} catch (Exception e) {
//					
//				}
				//Delete from payment
				String deletePayment = "DELETE FROM payment WHERE PaymentID = ?";
				PreparedStatement psPayment = con.prepareStatement(deletePayment);
				try {
					psPayment.setInt(1, payment_id);
					psPayment.executeUpdate();
					System.out.println(psPayment.toString());
				} catch (Exception e) {
		
				}
				
//				//Delete from table Customer
				String deleteCustomer = "DELETE FROM customer WHERE CustomerID = ?";
				PreparedStatement psCustomer = con.prepareStatement(deleteCustomer);
				try {
					psCustomer.setInt(1, customer_id);
					psCustomer.executeUpdate();
					System.out.println(psCustomer.toString());
				} catch (Exception e) {
					
				}
		
				
		
				JOptionPane.showMessageDialog(null, "Data successfully deleted!");
			
				
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
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
			
				data.addRow(listData);
				
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public void addListener() {
		
		
		
		jenisPaket.addItemListener(new ItemListener() {
	
			@Override
			public void itemStateChanged(ItemEvent e) {
				String weight = beratPakaian.getSelectedItem().toString();
				String packageType = jenisPaket.getSelectedItem().toString();
				PackageType.setPackageType(packageType);
				packageTypeID = PackageType.getId();
				PackageType.setWeight(weight);
				int packagePrice = PackageType.getPrice();
				packageWeight = PackageType.getWeight();
				Price.setPrice(packagePrice, packageWeight);
				String price = String.valueOf(Price.getPrice());
				priceTxt.setText(price);
				total_price = Integer.valueOf(price);
			}
		});
		
		beratPakaian.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				String weight = beratPakaian.getSelectedItem().toString();
				String packageType = jenisPaket.getSelectedItem().toString();
				PackageType.setPackageType(packageType);
				packageTypeID = PackageType.getId();
				PackageType.setWeight(weight);
				int packagePrice = PackageType.getPrice();
				packageWeight = PackageType.getWeight();
				Price.setPrice(packagePrice, packageWeight);
				String price = String.valueOf(Price.getPrice());
				priceTxt.setText(price);
				total_price = Integer.valueOf(price);
				
				
			}
		});
	
		addBtn.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.out.println(paymentID);
				String customerName = nameTxt.getText();
				String notelp = notelpTxt.getText();
				String address = alamatTxt.getText();
				String date_order = ((JTextField)orderDate.getDateEditor().getUiComponent()).getText();
				String date_return = ((JTextField)returnDate.getDateEditor().getUiComponent()).getText();
				String price = priceTxt.getText();
				String payment_TypeID = jenisPembayaran.getSelectedItem().toString();
				int paymentTypeID = PaymentType.getID(payment_TypeID);
				int order_date = 0;
				int return_date = 0;
				int status = 0;
				int staffID = login.staffID;
//				int paymentID = getCountRowPayment(con.executeQuery("SELECT max(PaymentID) FROM payment"));;
				if (customerName.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Name cannot be empty!", "Alert", JOptionPane.WARNING_MESSAGE);
				} else if (notelp.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No_telp cannot be empty!", "Alert", JOptionPane.WARNING_MESSAGE);
				} else if(address.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Address cannot be empty!", "Alert", JOptionPane.WARNING_MESSAGE);
				} else if(!(date_return.equals("") && date_order.equals(""))) {
					return_date = Integer.valueOf(date_return.replaceAll("-", ""));
					order_date = Integer.valueOf(date_order.replaceAll("-", ""));
					if (order_date > return_date) {
						JOptionPane.showMessageDialog(null, "Return date cannot before the order date!", "Alert", JOptionPane.WARNING_MESSAGE);
					}else if(price.equals("0")) {
						JOptionPane.showMessageDialog(null, "Weight must be bigger than 0!", "Alert", JOptionPane.WARNING_MESSAGE);
					} else if (price.equals("")) {
						JOptionPane.showMessageDialog(null, "Please select weight and package!", "Alert", JOptionPane.WARNING_MESSAGE);
					} else {
						//Insert into Customer Table
						String queryToCustomer = "INSERT INTO customer VALUES(NULL,?,?,?)";
						PreparedStatement ps = con.prepareStatement(queryToCustomer);
						try {
							ps.setString(1, customerName);
							ps.setString(2, notelp);
							ps.setString(3, address);
							ps.executeUpdate();
							System.out.println(ps.toString());
						} catch (SQLException e1) {
						
							e1.printStackTrace();
						}
						//Insert into Payment Table
						String queryToPayment = "INSERT INTO payment(PaymentID,PaymentTypeID) VALUES(NULL,?)";
						PreparedStatement psPayment = con.prepareStatement(queryToPayment);
						try {
							psPayment.setInt(1, paymentTypeID);
							psPayment.executeUpdate();
							System.out.println(psPayment.toString());
						} catch (SQLException e2) {
							
							e2.printStackTrace();
						}
						
						//Insert into Transaction Table
						String queryToTransaction = "INSERT INTO transaction(TransactionID, PaymentID, Total_Price) VALUES(NULL,?,?)";
						PreparedStatement psTransaction = con.prepareStatement(queryToTransaction);
						try {
							
							psTransaction.setInt(1, paymentID);
							psTransaction.setInt(2, total_price);
							System.out.println(psTransaction.toString());
							psTransaction.executeUpdate();
							
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						
						//Insert into Order Table
						String queryToOrder = "INSERT INTO order_tbl VALUES(NULL,?,?,?,?,?,?,?,?)";
						PreparedStatement psOrder = con.prepareStatement(queryToOrder);
						try {
							
							psOrder.setInt(1, customerID);
							psOrder.setInt(2, transactionID);
							psOrder.setInt(3, packageTypeID);
							psOrder.setInt(4, staffID);
							psOrder.setString(5, date_order);
							psOrder.setString(6, date_return);
							psOrder.setInt(7, packageWeight);
							psOrder.setInt(8, status);
							System.out.println(psOrder.toString());
							psOrder.executeUpdate();
							data.setRowCount(0);
							String querySelect = "SELECT * FROM order_tbl JOIN customer ON order_tbl.CustomerID = customer.CustomerID JOIN packagetype ON order_tbl.Package_TypeID = packagetype.Package_TypeID JOIN staff ON order_tbl.staffID = staff.staffID JOIN transaction ON order_tbl.TransactionID = transaction.TransactionID ";
							loadData(con.executeQuery(querySelect));
							JOptionPane.showMessageDialog(null, "Data successfully added!");
							
							//Set Form to null
							nameTxt.setText(null);
							notelpTxt.setText(null);
							alamatTxt.setText(null);
							((JTextField)orderDate.getDateEditor().getUiComponent()).setText(null);
							jenisPaket.setSelectedIndex(0);
							beratPakaian.setSelectedIndex(0);
							jenisPembayaran.setSelectedIndex(0);
							priceTxt.setText(null);
							((JTextField)returnDate.getDateEditor().getUiComponent()).setText(null);
							
							
						} catch (SQLException e1) {
							
							e1.printStackTrace();
						}
					
					}
				} else if (date_return.equals("")) {
					JOptionPane.showMessageDialog(null, "Return date cannot be empty!", "Alert", JOptionPane.WARNING_MESSAGE);
				} else if (date_order.equals("")) {
					JOptionPane.showMessageDialog(null, "Order date cannot be empty!", "Alert", JOptionPane.WARNING_MESSAGE);
				} 
				
			}
		});
	
		deleteBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int selectedRowIndex = orderTbl.getSelectedRow();
				System.out.println(paymentID);
				if (selectedRowIndex == -1 ) {
					JOptionPane.showMessageDialog(null, "Please select an order!", "Alert", JOptionPane.WARNING_MESSAGE);
				} else {
					int response = JOptionPane.showConfirmDialog(null, "Are you sure want to delete this order ?");
					if (response == JOptionPane.YES_OPTION) {
						int order_id = (int) data.getValueAt(selectedRowIndex, 0);
						int staff_id = (int) data.getValueAt(selectedRowIndex, 1);
						String customer_name =  (String) data.getValueAt(selectedRowIndex, 2);
						String package_type = (String) data.getValueAt(selectedRowIndex, 3);
						int total_price = (int) data.getValueAt(selectedRowIndex, 4);
						
						String selectDelete = "SELECT * FROM order_tbl WHERE OrderID =?";
						PreparedStatement psSelect = con.prepareStatement(selectDelete);
						try {
							psSelect.setInt(1, order_id);
							data.removeRow(selectedRowIndex);
							DeleteData(psSelect.executeQuery());
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						
//						String deleteQuery = "DELETE FROM order_tbl JOIN customer ON order_tbl.CustomerID = customer.CustomerID JOIN packagetype ON order_tbl.Package_TypeID = packagetype.Package_TypeID JOIN staff ON order_tbl.staffID = staff.staffID JOIN transaction ON order_tbl.TransactionID = transaction.TransactionID WHERE OrderID =? , CustomerID =? , Package_TypeID = ?, staffID =?, TransactionID=?";
//						PreparedStatement ps = con.prepareStatement(deleteQuery);
//						
//						try {
//							ps.setInt(1, order_id);
//							ps.executeUpdate();
//							data.setRowCount(0);
//							String querySelect = "SELECT * FROM order_tbl JOIN customer ON order_tbl.CustomerID = customer.CustomerID JOIN packagetype ON order_tbl.Package_TypeID = packagetype.Package_TypeID JOIN staff ON order_tbl.staffID = staff.staffID JOIN transaction ON order_tbl.TransactionID = transaction.TransactionID ";
//							loadData(con.executeQuery(querySelect));
//						} catch (SQLException e1) {
//							// TODO Auto-generated catch block
//							e1.printStackTrace();
//						}
					}
					
					
				}
			}
		});
	}
	
	public Integer getCountRowPayment(ResultSet rs) {
		int paymentID = 0;
		try {
			while (rs.next()) {
			paymentID = rs.getInt("max(PaymentID)");
		
			
	
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return paymentID + 1;
		
	}
	
	public Integer getCountRowCustomer(ResultSet rs) {
		int customerID = 0;
		try {
			while (rs.next()) {
			customerID = rs.getInt("max(CustomerID)");
			
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return customerID + 1;
		
	}
	
	public Integer getCountRowTransaction(ResultSet rs) {
		int transaction_ID = 0;
		try {
			while (rs.next()) {
			transaction_ID = rs.getInt("max(TransactionID)");
		
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return transaction_ID + 1;
		
	}
	
	public void frame() {
		init();
		JPanel mainPanel = new JPanel();
		setVisible(true);
		setSize(1200,900);
		setClosable(true);
		setIconifiable(true);
		setLayout(new BorderLayout());
		mainPanel.setLayout((new BorderLayout()));
		mainPanel.add(centerPnl, BorderLayout.CENTER);
		mainPanel.add(southPnl, BorderLayout.SOUTH);
		mainPanel.add(titleLblPnl, BorderLayout.NORTH);
		add(mainPanel);
		
		addListener();
		

	}


	
}
