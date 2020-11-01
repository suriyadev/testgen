package testgen.module.testsrcfilesgen;

import java.io.FileWriter;
import java.net.URL;

public class TestFileWriterImpl implements TestFileWriter {
	
	
	@Override
	public boolean writeFile(URL file, String content) {
		try {
			FileWriter myWriter = new FileWriter(file.getFile()+".java");

			myWriter.write(content);
			myWriter.close();

		} catch (Exception exp) {
			System.out.println(this.getClass().getSimpleName() + " writeFile => " + exp);
		}

		return true;
	}

}
