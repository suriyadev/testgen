package testgen.module.srcdataprovider;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class SrcFilesDataProvider implements DataProvider {

	private SrcFilesConfig srcFilesConfig;

	public SrcFilesConfig getSrcFilesConfig() {
		return srcFilesConfig;
	}

	public void setSrcFilesConfig(SrcFilesConfig srcFilesConfig) {
		this.srcFilesConfig = srcFilesConfig;
	}

	@Override
	public List<SrcFileData> getsrcFilesData() {

		if (srcFilesConfig == null) {
			srcFilesConfig = new SrcFilesConfig(SrcFilesConfig.DEFAULT);
		}

		List<SrcFileData> list = new ArrayList<SrcFileData>();
		List<String> classRefNameList = getSrcClassNames();
		for (String className : classRefNameList) {
			try {
				Class classRef = Class.forName(className);
				SrcFileData srcFileData = new SrcFileData();
				srcFileData.setClassRef(classRef);

				List<Object> methodR = getSrcMethods(classRef);

				srcFileData.setPackageName(classRef.getPackageName());
				srcFileData.setMethodList((List<Method>) methodR.get(0));
				srcFileData.setMethodNamesList((List<String>) methodR.get(1));
				
				srcFileData.setNoOfMethods(srcFileData.getMethodList().size());
				srcFileData.setSrcFileClassName(classRef.getSimpleName());
				srcFileData.setSrcFileName(classRef.getSimpleName());
				list.add(srcFileData);

			} catch (Exception exp) {
				System.out.println(this.getClass().getSimpleName() + " => " + exp);
			}
		}

		return list;
	}

	private List<String> getSrcClassNames() {
		List<String> classRefNameList = srcFilesConfig.getFullyQualifedSrcClassNames();
		return classRefNameList;

	}

	private List<Object> getSrcMethods(Class cls) {
		List<Method> list = new ArrayList<Method>();
		List<String> listNames = new ArrayList<String>();
		List<Object> result = new ArrayList<Object>();

		Method[] allMethods = cls.getDeclaredMethods();
		for (Method method : allMethods) {
			if (Modifier.isPublic(method.getModifiers())) {
				list.add(method);
				listNames.add(method.getName());
			}
		}
		result.add(list);
		result.add(listNames);
		return result;
	}

}
