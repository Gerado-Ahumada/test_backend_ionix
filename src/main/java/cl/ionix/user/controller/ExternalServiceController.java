/*package cl.ionix.user.controller;

import cl.ionix.user.controller.dto.ResponseExternalApiDto;
import cl.ionix.user.core.services.ExternalService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user/external")
@Validated
public class ExternalServiceController {
  @Autowired
  ExternalService externalService;

  @ApiOperation(value = "Consumo servicio externo", response = ExternalService.class)
  @ApiResponses(value = { @ApiResponse(code = 400, message = "Bad Request"),
          @ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 406, message = "Not Acceptable"),
          @ApiResponse(code = 422, message = "Unprocessable Entity"),
          @ApiResponse(code = 500, message = "Internal Server Error") })
  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/api/{param}")
    public ResponseExternalApiDto consumerExternalApi(@PathVariable ("param") String param){
    ResponseExternalApiDto responseExternalApiDto = externalService.search(param);

    return responseExternalApiDto;
    }

}*/
