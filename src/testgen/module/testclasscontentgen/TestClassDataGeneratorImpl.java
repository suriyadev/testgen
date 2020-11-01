package testgen.module.testclasscontentgen;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import testgen.module.srcdataprovider.DataProvider;
import testgen.module.srcdataprovider.SrcFileData;
import testgen.module.testsrcfilesgen.TestSrcFilesConfig;

public class TestClassDataGeneratorImpl implements TestClassDataGenerator {

	private final static String CLOSE_BR = "}\n";
	private final static String OPEN_BR = " {\n";
	private final static String TEST_ANNOTATION = "@Test\n";
	private final static String EMPTY_METHOD_PARAM = "()";
	private final static String BEFORE_ANNOTATION = "@Before\n";
	private final static String COLN = ";\n";
	private final static String PRIVATE = "private ";
	private final static String PUBLIC = "public ";
	private final static String PROTECTED = "protected ";
	private final static String STATIC = " static ";
	private final static String CLASS = " class ";
	private final static String CLASS_ = "class";
	private final static String VOID = " void ";
	private final static String OBJ_PREFIX = " obj";
	private final static String NEW = " = new ";
	private final static String DOT = ".";
	private final static String BREAK = "\n";
	private final static String EQUAL = " = ";
	private final static String COMMA = ",";
	private final static String OPEN_PARAM = "(";
	private final static String CLOSE_PARAM = ");\n";
	private final static String CLOSE_PARAM_ = ")\n";
	private final static String TEST_POSTFIX = "Test";
	private final static String SUITE_CLASS = "JunitTestSuite";

	private StringBuffer headerBuffer = new StringBuffer();

	private TestSrcFilesConfig testSrcFilesConfig;

	public TestSrcFilesConfig getTestSrcFilesConfig() {
		return testSrcFilesConfig;
	}

	public void setTestSrcFilesConfig(TestSrcFilesConfig testSrcFilesConfig) {
		this.testSrcFilesConfig = testSrcFilesConfig;
	}

	private static List<String> methodNamesListHistory = new ArrayList<String>();

	private DataProvider oDataProvider;

	public DataProvider getDataProvider() {
		return oDataProvider;
	}

	public void setDataProvider(DataProvider oDataProvider) {
		this.oDataProvider = oDataProvider;
	}

	@Override
	public List<TestFileData> getTestClassContents(List<SrcFileData> srcFileData) {

		List<TestFileData> contents = new ArrayList<TestFileData>();

		if (testSrcFilesConfig == null) {
			testSrcFilesConfig = new TestSrcFilesConfig(TestSrcFilesConfig.DEFAULT);
		}

		// 1 suite class
		contents.add(new TestFileData(SUITE_CLASS, getSuiteClassContents(srcFileData)));

		// 1...N source test classes
		try {
			for (SrcFileData sSrcFileData : srcFileData) {
				// clear the header contents;
				headerBuffer.delete(0, headerBuffer.length());

				contents.add(
						new TestFileData(sSrcFileData.getSrcFileClassName() + TEST_POSTFIX, getContents(sSrcFileData)));
			}
		} catch (Exception exp) {
			System.out.println(this.getClass().getSimpleName() + " => " + exp);

		}

		return contents;
	}

	private String getSuiteClassContents(List<SrcFileData> srcFileData) {
		StringBuffer str = new StringBuffer();

		str.append("package " + testSrcFilesConfig.getRootPackage() + ";\n import org.junit.runner.RunWith;\n"
				+ "import org.junit.runners.Suite;\n");
		str.append("@RunWith(Suite.class)\n");
		str.append("@Suite.SuiteClasses");

		str.append(OPEN_PARAM);
		str.append(OPEN_BR);

		int pIndex = 1;
		for (SrcFileData psrcFileData : srcFileData) {
			if (pIndex == srcFileData.size()) {
				str.append(psrcFileData.getSrcFileName() + TEST_POSTFIX + DOT + CLASS_);
			} else {
				str.append(psrcFileData.getSrcFileName() + TEST_POSTFIX + DOT + CLASS_ + COMMA);
			}
		}
		str.append(CLOSE_BR);
		str.append(CLOSE_PARAM_);
		str.append(PUBLIC + CLASS + SUITE_CLASS);
		str.append(OPEN_BR);
		str.append(CLOSE_BR);

		return str.toString();
	}

