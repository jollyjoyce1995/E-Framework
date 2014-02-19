package at.grueneis.spengergasse.lesson_plan.persistence.jdbc;

import java.util.UUID;

public class IdGeneration {
	
	public static long generateUniqueId() {      
	       UUID idOne = UUID.randomUUID();
	       String str=""+idOne;        
	       int uid=str.hashCode();
	       String filterStr=""+uid;
	       str=filterStr.replaceAll("-", "");
	       return (long) Integer.parseInt(str);
	   }

}
