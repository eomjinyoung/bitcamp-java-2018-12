package com.eomcs.lms.context;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.ibatis.io.Resources;
import com.eomcs.lms.handler.Command;

// Command 객체를 자동 생성하는 역할을 수행한다.
public class ApplicationContext {
  
  // 인스턴스를 생성할 클래스 정보
  ArrayList<Class<?>> classes = new ArrayList<>();
  
  // 생성한 인스턴스를 보관하는 저장소
  HashMap<String,Object> beanContainer = new HashMap<>();
  
  public ApplicationContext(String packageName, Map<String,Object> beans) throws Exception {
    
    // 외부에서 생성한 인스턴스가 파라미터로 넘어온다면 먼저 저장소에 보관한다.
    if (beans != null && beans.size() > 0) {
      Set<String> names = beans.keySet();
      for (String name : names) {
        addBean(name, beans.get(name));
      }
    }
    
    // 1) 패키지명으로 디렉토리 경로를 알아낸다.
    File packageDir = Resources.getResourceAsFile(packageName.replace(".", "/"));
   
    // 2) 해당 패키지 폴더와 그 하위 폴더를 뒤져서 클래스 이름을 알아낸다.
    // => 인스턴스를 생성할 수 없는 인터페이스나 추상 클래스는 제외한다.
    // => 또한 중첩 클래스도 제외한다.
    findClasses(packageDir, packageName);
    
    // 3) Command 인터페이스를 구현한 클래스만 찾아서 인스턴스를 생성한다.
    prepareCommand();
    
    // 저장소에 보관된 객체의 이름과 클래스명을 출력한다.
    System.out.println("-------------------------------");
    Set<String> names = beanContainer.keySet();
    for (String name : names) {
      System.out.printf("%s : %s\n", 
          name, beanContainer.get(name).getClass().getSimpleName());
    }
  }
  
  // 인스턴스를 추가할 때 호출한다.
  // 빈(bean) == 인스턴스 == 객체 
  //
  private void addBean(String name, Object bean) {
    if (name == null || name.length() == 0 || bean == null)
      return;
    beanContainer.put(name, bean);
  }
  
  // 저장소에 보관된 인스턴스를 꺼낸다.
  public Object getBean(String name) {
    return beanContainer.get(name);
  }
  
  private void findClasses(File dir, String packageName) throws Exception {
    // 디렉토리를 뒤져서 클래스 파일(.class)이나 하위 디렉토리 목록을 알아낸다.
    File[] files = dir.listFiles((File pathname) -> {
        if (pathname.isDirectory())
          return true;
        
        if (pathname.getName().endsWith(".class")/* 자바 클래스 파일 */ &&
            !pathname.getName().contains("$")/* 중첩 클래스가 아닌 경우 */)
          return true;
        
        return false;
      });
    
    for (File f : files) {
      if (f.isFile()) {
        // 클래스 파일일 경우,
        // => 파라미터로 받은 패키지 명과 파일 이름을 합쳐서 클래스 이름을 만든다.
        //    예) com.eomcs.lms(패키지명) + . + ServerApp(파일명) = com.eomcs.lms.ServerApp
        String filename = f.getName();
        String className = packageName + "." + 
            filename.substring(0, filename.indexOf('.'));
        
        // => 클래스 이름으로 클래스 파일(.class)을 로딩한다.
        Class<?> clazz = Class.forName(className);
        
        // => 클래스 정보를 분석하여 중첩 클래스이거나 인터페이스, Enum 이면 무시한다.
        if (clazz.isLocalClass() || clazz.isInterface() || clazz.isEnum())
          continue;
        
        // => 추상 클래스나 공개되지 않은 클래스(public이 아닌 클래스)도 무시한다.
        if (Modifier.isAbstract(clazz.getModifiers()) ||
            !Modifier.isPublic(clazz.getModifiers()))
          continue;
        
        // 즉 공개된(public) 일반 클래스인 경우 클래스 관리 목록에 추가한다. 
        classes.add(clazz);
        
      } else {
        // 디렉토리일 경우, 그 하위 디렉토리에서 다시 클래스를 찾는다.
        // => 하위 디렉토리에서 클래스를 찾을 때 사용할 패키지명을 준비한다.
        // => 파라미터로 받은 패키지 이름에 하위 디렉토리 이름을 붙이면 전체 패키지명이 된다.
        // => com.eomcs.lms(현재 패키지 이름) + . + dao(디렉토리 이름) = com.eomcs.lms.dao
        findClasses(f, packageName + "." + f.getName());
      }
    }
  }
  
