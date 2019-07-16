package com.walterjwhite.examples.jbrowserdriver;

import com.machinepublishers.jbrowserdriver.*;
import java.io.File;
import java.net.MalformedURLException;

public class Test {

  public static void main(final String[] arguments) throws MalformedURLException {
    try {
      // basic();
      builder();
    } catch (Exception e) {
      handleError(e);
    }
  }

  private static void handleError(Exception e) {}

  private static void builder() {
    Settings.Builder builder = Settings.builder();

    // TODO: do NOT hard-code this, we can inject these properties
    builder.javaOptions(
        "-classpath",
        "/home/w/.m2/repository/com/machinepublishers/jbrowserdriver/1.0.0-RC1/jbrowserdriver-1.0.0-RC1-uberjar.jar");
    builder.timezone(Timezone.AMERICA_NEWYORK);
    builder.ssl("trustanything");

    builder.ajaxResourceTimeout(5);
    builder.ajaxWait(5);
    //    builder.blockAds(true);
    builder.userAgent(UserAgent.CHROME);

    builder.connectTimeout(5);

    builder.saveAttachments(true);

    builder.cacheDir(new File("/tmp/jbrowserdriver"));

    // TODO: do NOT hard-code this, we can inject these properties
    builder.proxy(new ProxyConfig(ProxyConfig.Type.HTTP, "localhost", 8118));

    JBrowserDriver jBrowserDriver = new JBrowserDriver(builder.build());

    jBrowserDriver.get("google.com");
    jBrowserDriver.close();
  }

  private static void basic() {

    // You can optionally pass a Settings object here,
    // constructed using Settings.Builder
    JBrowserDriver driver =
        new JBrowserDriver(
            Settings.builder()
                .timezone(Timezone.AMERICA_NEWYORK)
                // TODO: do NOT hard-code this, we can inject these properties
                .proxy(new ProxyConfig(ProxyConfig.Type.HTTP, "localhost", 8118))
                .build());

    // This will block for the page load and any
    // associated AJAX requests
    driver.get("http://lxer.com");

    // You can get status code unlike other Selenium drivers.
    // It blocks for AJAX requests and page loads after clicks
    // and keyboard events.

    // Returns the page source in its current state, including
    // any DOM updates that occurred after page load

    // Close the browser. Allows this thread to terminate.
    driver.quit();
  }
}
