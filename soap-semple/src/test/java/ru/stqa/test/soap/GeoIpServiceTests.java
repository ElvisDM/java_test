package ru.stqa.test.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

public class GeoIpServiceTests {


  @Test
  public void MyIp() {
    String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("31.130.54.17");
    //Assert.assertEquals();
    System.out.println(ipLocation);
  }
}
