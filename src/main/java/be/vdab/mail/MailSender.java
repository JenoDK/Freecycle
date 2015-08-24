package be.vdab.mail;

import javax.mail.MessagingException;

import be.vdab.entities.User;
import be.vdab.valueobjects.ContactBericht;

public interface MailSender {
	void nieuwPaswoordMail(User user) throws MessagingException;
	
	void contacteerGebruikerEmail(ContactBericht contactBericht) throws MessagingException;
}
