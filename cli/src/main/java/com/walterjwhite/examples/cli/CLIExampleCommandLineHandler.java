package com.walterjwhite.examples.cli;

import com.google.inject.Binding;
import com.google.inject.Injector;
import com.walterjwhite.inject.cli.CLIApplicationHelper;
import com.walterjwhite.inject.cli.property.CommandLineHandlerShutdownTimeout;
import com.walterjwhite.inject.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.property.api.enumeration.SystemProxy;
import com.walterjwhite.property.api.property.ProxyHost;
import com.walterjwhite.property.api.property.ProxyPort;
import com.walterjwhite.property.impl.annotation.Property;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import javax.inject.Inject;

public class CLIExampleCommandLineHandler extends AbstractCommandLineHandler {
  protected final String proxyHost;
  protected final int proxyPort;

  @Inject
  public CLIExampleCommandLineHandler(
      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds,
      @Property(ProxyHost.class) String proxyHost,
      @Property(ProxyPort.class) int proxyPort) {
    super(shutdownTimeoutInSeconds);
    this.proxyHost = proxyHost;
    this.proxyPort = proxyPort;
  }

  @Override
  protected void doRun(String... arguments) {
    System.out.println("doRun:start");
    System.out.println("http_proxy from System.getenv():" + System.getenv().get("http_proxy"));
    System.out.println(
        "HttpProxy:"
            + System.getProperty(
                SystemProxy.HttpProxy.getClass().getName() + "." + SystemProxy.HttpProxy.name()));
    System.out.println("HttpProxy: host:" + proxyHost);
    System.out.println("HttpProxy: port:" + proxyPort);

    // System.out.println("ChronoUnit from String:" + ChronoUnit.valueOf("Seconds"));
    System.out.println("ChronoUnit from String:" + ChronoUnit.valueOf("SECONDS"));

    Arrays.stream(arguments)
        .forEach(argument -> System.out.println("cli arguments: argument:" + argument));
    System.out.println("doRun:end");
  }
}
