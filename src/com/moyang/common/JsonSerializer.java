package com.moyang.common;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonSerializer {
     public static ObjectMapper mapper = initMapper();

	 protected JsonSerializer() {
	 }

     protected static void setMapper(ObjectMapper m) {
	     mapper = m;
	 }

	 /**
	   * init mapper
	 */
	protected static ObjectMapper initMapper() {
	    ObjectMapper newMapper = new ObjectMapper();
	    return newMapper;
	}
	
	 /**
     * po2json
     */
    public static String serialize(Object obj) {
        Object newObj = obj;

        try {
            String result = mapper.writeValueAsString(newObj);
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
