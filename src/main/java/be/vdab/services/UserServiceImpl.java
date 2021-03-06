package be.vdab.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import be.vdab.dao.UserDAO;
import be.vdab.entities.User;
import be.vdab.exceptions.EmailExistsException;
import be.vdab.exceptions.NameExistsException;
import be.vdab.mail.MailSender;
import be.vdab.mail.RandomStringGenerator;

@ReadOnlyTransactionalService
public class UserServiceImpl implements UserService {
	private final UserDAO userDAO;
	private final MailSender mailSender;

	@Autowired
	UserServiceImpl(UserDAO userDAO, MailSender mailSender) {
		this.userDAO = userDAO;
		this.mailSender = mailSender;
	}

	@Override
	@ModifyingTransactionalServiceMethod
	public void create(User user) throws RuntimeException {
		if (nameExist(user.getNaam())) {
			throw new NameExistsException(
					"There is an account with that username: " + user.getNaam());
		}
		if (emailExist(user.getEmail())) {
			throw new EmailExistsException(
					"There is an account with that email adress: "
							+ user.getEmail());
		}
		if (!user.getPaswoord().equals(user.getMatchingPaswoord())) {
			throw new IllegalArgumentException();
		}
		String geencrypteerd = new BCryptPasswordEncoder().encode(user
				.getPaswoord());
		user.setPaswoord(geencrypteerd);
		user.setMatchingPaswoord(geencrypteerd);
		user.setId(userDAO.save(user).getId());
	}

	private boolean nameExist(String naam) {
		User user = findByNaamLike(naam);
		if (user != null) {
			return true;
		}
		return false;
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
	public User findByNaamLike(String naam) {
		return userDAO.findByNaamLike(naam);
	}

	@Override
	public User read(long id) {
		return userDAO.findOne(id);
	}

	@Override
	@ModifyingTransactionalServiceMethod
	public void update(User user) throws RuntimeException {
		if (nameExistUpdate(user)) {
			throw new NameExistsException();
		}
		if (emailExistUpdate(user)) {
			throw new EmailExistsException();
		}
		if (!user.getPaswoord().equals(user.getMatchingPaswoord())) {
			throw new IllegalArgumentException();
		}
		String geencrypteerd = new BCryptPasswordEncoder().encode(user
				.getPaswoord());
		user.setPaswoord(geencrypteerd);
		user.setMatchingPaswoord(geencrypteerd);
		userDAO.save(user);
	}

	private boolean nameExistUpdate(User user) {
		List<User> users = findByIdNotLike(user.getId());
		for (User u : users) {
			if (u.getNaam().equals(user.getNaam())) {
				return true;
			}
		}
		return false;
	}

	private boolean emailExistUpdate(User user) {
		List<User> users = findByIdNotLike(user.getId());
		for (User u : users) {
			if (u.getEmail().equals(user.getEmail())) {
				return true;
			}
		}
		return false;
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

	@Override
	public List<User> findByIdNotLike(long id) {
		return userDAO.findByIdNotLike(id);
	}

	@Override
	@ModifyingTransactionalServiceMethod
	public void safeUpdate(User user) throws Exception {
		String newPassword = RandomStringGenerator.generateRandomString(8,
				RandomStringGenerator.Mode.ALPHANUMERIC);
		user.setPaswoord(newPassword);
		user.setMatchingPaswoord(newPassword);
		mailSender.nieuwPaswoordMail(user);
		update(user);
	}

}
