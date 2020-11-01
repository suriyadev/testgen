package testgen.module.testsrcfilesgen;

import java.util.List;

import testgen.module.testclasscontentgen.TestFileData;

public interface SrcFileGenerator {

	public boolean generateTestSrcFiles(List<TestFileData> testClassData);
}
