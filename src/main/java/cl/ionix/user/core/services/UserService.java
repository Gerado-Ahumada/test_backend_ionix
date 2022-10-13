package cl.ionix.user.core.services;

import cl.ionix.user.core.bo.ResponseUserBo;
import cl.ionix.user.exception.*;
import javassist.NotFoundException;

import java.util.List;

public interface UserService {
	 ResponseUserBo findUserByEmail(String email) throws NotFoundException, NoFoundEntryException;

	ResponseUserBo findUserByName(String name) throws NoFoundEntryException;

	List<ResponseUserBo> findAll() throws NoDataRegister;

	void createUser(ResponseUserBo user) throws DuplicatedEntryException, NoFoundEntryException, NoFoundFieldException;

	void updateUser(ResponseUserBo user) throws NoFoundEntryException, DuplicatedEntryException, DuplicatedNameException;

	void deleteUserByEmail(String email) throws NoFoundEntryException;




}
