package core.domain.maps;

public class EmptyGrid extends Grid {

	public EmptyGrid(int newheiht, int newwidth){
			super();
			height = newheiht;
			width = newwidth;
		}

		
		@Override
	public	void draw(Graphics g) {
			
			for (int i = 0; i < width * sizeOfUnit; i += sizeOfUnit) {
				for (int j = 0; j < width  * sizeOfUnit; j += sizeOfUnit) {
					g.drawLine(i, 0, i, height * sizeOfUnit);
					g.drawLine(0, j, width * sizeOfUnit, j);
				}
			}

			g.drawLine(width * sizeOfUnit - 1, height * sizeOfUnit - 1,
					width * sizeOfUnit - 1, 0); // x1y1 x2y2

			g.drawLine(0, height * sizeOfUnit - 1, width * sizeOfUnit - 1,
					height * sizeOfUnit - 1);

		}

	}

	
