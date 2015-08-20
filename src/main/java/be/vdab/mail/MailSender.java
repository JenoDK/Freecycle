package be.vdab.mail;

import javax.mail.MessagingException;

import be.vdab.entities.User;

public interface MailSender {
	void nieuwPaswoordMail(User user) throws MessagingException;
}
