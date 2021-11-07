package Main;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.Scanner;

import Pesanan.PaketDua;
import Pesanan.PaketSatu;
import Pesanan.Pesanan;
import User.Admin;
import User.Kasir;
import User.Operasional;
import User.User;



public class Main {
	
	private int choose;
	
	private Scanner scan = new Scanner(System.in);
	
	public ArrayList<Pesanan> listPesanan =  new ArrayList<Pesanan>();
	ArrayList<User> listUser = new ArrayList<User>();
	
	ArrayList<String> ID = new ArrayList<String>();
	
	public String generateID() {
		String ID = "TR";
		
		for (int i = 0; i < 3; i++) {
			int max = 9;
			int min = 0;
			int randNum = (int)(Math.random() * (max - min) + min);
			ID = ID + randNum;
		}
		return ID;
	}
	
	public void Login() {
		boolean condition = true;
		String Username;
		String Password;
		
		//set User
		Kasir k = new Kasir();
		Operasional o = new Operasional();
		Admin a = new Admin();
		
	
		
		//set value
		k.setUsername("kasir");
		k.setPassword("kasir123");
		
		o.setUsername("operasional");
		o.setPassword("operasional123");
		
		a.setUsername("admin");
		a.setPassword("admin123");
		
		//add to arraylist
		listUser.add(k);
		listUser.add(o);
		listUser.add(a);
		
		//set dummy data
		DummyData();
		
		while (condition) {
			System.out.println("=========================");
			System.out.println("Sistem Perusahaan Laundry");
			System.out.println("=========================");
			System.out.println("         Login			 ");
			System.out.println("=========================");
			System.out.println("Username : ");
			System.out.println("Password : ");
			System.out.println("=========================");
		
			Username = scan.nextLine();
			Password = scan.nextLine();
		
			for (User usr : listUser) {
				if (Username.equals(usr.getUsername()) && Password.equals(usr.getPassword())) {
					System.out.println("Login Successfull");
					System.out.println();
					
					if (usr instanceof Kasir) {
						MenuKasir();
					}
					if (usr instanceof Operasional) {
						MenuOperasional();
					} if (usr instanceof Admin) {
						MenuAdmin();
					} 

				} 

			}

		}

		
	}
	
	public boolean validateDate(String input) {
		
		int hari = 0,tanggal, bulan, tahun;
		boolean Tanggal, Bulan, valid;
		String tgl,month,year;
		
		tgl = input.substring(0, 2);
		month = input.substring(2, 4);
		year = input.substring(4, 8);
		
		
		tanggal = Integer.parseInt(tgl);
		bulan = Integer.parseInt(month);
		tahun = Integer.parseInt(year);
		
		if (input.length() > 8) {
			return false;
		}
		
		if (bulan%2 != 0) {
			hari = 31;
			
		} else if (bulan == 4 || bulan == 6 || bulan == 8 || bulan == 10 || bulan == 12) {
			hari = 30;
		}else if (bulan == 2 && tahun%4 == 0) {
			//kabisat
			hari = 29;
		
		}else if(bulan == 2 && tahun%4 != 0) {
			hari = 28;
			
			//bukan kabisat
		}
		Tanggal = hari>= 1 && tanggal <= hari;
		Bulan = bulan>= 1 && bulan <= 12;
		valid = Tanggal && Bulan;
		
		if (valid) {
			return true;
		} else {
			return false;
		}
		
		
		
		
	}
	
	public String Tanggal() {
		Date d = new Date();
		SimpleDateFormat s = new SimpleDateFormat("dd/mm/yyyy");
		
		String date = s.format(d);
		return date;
	}
	
