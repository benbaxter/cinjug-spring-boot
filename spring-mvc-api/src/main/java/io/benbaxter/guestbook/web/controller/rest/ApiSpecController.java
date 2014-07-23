package io.benbaxter.guestbook.web.controller.rest;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.NameValueExpression;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

@RestController
public class ApiSpecController {

	@Autowired
	private RequestMappingHandlerMapping requestMappingHandlerMapping;
	
	Map<RequestMappingInfo, HandlerMethod> handlerMethods;
	
	@PostConstruct
	public void init() {
		Map<RequestMappingInfo, HandlerMethod> handlerMethods = this.requestMappingHandlerMapping
				.getHandlerMethods();

		for (Entry<RequestMappingInfo, HandlerMethod> item : handlerMethods
				.entrySet()) {
			RequestMappingInfo mapping = item.getKey();
			HandlerMethod method = item.getValue();

			for (String urlPattern : mapping.getPatternsCondition()
					.getPatterns()) {
				System.out.println(method.getBeanType().getName() + "#"
						+ method.getMethod().getName() + " <-- " + urlPattern);

				if (urlPattern.equals("some specific url")) {
					// add to list of matching METHODS
				}
			}
		}
		
		this.handlerMethods = this.requestMappingHandlerMapping.getHandlerMethods();
	}
	
	@Description("An api for definging the api spec")
	@RequestMapping("/api-spec") 
	public Map<String, Set<MappingInfo>> apiSpec() {
		
		Map<String, Set<MappingInfo>> api = Maps.newHashMap();
		
		for (Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethods.entrySet()) {
			MappingInfo info = new MappingInfo();
			
			RequestMappingInfo mappingInfo = entry.getKey();
			info.methods = mappingInfo.getMethodsCondition().getMethods();
			info.patterns = mappingInfo.getPatternsCondition().getPatterns();
			info.headers = mappingInfo.getHeadersCondition().getExpressions();
			info.params = mappingInfo.getParamsCondition().getExpressions();
			info.produces = mappingInfo.getProducesCondition().getProducibleMediaTypes();
			
			
			HandlerMethod handlerMethod = entry.getValue();
			Description description = handlerMethod.getMethodAnnotation(Description.class);
			if( description != null ) {
				info.description = description.value(); 
			}
			info.beanSimpleName = handlerMethod.getBeanType().getSimpleName();
			info.beanMethodName = handlerMethod.getMethod().getName();
			info.returnType = handlerMethod.getReturnType()
					.getMethod().getGenericReturnType().getTypeName();
			
			if( api.get(info.beanSimpleName) == null ) {
				api.put(info.beanSimpleName, Sets.newHashSet());
			}
			api.get(info.beanSimpleName).add(info);
		}
		
		return api;
	}
	
	class MappingInfo {
		public String description;
		Set<RequestMethod> methods;
		Set<String> patterns;
		Set<NameValueExpression<String>> headers;
		Set<NameValueExpression<String>> params;
		Set<MediaType> produces;
		String beanSimpleName;
		String beanMethodName;
		String returnType;
		
		public Set<RequestMethod> getMethods() {
			return methods;
		}
		public Set<String> getPatterns() {
			return patterns;
		}
		public String getBeanMethodName() {
			return beanMethodName;
		}
		public String getBeanSimpleName() {
			return beanSimpleName;
		}
		public Set<NameValueExpression<String>> getHeaders() {
			return headers;
		}
		public Set<NameValueExpression<String>> getParams() {
			return params;
		}
		public Set<MediaType> getProduces() {
			return produces;
		}
		public String getReturnType() {
			return returnType;
		}
		public String getDescription() {
			return description;
		}
	}
	
	
}