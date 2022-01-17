

public class Price {

	private static int cost;
	
	
	public static void setPrice(int packagePrice, int weight) {
		cost = packagePrice * weight;
	}
	
	public static Integer getPrice() {
		return cost;
	}
	
	public Price() {
	
	}

}
