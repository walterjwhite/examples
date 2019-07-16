package com.walterjwhite.examples.practice;

import com.walterjwhite.examples.practice.problems.fibonacci.Fibonacci;
import com.walterjwhite.examples.practice.problems.strings.StringReverser;
import com.walterjwhite.examples.practice.problems.trees.Trees;
import com.walterjwhite.inject.cli.property.CommandLineHandlerShutdownTimeout;
import com.walterjwhite.inject.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.property.impl.annotation.Property;
import javax.inject.Inject;

public class CLIExampleCommandLineHandler extends AbstractCommandLineHandler {

  @Inject
  public CLIExampleCommandLineHandler(
      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds) {
    super(shutdownTimeoutInSeconds);
  }

  @Override
  protected void doRun(String... arguments) {
    System.out.println("reversed:" + StringReverser.reverse("input"));
    System.out.println("reversed:" + StringReverser.reverse("abcdefg"));
    System.out.println("reversed:" + StringReverser.reverse("a"));
    System.out.println("reversed:" + StringReverser.reverse("ab"));
    System.out.println("reversed:" + StringReverser.reverse("abc"));

    Fibonacci fibonacci = new Fibonacci();
    fibonacci.print();

    Trees trees = new Trees();
    trees.print();

    final boolean[][] map = new boolean[][] {};
    // PathRecursion pathRecursion = new PathRecursion();

    // Assert.assertTrue
    // final PathRecursion2 pathRecursion2 = new PathRecursion2();
    // System.out.println("traversable:" + pathRecursion2.traversable(new Node(0, 0), new
    // Node(1,0)));
  }
}
