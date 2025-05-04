package plance;

public class PlanceNaveLivello1 extends PlanceNave{

	public PlanceNaveLivello1() {
		super(4, 6);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void creaNave() {
		int r=0;
		int c=0;
		
		if (r == 0) {
			if (c == 3) {
				getCaselle()[c][r].setOccupato(true);
				
		}
		}
		if (r == 1) {
			for(c = 2; c <= 4; c++) {
				getCaselle()[c][r].setOccupato(true);
			}
		}
		
		if (r == 2) {
			for(c = 1; c <= 5; c++) {
				getCaselle()[c][r].setOccupato(true);
			}
		}
		
		if (r == 3) {
			for(c = 1; c <= 5; c++) {
				getCaselle()[c][r].setOccupato(true);
			}
		}
		
		if (r == 4) {
			for(c = 1; c <= 2; c++) {
				getCaselle()[c][r].setOccupato(true);
			}
			for(c = 4; c <= 5; c++) {
				getCaselle()[c][r].setOccupato(true);
			}
		}	
	}

	@Override
	public void stampaNave() {
		for(int r = 0; r <= 4; r++) {
			for(int c = 0; c <= 6; c++) {
				if(getCaselle()[r][c].isOccupato()) {
					System.out.println("▢");
				}else {
					System.out.println(" ");
				}
				
			}
		}
	}
	
}