	private String getContents(SrcFileData sSrcFileData) throws IOException {

		List<Method> methodList = sSrcFileData.getMethodList();

		methodNamesListHistory.clear();

		String testClassfileName = sSrcFileData.getSrcFileClassName() + TEST_POSTFIX;

		StringBuilder testClass = new StringBuilder();

		testClass.append(PUBLIC + CLASS + testClassfileName);
		testClass.append(OPEN_BR);

		testClass.append(PRIVATE + sSrcFileData.getSrcFileClassName());
		testClass.append(OBJ_PREFIX + sSrcFileData.getSrcFileClassName());
		testClass.append(NEW + sSrcFileData.getSrcFileClassName() + EMPTY_METHOD_PARAM + COLN);

		headerBuffer.append("import ");
		headerBuffer.append(sSrcFileData.getPackageName());
		headerBuffer.append(DOT);
		headerBuffer.append(sSrcFileData.getSrcFileName());
		headerBuffer.append(COLN);

		testClass.append(getSetupContent(sSrcFileData));

		for (Method method : methodList) {
			testClass.append(TEST_ANNOTATION);
			testClass.append(PUBLIC + VOID + "test" + generateUniqueTestMethodName(method) + EMPTY_METHOD_PARAM);
			testClass.append(OPEN_BR);
			testClass.append(generateBodyContent(method, sSrcFileData));
			testClass.append(CLOSE_BR);
		}

		testClass.append(CLOSE_BR);

		testClass.insert(0, getHeaderContent());

		return testClass.toString();
	}

	private String generateBodyContent(Method method, SrcFileData currClassData) {
		StringBuffer str = new StringBuffer();
		List<String> paramList = new ArrayList<String>();
		List<String> cparamList = new ArrayList<String>();

		// construct the parameter instances
		if (method.getParameterCount() > 0) {
			Parameter[] params = method.getParameters();
			for (int pindex = 0; pindex < params.length; pindex++) {
				String paramType = params[pindex].getParameterizedType().getTypeName();
				paramList.add("param" + pindex);
				str.append(paramType + " param" + pindex);

				if (isPrimitive(paramType)) {
					str.append(getPrimitiveDefault(paramType));
				} else {
					if (paramType.contains("[]")) {
						str.append(NEW + paramType.split("\\[")[0] + "[1]");
					} else {

						if (hasParameterlessPublicConstructor(currClassData.getClassRef().getClass())) {
							str.append(NEW + paramType + EMPTY_METHOD_PARAM);
						}else {
							str.append(getRecursiveParams(currClassData));
						}
					}

				}
				str.append(COLN);
			}
		}

		str.append(OBJ_PREFIX + currClassData.getSrcFileClassName());
		str.append(DOT);
		str.append(method.getName());
		str.append(OPEN_PARAM);

		str.append(genParams(paramList));

		str.append(CLOSE_PARAM);
		str.append(generateLoggerContent(method.getName()));

		return str.toString();
	}

	private String getRecursiveParams(SrcFileData currClassData) {
		StringBuffer str = new StringBuffer();

		try {
		/*	Class classx = Class.forName(paramType);
			Parameter[] params2 = null;
			for (Constructor<?> constructor : classx.getConstructors()) {
				params2 = constructor.getParameters();
			}
			for (int cpindex = 0; cpindex < params2.length; cpindex++) {
				String cparamType = params2[cpindex].getParameterizedType().getTypeName();
				cparamList.add("cparam" + cpindex);
			}

			str.append(paramType + " param" + pindex + NEW + paramType);
			str.append(OPEN_PARAM);
			str.append(genParams(cparamList));
			str.append(CLOSE_PARAM);
			str.append(BREAK);
*/
		} catch (Exception exp) {
			System.out.println(exp);
		}
		return EQUAL+"null";
	}

