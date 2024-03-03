package com.youandjang.todaychef.common.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.youandjang.todaychef.auth.vo.ScreenMasterDto;
import com.youandjang.todaychef.common.dao.CommonDao;
import com.youandjang.todaychef.error.exception.TodayChefException;
import com.youandjang.todaychef.member.vo.MemberDto;
import com.youandjang.todaychef.util.MessageUtils;

import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@PropertySource(value = "classpath:sendEmail.properties", encoding = "UTF-8")
public class CommonService {

	@Autowired
	public CommonDao mapper;
	@Autowired
	MessageUtils messageUtils;

	@Value("${send.host}")
	String host;
	@Value("${send.username}")
	String userName;
	@Value("${send.password}")
	String password;

	public MemberDto findMemberId(String userId) throws Exception {
		return mapper.findMemberId(userId);
	}

	public List<ScreenMasterDto> findAccessibleScreen(String authCode) throws Exception {
		return mapper.findAccessibleScreen(authCode);
	}

	public void sendEmail(List<String> recipients, String title, String contents, List<String> ccList) throws Exception {

		int port = 465; // 決まってるportを使う
		
		List<String> emailList = new ArrayList<String>();
	    List<String> ccEmailList = new ArrayList<String>(); 
	    MemberDto dto = new MemberDto();

		for (int i = 0; i < recipients.size(); i++) {
		    	dto = findMemberId(recipients.get(i));
	            emailList.add(dto.getUserMail());
	      }
	      
	      for (int i = 0; i < ccList.size(); i++) {
	    	   dto = findMemberId(ccList.get(i));
	            ccEmailList.add(dto.getUserMail());
	      }

		
		//受信者追加
		InternetAddress[] toEmails = new InternetAddress[emailList.size()];
		for (int i = 0; i < emailList.size(); i++) {
			toEmails[i] = new InternetAddress(emailList.get(i));
		}

		//参照者追加
		InternetAddress[] toCcList = new InternetAddress[ccEmailList.size()];
		for (int i = 0; i < ccEmailList.size(); i++) {
			toCcList[i] = new InternetAddress(ccEmailList.get(i));
		}

		// メールアドレスを該当メールアドレスがに送信すること
		try {
			Properties props = System.getProperties(); // 情報を入れる引数生成

			// SMTP サーバー情報設定変更
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", port);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.ssl.trust", host);
			props.put("mail.smtp.socketFactory.port", port);
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

			// Session 生成
			Session session = Session.getDefaultInstance(props, new jakarta.mail.Authenticator() {
				String un = userName;
				String pw = password;

				@Override
				protected jakarta.mail.PasswordAuthentication getPasswordAuthentication() {
					return new jakarta.mail.PasswordAuthentication(un, pw);
				}
			});

			session.setDebug(true); // debug
			Message mimeMessage = new MimeMessage(session); // MimeMessage生成
			mimeMessage.setFrom(new InternetAddress(userName)); // 送り元設定
			mimeMessage.setRecipients(Message.RecipientType.TO, toEmails); // 送り先設定
			mimeMessage.setSubject(title); // タイトル設定
			mimeMessage.setText(contents); // 内容設定
			mimeMessage.setRecipients(Message.RecipientType.CC, toCcList); // 参照者設定

			Transport.send(mimeMessage, userName, password); // jakarta.mail.Transport.send()利用

		} catch (Exception e) {
			String messages = messageUtils.getMessage("HBMSG_95205");
			throw new TodayChefException(messages, e);
		}
	}

	public void sendEmail(List<String> emailList, String title, String contents) throws Exception {

		int port = 465; // 決まってるportを使う
		
		//受信者追加
		InternetAddress[] toEmails = new InternetAddress[emailList.size()];
		for (int i = 0; i < emailList.size(); i++) {
			toEmails[i] = new InternetAddress(emailList.get(i));
		}

		// メールアドレスを該当メールアドレスがに送信すること
		try {
			Properties props = System.getProperties(); // 情報を入れる引数生成

			// SMTP サーバー情報設定変更
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", port);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.ssl.trust", host);
			props.put("mail.smtp.socketFactory.port", port);
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

			// Session 生成
			Session session = Session.getDefaultInstance(props, new jakarta.mail.Authenticator() {
				String un = userName;
				String pw = password;

				@Override
				protected jakarta.mail.PasswordAuthentication getPasswordAuthentication() {
					return new jakarta.mail.PasswordAuthentication(un, pw);
				}
			});

			session.setDebug(true); // debug
			Message mimeMessage = new MimeMessage(session); // MimeMessage生成
			mimeMessage.setFrom(new InternetAddress(userName)); // 送り元設定
			mimeMessage.setRecipients(Message.RecipientType.TO, toEmails); // 送り先設定
			mimeMessage.setSubject(title); // タイトル設定
			mimeMessage.setText(contents); // 内容設定

			Transport.send(mimeMessage, toEmails, userName, password); // jakarta.mail.Transport.send()利用

		} catch (Exception e) {
			String messages = messageUtils.getMessage("NEXTMNA_STB01");
			throw new TodayChefException(messages, e);
		}
	}
	
	public boolean sendEmailByAddress(List<String> email, String tilte, String contents) throws Exception {

		boolean errorFlag = false;
		
		int port = 465; // 決まってるportを使う
		
		//受信者追加
		InternetAddress[] toEmails = new InternetAddress[email.size()];
		for (int i = 0; i < email.size(); i++) {
			toEmails[i] = new InternetAddress(email.get(i));
		}

		// メールアドレスを該当メールアドレスがに送信すること
		try {
			Properties props = System.getProperties(); // 情報を入れる引数生成

			// SMTP サーバー情報設定変更
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", port);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.ssl.trust", host);
			props.put("mail.smtp.socketFactory.port", port);
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

			// Session 生成
			Session session = Session.getDefaultInstance(props, new jakarta.mail.Authenticator() {
				String un = userName;
				String pw = password;

				@Override
				protected jakarta.mail.PasswordAuthentication getPasswordAuthentication() {
					return new jakarta.mail.PasswordAuthentication(un, pw);
				}
			});

			session.setDebug(true); // debug
			Message mimeMessage = new MimeMessage(session); // MimeMessage生成
			mimeMessage.setFrom(new InternetAddress(userName)); // 送り元設定
			mimeMessage.setRecipients(Message.RecipientType.TO, toEmails); // 送り先設定
			mimeMessage.setSubject(tilte); // タイトル設定
			mimeMessage.setText(contents); // 内容設定

			Transport.send(mimeMessage, toEmails, userName, password); // jakarta.mail.Transport.send()利用

		} catch (Exception e) {
			String messages = messageUtils.getMessage("TodayChef_STB01");
			errorFlag = true;
			throw new TodayChefException(messages, e);
		}
		return errorFlag;
	}
}