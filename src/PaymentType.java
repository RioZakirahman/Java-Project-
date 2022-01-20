

public class PaymentType {


	public static int paymentTypeID;

	
	public static Integer getID(String payment_TypeID) {
		paymentTypeID = 0;
		
		if (payment_TypeID.equals("CASH")) {
			paymentTypeID = 3;	
		} else if (payment_TypeID.equals("OVO")) {
			paymentTypeID = 1;
		} else if (payment_TypeID.equals("DANA")) {
			paymentTypeID = 2;
		}
		
		return paymentTypeID;
	}
	

}
