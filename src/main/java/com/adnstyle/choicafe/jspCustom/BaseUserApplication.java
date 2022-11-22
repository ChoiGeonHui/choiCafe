package com.adnstyle.choicafe.jspCustom;

import com.adnstyle.choicafe.jspCustom.CustomJspConfigDescriptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Context;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.webresources.StandardRoot;
import org.apache.tomcat.util.scan.StandardJarScanner;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

@Slf4j
public class BaseUserApplication extends SpringBootServletInitializer {

  /**
   * JSP 관련 설정
   */
  @Bean
  public ConfigurableServletWebServerFactory configurableServletWebServerFactory() {
    ApplicationHome home = new ApplicationHome(this.getClass());
    String targetPath = home.getSource().toString();
    boolean isExecutableJar = targetPath.contains(".jar");
    TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory() {
      @Override
      protected void postProcessContext(Context context) {
        if(isExecutableJar) {
          try {
            context.setResources(new StandardRoot(context));
            context.getResources().createWebResourceSet(
              WebResourceRoot.ResourceSetType.RESOURCE_JAR, "/",
              new URL("jar:file:" + targetPath + "!/"), "/BOOT-INF/classes/webapp");
          } catch (MalformedURLException e) {
            log.error("MalformedURLException", e);
          }
        }
        super.postProcessContext(context);
        context.setJspConfigDescriptor(new CustomJspConfigDescriptor());
        ((StandardJarScanner) context.getJarScanner()).setScanManifest(false);
      }
    };
    if(!isExecutableJar) {
      factory.setDocumentRoot(new File(targetPath + "/main/webapp"));
    }
    factory.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, "/error/403"));
    factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error/404"));
    factory.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500"));
    return factory;
  }

}
