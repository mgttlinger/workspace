package model.szenario;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileManager {
	static private File folder = new File("data");
	static private String fileEnding = ".sud";
	static private String filePath = String.format("%s%s%s%s", folder.getPath(), File.separatorChar, "%s", fileEnding);

	public static File[] getFileList() {
		File[] files = folder.listFiles();
		for (File file : files)
			System.out.println(file.getAbsolutePath());
		return files;
	}

	public static Szenario readSzenario(File file) {
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
			return (Szenario) in.readObject();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			// ex.printStackTrace();
		}
		return null;
	}

	public static Szenario readSzenario(String filename) {
		return readSzenario(new File(String.format(filePath, filename)));
	}

	public static void saveSzenario(Szenario field) {
		File file = new File(String.format(filePath, Integer.toHexString(field.hashCode())));
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
			out.writeObject(field);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
