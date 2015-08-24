package be.vdab.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import be.vdab.entities.User;
import be.vdab.valueobjects.ContactBericht;

@Component
public class MailSenderImpl implements MailSender {
	private final JavaMailSender sender;

	@Autowired
	public MailSenderImpl(JavaMailSender sender) {
		this.sender = sender;
	}

	@Override
	public void nieuwPaswoordMail(User user) throws MessagingException {

		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setTo(user.getEmail());
		helper.setSubject("Paswoord reset - Freecycle");
		helper.setText(
				String.format("Uw nieuw paswoord is: <strong>%s</strong>",
						user.getPaswoord()), true);
		sender.send(message);

	}

	@Override
	public void contacteerGebruikerEmail(ContactBericht contactBericht)
			throws MessagingException {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setTo(contactBericht.getArtikel().getUser().getEmail());
		helper.setSubject(contactBericht.getArtikel().getNaam()
				+ " - Freecycle");
		helper.setText(
				"<html><body><h1>Beste <strong>"
						+ contactBericht.getArtikel().getUser().getNaam()
						+ "</strong></h1><p>" + contactBericht.getNaam()
						+ " toonde interesse voor uw producht: <strong>"
						+ contactBericht.getArtikel().getNaam()
						+ "</strong></p><p><strong>Bericht</strong>: "
						+ contactBericht.getBericht()
						+ "</p><p>U kan hem contacteren via email: <strong>"
						+ contactBericht.getEmail()
						+ "</strong></p></body></html>", true);
		sender.send(message);
	}

}
