

public class PackageType {

	private static int id;
	private static String name;
	private static int price;
	private static int weight;
	
	public static void setPackageType(String packageType) {
		if (packageType.equals("Paket Satu")) {
			id = 1;
			name = "Paket Satu";
			price = 12000;
		} else if (packageType.equals("Paket Dua")){
			id = 2;
			name = "Paket Dua";
			price = 20000;
		}
	}
	
	
	public static int getWeight() {
		return weight;
	}


	public static void setWeight(String weight) {
		if (weight.equals("1 kg")) {
			PackageType.weight = 1;
		} else if (weight.equals("2 kg")) {
			PackageType.weight = 2;
		} else if (weight.equals("3 kg")) {
			PackageType.weight = 3;
		} else if (weight.equals("4 kg")) {
			PackageType.weight = 4;
		} else if (weight.equals("5 kg")) {
			PackageType.weight = 5;
		}
	}


	public static int getId() {
		return id;
	}
	
	public static int getPrice() {
		return price;
	}

	public static String getName() {
		return name;
	}


}
