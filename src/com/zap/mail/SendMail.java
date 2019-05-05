package com.zap.mail;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.zap.input.ReadConfig;
import com.zap.output.TestOutput;

public class SendMail {
	public void sendMail(TestOutput tOutput, String toMail,
			ReadConfig readConfig1) throws IOException {
		System.out.println("inside sendMail function");
		StringBuilder htmlContent = new StringBuilder();
		final ReadConfig readConfig = readConfig1;
		Properties props = new Properties();
		props.put("mail.smtp.host", readConfig.mailHost);
		props.put("mail.smtp.socketFactory.port", readConfig.mailPort);
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", readConfig.auth);
		props.put("mail.smtp.port", readConfig.mailPort);

		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(readConfig.emailFrom,
								readConfig.emailFromPassword);
					}
				});

		try {

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(readConfig.emailFrom));

			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(toMail));
			Date date = new Date();
			Timestamp timestamp = new Timestamp(date.getTime());
			Timestamp myTimeStamp = timestamp;
			message.setSubject("**" + readConfig.projectName
					+ ": Security Testing Report On"
					+ myTimeStamp.toString().substring(0, 19));

			htmlContent
					.append("<head>"
							+ "<title></title>"
							+ "<meta name=viewport content=width=device-width, initial-scale=1.0/>"
							+ "</head>"
							+ "</html>"
							+ "<body style=margin: 0; padding: 0;>"
							+ "<table align=center border=1 cellpadding=0 cellspacing=0 width=600>"
							+ "<tr>"
							+ "<td align=center bgcolor=#FFFF00 style=padding: 40px 0 30px 0;>"
							+ "<h2>"
							+ readConfig.projectName
							+ ": Security Testing Report On "
							+ myTimeStamp.toString().substring(0, 19)
							+ "</h2>"
							+ "</td>"
							+ "</tr>"
							+ "<tr>"
							+ "<td bgcolor=#ffffff>"
							+ "<table border=1 cellpadding=0 cellspacing=0 width=100%>"
							+ "<tr>"
							+ "<td style=padding: 20px 0 30px 0;>"
							+ "<h4>Team,"
							+ "<br><br><br>"
							+ "&nbsp;&nbsp;&nbsp;&nbsp;"
							+ "This is a auto generated mail after <b>"
							+ readConfig.projectName
							+ "</b> Security Testing completion. To generate this report Zap, Java, Ant is used. This mail is sent to a testing team of a "
							+ readConfig.projectName
							+ ". For more information about testing result visit link of <a href="
							+ readConfig.serverUrl
							+ ">Security Report Server</a>. You can also compare previous Security Testing reports at Security Report Server."
							+ "<br><br>"
							+ "Thanks,"
							+ "<br>"
							+ readConfig.projectName
							+ "-QA"
							+ "</td>"
							+ "</tr>"
							+ "<tr>"
							+ "<td>"
							+ "<table border=1 cellpadding=0 cellspacing=0 width=100%>"
							+ "<tr><th>Sr. No</th><th>Alert</th><th>Reliability</th><th>Risk</th><th>Url</th></tr>");

			for (int i = 0; i < tOutput.alertFilter.size(); i++) {

				htmlContent.append("<tr>" + "<td width=500 valign=top>"
						+ (i + 1) + "</td>" + "<td width=500 valign=top>"
						+ tOutput.alertFilter.get(i).toString() + "</td>"
						+ "<td width=500 valign=top>"
						+ tOutput.reliabilityFilter.get(i).toString() + "</td>"
						+ "<td width=500 valign=top>"
						+ tOutput.riskFilter.get(i).toString() + "</td>"
								+ "<td width=500 valign=top>"
						+ tOutput.urlFilter.get(i).toString() + "</td></tr>");

			}

			htmlContent.append("</table>" + "</td>" + "</tr>" + "</table>"

			+ "</td>" + "</tr>" + "<tr>"
					+ "<td bgcolor=#FFFF00 align=center><a href="+tOutput.htmlreportLink+">"
							+ "<button type=button style=background-color:lightgreen>"
+"<br />Report!</button></a></td></tr></table>");
			message.setContent(htmlContent.toString(), "text/html");

			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