	public void DummyData() {
		
		PaketSatu p = new PaketSatu();
		PaketDua p2 = new PaketDua();
		
		p.setName("Raihan Fabian");
		p.setPhoneNum("089955556135");
		p.setAddress("Jl Pemuda , Jawa Timur, Surabaya");
		p.setPaymentType("OVO");
		p.setDate("06112021");
		p.setWeight(2);
		
		p.setDateOfReturn("08112021");
		p.setID(generateID());
		p.setStatus("Belum Dikerjakan");
		listPesanan.add(p);
		
		p2.setName("Sannya Vanessa");
		p2.setPhoneNum("089655594823");
		p2.setAddress("Jl Pahlawan Seribu, ITC Bumi Serpong ");
		p2.setPaymentType("DANA");
		p2.setDate("02122021");
		p2.setWeight(5);
		
		p2.setDateOfReturn("04122021");
		p2.setID(generateID());
		p2.setStatus("Belum Dikerjakan");
		listPesanan.add(p2);
		
		
		
	}
	
	public void Add() {
		//Menu Tambah Pesanan
	
		PaketSatu paketSatu = new PaketSatu();
		PaketDua paketDua = new PaketDua();
		String name;
		String phoneNum;
		String address;
		int paket;
		int berat;
		String priceType = "";
		String tanggal = "";
		String tglKeluar ;
		int change = 0 ;
		int amount = 0;
		int price = 0;
		
	
		
		do {
			System.out.println("Nama Customer : ");
			name = scan.nextLine();	
		} while (name.length() < 3 || name.length() > 30);
		
		
		do {
			System.out.println("No Telp Customer [12 digit] : ");
			phoneNum = scan.nextLine();	
		} while (phoneNum.length() < 12 || phoneNum.length() > 12);
		
		do {
			System.out.println("Alamat Customer : ");
			address = scan.nextLine();
		} while (address.length() < 5|| address.length() > 100);
		
		
		boolean condition3 = true;
		while (condition3) {
			System.out.println("Tanggal Pemesanan [ddmmyyyy]:  ");
			tanggal = scan.nextLine();
			
			if (validateDate(tanggal)) {
				condition3 = false;
			} else {
				System.out.println("Tanggal Salah");
				condition3 = true;
			}
			
		}
			
			

		

		
		do {
			System.out.println("Jenis Paket [pilih 1 (Laundry) atau 2 (Laundry + Setrika)] : ");
			paket = scan.nextInt();
			scan.nextLine();
		} while (paket > 2 || paket <= 0);
		
		
		do {
			System.out.println("Berat Pakaian [1-5kg] : ");
			berat = scan.nextInt();
			scan.nextLine();		
		} while (berat < 1 || berat> 5);
		
		if (paket ==1 ) {
			paketSatu.setWeight(berat);
			price = berat * paketSatu.getPackagePrice();
		}
		if (paket == 2) {
			paketDua.setWeight(berat);
			price = berat * paketDua.getPackagePrice();
		}
		
		System.out.println("Total Harga : " + price);
		
		boolean condition = true;
		while (condition) {
			System.out.println("Jenis Pembayaran [ Cash || OVO || DANA] : ");
			priceType = scan.nextLine();
			if (priceType.equalsIgnoreCase("Dana")) {
				condition = false;
			} 
			if (priceType.equalsIgnoreCase("OVO")) {
				condition = false;
			}
			if (priceType.equalsIgnoreCase("Cash")) {
				condition = false;
			}
		}
		
		if (priceType.equalsIgnoreCase("Cash")) {
			do {
				System.out.println("Total Pembayaran : ");
				amount = scan.nextInt();
				scan.nextLine();	
			} while (amount < 12000  || amount > 100000);
				if (paket == 1) {
					
					change = amount - (paketSatu.getPackagePrice() * paketSatu.getWeight());
					System.out.println("Jumlah kembalian: " + change);	
				}
				if (paket == 2) {
					
					change = amount - (paketDua.getPackagePrice() * paketDua.getWeight());
					System.out.println("Jumlah kembalian: " + change);	
				}
			
				
		}
	
	
		do {
			System.out.println("Tanggal Pengembalian Pakaian: ");
			tglKeluar = scan.nextLine();
			
		} while (!(validateDate(tglKeluar)));
		
	
		if (paket == 1) {
			
			paketSatu.setName(name);
			paketSatu.setPhoneNum(phoneNum);
			paketSatu.setAddress(address);
			
			paketSatu.setPaymentType(priceType);
			paketSatu.setDate(tanggal);
			paketSatu.setAmount(amount);
			paketSatu.setDateOfReturn(tglKeluar);
			
			listPesanan.add(paketSatu);
			paketSatu.setID(generateID());
			paketSatu.setStatus("Belum Dikerjakan");
		}
		if (paket == 2) {
			paketDua.setName(name);
			paketDua.setPhoneNum(phoneNum);
			paketDua.setAddress(address);
			
			paketDua.setPaymentType(priceType);
			paketDua.setDate(tanggal);
			paketDua.setAmount(amount);
			paketDua.setDateOfReturn(tglKeluar);
			
			listPesanan.add(paketDua);
			paketDua.setID(generateID());
			paketDua.setStatus("Belum Dikerjakan");
		}
		
	}
	
