package joagz.net.carparkingapp.controllers;

import joagz.net.carparkingapp.jwt.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

  @Autowired
  private TokenService tokenService;

  @PostMapping("/token")
  public String token(Authentication authentication) {
    System.out.println(authentication.toString());
    String token = tokenService.generateToken(authentication);
    return token;
  }
}