	private String genParams(List<String> paramList) {
		StringBuffer str = new StringBuffer();
		if (paramList.size() > 0) {
			int pvcount = 1;
			for (String paramVarName : paramList) {
				if (pvcount == paramList.size()) {
					str.append(paramVarName);
				} else {
					str.append(paramVarName + COMMA);
				}
				pvcount++;
			}
		}
		return str.toString();
	}

	private String getSetupContent(SrcFileData currClassData) {
		StringBuffer str = new StringBuffer();
		str.append(BEFORE_ANNOTATION);
		str.append(PUBLIC);
		str.append(VOID);
		str.append("setup");
		str.append(EMPTY_METHOD_PARAM);
		str.append(OPEN_BR);
		str.append(generateLoggerContent("setup executing"));
		str.append(getSetupBody(currClassData));
		str.append(CLOSE_BR);
		return str.toString();
	}

	private String getSetupBody(SrcFileData currClassData) {
		StringBuffer str = new StringBuffer();
		str.append(OBJ_PREFIX + currClassData.getSrcFileClassName());
		str.append(NEW);
		str.append(currClassData.getSrcFileClassName());
		str.append(EMPTY_METHOD_PARAM);
		str.append(COLN);
		return str.toString();
	}

	private String getHeaderContent() {
		String content = "package " + testSrcFilesConfig.getRootPackage() + ";\n" + "import org.junit.Test;\n"
				+ "import org.junit.Before;\n";
		headerBuffer.insert(0, content);
		return headerBuffer.toString();
	}

	private String generateUniqueTestMethodName(Method method) {
		StringBuffer str = new StringBuffer();
		if (methodNamesListHistory.contains(method.getName())) {
			str.append(method.getName() + "_" + getUniqueId());
		} else {
			str.append(method.getName());
		}
		methodNamesListHistory.add(method.getName());
		return str.toString();
	}

	private String getUniqueId() {
		return UUID.randomUUID().toString().split("-")[0];
	}

	private String generateLoggerContent(String value) {
		return "System.out.println(this.getClass().getSimpleName()+\" => " + value + "\")" + COLN;
	}

	@Override
	public List<TestFileData> getTestClassContents() {
		return getTestClassContents(oDataProvider.getsrcFilesData());
	}

	private boolean hasParameterlessPublicConstructor(Class<?> clazz) {
		for (Constructor<?> constructor : clazz.getConstructors()) {
			if (constructor.getParameterCount() == 0) {
				return true;
			}
		}
		return false;
	}

	private boolean isPrimitive(String param) {

		return Arrays.asList(new String[] { "long", "int", "float", "boolean", "short", "char", "double", "byte",
				"long[]", "float[]", "int[]", "boolean[]", "short[]", "char[]", "double[]", "byte[]" }).contains(param);

	}

	private String getPrimitiveDefault(String type) {
		HashMap<String, String> defaults = new HashMap<String, String>();
		defaults.put("int", "0");
		defaults.put("long", "0L");
		defaults.put("boolean", "false");
		defaults.put("short", "0");
		defaults.put("char", "''");
		defaults.put("double", "0d");
		defaults.put("float", "0f");
		defaults.put("byte", "0");
		defaults.put("int[]", " new int[1]");
		defaults.put("long[]", "new long[1]");
		defaults.put("boolean[]", "new boolean[1]");
		defaults.put("short[]", "new short[1]");
		defaults.put("char[]", "new char[1]");
		defaults.put("double[]", "new double[1]");
		defaults.put("float[]", "new float[1]");
		defaults.put("byte[]", "new byte[1]");

		return EQUAL + defaults.get(type);

	}

}
