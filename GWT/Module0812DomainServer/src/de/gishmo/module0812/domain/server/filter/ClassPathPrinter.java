package de.gishmo.module0812.domain.server.filter;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ClassPathPrinter implements Filter {

  @Override
  public void destroy() {
  }

  @Override
  public void doFilter(ServletRequest req,
                       ServletResponse res,
                       FilterChain chain) throws IOException, ServletException {

    ClassLoader applicationClassLoader = this.getClass().getClassLoader();
    if (applicationClassLoader == null) {
      applicationClassLoader = ClassLoader.getSystemClassLoader();
    }
    URL[] urls = ((URLClassLoader) applicationClassLoader).getURLs();
    for (int i = 0; i < urls.length; i++) {
      System.out.println(urls[i].getFile());
    }

    chain.doFilter(req, res);
  }

  @Override
  public void init(FilterConfig arg0) throws ServletException {
  }

}
