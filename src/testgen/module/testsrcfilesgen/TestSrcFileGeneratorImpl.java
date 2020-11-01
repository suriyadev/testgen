package testgen.module.testsrcfilesgen;

import java.io.File;
import java.util.List;

import testgen.module.testclasscontentgen.TestFileData;

public class TestSrcFileGeneratorImpl implements SrcFileGenerator {

	private TestSrcFilesConfig testSrcFilesConfig;

	public TestSrcFilesConfig getTestSrcFilesConfig() {
		return testSrcFilesConfig;
	}

	public void setTestSrcFilesConfig(TestSrcFilesConfig oTestSrcFilesConfig) {
		this.testSrcFilesConfig = oTestSrcFilesConfig;
	}

	public TestSrcFileGeneratorImpl() {
		// defaults
		this.testSrcFilesConfig = new TestSrcFilesConfig(TestSrcFilesConfig.DEFAULT);
		this.testFileWriter = new TestFileWriterImpl();
	}

	private TestFileWriter testFileWriter;

	public TestFileWriter getTestFileWriter() {
		return testFileWriter;
	}

	public void setTestFileWriter(TestFileWriter testFileWriter) {
		this.testFileWriter = testFileWriter;
	}


	
	public boolean generateTestSrcFiles(List<TestFileData> testClassData) {

		File targetDir = new File(testSrcFilesConfig.getTargetLocation());
		File rootPackage = new File(targetDir + "/" + testSrcFilesConfig.getRootPackage());

		if (!targetDir.exists())
			targetDir.mkdir();

		if (!rootPackage.exists())
			rootPackage.mkdir();

		try {
			for (TestFileData oTestFileData : testClassData) {

				File file = new File(rootPackage + "/" + oTestFileData.getTestClassName());

				boolean filestatus = testFileWriter.writeFile(file.toURL(), oTestFileData.getTestClassContent());

				if (filestatus) {
					System.out.println(oTestFileData.getTestClassName() + " Source files generated.");
				}
			}
		} catch (Exception exp) {
			System.out.println(this.getClass().getSimpleName() + " generateTestFiles => " + exp);
		}
		return true;
	}

}
