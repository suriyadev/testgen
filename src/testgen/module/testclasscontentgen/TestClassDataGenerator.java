package testgen.module.testclasscontentgen;

import java.util.List;

import testgen.module.srcdataprovider.SrcFileData;

public interface TestClassDataGenerator {

	public List<TestFileData> getTestClassContents(List<SrcFileData> srcFileData);

	public List<TestFileData> getTestClassContents();
	
}
