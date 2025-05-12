package plance;

public class PlanceNaveLivello3 extends PlanceNave{

	public PlanceNaveLivello3() {
		super(5, 8);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void creaNave() {
		
		for (int r = 0; r <= 4; r++) {
		int c=0;
		
		if (r == 0) {
			if (c == 4) {
				getCaselle()[c][r].setUtilizzabile(true);
		}
		}
		if (r == 1) {
			for(c = 3; c <= 5; c++) {
				getCaselle()[c][r].setUtilizzabile(true);
			}
		}
		
		if (r == 2) {
			if (c == 0) {
				getCaselle()[c][r].setUtilizzabile(true);
			}
			for(c = 2; c <= 6; c++) {
				getCaselle()[c][r].setUtilizzabile(true);
			}
			if (c == 8) {
				getCaselle()[c][r].setUtilizzabile(true);
			}
		}
		
		if (r == 3) {
			for(c = 0; c <= 8; c++) {
				getCaselle()[c][r].setUtilizzabile(true);
			}
		}
		if (r == 4) {
			for(c = 0; c <= 8; c++) {
				getCaselle()[c][r].setUtilizzabile(true);
			}
		}
		
		if (r == 5) {
			for(c = 0; c <= 1; c++) {
				getCaselle()[c][r].setUtilizzabile(true);
			}
			for(c = 3; c <= 5; c++) {
				getCaselle()[c][r].setUtilizzabile(true);
			}
			for(c = 7; c <= 8; c++) {
				getCaselle()[c][r].setUtilizzabile(true);
			}
		}
		}
	}
	
	@Override
	public void stampaNave() {
		for(int r = 0; r <= 5; r++) {
			for(int c = 0; c <= 8; c++) {
				if(getCaselle()[r][c].isUtilizzabile()) {
					System.out.println("▢");
				}else {
					System.out.println(" ");
				}
				
			}
		}
	}
	


}
