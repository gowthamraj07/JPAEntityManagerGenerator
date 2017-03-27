package com.entitymanager.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class DaoControllerCreator {

	public static void main(String[] args) {
		HashMap<String, String> entityDaoMap = new HashMap<String, String>();
		entityDaoMap.put("ClientDetailManager", "ClientDetail");
		entityDaoMap.put("EmployeeManager", "Employee");
		entityDaoMap.put("EventDetailManager", "EventDetail");
		entityDaoMap.put("EventTypeDetailManager", "EventTypeDetail");
		entityDaoMap.put("OrderSummaryManager", "OrderSummary");
		entityDaoMap.put("RecipeManager", "Recipe");
		
		for(Map.Entry<String, String> entity : entityDaoMap.entrySet()) {
			//printDeclarations(entity.getValue());
			printServices(entity.getValue());
			//System.out.println("**********************************************");
		}
		
		
		
		//constructDAOControllerFromFile();
		
	}
	
	public static void constructDAOControllerFromFile() {
		File file = new File("DAOController.txt");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String line = null;
			while((line = br.readLine()) != null) {
				System.out.println("System.out.println(\""+line.replaceAll("\"", "\\\\\"")+"\");");
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void printDeclarations(String className) {
		String objName = className.toLowerCase().charAt(0)+className.substring(1);
		System.out.println("	"+className+"Manager "+objName+"Manager;");
		System.out.println("	");
		System.out.println("	");
		System.out.println("	@Autowired");
		System.out.println("	public void set"+className+"Manager("+className+"Manager "+objName+"Manager) {");
		System.out.println("		this."+objName+"Manager = "+objName+"Manager;");
		System.out.println("	}");
		System.out.println("");
	}
	public static void printServices(String className) {
		String objName = className.toLowerCase().charAt(0)+className.substring(1);
		System.out.println("");
		System.out.println("	@RequestMapping(value=\"/add"+className+"\", method=RequestMethod.POST)");
		System.out.println("	@ResponseBody");
		System.out.println("	public ResponseDto<String> add"+className+"(@RequestBody "+className+" "+objName+") {");
		System.out.println("		ResponseDto<String> response = new ResponseDto<String>();");
		System.out.println("		try {");
		System.out.println("			if("+objName+" == null) {");
		System.out.println("				throw new NullPointerException(\"data not assigned properly\");");
		System.out.println("			}");
		System.out.println("			"+objName+"Manager.add"+className+"("+objName+");");
		System.out.println("			response.setData(\"true\");");
		System.out.println("		} catch (Exception e) {");
		System.out.println("			e.printStackTrace();");
		System.out.println("			response.setData(\"false\");");
		System.out.println("		}");
		System.out.println("		return response;");
		System.out.println("	}");
		System.out.println("	");
		System.out.println("	@RequestMapping(value=\"/add"+className+"s\", method=RequestMethod.POST)");
		System.out.println("	@ResponseBody");
		System.out.println("	public ResponseDto<String> add"+className+"s(@RequestBody "+className+"List li) {");
		System.out.println("		ResponseDto<String> response = new ResponseDto<String>();");
		System.out.println("		try {");
		System.out.println("			if(li == null) {");
		System.out.println("				throw new NullPointerException(\"data not assigned properly\");");
		System.out.println("			}");
		System.out.println("			System.out.println(li);");
		System.out.println("			"+objName+"Manager.add"+className+"s(li);");
		System.out.println("			response.setData(\"true\");");
		System.out.println("		} catch (Exception e) {");
		System.out.println("			e.printStackTrace();");
		System.out.println("			response.setData(\"false\");");
		System.out.println("		}");
		System.out.println("		return response;");
		System.out.println("	}");
		System.out.println("	");
		System.out.println("	");
		System.out.println("	@RequestMapping(value=\"/count"+className+"\", method=RequestMethod.GET)");
		System.out.println("	@ResponseBody");
		System.out.println("	public ResponseDto<String> count"+className+"() {");
		System.out.println("		ResponseDto<String> response = new ResponseDto<String>();");
		System.out.println("		response.setData(\"\"+"+objName+"Manager.get"+className+"sCount());");
		System.out.println("		return response;");
		System.out.println("	}");
		System.out.println("	");
		System.out.println("	@RequestMapping(value=\"/getAll"+className+"s\", method=RequestMethod.GET)");
		System.out.println("	@ResponseBody");
		System.out.println("	public ResponseDto<List<"+className+">> getAll"+className+"s() {");
		System.out.println("		ResponseDto<List<"+className+">> response = new ResponseDto<List<"+className+">>();");
		System.out.println("		List<"+className+"> "+objName+"s = new LinkedList<"+className+">();");
		System.out.println("		"+objName+"s.addAll("+objName+"Manager.getAll"+className+"());");
		System.out.println("		response.setData("+objName+"s);");
		System.out.println("		return response;");
		System.out.println("	}");
		System.out.println("");


	}

}
