package org.moon.test.ee.service;

import java.util.List;

import org.moon.test.ee.persistence.User;

public interface UserService {

	public static final String SUCCESS = "ok";
	public static final String ERROR = "error";

	String batchSave(List<User> users);

	String throwRuntimeExceptionAfterBatchSave(List<User> users);

	String batchSaveWithTransactionAttributeMandatory(List<User> users);

	String batchSaveWithTransactionAttributeNever(List<User> users);

	String batchSaveWithTransactionAttributeNotSupport(List<User> users);

	String batchSaveWithTransactionAttributeRequired(List<User> users);

	String batchSaveWithTransactionAttributeSupports(List<User> users);

	String batchSaveWithTransactionAttributeRequestNew(List<User> users);
}
