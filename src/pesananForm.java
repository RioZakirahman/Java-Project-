




import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;


public class pesananForm extends JInternalFrame {

	JTable orderTbl;
	DefaultTableModel data;
	JScrollPane orderTblScroll;
	JPanel titleLblPnl,leftPnl,rightPnl,mainLeftPnl,orderTblPnl, form1Pnl, form2Pnl, nameLblPnl, nameTxtPnl,notelpPnl,notelpTxtPnl, alamatPnl,alamatTxtPnl, orderDatePnl, tanggalPesananPnl, jenisPaketLblPnl, jenisPaketPnl, beratPakaianLblPnl, beratPakaianPnl,jenisPembayaranLblPnl,jenisPembayaranPnl, pricePnl, priceTxtPnl, returnDateLblPnl, returnDatePnl, visibleLblPnl, visibleTxtPnl, addBtnPnl,deleteBtnPnl, southPnl, centerPnl, orderTblScrollPnl, updateBtnPnl, searchBtnPnl, searchPnl;
	JLabel titleLbl,nameLbl, notelp, alamat, tanggalPesanan, jenisPaketLbl, beratPakaianLbl, jenisPembayaranLbl, price, returnDateLbl, visibleLbl;
	JTextField nameTxt, notelpTxt, priceTxt, visibleTxt, searchTxt;
	JTextArea alamatTxt;
	JDateChooser orderDate, returnDate;
	JComboBox<String> jenisPaket, jenisPembayaran, beratPakaian;
	JButton addBtn,updateBtn, deleteBtn, searchBtn;
	private int packageTypeID;
	private int total_price;
	private int packageWeight;
	private Connect con = Connect.getConnection();
	
	public void init() {
		
		
		//Table

		Vector<Object> header = new Vector<>();
		header.add("Order ID");
		header.add("Staff ID");
		header.add("Customer Name");
		header.add("Package");
		header.add("Total Harga");
		
		data = new DefaultTableModel(header,0);
		orderTbl = new JTable(data) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		
			
		};
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(orderTbl.getModel());
		orderTbl.setRowSorter(sorter);
	
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
		searchBtn = new JButton("Search");
		searchBtn.setPreferredSize(new Dimension(150,40));
		
		//ComboBox
		Vector<String> jenisPaketlist = new Vector<>();
		jenisPaketlist.add("Paket Satu");
		jenisPaketlist.add("Paket Dua");
		
		Vector<String> beratPakaianlist = new Vector<>();
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
		searchTxt = new JTextField();
		searchTxt.setPreferredSize(new Dimension(200,40));
		
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
		searchBtnPnl = new JPanel();
		searchPnl = new JPanel();
		
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
		
		searchBtnPnl.add(searchBtn);
		searchPnl.add(searchTxt);
		//South Panel
		southPnl.add(addBtn);
		southPnl.add(deleteBtn);
		southPnl.add(updateBtnPnl);
		southPnl.add(searchBtnPnl);
		southPnl.add(searchTxt);
		
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
	
