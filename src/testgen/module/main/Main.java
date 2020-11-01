package testgen.module.main;

import java.util.Arrays;
import java.util.List;

import testgen.module.srcdataprovider.SrcFileData;
import testgen.module.srcdataprovider.SrcFilesConfig;
import testgen.module.srcdataprovider.SrcFilesDataProvider;
import testgen.module.testclasscontentgen.TestClassDataGeneratorImpl;
import testgen.module.testclasscontentgen.TestFileData;
import testgen.module.testsrcfilesgen.TestSrcFileGeneratorImpl;
import testgen.module.testsrcfilesgen.TestSrcFilesConfig;

public class Main {

	public static void main(String[] args) {

		SrcFilesConfig config = new SrcFilesConfig();
		config.setFullyQualifedSrcClassNames(
				Arrays.asList(new String[] {
						"test.model.Module1", 
						"test.model.Module2",
						"test.model.Module3",
						"test.model.Pojo" }));
		config.setTargetLocation("");

		TestSrcFilesConfig tconfig = new TestSrcFilesConfig();
		tconfig.setTargetLocation("test-target");
		tconfig.setRootPackage("testgen");

		SrcFilesDataProvider oSrcFilesDataProvider = new SrcFilesDataProvider();
		oSrcFilesDataProvider.setSrcFilesConfig(config);
		List<SrcFileData> srcdata = oSrcFilesDataProvider.getsrcFilesData();

		TestClassDataGeneratorImpl objTestClassDataGeneratorImpl = new TestClassDataGeneratorImpl();
		objTestClassDataGeneratorImpl.setTestSrcFilesConfig(tconfig);

		List<TestFileData> testClassData = objTestClassDataGeneratorImpl.getTestClassContents(srcdata);

		TestSrcFileGeneratorImpl oTestSrcFileGeneratorImpl = new TestSrcFileGeneratorImpl();

		oTestSrcFileGeneratorImpl.setTestSrcFilesConfig(tconfig);
		oTestSrcFileGeneratorImpl.generateTestSrcFiles(testClassData);

	}

}
