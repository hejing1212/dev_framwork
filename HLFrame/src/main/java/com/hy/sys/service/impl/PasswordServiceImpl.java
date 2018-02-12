package com.hy.sys.service.impl;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hy.sys.entity.SysUser;

 

@Service("passwordService")
public class PasswordServiceImpl {

	private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
	
	@Value(value = "${shiro.credentials.hashAlgorithmName}")
	private String algorithmName = "md5";
	@Value(value = "${shiro.credentials.hashIterations}")
	private final int hashIterations = 2;

	public void encryptPassword(SysUser user) {
		user.setSalt(randomNumberGenerator.nextBytes().toHex());
		String newPassword = new SimpleHash(algorithmName, user.getPassword(),
				ByteSource.Util.bytes(user.getCredentialsSalt()), hashIterations).toHex();
		user.setPassword(newPassword);
	}
}
