package cl.ionix.user.core.services.impl;

import cl.ionix.user.core.bo.ResponseUserBo;
import cl.ionix.user.core.services.UserService;
import cl.ionix.user.data.entity.UserEntity;
import cl.ionix.user.data.repository.UserRepository;
import cl.ionix.user.exception.*;
import cl.ionix.user.util.EntityUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	UserEntity userEntity;

	@Override
	public ResponseUserBo findUserByEmail(String email) throws NoFoundEntryException {
			UserEntity user = userRepository.findByEmail(email);
		if (user==null){
			throw new NoFoundEntryException("The User Email: '" + email + "' don't exists!");
			}
			ResponseUserBo responseUserBo = new ResponseUserBo();
			responseUserBo.setName(user.getName());
			responseUserBo.setUsername(user.getUsername());
			responseUserBo.setEmail(user.getEmail());
			responseUserBo.setPhone(user.getPhone());
			return responseUserBo;
	}

	@Override
	public ResponseUserBo findUserByName(String name) throws NoFoundEntryException {
			UserEntity user = userRepository.findByName(name);
			if (user==null ){
				throw new NoFoundEntryException("The User Name '" + name + "' don't exist!");
			}
			ResponseUserBo responseUserBo = new ResponseUserBo();
			responseUserBo.setName(user.getName());
			responseUserBo.setUsername(user.getUsername());
			responseUserBo.setEmail(user.getEmail());
			responseUserBo.setPhone(user.getPhone());
			return responseUserBo;
	}
	@Override
	public List<ResponseUserBo> findAll() throws NoDataRegister {
		List<UserEntity> userList = userRepository.findAll();
		List<ResponseUserBo> listUserResponse = new ArrayList<ResponseUserBo>();
		for (UserEntity user : userList) {
			ResponseUserBo responseUserBo = new ResponseUserBo();
			responseUserBo.setName(user.getName());
			responseUserBo.setUsername(user.getUsername());
			responseUserBo.setEmail(user.getEmail());
			responseUserBo.setPhone(user.getPhone());
			listUserResponse.add(responseUserBo);
			}
		if (listUserResponse.stream().count()==0) {
			throw new NoDataRegister("Datos no registrados");
		}
		return listUserResponse;
	}

	@Override
	public void createUser(ResponseUserBo userBo) throws NoFoundFieldException, DuplicatedEntryException {
		UserEntity userEmail = userRepository.findByEmail(userBo.getEmail());
		UserEntity userName = userRepository.findByName(userBo.getName());
		if (userEmail!= null || userName!=null){
			throw new DuplicatedEntryException("usuario ya registrado");
		} else if (userBo.getEmail()==null || userBo.getName()==null || userBo.getUsername()==null){
			throw new NoFoundFieldException("Campo obligatorio name no ingresado");
			}
		userEntity = EntityUtilities.copyObjectFrom(userBo, UserEntity.class );
		userRepository.save(userEntity);
	}

	@Override
	public void  updateUser(ResponseUserBo userBo) throws NoFoundEntryException, DuplicatedNameException {
		UserEntity user = userRepository.findByEmail(userBo.getEmail());
		UserEntity userName = userRepository.findByName(userBo.getName());
		if (user==null){
			throw new NoFoundEntryException ("The user email "+ userBo.getEmail() +"not found");
		}else if (userName!=null){
			throw new DuplicatedNameException("User Name in use!");
		}
		userEntity = EntityUtilities.copyObjectFrom(userBo, UserEntity.class);
		userRepository.setUserInfoByEmail(userEntity.getName(), userEntity.getUsername(), userEntity.getPhone(),userEntity.getEmail());
	}

	@Override
	public void deleteUserByEmail(String email) throws NoFoundEntryException {
	UserEntity user = userRepository.findByEmail(email);
	if (user == null) {
		throw new NoFoundEntryException ("The user email "+ email +"not found");
	}
	userRepository.deleteUserByEmail(email);
	}
}
