package UI.window;

import java.util.EventObject;

public class FormEvent extends EventObject {

	private int height;
	private int width;
	
	public FormEvent(Object source, int width, int height) {
		super(source);
		this.height = height;
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	

}