	public void tableListener() {
		orderTbl.addMouseListener(new MouseListener() {
			int selectedRowIndex;
			@Override
			public void mouseReleased(MouseEvent e) {
				selectedRowIndex = orderTbl.getSelectedRow();
				if (!(selectedRowIndex == -1)) {
					addBtn.setEnabled(false);
					searchBtn.setEnabled(false);
					searchTxt.setEnabled(false);
				
//						JOptionPane.showMessageDialog(null, "Please select an order!", "Alert", JOptionPane.WARNING_MESSAGE);
					
						int order_id = (int) data.getValueAt(selectedRowIndex, 0);
						con.rs = con.executeQuery("SELECT * FROM order_tbl WHERE OrderID = " + order_id);
						try {
							int customerID;
							String customer_name = "";
							String noTelp = "";
							String address = "";
							while (con.rs.next()) {
								customerID = con.rs.getInt("CustomerID");
								String order_date = con.rs.getString("Order_Date");
								int package_typeID = con.rs.getInt("Package_TypeID");
								String return_date = con.rs.getString("Return_Date");
								int transactionID = con.rs.getInt("TransactionID");
								int weight = con.rs.getInt("Weight");
								System.out.println(weight);
								int typeId = 0;
								int weightIdx = 0;
								int paymentID = 0;
								int payment_typeID = 0;
								int paymentTypeIdx = 0;
								if (package_typeID == 1) {
									 typeId = 0;
								} else {
									typeId = 1;
								}
								if (weight == 1) {
									weightIdx = 0;
								} else if (weight == 2) {
									weightIdx = 1;
								} else if (weight == 3) {
									weightIdx = 2;
								} else if (weight == 4) {
									weightIdx = 3;
								} else if (weight == 5) {
									weightIdx = 4;
								}
								
								
								con.rs = con.executeQuery("SELECT * FROM customer WHERE CustomerID = " + customerID);
								while (con.rs.next()) {
									customer_name = con.rs.getString("Customer_Name");
									noTelp = con.rs.getString("No_telp");
									address = con.rs.getString("Address");
									
								}
								con.rs = con.executeQuery("SELECT * FROM transaction WHERE TransactionID = " + transactionID);
								while (con.rs.next()) {
									paymentID = con.rs.getInt("PaymentID");
								}
								
								con.rs = con.executeQuery("SELECT * FROM payment WHERE PaymentID = " + paymentID);
								while (con.rs.next()) {
									payment_typeID = con.rs.getInt("PaymentTypeID");
								}
								if (payment_typeID == 1) {
									//CASH,OVO,DANA
									paymentTypeIdx = 1;
								} else if (payment_typeID == 2) {
									paymentTypeIdx = 2;
								} else if (payment_typeID == 3) {
									paymentTypeIdx = 0;
								}
							
								nameTxt.setText(customer_name);
								notelpTxt.setText(noTelp);
								alamatTxt.setText(address);
								((JTextField)orderDate.getDateEditor().getUiComponent()).setText(order_date);
								jenisPaket.setSelectedIndex(typeId);
								beratPakaian.setSelectedIndex(weightIdx);
								jenisPembayaran.setSelectedIndex(paymentTypeIdx);
								((JTextField)returnDate.getDateEditor().getUiComponent()).setText(return_date);

						
								
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					

				} else if (selectedRowIndex == -1) {
					addBtn.setEnabled(true);
					searchBtn.setEnabled(true);
					searchTxt.setEnabled(true);
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
	
				}
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
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
		TableRowSorter<TableModel> sorter = new TableRowSorter<>(data);
		orderTbl.setRowSorter(sorter);
		
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
				int payment_id = 0;
				int transaction_id = 0;
				int customer_id = 0;
				
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
						String queryToCustomer = "INSERT INTO customer(Customer_Name,No_telp,Address) VALUES(?,?,?)";
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
						//Get generated Key
						try {
							con.rs = ps.getGeneratedKeys();
							while (con.rs.next()) {
								customer_id = con.rs.getInt(1);
							}
						} catch (SQLException e4) {
							// TODO Auto-generated catch block
							e4.printStackTrace();
						}
						
						//Insert into Payment Table
						String queryToPayment = "INSERT INTO payment(PaymentTypeID) VALUES(?)";
						PreparedStatement psPayment = con.prepareStatement(queryToPayment);
						
						try {
							
							psPayment.setInt(1, paymentTypeID);
							psPayment.executeUpdate();
							System.out.println(psPayment.toString());
						} catch (SQLException e2) {
							
							e2.printStackTrace();
						}
						//Get generated key
						try {
							con.rs = psPayment.getGeneratedKeys();
							while (con.rs.next()) {
								payment_id = con.rs.getInt(1);
							}
						} catch (SQLException e3) {
							// TODO Auto-generated catch block
							e3.printStackTrace();
						}
						
						//Insert into Transaction Table
						String queryToTransaction = "INSERT INTO transaction(PaymentID, Total_Price) VALUES(?,?)";
						PreparedStatement psTransaction = con.prepareStatement(queryToTransaction);
						try {
							
							psTransaction.setInt(1, payment_id);
							psTransaction.setInt(2, total_price);
							System.out.println(psTransaction.toString());
							psTransaction.executeUpdate();
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						//Get generated key
						try {
							con.rs = psTransaction.getGeneratedKeys();
							while (con.rs.next()) {
								transaction_id = con.rs.getInt(1);
							}
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						
						//Insert into Order Table
						String queryToOrder = "INSERT INTO order_tbl (CustomerID,TransactionID,Package_TypeID,staffID,Order_Date,Return_Date,Weight,Status) VALUES(?,?,?,?,?,?,?,?)";
						PreparedStatement psOrder = con.prepareStatement(queryToOrder);
						try {
							
							psOrder.setInt(1, customer_id);
							psOrder.setInt(2, transaction_id);
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
		
		
		updateBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRowIndex = orderTbl.getSelectedRow();
//				System.out.println(paymentID);
				if (selectedRowIndex == -1 ) {
					JOptionPane.showMessageDialog(null, "Please select an order!", "Alert", JOptionPane.WARNING_MESSAGE);
				} else {
					int order_id = (int) data.getValueAt(selectedRowIndex, 0);
					
					String querySelect = "SELECT * FROM order_tbl WHERE OrderID = " + order_id;
					con.rs = con.executeQuery(querySelect);
					
					int customerID = 0;
					int transactionID = 0;
					int payment_ID = 0;
					try {
						while (con.rs.next()) {
	
							customerID = con.rs.getInt("CustomerID");
							transactionID = con.rs.getInt("TransactionID");
							//Order
							int package_typeID = con.rs.getInt("Package_TypeID");
							String orderDate = con.rs.getString("Order_Date");
							String returnDate = con.rs.getString("Return_Date");
							int weight = con.rs.getInt("Weight");
							int status = con.rs.getInt("Status");
						}
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
					//SELECT transaction to get PaymentID
					con.rs = con.executeQuery("SELECT * FROM transaction WHERE TransactionID = " + transactionID);
					try {
						while (con.rs.next()) {
							 payment_ID = con.rs.getInt("PaymentID");
						}
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					
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
					
					//validation
					
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
							
							//Update to Customer
							String queryCustomer = "UPDATE customer SET Customer_Name = ?, No_telp = ?, Address = ? WHERE CustomerID=?";
							PreparedStatement psCustomer = con.prepareStatement(queryCustomer);
							try {
								psCustomer.setString(1, customerName);
								psCustomer.setString(2, notelp);
								psCustomer.setString(3, address);
								psCustomer.setInt(4, customerID);
								psCustomer.executeUpdate();
								System.out.println(psCustomer.toString());
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							//Update to Payment
							String queryPayment = "UPDATE payment SET PaymentTypeID = ? WHERE PaymentID = ?";
							PreparedStatement psPayment = con.prepareStatement(queryPayment);
							try {
								psPayment.setInt(1, paymentTypeID);
								psPayment.setInt(2, payment_ID);
								psPayment.executeUpdate();
								System.out.println(psPayment.toString());
								
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							//Update to Transaction
							String queryTransaction = "UPDATE transaction SET Total_Price = ? WHERE TransactionID = ?";
							PreparedStatement psTransaction = con.prepareStatement(queryTransaction);
							try {
								psTransaction.setInt(1, total_price);
								psTransaction.setInt(2, transactionID);
								psTransaction.executeUpdate();
								System.out.println(psTransaction.toString());
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							//Update to Order
							String queryOrder = "UPDATE order_tbl SET Order_Date = ?, Package_TypeID = ?, Weight = ?, Return_Date = ? WHERE OrderID = ? ";
							PreparedStatement psOrder = con.prepareStatement(queryOrder);
							try {
								psOrder.setString(1, date_order);
								psOrder.setInt(2, packageTypeID);
								psOrder.setInt(3, packageWeight);
								psOrder.setString(4, date_return);
								psOrder.setInt(5, order_id);
								psOrder.executeUpdate();
								System.out.println(psOrder.toString());
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							JOptionPane.showMessageDialog(null, "Data successfully Updated!");
							data.setRowCount(0);
							String queryLoad = "SELECT * FROM order_tbl JOIN customer ON order_tbl.CustomerID = customer.CustomerID JOIN packagetype ON order_tbl.Package_TypeID = packagetype.Package_TypeID JOIN staff ON order_tbl.staffID = staff.staffID JOIN transaction ON order_tbl.TransactionID = transaction.TransactionID ";
							loadData(con.executeQuery(queryLoad));
							//set form to null
							nameTxt.setText(null);
							notelpTxt.setText(null);
							alamatTxt.setText(null);
							((JTextField)orderDate.getDateEditor().getUiComponent()).setText(null);
							jenisPaket.setSelectedIndex(0);
							beratPakaian.setSelectedIndex(0);
							jenisPembayaran.setSelectedIndex(0);
							priceTxt.setText(null);
							((JTextField)returnDate.getDateEditor().getUiComponent()).setText(null);
						}
					} else if (date_return.equals("")) {
						JOptionPane.showMessageDialog(null, "Return date cannot be empty!", "Alert", JOptionPane.WARNING_MESSAGE);
					} else if (date_order.equals("")) {
						JOptionPane.showMessageDialog(null, "Order date cannot be empty!", "Alert", JOptionPane.WARNING_MESSAGE);
					} 
					

					
					
					
					
					
					
					
				}
		

						
					
			
				
				
				
			}
		});

		
		deleteBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRowIndex = orderTbl.getSelectedRow();
//				System.out.println(paymentID);
				if (selectedRowIndex == -1 ) {
					JOptionPane.showMessageDialog(null, "Please select an order!", "Alert", JOptionPane.WARNING_MESSAGE);
				} else {
					int order_id = (int) data.getValueAt(selectedRowIndex, 0);
					
					String querySelect = "SELECT * FROM order_tbl WHERE OrderID = " + order_id;
					con.rs = con.executeQuery(querySelect);
					
					int customerID = 0;
					int transactionID = 0;
					int payment_ID = 0;
					
					
					try {
						while (con.rs.next()) {
	
							customerID = con.rs.getInt("CustomerID");
							transactionID = con.rs.getInt("TransactionID");
							
							
						}
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
					
					// Delete from customer
					String deleteCustomer = "DELETE FROM customer WHERE CustomerID = " + customerID;
					con.executeUpdate(deleteCustomer);
					System.out.println(deleteCustomer);
					
					//SELECT from Transaction
					String selectTransaction = "SELECT * FROM transaction WHERE TransactionID = " + transactionID;
					con.rs = con.executeQuery(selectTransaction);
					try {
						while (con.rs.next()) {
							payment_ID = con.rs.getInt("PaymentID");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					// Delete from payment
					String deletePayment = "DELETE FROM payment WHERE PaymentID =" + payment_ID;
					con.executeUpdate(deletePayment);
					System.out.println(deletePayment);
					
					JOptionPane.showMessageDialog(null, "Data successfully deleted");
					data.setRowCount(0);
					String queryLoad = "SELECT * FROM order_tbl JOIN customer ON order_tbl.CustomerID = customer.CustomerID JOIN packagetype ON order_tbl.Package_TypeID = packagetype.Package_TypeID JOIN staff ON order_tbl.staffID = staff.staffID JOIN transaction ON order_tbl.TransactionID = transaction.TransactionID ";
					loadData(con.executeQuery(queryLoad));
					
					nameTxt.setText(null);
					notelpTxt.setText(null);
					alamatTxt.setText(null);
					((JTextField)orderDate.getDateEditor().getUiComponent()).setText(null);
					jenisPaket.setSelectedIndex(0);
					beratPakaian.setSelectedIndex(0);
					jenisPembayaran.setSelectedIndex(0);
					priceTxt.setText(null);
					((JTextField)returnDate.getDateEditor().getUiComponent()).setText(null);
					addBtn.setEnabled(true);
					searchBtn.setEnabled(true);
					searchTxt.setEditable(true);
				}
				
			}
		});
	
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
		return customerID ;
		
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
		tableListener();

	}


	
}