	public void View() {
		if (listPesanan.isEmpty()) {
			System.out.println("Tidak ada pesanan!");
			System.out.println();
		}
		int j  = 0;
		for (Pesanan pesanan : listPesanan) {
			
			
				j++;
			System.out.println("==========================================");
			System.out.println("No " + j);
			System.out.println("TransactionID :  " + pesanan.getID() );
			
			System.out.println("Customer : " + pesanan.getName());
			System.out.println("No telphone: " + pesanan.getPhoneNum());
			System.out.println("Alamat: " +  pesanan.getAddress());
			System.out.println("Tanggal Pemesanan: " + pesanan.getDate());
			if (pesanan instanceof PaketSatu) {
				System.out.println("Jenis Paket: " + pesanan.getPackageName() );
				
			}
			if (pesanan instanceof PaketDua) {
				System.out.println("Jenis Paket: " + pesanan.getPackageName() );
				
			}
			
			System.out.println("Jenis Pembayaran: " + pesanan.getPaymentType());
			
			System.out.println("Berat Pakaian: " + pesanan.getWeight() + "kg");
			if (pesanan.getPaymentType().equalsIgnoreCase("Cash")) {
				System.out.println("Total Pembayaran " + pesanan.getAmount());
			}
			if (pesanan instanceof PaketSatu) {
				System.out.println("Total Harga: " +  pesanan.TotalPrice());
					
			}
			if (pesanan instanceof PaketDua) {
				System.out.println("Total Harga: " +  pesanan.TotalPrice());
					
			}
			System.out.println("Tanggal Pengembalian: " + pesanan.getDateOfReturn());
			System.out.println("Status Pengerjaan: " + pesanan.getStatus());
			System.out.println("==========================================");
	
			System.out.println();
			System.out.println();
			
		}
	}
	
