package UI.window;

import java.awt.Color;
import java.util.EventObject;

public class PathEvent extends EventObject {
	private Color backgroundColor;

	public PathEvent(Object source, Color backgroundColor) {
		super(source);
		this.backgroundColor = backgroundColor;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	

}
