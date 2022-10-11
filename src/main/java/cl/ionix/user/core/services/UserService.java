package cl.ionix.user.core.services;

import cl.ionix.user.core.bo.ResponseUserBo;
import cl.ionix.user.exception.DuplicatedEntryException;
import cl.ionix.user.exception.NoFoundEntryException;

import java.util.List;

public interface UserService {
	 ResponseUserBo findUserByEmail(String email);

	ResponseUserBo findUserByName(String name);

	List<ResponseUserBo> findAll();

	void createUser(ResponseUserBo user) throws DuplicatedEntryException, NoFoundEntryException;

	void updateUser(ResponseUserBo user);

	void deleteUserByEmail(String email) throws NoFoundEntryException;




}
