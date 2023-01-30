package joagz.net.carparkingapp.jwt.utils;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

public class KeyGeneratorUtils {

  private KeyGeneratorUtils() {}

  static KeyPair generateRsaKey() {
    KeyPair keyPair;
    try {
      KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
      generator.initialize(2048);
      keyPair = generator.genKeyPair();
    } catch (Exception ex) {
      throw new IllegalStateException(ex);
    }
    return keyPair;
  }
}
