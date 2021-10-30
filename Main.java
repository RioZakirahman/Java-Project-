package Main;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
	
	private int choose;
	private Scanner scan = new Scanner(System.in);
	
	private ArrayList<Pesanan> listPesanan =  new ArrayList<Pesanan>();

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
	
	public boolean validateDate(String date) {
		
		if (date.trim().equals(""))
		{
		    return true;
		}
		
		else
		{
		   
		    SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
		    format.setLenient(false);
		    try
		    {
		        Date javaDate = format.parse(date); 
		       
		        return true;
		    }
		    
		    catch (Exception e)
		    {
		        System.out.println(date +" is Invalid Date format");
		        return false;
		    }
		   
		    
		}
	}
	
	public String Tanggal() {
		Date d = new Date();
		SimpleDateFormat s = new SimpleDateFormat("dd/mm/yyyy");
		
		String date = s.format(d);
		return date;
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
		int machine;
		
		
	
		
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
			System.out.println("Tanggal Pemesanan [dd/mm/yyyy]:  ");
			tanggal = scan.nextLine();
			boolean test = validateDate(tanggal);
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
			price = berat * paketSatu.getPackagePrice();
		}
		if (paket == 2) {
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
					paketSatu.setWeight(berat);
					change = amount - (paketSatu.getPackagePrice() * paketSatu.getWeight());
					System.out.println("Jumlah kembalian: " + change);	
				}
				if (paket == 2) {
					paketDua.setWeight(berat);
					change = amount - (paketDua.getPackagePrice() * paketDua.getWeight());
					System.out.println("Jumlah kembalian: " + change);	
				}
			
				
		}
		do {
			System.out.println("No Mesin Cuci [pilih mesin cuci no 1 - 5] : ");
			machine = scan.nextInt();
			scan.nextLine();
		} while (machine < 1 || machine > 5);
	
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
			paketSatu.setMachine(machine);
			listPesanan.add(paketSatu);
			paketSatu.setID(generateID());
		}
		if (paket == 2) {
			paketDua.setName(name);
			paketDua.setPhoneNum(phoneNum);
			paketDua.setAddress(address);
			
			paketDua.setPaymentType(priceType);
			paketDua.setDate(tanggal);
			paketDua.setAmount(amount);
			paketDua.setDateOfReturn(tglKeluar);
			paketDua.setMachine(machine);
			listPesanan.add(paketDua);
			paketDua.setID(generateID());
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
			System.out.println("No Mesin Cuci: " + pesanan.getMachine());
			System.out.println("Tanggal Pengembalian: " + pesanan.getDateOfReturn());
		
	
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
			int machine;
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
				System.out.println("Tanggal Pemesanan [MMDDYYYY]:  ");
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
				price = berat * paketSatu.getPackagePrice();
			}
			if (paket == 2) {
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
						change = amount - paketSatu.getPackagePrice();
					}
				
					
			}
			do {
				System.out.println("No Mesin Cuci [pilih mesin cuci no 1 - 5] : ");
				machine = scan.nextInt();
				scan.nextLine();
			} while (machine < 1 || machine > 5);
		
			do {
				System.out.println("Tanggal Pengembalian Pakaian: ");
				tglKeluar = scan.nextLine();
			} while (!(validateDate(tglKeluar)));
			
			
				paketSatu.setName(name);
				paketSatu.setPhoneNum(phoneNum);
				paketSatu.setAddress(address);
				paketSatu.setWeight(berat);
				paketSatu.setPaymentType(priceType);
				paketSatu.setDate(tanggal);
				paketSatu.setAmount(amount);
				paketSatu.setDateOfReturn(tglKeluar);
				paketSatu.setMachine(machine);
				listPesanan.set(input - 1, paketSatu);
			
			
				
			
			
			
			
			
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
				System.out.println("Jumlah Kembalian: " + (listPesanan.get(print-1).getAmount() - listPesanan.get(print-1).TotalPrice()) );	
			}
			if (listPesanan.get(print-1) instanceof PaketDua) {
				System.out.println("Total Harga: " +  listPesanan.get(print-1).TotalPrice());
				System.out.println("Jumlah Kembalian: " + (listPesanan.get(print-1).getAmount() - listPesanan.get(print-1).TotalPrice()) );	
			}
			
			
		
	
			System.out.println();
			System.out.println();
		}
	}
	
	public void Menu() {
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

	
	public Main() {
		Menu();
	}

	public static void main(String[] args) {
		new Main();

	}

}
