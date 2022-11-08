package com.kaede.myssm.myspringmvc;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kaede
 * @create 2022-11-07
 */

@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet {

    //存放bean中id和class对应的实例对象
    private Map<String,Object> beanMap = new HashMap<>();

    public void init() throws ServletException {
        super.init();
        try(InputStream is = getClass().getClassLoader().getResourceAsStream("applicationContext.xml")) {
            //1.创建DocumentBuilderFactory
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            //2.创建DocumentBuilder对象
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder() ;
            //3.创建Document对象解析配置文件applicationContext.xml
            Document document = documentBuilder.parse(is);

            //4.获取所有的bean节点
            NodeList beanNodeList = document.getElementsByTagName("bean");
            for(int i = 0 ; i < beanNodeList.getLength() ; i++){
                Node beanNode = beanNodeList.item(i);
                //节点为元素节点
                if(beanNode.getNodeType() == Node.ELEMENT_NODE){
                    Element beanElement = (Element) beanNode;
                    String beanId =  beanElement.getAttribute("id");
                    String className = beanElement.getAttribute("class");
                    Class controllerBeanClass = Class.forName(className);
                    Object beanObj = controllerBeanClass.newInstance();
                    beanMap.put(beanId , beanObj) ;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("UTF-8");
        //假设url是：  http://localhost:8080/pro18/fruit/index.do
        //那么servletPath是：   /pro18/fruit/index.do
        //第1步：/pro18/fruit/index.do -> fruit
        //第2步：fruit -> FruitController

        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        //有上下文，/pro17/fruit/add.do；无上下文，/fruit/add.do
        //uri2  fruit/add.do
        String uri2 = uri.substring(contextPath.length()+1);
        //servletPath  fruit
        String servletPath = uri2.substring(0, uri2.indexOf("/"));
        //temp  add.do
        String temp = uri2.substring(uri2.indexOf("/") + 1);
        //methodName  add
        String methodName = temp.substring(0, temp.length() - 3);
        Object controllerBeanObj = beanMap.get(servletPath);

        try {
            Method[] methods = controllerBeanObj.getClass().getDeclaredMethods();
            for(Method method : methods) {
                if(methodName.equals(method.getName())){
                    //1.统一获取请求参数

                    //1-1.获取当前方法的参数，返回参数数组
                    Parameter[] parameters = method.getParameters();
                    //1-2.parameterValues 用来承载参数的值
                    Object[] parameterValues = new Object[parameters.length];
                    for (int i = 0; i < parameters.length; i++) {
                        Parameter parameter = parameters[i];
                        String parameterName = parameter.getName() ;
                        //如果参数名是request,response,session，那么就不是通过请求中获取参数的方式了
                        if("request".equals(parameterName)) {
                            parameterValues[i] = request ;
                        } else if("response".equals(parameterName)) {
                            parameterValues[i] = response ;
                        } else if("session".equals(parameterName)) {
                            parameterValues[i] = request.getSession() ;
                        } else {
                            //从请求中获取参数值
                            String parameterValue = request.getParameter(parameterName);
                            String typeName = parameter.getType().getName();

                            Object parameterObj = parameterValue ;

                            if(parameterObj != null) {
                                if ("java.lang.Integer".equals(typeName)) {
                                    parameterObj = Integer.parseInt(parameterValue);
                                }
                            }

                            parameterValues[i] = parameterObj ;
                        }
                    }
                    //2.controller组件中的方法调用
                    method.setAccessible(true);
                    Object returnObj = method.invoke(controllerBeanObj, parameterValues);

                    //3.视图处理
                    String methodReturnStr = (String) returnObj ;
                    if(methodReturnStr.startsWith("redirect:")){        //比如：redirect:index.do
                        String redirectStr = methodReturnStr.substring("redirect:".length());
                        response.sendRedirect(redirectStr);
                        return;
                    }else{
                        super.processTemplate(methodReturnStr,request,response);    //比如："edit"
                        return;
                    }
                }
            }

            throw new RuntimeException("URL Error!");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
