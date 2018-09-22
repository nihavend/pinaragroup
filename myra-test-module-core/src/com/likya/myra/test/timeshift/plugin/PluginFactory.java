package com.likya.myra.test.timeshift.plugin;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;

/** 
 Return concrete implementations of interfaces.
 
 Here, each concrete implementation class needs to have a 
 no-argument constructor, in order to be built by this 
 factory.
*/
public final class PluginFactory {
  
  /** Simple demo. */
  public static void main(String... args) throws Exception {
    Map<String, String> config = new LinkedHashMap<>();
    //hard-coded, instead of using a text file as config: 
    config.put(TimeSource.class.getName(), TimeSourceOneDayAhead.class.getName());
    PluginFactory.init(config);
    
    TimeSource ts = PluginFactory.instanceOf(TimeSource.class);
    System.out.println(ts);
  }
  
  /**
   Read in configuration data that maps names of interfaces to names of 
   corresponding concrete implementation classes. Called early upon startup, 
   before any implementations are needed by the rest of the program.
   
   <P>Example of a possible entry in such a config file (where package names
   have been added):
   myapp.TimeSource = myapp.TimeSourceOneDayAdvance
   
   @param config map-key is the fully-qualified interface name, map-value is 
   the fully-qualified name of a corresponding concrete implementation class, 
   having a no-argument constructor.
   The caller decides where this data comes from. It may be a simple text file, a 
   database, etc. 
  */
  public static void init(Map<String, String> config){
    /*
     for testing (outside of production), you might want to let the caller 
     call this method more than once, to swap around different implementations. 
    */
    //interfaceToImpl.clear();
    
    interfaceToImpl.putAll(config);
    
    //variation: you might want to fail as early as possible, by trying to 
    //build an object here for each interface, as a simple test 
    
    //variation: you might want to allow the mapping to be set in code, instead of 
    //in a text file. In that case, the Map would use Class objects directly, and 
    //not their names.
  }
  
  /**
   Return an object that implements the given interface.
   If the given interface has no known mapping defined by the config, 
   or if the instance cannot be created, then an exception is thrown.
   
   Example of getting an instance that implements the TimeSoure interface: 
   <code>TimeSource ts = PluginFactory.instanceOf(TimeSource.class);</code>
   
   @param aInterface the class object representing the interface. 
  */
  public static <T> T instanceOf(Class<T> aInterface) throws 
    ClassNotFoundException, InstantiationException, IllegalAccessException, 
    IllegalArgumentException, InvocationTargetException, NoSuchMethodException, 
    SecurityException
  {
    T result = null;
    for(String interfaceName : interfaceToImpl.keySet()) {
      if (interfaceName.equals(aInterface.getName())) {
        String implName = interfaceToImpl.get(interfaceName);
        //unavoidable cast!
        Class<? extends T> implClass = (Class<? extends T>)Class.forName(implName);
        result = implClass.getDeclaredConstructor().newInstance();
      }
    }
    if (result == null) {
      throw new InstantiationException(
        "The interface " + aInterface.getName() + " has no mapping to an impl."
      );
    }
    return result;
  }
  
  // PRIVATE
  
  /**
   Maps the name of an interface to the name of a corresponding concrete 
   implementation class. 
  */
  private static final Map<String, String> interfaceToImpl = new LinkedHashMap<>();
} 