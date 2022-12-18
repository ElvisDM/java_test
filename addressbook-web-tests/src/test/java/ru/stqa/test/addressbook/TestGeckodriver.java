package ru.stqa.test.addressbook;

import org.openqa.selenium.os.CommandLine;
import org.testng.annotations.Test;

public class TestGeckodriver {

  @Test
  public void testGeckodriver() {
    CommandLine cmd = new CommandLine("geckodriver", "--version");
    cmd.copyOutputTo(System.out);
    cmd.execute();
  }
}
