package com.walterjwhite.examples.cdi;

import com.walterjwhite.property.api.property.ConfigurableProperty;
import com.walterjwhite.property.impl.PropertyImpl;
import java.util.Map;
import java.util.Properties;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.Extension;

public class PropertyExtension implements Extension {
  protected final Properties properties = new Properties();

  void afterBeanDiscovery(
      @Observes AfterBeanDiscovery afterBeanDiscovery, BeanManager beanManager) {

    afterBeanDiscovery
        .addBean()
        /* read annotations of the class and create an InjectionTarget used to instantiate the class and inject dependencies */
        .read(beanManager.createAnnotatedType(Properties.class))
        .beanClass(Properties.class)
        .scope(ApplicationScoped.class)
        .name(Properties.class.getName());

    addProperties(afterBeanDiscovery, beanManager);
  }

  protected void addProperties(AfterBeanDiscovery afterBeanDiscovery, BeanManager beanManager) {
    for (Map.Entry<Object, Object> property : properties.entrySet()) {
      addProperty(afterBeanDiscovery, beanManager, property);
    }
  }

  protected void addProperty(
      AfterBeanDiscovery afterBeanDiscovery,
      BeanManager beanManager,
      Map.Entry<Object, Object> property) {
    final Class<? extends ConfigurableProperty> configurablePropertyClass = null;
    afterBeanDiscovery
        .addBean()
        .addQualifier(new PropertyImpl(configurablePropertyClass))
        .beanClass(property.getValue().getClass())
        .scope(ApplicationScoped.class)
        .name(getBeanName(property.getKey()));
  }

  /**
   * NOTE that in test, this may be overridden
   *
   * @param propertyKey
   * @return
   */
  protected String getBeanName(final Object propertyKey) {
    return propertyKey.getClass().getName();
  }
}
