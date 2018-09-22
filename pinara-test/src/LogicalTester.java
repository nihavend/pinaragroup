
public class LogicalTester {

	public static void main(String[] args) {

		boolean isAutoretry = true;
		boolean wdtCounterBuyukZero = true;
		if(!(isAutoretry && wdtCounterBuyukZero)) {
			System.out.println("start wdtimer1");
		}

		isAutoretry = true;
		wdtCounterBuyukZero = false;	
		if(!(isAutoretry && wdtCounterBuyukZero)) {
			System.out.println("start wdtimer2");
		}

		isAutoretry = false;
		wdtCounterBuyukZero = true;
		if(!(isAutoretry && wdtCounterBuyukZero)) {
			System.out.println("start wdtimer3");
		}

		isAutoretry = false;
		wdtCounterBuyukZero = false;
		if(!(isAutoretry && wdtCounterBuyukZero)) {
			System.out.println("start wdtimer4");
		}
		
		isAutoretry = true;
		wdtCounterBuyukZero = true;
		if(isAutoretry && wdtCounterBuyukZero) {
			System.out.println("do not start wdtimer1");
		}

	}

}
