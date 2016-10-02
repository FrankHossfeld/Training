package de.gishmo.module0812.domain.server;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ServerProperties;

import de.gishmo.module0812.domain.server.filter.LoggingRequestFilter;
import de.gishmo.module0812.domain.server.filter.LoggingResponseFilter;

public class DomainServiceApplication extends Application {

  @Override
  public Set<Class<?>> getClasses() {
    final Set<Class<?>> classes = new HashSet<Class<?>>();
    classes.add(LoggingFilter.class);
    classes.add(LoggingRequestFilter.class);
    classes.add(LoggingResponseFilter.class);

    classes.add(DomianServiceApplicationEventListener.class);
    return classes;
  }

  @Override
  public Map<String, Object> getProperties() {
    Map<String, Object> properties = new HashMap<String, Object>();
    properties.put(ServerProperties.MONITORING_STATISTICS_MBEANS_ENABLED, true);
    return properties;
  }
}
