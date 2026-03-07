package com.youandjang.todaychef.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtil {
	private final PasswordEncoder passwordEncoder;

	public SecurityUtil(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	public String encryptPassword(String rawPassword) {
		return passwordEncoder.encode(rawPassword);
	}

	public boolean matchesPassword(String rawPassword, String encodedPassword) {
		if (encodedPassword == null) {
			return false;
		}
		if (encodedPassword.startsWith("$2a$") || encodedPassword.startsWith("$2b$") || encodedPassword.startsWith("$2y$")) {
			return passwordEncoder.matches(rawPassword, encodedPassword);
		}
		// Support existing SHA-256 records until all accounts are migrated.
		return encryptSHA256(rawPassword).equals(encodedPassword);
	}

	public String encryptSHA256(String str) {
		String sha = "";
		try {
			MessageDigest sh = MessageDigest.getInstance("SHA-256");
			sh.update(str.getBytes());
			byte byteData[] = sh.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}
			sha = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			sha = null;
		}
		return sha;
	}

}
