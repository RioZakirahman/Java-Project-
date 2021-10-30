package Main;

public class PaketSatu extends Pesanan {

	private String namaPaket;
	public int PackagePrice = 12000;
	
	
	
	
	public String getNamaPaket() {
		return namaPaket;
	}




	@Override
	public String getPackageName() {
		String namaPaket = "Paket Satu";
		return namaPaket;
		
	}




	public int getPackagePrice() {
		return PackagePrice;
	}




	public void setPackagePrice(int packagePrice) {
		PackagePrice = packagePrice;
	}




	@Override
	public int TotalPrice() {
		return this.PackagePrice * Pesanan.getWeight();

	}

}
