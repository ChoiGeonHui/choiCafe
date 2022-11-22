package com.adnstyle.choicafe.jspCustom;

import org.apache.tomcat.util.descriptor.web.JspPropertyGroup;
import org.apache.tomcat.util.descriptor.web.JspPropertyGroupDescriptorImpl;

import javax.servlet.descriptor.JspConfigDescriptor;
import javax.servlet.descriptor.JspPropertyGroupDescriptor;
import javax.servlet.descriptor.TaglibDescriptor;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class CustomJspConfigDescriptor implements JspConfigDescriptor {

  private Collection<JspPropertyGroupDescriptor> jspPropertyGroups = new LinkedHashSet<>();
  private final Collection<TaglibDescriptor> tagLibs = new HashSet<>();

  /**
   * 모든 JSP 상단에 삽입되는 GlobalHeader 설정
   *
   * @return
   */
  @Override
  public Collection<JspPropertyGroupDescriptor> getJspPropertyGroups() {
    JspPropertyGroup newJspPropertyGroup = new JspPropertyGroup();
    newJspPropertyGroup.addUrlPattern("*.jsp");
    newJspPropertyGroup.setPageEncoding("UTF-8");
    newJspPropertyGroup.addIncludePrelude("/WEB-INF/jsp/layout/global-jsp-header.jsp");
    newJspPropertyGroup.setTrimWhitespace("true");
    JspPropertyGroupDescriptorImpl jspDescriptor = new JspPropertyGroupDescriptorImpl(newJspPropertyGroup);
    jspPropertyGroups.add(jspDescriptor);
    return jspPropertyGroups;
  }

  @Override
  public Collection<TaglibDescriptor> getTaglibs() {
    return tagLibs;
  }

}
