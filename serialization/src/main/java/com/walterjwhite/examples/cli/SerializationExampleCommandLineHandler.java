package com.walterjwhite.examples.cli;

import com.walterjwhite.email.organization.api.configuration.rule.AttributeRule;
import com.walterjwhite.email.organization.api.configuration.rule.EmailMatcherRule;
import com.walterjwhite.email.organization.api.configuration.rule.EmailMessageField;
import com.walterjwhite.inject.cli.property.CommandLineHandlerShutdownTimeout;
import com.walterjwhite.inject.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.property.impl.annotation.Property;
import com.walterjwhite.serialization.api.service.SerializationService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashSet;
import java.util.Set;
import javax.inject.Inject;

public class SerializationExampleCommandLineHandler extends AbstractCommandLineHandler {
  protected final SerializationService serializationService;

  @Inject
  public SerializationExampleCommandLineHandler(
      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds,
      SerializationService serializationService) {
    super(shutdownTimeoutInSeconds);
    this.serializationService = serializationService;
  }

  @Override
  protected void doRun(String... arguments) throws Exception {
    System.out.println("doRun:start");

    testWrite();
    testRead();

    System.out.println("doRun:end");
  }

  protected void testWrite() throws Exception {
    final EmailMatcherRule emailMatcherRule = new EmailMatcherRule();
    emailMatcherRule.setName("test name");
    final AttributeRule attributeRule = new AttributeRule();
    final Set<String> values = new HashSet<>();
    values.add("value 1");
    values.add("value 2");
    values.add("value 3");
    attributeRule.setValues(values);
    attributeRule.setEmailMessageField(EmailMessageField.Cc);
    attributeRule.setInvert(true);

    emailMatcherRule.setRule(attributeRule);
    serializationService.serialize(emailMatcherRule, new FileOutputStream(new File("/tmp/output")));
  }

  protected void testRead() throws Exception {
    EmailMatcherRule deserializedEmailMatcherRule =
            (EmailMatcherRule)
                    serializationService.deserialize(
                            new FileInputStream("/tmp/test"), EmailMatcherRule.class);
    System.out.println("deserialized:\n" + deserializedEmailMatcherRule);
  }
}
