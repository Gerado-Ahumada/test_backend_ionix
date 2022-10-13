/**package cl.ionix.user.core.services.impl;

import cl.ionix.user.controller.dto.ResponseExternalApiDto;
import cl.ionix.user.core.services.ExternalService;
import cl.ionix.user.util.EntityUtilities;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;


@Service
public class ExternalServiceImpl implements ExternalService {
  public static final String APIKEY = "f2f719e0";
  RestTemplate template;
  @Value("${url-external-service}")

  @Override
  public ResponseExternalApiDto search(String param) {
    SecretKeyFactory keyFactory = null;
    String paramEncrip = null;
    try {
      keyFactory = SecretKeyFactory.getInstance("DES");
      DESKeySpec keySpec = new DESKeySpec("ionix123456".getBytes(StandardCharsets.UTF_8));
      SecretKey ks = keyFactory.generateSecret(keySpec);

      byte[]  cleartext = param.getBytes(StandardCharsets.UTF_8);
      Cipher cipher = Cipher.getInstance("DES");
      cipher.init(Cipher.ENCRYPT_MODE, ks);
      paramEncrip = Base64.getEncoder().encodeToString(cipher.doFinal(cleartext));
      }
      catch
        (NoSuchAlgorithmException | InvalidKeyException | InvalidKeySpecException | NoSuchPaddingException |
             IllegalBlockSizeException | BadPaddingException e) {
      e.printStackTrace();
    }

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.set("X-API-Key", APIKEY);
    HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);
    try {
      ResponseEntity<String> entity = template.getForEntity(APIKEY+"/"+ paramEncrip, HttpMethod.GET, requestEntity, Object.class);
      String body = entity.getBody();
      MediaType contentType = entity.getHeaders().getContentType();
      HttpStatus statusCode = entity.getStatusCode();

      );
      ResponseExternalApiDto responseExternalApiDto = EntityUtilities.copyObjectFrom(response.getBody(),ResponseExternalApiDto.class);
      return responseExternalApiDto;
    } catch (Exception e){
      return null;
    }
  }
}**/
