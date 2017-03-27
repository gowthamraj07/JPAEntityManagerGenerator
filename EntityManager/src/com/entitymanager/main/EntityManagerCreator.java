package com.entitymanager.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class EntityManagerCreator {

	public static void main(String[] args) {
		String pkg = "com.techryte.catering.manager";
		HashMap<String, String> entityDaoMap = new HashMap<String, String>();
		entityDaoMap.put("ClientDetailManager", "ClientDetail");
		entityDaoMap.put("EmployeeManager", "Employee");
		entityDaoMap.put("EventDetailManager", "EventDetail");
		entityDaoMap.put("EventTypeDetailManager", "EventTypeDetail");
		entityDaoMap.put("OrderSummaryManager", "OrderSummary");
		entityDaoMap.put("RecipeManager", "Recipe");
		
		for(Map.Entry<String, String> entity : entityDaoMap.entrySet()) {
			printEntityManager(pkg, entity.getKey(), entity.getValue());
			System.out.println("**********************************************");
		}
		
		
		
		
		
	}
	
	public static void constructEntityManagerFromFile() {
		File file = new File("ItemManager.txt");
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
	
	public static void printEntityManager(String pkg, String className, String daoClassName) {
		System.out.println("package "+pkg+";");
		System.out.println("");
		System.out.println("import java.util.List;");
		System.out.println("");
		System.out.println("import javax.persistence.Query;");
		System.out.println("");
		System.out.println("import org.springframework.stereotype.Component;");
		System.out.println("");
		System.out.println("import com.techryte.catering.model."+daoClassName+";");
		System.out.println("");
		System.out.println("@Component");
		System.out.println("public class "+className+" extends AbstractEntityManager {");
		System.out.println("	");
		System.out.println("	public void add"+daoClassName+"("+daoClassName+" "+daoClassName.toLowerCase().charAt(0)+") {");
		System.out.println("		getEntityManager().getTransaction().begin();");
		System.out.println("		getEntityManager().persist("+daoClassName.toLowerCase().charAt(0)+");");
		System.out.println("		getEntityManager().getTransaction().commit();");
		System.out.println("	}");
		System.out.println("	");
		System.out.println("	public void add"+daoClassName+"s(List<"+daoClassName+"> li) {");
		System.out.println("		getEntityManager().getTransaction().begin();");
		System.out.println("		for("+daoClassName+" "+daoClassName.toLowerCase().charAt(0)+" : li) {");
		System.out.println("			getEntityManager().persist("+daoClassName.toLowerCase().charAt(0)+");");
		System.out.println("		}");
		System.out.println("		getEntityManager().getTransaction().commit();");
		System.out.println("	}");
		System.out.println("");
		System.out.println("	public List<"+daoClassName+"> getAll"+daoClassName+"() {");
		System.out.println("		List<"+daoClassName+"> list = getEntityManager().createQuery(\"select "+daoClassName.toLowerCase().charAt(0)+" from "+daoClassName+" "+daoClassName.toLowerCase().charAt(0)+"\", "+daoClassName+".class).getResultList();");
		System.out.println("		return list;");
		System.out.println("	}");
		System.out.println("	");
		System.out.println("	public long get"+daoClassName+"sCount() {");
		System.out.println("		Query query = getEntityManager().createQuery(\"SELECT count("+daoClassName.toLowerCase().charAt(0)+") FROM "+daoClassName+" "+daoClassName.toLowerCase().charAt(0)+"\");");
		System.out.println("        long count = (long) query.getSingleResult();");
		System.out.println("        return count;");
		System.out.println("	}");
		System.out.println("	");
		
		/*
		System.out.println("	public Item getItemByItemName(String itemName) {");
		System.out.println("		Item result = getEntityManager().createQuery(\"SELECT i FROM Item i WHERE i.name like :itemName\", Item.class)");
		System.out.println("										.setParameter(\"itemName\", \"%\"+itemName+\"%\")");
		System.out.println("										.getResultList().get(0);");
		System.out.println("		return result;");
		System.out.println("	}");
		System.out.println("	");
		System.out.println("	public List<Item> getItemByCategoryName(String categoryName) {");
		System.out.println("		List<Item> result = getEntityManager().createQuery(\"SELECT i FROM Item i WHERE i.categoryName like :categoryName\", Item.class)");
		System.out.println("										.setParameter(\"categoryName\", \"%\"+categoryName+\"%\")");
		System.out.println("										.getResultList();");
		System.out.println("		return result;");
		System.out.println("	}");
		System.out.println("	");
		System.out.println("	public Item getItemByItemId(int itemId) {");
		System.out.println("		Item result = getEntityManager().createQuery(\"SELECT i FROM Item i WHERE i.itemId=:itemId\", Item.class)");
		System.out.println("										.setParameter(\"itemId\", itemId)");
		System.out.println("										.getResultList().get(0);");
		System.out.println("		return result;");
		System.out.println("	}");
		System.out.println("	");
		System.out.println("	public List<String> getCategoryList() {");
		System.out.println("		List<String> result = getEntityManager().createQuery(\"SELECT DISTINCT(i.categoryName) FROM Item i \", String.class)");
		System.out.println("				.getResultList();");
		System.out.println("		return result;");
		System.out.println("		");
		System.out.println("	}");
		*/
		
		System.out.println("}");

	}

}
