package com.hy.cb.service.member.impl;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hy.cb.entity.member.SeMember;
import com.hy.sys.entity.SysUser;

 

@Service("memberPasswordService")
public class MemberPasswordServiceImpl {

	private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
	
	@Value(value = "${shiro.credentials.hashAlgorithmName}")
	private String algorithmName = "md5";
	@Value(value = "${shiro.credentials.hashIterations}")
	private final int hashIterations = 2;

	public void encryptPassword( SeMember member) {
		member.setRandom(randomNumberGenerator.nextBytes().toHex());
		String newPassword = new SimpleHash(algorithmName, member.getPassword(),
				ByteSource.Util.bytes(member.getRandom()), hashIterations).toHex();
		member.setPassword(newPassword);
	}
}
