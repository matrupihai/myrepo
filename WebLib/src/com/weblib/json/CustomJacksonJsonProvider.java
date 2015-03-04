//package com.weblib.json;
//
//import java.io.IOException;
//import java.io.OutputStream;
//import java.lang.reflect.Type;
//
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.MultivaluedMap;
//import javax.ws.rs.ext.Provider;
//
//import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
//import org.codehaus.jackson.map.DeserializationConfig;
//import org.codehaus.jackson.map.ObjectMapper;
//import org.codehaus.jackson.map.SerializationConfig;
//
//@Provider
//public class CustomJacksonJsonProvider extends JacksonJsonProvider {
//	@Override
//	public void writeTo(Object arg0, Class<?> arg1, Type arg2,
//			java.lang.annotation.Annotation[] arg3, MediaType arg4,
//			MultivaluedMap<String, Object> arg5, OutputStream arg6)
//			throws IOException {
//		ObjectMapper mapper = locateMapper(arg1, arg4);
//
//		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false)
//		.configure(SerializationConfig.Feature.INDENT_OUTPUT, true)
//		.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
//		
//		super.writeTo(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
//	}
//}
