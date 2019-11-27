package com.walterjwhite.examples.cli;

import com.walterjwhite.inject.cli.property.CommandLineHandlerShutdownTimeout;
import com.walterjwhite.inject.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.inject.cli.service.CommandLineHandler;
import com.walterjwhite.logging.annotation.LogStackTrace;
import com.walterjwhite.property.impl.annotation.Property;
import javax.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@CommandLineHandler
public class CLIExampleCommandLineHandler extends AbstractCommandLineHandler {
  protected final WebDriver webDriver;

  @Inject
  public CLIExampleCommandLineHandler(
      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds,
      WebDriver webDriver) {
    super(shutdownTimeoutInSeconds);
    this.webDriver = webDriver;
  }

  @Override
  protected void doRun(String... arguments) {
    webDriver.get("duckduckgo.com");

    final WebElement search =
        webDriver.findElement(By.xpath("//*[@id=\"search_form_input_homepage\"]"));
    search.sendKeys("weather");
    search.submit();

    System.out.println("Source:\n" + webDriver.getPageSource());
  }

  @LogStackTrace
  protected void onException(Exception e) {}
}
