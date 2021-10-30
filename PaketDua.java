package Main;

public class PaketDua extends Pesanan {

	private int PackagePrice = 20000;
	
	
	
	@Override
	public int TotalPrice() {
		
		return this.PackagePrice * Pesanan.getWeight();
	}
	@Override
	public String getPackageName() {
		String namaPaket = "Paket Dua";
		return namaPaket;
	}
	
	public int getPackagePrice() {
		return PackagePrice;
	}

	
	
}


