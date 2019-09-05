package com.walterjwhite.examples.email;

import com.walterjwhite.email.api.model.PrivateEmailAccount;
import com.walterjwhite.email.organization.RuleConfigurer;
import com.walterjwhite.examples.email.model.EmailAccountRules;
import com.walterjwhite.examples.email.model.EmailOrganizationSession;
import com.walterjwhite.examples.email.property.CleanupType;
import com.walterjwhite.examples.email.property.EmailAccountExtension;
import com.walterjwhite.property.impl.annotation.Property;
import com.walterjwhite.serialization.api.service.SerializationService;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
public class EmailAccountConfigurer {
  private static final Logger LOGGER = LoggerFactory.getLogger(EmailAccountConfigurer.class);
  protected final SerializationService serializationService;
  protected final String accountExtension;
  protected final RuleConfigurer ruleConfigurer;
  protected final CleanupType cleanupType;

  @Inject
  public EmailAccountConfigurer(
      final SerializationService serializationService,
      @Property(EmailAccountExtension.class) String accountExtension,
      @Property(CleanupType.class) CleanupType cleanupType,
      RuleConfigurer ruleConfigurer) {
    this.serializationService = serializationService;
    this.accountExtension = accountExtension;
    this.ruleConfigurer = ruleConfigurer;
    this.cleanupType = cleanupType;
  }

  public EmailOrganizationSession load(final String rulePath) {
    final List<EmailAccountRules> emailAccountRules = new ArrayList<>();

    final File root = new File(rulePath);
    loadAll(emailAccountRules, root, root);

    final EmailOrganizationSession emailOrganizationSession = new EmailOrganizationSession();
    emailOrganizationSession.getEmailAccountRules().addAll(emailAccountRules);
    return emailOrganizationSession;
  }

  protected void loadAll(
      final List<EmailAccountRules> emailAccounts,
      final File rootDirectory,
      final File absoluteRootDirectory) {
    for (final File file : rootDirectory.listFiles()) {
      if (file.isDirectory()) {
        if (!"rules".equals(file.getName())) {
          loadAll(emailAccounts, file, absoluteRootDirectory);
        }
      } else {
        if (("account" + accountExtension).equals(file.getName())) {
          addEmailAccount(emailAccounts, read(accountExtension, file, absoluteRootDirectory));
        }
      }
    }
  }

  protected void addEmailAccount(
      List<EmailAccountRules> emailAccountRulesList, final EmailAccountRules emailAccountRules) {
    if (emailAccountRules == null) return;

    emailAccountRulesList.add(emailAccountRules);
  }

  protected EmailAccountRules read(
      final String fileExtension, final File file, final File absoluteRootDirectory) {
    try {
      final PrivateEmailAccount emailAccount =
          serializationService.deserialize(new FileInputStream(file), PrivateEmailAccount.class);

      final EmailAccountRules emailAccountRules = new EmailAccountRules();
      emailAccountRules.setEmailAccount(emailAccount);
      emailAccountRules.setCleanupType(cleanupType);
      emailAccountRules.setEmailMatcherRules(
          ruleConfigurer.load(file.getParent() + File.separator + "rules"));

      return emailAccountRules;
    } catch (Exception e) {
      LOGGER.error("Error reading rule: ", absoluteRootDirectory, file, e);
      return null;
    }
  }
}
