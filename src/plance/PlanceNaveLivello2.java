package plance;

public class PlanceNaveLivello2 extends PlanceNave{

	public PlanceNaveLivello2() {
		super(4, 6);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void creaNave() {
		for (int r = 0; r <=4; r++) {
		int c=0;
		if (r == 0) {
			if (c == 2) {
				getCaselle()[c][r].setUtilizzabile(true);	
		}
			if (c == 4) {
				getCaselle()[c][r].setUtilizzabile(true);
				
		}
		}
		if (r == 1) {
			for(c = 1; c <= 5; c++) {
				getCaselle()[c][r].setUtilizzabile(true);
			}
		}
		
		if (r == 2) {
			for(c = 0; c <= 6; c++) {
				getCaselle()[c][r].setUtilizzabile(true);
			}
		}
		
		if (r == 3) {
			for(c = 0; c <= 6; c++) {
				getCaselle()[c][r].setUtilizzabile(true);
			}
		}
		
		if (r == 4) {
			for(c = 0; c <= 2; c++) {
				getCaselle()[c][r].setUtilizzabile(true);
			}
			for(c = 4; c <= 6; c++) {
				getCaselle()[c][r].setUtilizzabile(true);
			}
		}	
		}
	}
	
	@Override
	public void stampaNave() {
		for(int r = 0; r <= 4; r++) {
			for(int c = 0; c <= 6; c++) {
				if(getCaselle()[r][c].isUtilizzabile()) {
					System.out.println("▢");
				}else {
					System.out.println(" ");
				}
				
			}
		}
	}


}
