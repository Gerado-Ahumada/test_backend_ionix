package cl.ionix.user.controller;

import cl.ionix.user.controller.dto.ResponseUserDto;
import cl.ionix.user.core.bo.ResponseUserBo;
import cl.ionix.user.core.services.UserService;
import cl.ionix.user.exception.DuplicatedEntryException;
import cl.ionix.user.exception.NoFoundEntryException;
import cl.ionix.user.util.EntityUtilities;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/user")
@Validated
public class UserController {

	@Autowired
	private UserService userService;


	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "List User", notes = "List all storage users")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Bad Request"),
					@ApiResponse(code = 500, message = "Internal Server Error") })
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ResponseUserDto> getAllUsers() {
		List<ResponseUserBo> userList = userService.findAll();
		List<ResponseUserDto> listUserResponse = new ArrayList<ResponseUserDto>();
		for (ResponseUserBo user : userList) {
			ResponseUserDto responseUserDto = new ResponseUserDto();
			responseUserDto.setName(user.getName());
			responseUserDto.setUsername(user.getUsername());
			responseUserDto.setEmail(user.getEmail());
			responseUserDto.setPhone(user.getPhone());
			listUserResponse.add(responseUserDto);}
			return listUserResponse;
	}
	@ApiOperation(value = "Get user by email", notes = "Response user by email searching ")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Bad Request"),
					@ApiResponse(code = 404, message = "Not Found"),
					@ApiResponse(code = 406, message = "Not Acceptable"),
					@ApiResponse(code = 422, message = "Unprocessable Entity"),
					@ApiResponse(code = 500, message = "Internal Server Error") })
	@GetMapping(value = "/@/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseUserDto getUserByEmail(@PathVariable ("email") String email) {
		ResponseUserBo responseUserBo = userService.findUserByEmail(email);
		ResponseUserDto UserDto = new ResponseUserDto();
		UserDto.setName(responseUserBo.getName());
		UserDto.setUsername(responseUserBo.getUsername());
		UserDto.setEmail(responseUserBo.getEmail());
		UserDto.setPhone(responseUserBo.getPhone());
		return UserDto;
		}

	@ApiOperation(value = "Get user by name", notes = "Response user by name searching ")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Bad Request"),
					@ApiResponse(code = 404, message = "Not Found"),
					@ApiResponse(code = 406, message = "Not Acceptable"),
					@ApiResponse(code = 422, message = "Unprocessable Entity"),
					@ApiResponse(code = 500, message = "Internal Server Error") })
	@GetMapping(value = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseUserDto getUserByName(@PathVariable ("name") String name) {
		ResponseUserBo responseUserBo = userService.findUserByName(name);
		ResponseUserDto UserDto = new ResponseUserDto();
		UserDto.setName(responseUserBo.getName());
		UserDto.setUsername(responseUserBo.getUsername());
		UserDto.setEmail(responseUserBo.getEmail());
		UserDto.setPhone(responseUserBo.getPhone());
		return UserDto;
		}

	@ResponseStatus (HttpStatus.CREATED)
	@ApiOperation(value = "Create a new user", notes = "Create a new user")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Bad Request"),
					@ApiResponse(code = 406, message = "Not Acceptable"),
					@ApiResponse(code = 422, message = "Unprocessable Entity"),
					@ApiResponse(code = 500, message = "Internal Server Error") })
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseUserDto createUser(@RequestBody ResponseUserDto userDto)
					throws DuplicatedEntryException
	{userService.createUser(EntityUtilities.copyObjectFrom(userDto, ResponseUserBo.class));
						return userDto;
	}
	@ResponseStatus (HttpStatus.OK)
	@ApiOperation(value = "Update user", notes = "Update user information")
	@ApiResponses(value =  { @ApiResponse(code = 400, message = "Bad Request"),
					@ApiResponse(code = 404, message = "Not Found"),
					@ApiResponse(code = 406, message = "Not Acceptable"),
					@ApiResponse(code = 422, message = "Unprocessable Entity"),
					@ApiResponse(code = 500, message = "Internal Server Error") })
	@PutMapping(value = "/update",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseUserDto updateUser(@RequestBody ResponseUserDto userDto){
		userService.updateUser(EntityUtilities.copyObjectFrom(userDto, ResponseUserBo.class));
		return userDto;
	}
	@ResponseStatus (HttpStatus.ACCEPTED)
	@ApiOperation(value = "Delete user", notes = "Delete user information")
	@ApiResponses(value =  { @ApiResponse(code = 400, message = "Bad Request"),
					@ApiResponse(code = 404, message = "Not Found"),
					@ApiResponse(code = 406, message = "Not Acceptable"),
					@ApiResponse(code = 422, message = "Unprocessable Entity")})
	@DeleteMapping(value = "/delete/{email}")
	public  void deleteUserByEmail(@PathVariable String  email) throws NoFoundEntryException
	{	userService.deleteUserByEmail(email);}
}



