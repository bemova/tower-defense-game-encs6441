package UI.window;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class MapFileFilter extends FileFilter {

	@Override
	public boolean accept(File file) {
		if(file.isDirectory())
			return true;
		String name = file.getName();
		Utils utils = new Utils();
		String extention = utils.getFileExtention(name);
		if(extention == null)
			return false;
		if(extention.equals("text"))
			return true;
		return false;
	}

	@Override
	public String getDescription() {
		
		return "map file filter (*.text)";
	}

}
