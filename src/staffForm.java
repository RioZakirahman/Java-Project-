import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class staffForm extends JInternalFrame {
 JPanel mainPanel, leftPanel, rightPanel, addBtnPanel, deleteBtnPnl, updateBtnPnl, allBtnPnl,  searchTxtPnl, searchBtnPnl;
 JTable viewTable;
 DefaultTableModel viewTableData;
 JScrollPane viewTblScroll;
 JLabel emailLbl, passwordLbl, roleLbl;
 JTextField emailField, searchTxt;
 JPasswordField passwordField;
 JComboBox roleOpt;
 JButton addBtn, deleteBtn, updateBtn, searchBtn;
 Vector<String> view, role;
 private Connect con = Connect.getConnection();

		public void init() {
			
			  mainPanel = new JPanel(new GridLayout(1, 2));
			  mainPanel.setBorder(new EmptyBorder(10, 60, 40, 20));
			
			  // leftPanel
			  leftPanel = new JPanel(new BorderLayout());
			  view = new Vector<String>();
			  view.add("StaffID");
			  view.add("Email");
			  view.add("Password");
			  view.add("Role");
			  
			  viewTableData = new DefaultTableModel(view, 0);
			  viewTable = new JTable(viewTableData) {
				  @Override
				public boolean isCellEditable(int row, int column) {
					// TODO Auto-generated method stub
					return false;
				}
			  };

			  viewTblScroll = new JScrollPane(viewTable);
			  leftPanel.add(viewTblScroll);
			
			  mainPanel.add(leftPanel);
			
			  // rightPanel
			  rightPanel = new JPanel(new GridLayout(7, 1));
			  rightPanel.setBorder(new EmptyBorder(-5, 20, 260, 40));
			
			
			  // emailLbl
			  emailLbl = new JLabel("Email");
			  rightPanel.add(emailLbl);
			  
			  // emailField
			  emailField = new JTextField();
			  rightPanel.add(emailField);
			  
			  // passwordLbl
			  passwordLbl = new JLabel("Password");
			  rightPanel.add(passwordLbl);
			
			  // passwordField
			  passwordField = new JPasswordField();
			  rightPanel.add(passwordField);
			
			  // genreLbl
			  roleLbl = new JLabel("Role");
			  rightPanel.add(roleLbl);
			
			  // role
			  role = new Vector<>();
			  role.add("Admin");
			  role.add("Kasir");
			  role.add("Operasional");
			  roleOpt = new JComboBox(role);
			  rightPanel.add(roleOpt);
			
			  // addMusicBtn
			  addBtnPanel = new JPanel();
			  addBtn = new JButton("Add Staff");
			  addBtn.setPreferredSize(new Dimension(90, 40));
			  addBtnPanel.add(addBtn);
			
			  //delete Btn
			  deleteBtn = new JButton("Delete");
			  deleteBtn.setPreferredSize(new Dimension(90,40));
			  deleteBtnPnl = new JPanel();
			  deleteBtnPnl.add(deleteBtn);
			  
			  //Update Btn
			  updateBtn = new JButton("Update");
			  updateBtn.setPreferredSize(new Dimension(90,40));
			  updateBtnPnl = new JPanel();
			  updateBtnPnl.add(updateBtn);
			  
			  // search Txt
			  searchTxt = new JTextField();
			  searchTxt.setPreferredSize(new Dimension(180,40));
			  searchTxtPnl = new JPanel();
			  searchTxtPnl.add(searchTxt);
			  
			  //Search Btn
			  searchBtn = new JButton("Search");
			  searchBtn.setPreferredSize(new Dimension(150,40));
			  searchBtnPnl = new JPanel();
			  searchBtnPnl.add(searchBtn);
			  searchBtnPnl.add(searchTxtPnl);
			  
			  leftPanel.add(searchBtnPnl, BorderLayout.SOUTH);
			  
			  allBtnPnl = new JPanel();
			  allBtnPnl.add(addBtnPanel);
			  allBtnPnl.add(deleteBtnPnl);
			  allBtnPnl.add(updateBtnPnl);
			  
			  
			  rightPanel.add(allBtnPnl);
			
			  mainPanel.add(rightPanel);
			
			  add(mainPanel);
		
		 }
		
		public void loadData() {
			
			//Select table staff
			String query = "SELECT * FROM staff";
			PreparedStatement ps = con.prepareStatement(query);
			try {
				con.rs = ps.executeQuery();
				while (con.rs.next()) {
					int staffID = con.rs.getInt("staffID");
					String email = con.rs.getString("email");
					String password = con.rs.getString("password");
					int role = con.rs.getInt("role");
					
					Vector<Object> listData = new Vector<>();
					listData.add(staffID);
					listData.add(email);
					listData.add(password);
					listData.add(role);
					
					viewTableData.addRow(listData);
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		public void tableListener() {
			
			viewTable.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					int selectedRowIndex = viewTable.getSelectedRow();
					if (selectedRowIndex == -1) {
						addBtn.setEnabled(true);
					} else {
						addBtn.setEnabled(false);
						
						String email = (String) viewTableData.getValueAt(selectedRowIndex, 1);
						String password = (String) viewTableData.getValueAt(selectedRowIndex, 2);
						int role = (int) viewTableData.getValueAt(selectedRowIndex, 3);
						System.out.println(role);
						int role_id = 0;
				        
				        if (role == 3) {
							role_id = 2;
						} else if (role == 2) {
							role_id = 1;
						} else if (role == 1) {
							role_id = 0;
						}
				        emailField.setText(email);
				        passwordField.setText(password);
				        roleOpt.setSelectedIndex(role_id);
				        
						
					}
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
			TableRowSorter<TableModel> sorter = new TableRowSorter<>(viewTableData);
			viewTable.setRowSorter(sorter);
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
		
		public void initActionListener() { 
				
			
		addBtn.addActionListener(new ActionListener() {
		      @Override
		   public void actionPerformed(ActionEvent e) {
		    {
		     if (emailField.getText().isEmpty()) {
		      JOptionPane.showMessageDialog(null, "Please input email !","Alert", JOptionPane.WARNING_MESSAGE);
		     } 
		      else if(passwordField.getText().isEmpty()) {
		      JOptionPane.showMessageDialog(null, "Please input password !","Alert", JOptionPane.WARNING_MESSAGE);
		      passwordField.requestFocus();
		     
		        } else {
		        	
		        //Insert Into Staff Table	
		        String email = emailField.getText();
		        String password = passwordField.getText();
		        String role = roleOpt.getSelectedItem().toString();
		        int role_id = 0;
		        
		        if (role.equals("Admin")) {
					role_id = 1;
				} else if (role.equals("Kasir")) {
					role_id = 2;
				} else if (role.equals("Operasional")) {
					role_id = 3;
				}
		        
		        //Insert into Staff
		        String query = "INSERT INTO staff(email,password,role) VALUES(?,?,?) ";
		        PreparedStatement ps = con.prepareStatement(query);
		        try {
		        	ps.setString(1, email);
		        	ps.setString(2, password);
		        	ps.setInt(3, role_id);
					ps.executeUpdate();
					System.out.println(ps.toString());
					
					emailField.setText(null);
					passwordField.setText(null);
					roleOpt.setSelectedIndex(0);
					 JOptionPane.showMessageDialog(null, "Staff successfully added!");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		      
		       
		         
		         
		        }
		    }
		
		   }
		  });
		 
		deleteBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRowIndex = viewTable.getSelectedRow();
				if (selectedRowIndex == -1) {
					JOptionPane.showMessageDialog(null, "Please select a staff!");
				} else {
					int response = JOptionPane.showConfirmDialog(null, "Are you sure want to delete this order ?");
				
					if (response == JOptionPane.YES_OPTION) {
					
						int staff_id = (int) viewTableData.getValueAt(selectedRowIndex, 0);
						//Delete from staff table
						String query  = "DELETE FROM staff WHERE staffID =?";
						PreparedStatement ps = con.prepareStatement(query);
						try {
							ps.setInt(1, staff_id);
							ps.executeUpdate();
//							if (viewTable.getRowCount() == 0) {
//								String queryAlter = "ALTER TABLE staff AUTO_INCREMENT= 1";
//								con.executeUpdate(queryAlter);
//							}	
						
							
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "Data successfully deleted!");
						viewTableData.setRowCount(0);
						loadData();
				}
				}
				
			}
		});
		
		updateBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRowIndex = viewTable.getSelectedRow();
				if (selectedRowIndex == -1) {
					JOptionPane.showMessageDialog(null, "Please select a staff!");
					
				} else {
					int response = JOptionPane.showConfirmDialog(null, "Are you sure want to update the staff ?");
					if (response == JOptionPane.YES_OPTION) {
						int staff_id = (int) viewTableData.getValueAt(selectedRowIndex, 0);
						
						if (emailField.getText().isEmpty()) {
						      JOptionPane.showMessageDialog(null, "Please input email !","Alert", JOptionPane.WARNING_MESSAGE);
						} else if(passwordField.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Please input password !","Alert", JOptionPane.WARNING_MESSAGE);
						passwordField.requestFocus();
						     
						} else {
							String email = emailField.getText();
							String password = passwordField.getText();
							String role = roleOpt.getSelectedItem().toString();
							
							   int role_id = 0;
						        
						        if (role.equals("Admin")) {
									role_id = 1;
								} else if (role.equals("Kasir")) {
									role_id = 2;
								} else if (role.equals("Operasional")) {
									role_id = 3;
								}
							
							// Update to staff
							String query = "UPDATE staff SET email =? ,password =?, role=? WHERE staffID =?";
							PreparedStatement ps = con.prepareStatement(query);
							try {
								ps.setString(1, email);
								ps.setString(2, password);
								ps.setInt(3, role_id);
								ps.setInt(4, staff_id);
								ps.executeUpdate();
								viewTableData.setRowCount(0);
								loadData();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						
						
					}
				}
				
			}
		});
		
		
		}
		
		
		
		public staffForm() {
		  init();
		  initActionListener();
		  loadData();
		  tableListener();
		  setVisible(true);
		  setSize(1000,700);
		  setClosable(true);
		  setIconifiable(true);
		
		 }
		
		}