	public void Update() {
	
		
		if (listPesanan.isEmpty()) {
			System.out.println("Tidak ada pesanan!");
			System.out.println();
		} else {
			View();
			
			PaketSatu paketSatu = new PaketSatu();
			PaketDua paketDua = new PaketDua();
			
			String name;
			String phoneNum;
			String address;
			int paket;
			int berat;
			String priceType = "";
			String tanggal;
			String tglKeluar ;
			int change = 0;
			int amount = 0;
			int price = 0;
			
			int input;
			do {
				System.out.println("Masukkan no pesanan yang ingin di update:");
				input = scan.nextInt();
				scan.nextLine();
			} while (input < 0 || input > listPesanan.size());
	
			
			do {
				System.out.println("Nama Customer : ");
				name = scan.nextLine();	
			} while (name.length() < 3 || name.length() > 30);
			
			
			do {
				System.out.println("No Telp Customer [12 digit] : ");
				phoneNum = scan.nextLine();	
			} while (phoneNum.length() < 12 || phoneNum.length() > 12);
			
			do {
				System.out.println("Alamat Customer : ");
				address = scan.nextLine();
			} while (address.length() < 5|| address.length() > 50);
			
			do {
				System.out.println("Tanggal Pemesanan [ddmmyyyy]:  ");
				tanggal = scan.nextLine();
			} while (!(validateDate(tanggal)));
			
			do {
				System.out.println("Jenis Paket [pilih 1 (Laundry) atau 2 (Laundry + Setrika)] : ");
				paket = scan.nextInt();
				scan.nextLine();
			} while (paket > 2 || paket <= 0);
			
			
			do {
				System.out.println("Berat Pakaian [1-5kg] : ");
				berat = scan.nextInt();
				scan.nextLine();		
			} while (berat < 1 || berat> 5);
			
			if (paket ==1 ) {
				paketSatu.setWeight(berat);
				price = berat * paketSatu.getPackagePrice();
			}
			if (paket == 2) {
				paketDua.setWeight(berat);
				price = berat * paketDua.getPackagePrice();
			}
			
			System.out.println("Total Harga : " + price);
			
			boolean condition = true;
			while (condition) {
				System.out.println("Jenis Pembayaran [ Cash || OVO || DANA] : ");
				priceType = scan.nextLine();
				if (priceType.equalsIgnoreCase("Dana")) {
					condition = false;
				} 
				if (priceType.equalsIgnoreCase("OVO")) {
					condition = false;
				}
				if (priceType.equalsIgnoreCase("Cash")) {
					condition = false;
				}
			}
			
			if (priceType.equalsIgnoreCase("Cash")) {
				do {
					System.out.println("Total Pembayaran : ");
					amount = scan.nextInt();
					scan.nextLine();	
				} while (amount < 12000  || amount > 100000);
				if (paket == 1) {
					
					change = amount - (paketSatu.getPackagePrice() * paketSatu.getWeight());
					System.out.println("Jumlah kembalian: " + change);	
				}
				if (paket == 2) {
					
					change = amount - (paketDua.getPackagePrice() * paketDua.getWeight());
					System.out.println("Jumlah kembalian: " + change);	
				}
				
					
			}
		
		
			do {
				System.out.println("Tanggal Pengembalian Pakaian[ddmmyyyy]: ");
				tglKeluar = scan.nextLine();
			} while (!(validateDate(tglKeluar)));
			

				
				
			
				if (paket == 1) {
					paketSatu.setID(listPesanan.get(input-1).getID());
					paketSatu.setName(name);
					paketSatu.setPhoneNum(phoneNum);
					paketSatu.setAddress(address);
			
					paketSatu.setPaymentType(priceType);
					paketSatu.setDate(tanggal);
					paketSatu.setAmount(amount);
					paketSatu.setDateOfReturn(tglKeluar);
			
					paketSatu.setStatus(listPesanan.get(input-1).getStatus());
					listPesanan.set(input - 1, paketSatu);
				}
				if (paket == 2) {
					paketDua.setID(listPesanan.get(input-1).getID());
					paketDua.setName(name);
					paketDua.setPhoneNum(phoneNum);
					paketDua.setAddress(address);
					
					paketDua.setPaymentType(priceType);
					paketDua.setDate(tanggal);
					paketDua.setAmount(amount);
					paketDua.setDateOfReturn(tglKeluar);
					paketDua.setStatus(listPesanan.get(input-1).getStatus());
					listPesanan.set(input - 1, paketDua);
				}
				System.out.println("Pesanan berhasil diupdate!");
				System.out.println();
				System.out.println();
				
			
			
			
			
			
		}
	}

	public void Delete() {
		int delete;
		if (listPesanan.isEmpty()) {
			System.out.println("Tidak ada pesanan!");
			System.out.println();
		} else {
			View();
			do {
				System.out.println("Masukkan no pesanan yang ingin dihapus[1-" + listPesanan.size() + "]:");
				delete = scan.nextInt();
				scan.nextLine();
			} while (delete < 0 || delete > listPesanan.size());
			listPesanan.remove(delete - 1);
			System.out.println("Pesanan telah dihapus!");
			System.out.println();
			System.out.println();
			
		}
	}
	