  private void prepareCommand() throws Exception {
    for (Class<?> clazz : classes) {
      // 클래스 또는 조상 클래스가 구현한 인터페이스의 목록을 알아낸다.
      List<Class<?>> interfaces = getAllInterfaces(clazz);
      
      for (Class<?> i : interfaces) {
        if (i == Command.class) {
          // Command 인터페이스의 구현체인 경우 해당 클래스의 인스턴스를 생성한다.
          Object obj = createInstance(clazz);
          if (obj != null) { // 제대로 생성했으면 빈컨테이너에 저장한다.
            // 빈컨테이너에 Command 객체를 저장할 때 key 값은 name 필드 값으로 한다.
            // => 클래스에서 getName() 메서드를 알아낸다.
            Method getName = clazz.getMethod("getName");
            addBean(
                (String) getName.invoke(obj), // getName()을 호출하여 리턴 값을 키로 사용한다.
                obj);
          }
          break;
        }
      }
    }
  }
  
  private List<Class<?>> getAllInterfaces(Class<?> clazz) {
    ArrayList<Class<?>> list = new ArrayList<>();
    
    while (clazz != Object.class) {
      Class<?>[] interfaces = clazz.getInterfaces();
      for (Class<?> i : interfaces) 
        list.add(i);
      clazz = clazz.getSuperclass();
    }
    
    return list;
  }
  
  private Object createInstance(Class<?> clazz) throws Exception {
    // 파라미터로 주어진 클래스 정보를 가지고 인스턴스를 생성한다.
    // => 기본 생성자를 알아낸다.
    try { 
      Constructor<?> defaultConstructor = clazz.getConstructor();
      return defaultConstructor.newInstance();
    } catch (Exception e) {
      // 기본 생성자를 못 찾으면 예외 발생한다. 
      // 그냥 무시하고 다른 생성자를 찾아 인스턴스를 생성한다.
    }
    
    // => 기본 생성자가 없다면, 다른 생성자 목록을 얻는다.
    Constructor<?>[] constructors = clazz.getConstructors();
    for (Constructor<?> c : constructors) {
      // => 생성자를 호출하려면 먼저 어떤 타입의 파라미터가 필요한지 알아야 한다.
      Class<?>[] paramTypes = c.getParameterTypes();
      
      // => 생성자가 요구하는 타입의 파라미터 값이 저장소에 있는지 찾아 본다.
      Object[] paramValues = getParameterValues(paramTypes);
      if (paramValues != null) { // 생성자가 요구하는 모든 파라미터 값을 찾았다면 
        // 생성자를 통해 인스턴스를 생성하여 리턴한다.
        return c.newInstance(paramValues);
      }
    }
    return null;
  }
  
  private Object[] getParameterValues(Class<?>[] paramTypes) {
    // 파라미터 타입에 해당하는 객체를 빈컨테이너에서 찾아 배열을 만들어 리턴한다.
    ArrayList<Object> values = new ArrayList<>();
    
    for (Class<?> type : paramTypes) {
      Object value = findBean(type);
      if (value != null) {
        values.add(value);
      }
    }
    
    if (values.size() == paramTypes.length) 
      // 파라미터의 타입 목록에 지정된 객체를 모두 찾았으면 배열로 리턴한다.  
      return values.toArray();
    else // 못 찾았으면 null을 리턴한다.
      return null;
  }
  
  private Object findBean(Class<?> type) {
    // 빈 컨테이너에서 특정 타입의 인스턴스를 찾기
    // => 먼저 빈 컨테이너에서 인스턴스 목록을 꺼낸다.
    Collection<Object> beans = beanContainer.values();
    for (Object bean : beans) {
      if (type.isInstance(bean))
        return bean;
    }
    return null;
  }
}










