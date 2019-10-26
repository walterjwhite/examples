package com.walterjwhite.examples.cli;

import com.walterjwhite.inject.cli.property.CommandLineHandlerShutdownTimeout;
import com.walterjwhite.inject.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.inject.cli.service.CommandLineHandler;
import com.walterjwhite.logging.annotation.LogStackTrace;
import com.walterjwhite.property.api.enumeration.SystemProxy;
import com.walterjwhite.property.api.property.ProxyHost;
import com.walterjwhite.property.api.property.ProxyPort;
import com.walterjwhite.property.impl.annotation.Property;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Optional;
import javax.inject.Inject;

@CommandLineHandler
public class CLIExampleCommandLineHandler extends AbstractCommandLineHandler {
  protected final Optional<String> proxyHost;
  protected final Optional<Integer> proxyPort;

  @Inject
  public CLIExampleCommandLineHandler(
      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds,
      @Property(ProxyHost.class) Optional<String> proxyHost,
      @Property(ProxyPort.class) Optional<Integer> proxyPort) {
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

    if (proxyHost.isPresent()) System.out.println("HttpProxy: host:" + proxyHost.get());
    if (proxyPort.isPresent()) System.out.println("HttpProxy: port:" + proxyPort.get());

    // System.out.println("ChronoUnit from String:" + ChronoUnit.valueOf("Seconds"));
    System.out.println("ChronoUnit from String:" + ChronoUnit.valueOf("SECONDS"));

    executeTest();

    Arrays.stream(arguments)
        .forEach(argument -> System.out.println("cli arguments: argument:" + argument));
    System.out.println("doRun:end");
  }

  protected void executeTest() {
    // no, use process builder here
    try {
      final Process process =
          Runtime.getRuntime()
              .exec(
                  "GOPATH=/usr/local go get -u github.com/walterjwhite/go-application/commands/secrets/find...");
      process.waitFor();
    } catch (Exception e) {
      onException(e);
    }
  }

  @LogStackTrace
  protected void onException(Exception e) {}
}