	public void Print() {
		int print;
		if (listPesanan.isEmpty()) {
			System.out.println("Tidak ada pesanan!");
			System.out.println();
		} else {
			View();
			do {
				System.out.println("Masukkan no pesanan yang ingin print[1-" + listPesanan.size() + "]:");
				print = scan.nextInt();
				scan.nextLine();
			} while (print < 0 || print > listPesanan.size());
			String tanggal = Tanggal();
			System.out.println("TransactionID :  " + listPesanan.get(print-1).getID() );
			System.out.println("Nama Customer: " + listPesanan.get(print - 1).getName());
			System.out.println("============================");
			System.out.println("Tanggal : " + tanggal);
			System.out.println("============================");	
			
			
			System.out.println("Jenis Paket: " + listPesanan.get(print - 1).getPackageName());
			System.out.println("Berat Pakaian: " + listPesanan.get(print - 1).getWeight());
			
			System.out.println("Jenis Pembayaran: " + listPesanan.get(print - 1).getPaymentType());
			System.out.println("============================");	
			if (listPesanan.get(print-1).getPaymentType().equalsIgnoreCase("Cash")) {
				System.out.println("Total Pembayaran " + listPesanan.get(print-1).getAmount());
			}
			
			if (listPesanan.get(print-1) instanceof PaketSatu) {
				System.out.println("Total Harga: " +  listPesanan.get(print-1).TotalPrice());
				if (listPesanan.get(print-1).getPaymentType().equalsIgnoreCase("Cash")) {
					System.out.println("Jumlah Kembalian: " + (listPesanan.get(print-1).getAmount() - listPesanan.get(print-1).TotalPrice()) );
				}
					
			}
			if (listPesanan.get(print-1) instanceof PaketDua) {
				System.out.println("Total Harga: " +  listPesanan.get(print-1).TotalPrice());
				if (listPesanan.get(print-1).getPaymentType().equalsIgnoreCase("Cash")) {
					System.out.println("Jumlah Kembalian: " + (listPesanan.get(print-1).getAmount() - listPesanan.get(print-1).TotalPrice()) );
				}
				
			}
			
			
		
	
			System.out.println();
			System.out.println();
		}
	}
	
	public void Proses() {
		if (listPesanan.isEmpty()) {
			System.out.println("Tidak ada pesanan!");
		} else {
			
		
		
		int index;
		int machine;
		View();
		do {
			System.out.println("Masukkan no pesanan yang ingin diproses[1-"+ listPesanan.size() + "]: ");
			index = scan.nextInt();
			scan.nextLine();
		} while (index < 0 || index > listPesanan.size());
		do {
			System.out.println("No Mesin Cuci [pilih mesin cuci no 1 - 5] : ");
			machine = scan.nextInt();
			scan.nextLine();
		} while (machine < 1 || machine > 5);
		listPesanan.get(index-1).setMachine(machine);
		listPesanan.get(index-1).setStatus("Sudah Dikerjakan");
		System.out.println("Status pesanan telah diubah menjadi sudah dikerjakan!");
		System.out.println();
		System.out.println();
		}
		
	}
	
	public void MenuKasir() {
		//Main Menu
		do {
			System.out.println("Sistem Pembayaran Laundry Kiloan");
			System.out.println("================================");
			System.out.println("1. Tambah Pesanan");
			System.out.println("2. Daftar Pesanan");
			System.out.println("3. Update Pesanan");
			System.out.println("4. Delete Pesanan");
			System.out.println("5. Print Struk Pemesanan");
			System.out.println("6. Exit");
			System.out.println(">>");
			choose = scan.nextInt();
			scan.nextLine();
			switch (choose) {
			case 1:
				Add();
				break;

			case 2:
				View();
				break;
			
			case 3:
				Update();
				break;
			case 4:
				Delete();
				break;
			case 5:
				Print();
				break;
			}
		} while (choose != 6);
	}
	
