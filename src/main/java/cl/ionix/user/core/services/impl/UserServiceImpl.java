package cl.ionix.user.core.services.impl;

import cl.ionix.user.core.bo.ResponseUserBo;
import cl.ionix.user.core.services.UserService;
import cl.ionix.user.data.entity.UserEntity;
import cl.ionix.user.data.repository.UserRepository;
import cl.ionix.user.exception.DuplicatedEntryException;
import cl.ionix.user.exception.NoFoundEntryException;
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
	public ResponseUserBo findUserByEmail(String email) {
		UserEntity user = userRepository.findByEmail(email);
		ResponseUserBo responseUserBo = new ResponseUserBo();
		responseUserBo.setName(user.getName());
		responseUserBo.setUsername(user.getUsername());
		responseUserBo.setEmail(user.getEmail());
		responseUserBo.setPhone(user.getPhone());
		return responseUserBo;
	}

	@Override
	public ResponseUserBo findUserByName(String name) {
		UserEntity user = userRepository.findByName(name);
		ResponseUserBo responseUserBo = new ResponseUserBo();
		responseUserBo.setName(user.getName());
		responseUserBo.setUsername(user.getUsername());
		responseUserBo.setEmail(user.getEmail());
		responseUserBo.setPhone(user.getPhone());
		return responseUserBo;
	}
	@Override
	public List<ResponseUserBo> findAll() {
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

		return listUserResponse;
	}
	@Override
	public void createUser(ResponseUserBo userBo) throws DuplicatedEntryException {
		if (findUserByEmail(userBo.getEmail()) != null) {
			throw new DuplicatedEntryException("The User Email '" + userBo.getEmail() + "' already exists!");
		}
		userEntity = EntityUtilities.copyObjectFrom(userBo, UserEntity.class );
		userRepository.save(userEntity);
	}
	@Override
	public void  updateUser(ResponseUserBo userBo) {
		userEntity = EntityUtilities.copyObjectFrom(userBo, UserEntity.class );
		userRepository.setUserInfoByEmail(userEntity.getName(), userEntity.getUsername(), userEntity.getPhone(),userEntity.getEmail());
	}

	@Override
	public void deleteUserByEmail(String email) throws NoFoundEntryException {
		if (findUserByEmail(email) == null) {
			throw new NoFoundEntryException ("The User Email "+ email +"not Found");
		} userRepository.deleteUserByEmail(email);
	}
}
