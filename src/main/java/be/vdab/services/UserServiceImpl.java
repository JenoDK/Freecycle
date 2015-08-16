package be.vdab.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import be.vdab.dao.UserDAO;
import be.vdab.entities.User;
import be.vdab.exceptions.EmailExistsException;

@ReadOnlyTransactionalService
public class UserServiceImpl implements UserService {
	private final UserDAO userDAO;

	@Autowired
	UserServiceImpl(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	@ModifyingTransactionalServiceMethod
	public void create(User user) throws EmailExistsException {
		if (emailExist(user.getEmail())) {
			throw new EmailExistsException(
					"There is an account with that email adress: "
							+ user.getEmail());
		}
		user.setId(userDAO.save(user).getId());
	}

	private boolean emailExist(String email) {
		User user = findByEmailLike(email);
		if (user != null) {
			return true;
		}
		return false;
	}

	@Override
	public User findByEmailLike(String email) {
		return userDAO.findByEmailLike(email);
	}

	@Override
	public User read(long id) {
		return userDAO.findOne(id);
	}

	@Override
	@ModifyingTransactionalServiceMethod
	public void update(User user) {
		userDAO.save(user);
	}

	@Override
	@ModifyingTransactionalServiceMethod
	public void delete(long id) {
		User user = userDAO.findOne(id);
		if (user != null) {
			userDAO.delete(id);
		}
	}

	@Override
	public List<User> findAll() {
		return userDAO.findAll(new Sort("id"));
	}

	@Override
	public long findAantalUsers() {
		return userDAO.count();
	}
}
