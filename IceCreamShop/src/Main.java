import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class Main {
	Scanner scan = new Scanner(System.in);
	Random rand = new Random();
	
	void cls() {
		for(int i=0; i<20; i++) {
			System.out.println();
		}
	}
	
	void menu1(Vector<IceCream> iceCreamVec) {
		if(iceCreamVec.isEmpty()) {
			System.out.println("No Ice Cream");
			return;
		}
		System.out.println("===============================================================================================================");
		System.out.println("| ID    | Name                           | Size       | Toppings                                 | Price      |");
		System.out.println("===============================================================================================================");
		for (IceCream iceCream : iceCreamVec) {
			System.out.printf("| %s | %-30s | %-10s | %-40s | %-10d |\n", iceCream.getId(), iceCream.getName(), iceCream.getSize(), iceCream.getTopping(), iceCream.getPrice());
		}
		System.out.println("===============================================================================================================");
	}
	
	void menu2(Vector<IceCream> iceCreamVec) {
		int tempPrice = 0;
		String tempName = "";
		do {
			System.out.print("Input Ice Cream Name : ");
			tempName = scan.nextLine();
		}while(tempName.equals(""));
		
		String tempSize;
		do {
			System.out.print("Choose Ice Cream Size [Small | Medium | Large] : ");
			tempSize = scan.nextLine();
		}while(!tempSize.equals("Small") && !tempSize.equals("Medium") && !tempSize.equals("Large"));
		
		if(tempSize.equals("Small")) {
			tempPrice += 5000;
		}
		else if(tempSize.equals("Medium")) {
			tempPrice += 8000;
		}
		else if(tempSize.equals("Large")) {
			tempPrice += 10000;
		}
		
		String tempId = "IC";
		Integer randNum;
		for(int i=0; i<3; i++) {
			randNum = rand.nextInt(10);
			tempId += randNum.toString(); 
		};
		
		IceCream currIceCream = new IceCream(tempId, tempName, tempSize);
		
		int[] toppingUnique = new int[3];
		boolean flag;
		int topPick = 0;
		for(int i=0; i<3; i++) {
			do {
				flag = false;
				System.out.print("1. Chocolate - 2000");
				if(toppingUnique[0] == 1) {
					System.out.println(" (Added)");
				}
				else {
					System.out.println();
				}
				System.out.print("2. Vanilla - 3000");
				if(toppingUnique[1] == 1) {
					System.out.println(" (Added)");
				}
				else {
					System.out.println();
				}
				System.out.print("3. Strawberry - 3500");
				if(toppingUnique[2] == 1) {
					System.out.println(" (Added)");
				}
				else {
					System.out.println();
				}

				do {					
					System.out.print("Pick Topping [1-3] [0 to skip] : ");
					try {
						topPick = scan.nextInt();
						scan.nextLine();
					} catch (Exception e) {
						topPick = -1;
						scan.nextLine();
					}
				}while(topPick > 3 || topPick < 0);
				
				if(topPick != 0 && toppingUnique[topPick-1] == 1) {
					System.out.println("You Already Add this topping");
					System.out.println("press Enter to Continue...");
					scan.nextLine();
					flag = true;
				}
				else if(topPick != 0){
					toppingUnique[topPick-1] = 1;
				}
			}while(flag == true && topPick != 0);
			
			if(topPick != 0) {				
				switch(topPick) {
					case 1:{
						currIceCream.addTopping("Chocolate");
						tempPrice += 2000;
					}break;
					
					case 2:{
						currIceCream.addTopping("Vanilla");
						tempPrice += 3000;
					}break;
					
					case 3:{
						currIceCream.addTopping("Strawberry");
						tempPrice += 3500;
					}
				}
			}
			System.out.println("Success Insert Ice Cream");
			System.out.println("press Enter to Continue...");
			scan.nextLine();
		}
		
		currIceCream.setPrice(tempPrice);
		iceCreamVec.addElement(currIceCream);
		
		System.out.println("Success add Ice Cream");
	}
	
	int searchId(Vector<IceCream> iceCreamVec, String id) {
		
		if(id.length() > 5 || !id.contains("IC")) {
			return -1;
		}
		
		int idx = 0;
		for (IceCream iceCream : iceCreamVec) {
			if(iceCream.getId().equals(id)) {
				return idx;
			}
			idx += 1;
		}
		
		return -1;
	}
	
	void menu3(Vector<IceCream> iceCreamVec) {
		menu1(iceCreamVec);
		if(iceCreamVec.isEmpty()) {
			return;
		}
		
		String id;
		int idx;
		do {
			System.out.print("Choose Ice Cream ID to be deleted ['cancel' to Cancel]: ");
			id = scan.nextLine();
			idx = searchId(iceCreamVec, id);
		}while(!id.equals("cancel") && idx == -1);
		
		if(!id.equals("cancel")) {
			iceCreamVec.remove(idx);
			System.out.println("Success remove Ice Cream");
		}
	}
	
	public Main() {
		Vector<IceCream> iceCreamVec = new Vector<>();
		int put;
		
		do {
			cls();
			System.out.println("Ice Cream Shop");
			System.out.println("1. View Ice Cream");
			System.out.println("2. Add Ice Cream");
			System.out.println("3. Delete Ice Cream");
			System.out.println("4. Exit");
			do {
				System.out.print("Choose : ");
				try {					
					put = scan.nextInt();
					scan.nextLine();
				} catch (Exception e) {
					put = 0;
					scan.nextLine();
				}
			}while(put < 1 || put > 4);
			
			switch (put) {
				case 1:{
					menu1(iceCreamVec);
				}break;
				
				case 2:{
					menu2(iceCreamVec);
				}break;
				
				case 3: {
					menu3(iceCreamVec);
				}break;
			}
			if(put != 4) {
				System.out.println("Press Enter to Continue...");
				scan.nextLine();
			}
		}while(put != 4);
	}

	public static void main(String[] args) {
		new Main();

	}

}
