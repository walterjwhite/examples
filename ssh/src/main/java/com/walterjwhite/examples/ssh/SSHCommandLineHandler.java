package com.walterjwhite.examples.ssh;

import com.walterjwhite.inject.cli.property.CommandLineHandlerShutdownTimeout;
import com.walterjwhite.inject.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.property.impl.annotation.Property;
import com.walterjwhite.shell.api.model.ShellCommand;
import com.walterjwhite.ssh.api.SSHCommandService;
import com.walterjwhite.ssh.api.model.SSHHost;
import com.walterjwhite.ssh.api.model.SSHUser;
import com.walterjwhite.ssh.api.model.command.SSHCommand;
import javax.inject.Inject;

public class SSHCommandLineHandler extends AbstractCommandLineHandler {
  protected final String exampleSSHUsername;
  protected final SSHCommandService sshCommandService;

  @Inject
  public SSHCommandLineHandler(
      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds,
      @Property(ExampleSSHUser.class) final String exampleSSHUsername,
      SSHCommandService sshCommandService) {
    super(shutdownTimeoutInSeconds);
    this.exampleSSHUsername = exampleSSHUsername;
    this.sshCommandService = sshCommandService;
  }

  @Override
  protected void doRun(String... arguments) throws Exception {
    sshCommandService.execute(
        new SSHCommand(
            new SSHHost("localhost"),
            new SSHUser(exampleSSHUsername),
            new ShellCommand().withCommandLine("ls")));
  }
}
