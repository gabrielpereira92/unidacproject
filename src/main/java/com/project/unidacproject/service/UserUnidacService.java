package com.project.unidacproject.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.unidacproject.model.UserUnidac;
import com.project.unidacproject.repository.UserUnidacRepository;

@Service
public class UserUnidacService {

	private UserUnidacRepository userUnidacRepository;

	public UserUnidacService(UserUnidacRepository userUnidacRepository) {
		super();
		this.userUnidacRepository = userUnidacRepository;
	}

	public List<UserUnidac> findAllUserUnidac(String name) {
		if (name == null) {
			return userUnidacRepository.findAll();
		} else {
			return userUnidacRepository.findByNameContainingIgnoreCase(name);
		}
	}

	public UserUnidac findUserUnidacById(Long id) {

		return userUnidacRepository.findById(id).orElse(null);

	}

	public UserUnidac saveUserUnidac(UserUnidac userUnidac)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		try {
			userUnidac.setId(null);

			String aniver = userUnidac.getBirthday();
			String cpf = userUnidac.getCpf();
			String criptPassword = this.criptPassword(aniver, cpf);
			
			Calendar dataAniver = this.parserDate(aniver);
			
			
			
			
			userUnidac.setPassword(criptPassword);

			if (!userUnidac.isAlpha()) {
				throw new IllegalArgumentException("Name contains numbers or special character");
			}
			return userUnidacRepository.save(userUnidac);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public UserUnidac updateUserUnidac(UserUnidac userUnidac) {
		return userUnidacRepository.save(userUnidac);
	}

	public void deleteUserUnidacById(Long id) {
		userUnidacRepository.deleteById(id);
	}

	public void deleteAllUserUnidac() {
		userUnidacRepository.deleteAll();
	}

	public String criptPassword(String date, String cpf) throws NoSuchAlgorithmException, UnsupportedEncodingException {

		String splitCpf = cpf.substring(0, 3);
		String[] splitDate = date.split("/");

		String pass = splitCpf + splitDate[1];

		MessageDigest algorithm = MessageDigest.getInstance("SHA-1");
		byte messageDigest[] = algorithm.digest(pass.getBytes("UTF-8"));

		StringBuilder hexString = new StringBuilder();
		for (byte b : messageDigest) {
			hexString.append(String.format("%02X", 0xFF & b));
		}
		String passHex = hexString.toString();

		return passHex;

	}
	
	private final static String DATE_PATTERN = "yyyy-MM-dd";
	public static Calendar parserDate(String dateStr) throws java.text.ParseException {
		Date date = new SimpleDateFormat(DATE_PATTERN).parse(dateStr);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

}
