package com.youandjang.todaychef.auth.social;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HexFormat;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.youandjang.todaychef.member.service.JoinService;
import com.youandjang.todaychef.member.service.LoginService;
import com.youandjang.todaychef.member.vo.MemberDto;

@Service
public class SocialMemberService {

	@Autowired
	private LoginService loginService;
	@Autowired
	private JoinService joinService;

	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public MemberDto findOrCreate(SocialUserProfile profile) throws Exception {
		MemberDto existing = loginService.findBySocialAccount(profile.getProvider(), profile.getSocialUserId());
		if (existing != null) {
			return existing;
		}

		MemberDto member = new MemberDto();
		member.setUserLoginId(generateUniqueLoginId(profile.getProvider(), profile.getSocialUserId()));
		member.setUserPassword(passwordEncoder.encode(UUID.randomUUID().toString()));
		member.setUserMail(resolveEmail(profile));
		member.setStopReason("");
		member.setSocialProvider(profile.getProvider());
		member.setSocialUserId(profile.getSocialUserId());

		joinService.Join(member);
		return loginService.findBySocialAccount(profile.getProvider(), profile.getSocialUserId());
	}

	private String generateUniqueLoginId(String provider, String socialUserId) throws Exception {
		String base = (provider + "_" + hash(socialUserId)).toLowerCase();
		if (base.length() > 40) {
			base = base.substring(0, 40);
		}

		String candidate = base;
		int suffix = 1;
		while (joinService.isUserIdDuplicate(candidate)) {
			String nextSuffix = "_" + suffix;
			int maxBase = Math.max(1, 40 - nextSuffix.length());
			String trimmed = base.length() > maxBase ? base.substring(0, maxBase) : base;
			candidate = trimmed + nextSuffix;
			suffix++;
		}
		return candidate;
	}

	private String resolveEmail(SocialUserProfile profile) {
		if (StringUtils.hasText(profile.getEmail())) {
			return profile.getEmail();
		}
		return profile.getProvider() + "_" + hash(profile.getSocialUserId()) + "@social.todaychef.local";
	}

	private String hash(String value) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] bytes = digest.digest(value.getBytes(StandardCharsets.UTF_8));
			return HexFormat.of().formatHex(bytes).substring(0, 20);
		} catch (Exception e) {
			return Integer.toHexString(value.hashCode());
		}
	}
}