	public void MenuOperasional() {
		do {
			System.out.println("Sistem Pembayaran Laundry Kiloan");
			System.out.println("================================");
			System.out.println("1. Daftar Pesanan");
			System.out.println("2. Proses Pesanan");
			System.out.println("3. Exit");
			System.out.println(">>");
			choose = scan.nextInt();
			scan.nextLine();
			switch (choose) {
			case 1:
				ViewOperasional();
				break;

			case 2:
				Proses();
				break;
			
		
			}
		} while (choose != 3);
	}
	
	public void ViewOperasional() {
		if (listPesanan.isEmpty()) {
			System.out.println("Tidak ada pesanan!");
			System.out.println();
		}
		int j  = 0;
		for (Pesanan pesanan : listPesanan) {
			
			
				j++;
			
			System.out.println("No " + j);
			System.out.println("TransactionID :  " + pesanan.getID() );
			
			System.out.println("Customer : " + pesanan.getName());
			System.out.println("No telphone: " + pesanan.getPhoneNum());
			System.out.println("Alamat: " +  pesanan.getAddress());
			System.out.println("Tanggal Pemesanan: " + pesanan.getDate());
			if (pesanan instanceof PaketSatu) {
				System.out.println("Jenis Paket: " + pesanan.getPackageName() );
			}
			if (pesanan instanceof PaketDua) {
				System.out.println("Jenis Paket: " + pesanan.getPackageName() );
			}
			System.out.println("Berat Pakaian: " + pesanan.getWeight() + "kg");
			System.out.println("Jenis Pembayaran: " + pesanan.getPaymentType());
			if (pesanan.getPaymentType().equalsIgnoreCase("Cash")) {
				System.out.println("Total Pembayaran " + pesanan.getAmount());
			}
			if (pesanan instanceof PaketSatu) {
				System.out.println("Total Harga: " +  pesanan.TotalPrice());
					
			}
			if (pesanan instanceof PaketDua) {
				System.out.println("Total Harga: " +  pesanan.TotalPrice());
					
			}
			System.out.println("Tanggal Pengembalian: " + pesanan.getDateOfReturn());
			System.out.println("Status Pengerjaan: " + pesanan.getStatus());
			if (!(pesanan.getMachine() == 0)) {
				System.out.println("No mesin cuci: " + pesanan.getMachine());
			}
			
	
			System.out.println();
			System.out.println();
			
		}
	}
	
	public void Income() {
		int Total = 0;
		int j = 0;
		for (Pesanan pesanan : listPesanan) {
			j++;
			Total = Total + pesanan.TotalPrice();
			
			System.out.println(j +". " + "TransactionID: " + pesanan.getID());
			System.out.println("   Total Price: " + pesanan.TotalPrice());
			System.out.println("=========================");
			
			
			
		}
		System.out.println("   Pendapatan: " + Total);
		System.out.println("=========================");
		System.out.println();
		System.out.println();
	}
	
	public void MenuAdmin() {
		do {
			System.out.println("Sistem Pembayaran Laundry Kiloan");
			System.out.println("================================");
			System.out.println("1. Tambah Pesanan");
			System.out.println("2. Daftar Pesanan");
			System.out.println("3. Update Pesanan");
			System.out.println("4. Delete Pesanan");
			System.out.println("5. Proses Pesanan");
			System.out.println("6. Pendapatan");
			System.out.println("7. Exit");
			System.out.println(">>");
			choose = scan.nextInt();
			scan.nextLine();
			switch (choose) {
			case 1:
				Add();
				break;

			case 2:
				ViewOperasional();
				break;
			case 3:
				Update();
				break;
				
			case 4:
				Delete();
				break;
			case 5:	
				Proses();;
				break;
			case 6:	
				Income();
				break;	
				
				
			}
		} while (choose != 7);
		
	
		
	}
	
	public Main() {
		Login();
		
	}

	public static void main(String[] args) {
		new Main();

	}

}