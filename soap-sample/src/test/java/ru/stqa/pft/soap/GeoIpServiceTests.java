package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {

  @Test
  public void testMyIp(){
    String geoIP = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("192.168.0.119");
    assertEquals(geoIP,"<GeoIP><Country>BY</Country><State>04</State></GeoIP>");
  }
}
