package com.walterjwhite.examples.email.property;

import com.walterjwhite.email.api.model.Email;
import com.walterjwhite.email.api.model.PrivateEmailAccount;
import com.walterjwhite.email.modules.javamail.service.JavaMailFolderEmailSupplier;
import com.walterjwhite.email.modules.javamail.service.JavaMailNewEmailSupplier;
import com.walterjwhite.email.organization.EmailOrganizer;
import com.walterjwhite.email.organization.api.configuration.rule.EmailMatcherRule;
import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.property.ConfigurableProperty;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.function.Supplier;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CleanupType implements ConfigurableProperty {
  @DefaultValue
  SinglePass(new Class[] {JavaMailFolderEmailSupplier.class}),
  NewMessagesOnly(new Class[] {JavaMailNewEmailSupplier.class}),
  Both(new Class[] {JavaMailNewEmailSupplier.class, JavaMailFolderEmailSupplier.class});

  private final Class<? extends Supplier<Email>>[] emailSupplierClasses;

  public EmailOrganizer[] build(
      final PrivateEmailAccount emailAccount,
      final String folderName,
      /*new JavaMailNewEmailSupplier(emailAccount, folderName),*/
      final List<EmailMatcherRule> emailMatcherRules) {
    final EmailOrganizer[] emailOrganizers = new EmailOrganizer[emailSupplierClasses.length];
    for (int i = 0; i < emailSupplierClasses.length; i++) {
      emailOrganizers[i] =
          new EmailOrganizer(
              folderName,
              emailAccount,
              get(emailSupplierClasses[i], emailAccount, folderName),
              emailMatcherRules);
    }

    return emailOrganizers;
  }

  protected Supplier<Email> get(
      final Class<? extends Supplier<Email>> emailSupplierClass,
      final PrivateEmailAccount emailAccount,
      final String folderName) {
    try {
      final Constructor emailConstructor =
          emailSupplierClass.getConstructor(PrivateEmailAccount.class, String.class);
      return (Supplier<Email>) emailConstructor.newInstance(emailAccount, folderName);
    } catch (NoSuchMethodException
        | InstantiationException
        | IllegalAccessException
        | InvocationTargetException e) {
      throw new Error("Application is mis-configured, unable to get email supplier:", e);
    }
  }
}
