package com.aispeech.test;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/***
 * websocket签名算法
 * 
 * @author liangjiatang
 *
 */
public class HMACSHA1 {

	private static final String HMAC_SHA1 = "HmacSHA1";

	/**
	 * 生成签名数据
	 * 
	 * @param data
	 *            待加密的数据
	 * @param key
	 *            加密使用的key
	 * @return 生成MD5编码的字符串
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 */
	public static String getSignature(String encData, String encKey)
			throws InvalidKeyException, NoSuchAlgorithmException {
		byte[] data = encData.getBytes();
		byte[] key = encKey.getBytes();

		SecretKeySpec signingKey = new SecretKeySpec(key, HMAC_SHA1);
		Mac mac = Mac.getInstance(HMAC_SHA1);
		mac.init(signingKey);
		byte[] rawHmac = mac.doFinal(data);
		return HMACSHA1.bytes2Hex(rawHmac);
	}

	// 将字节数组转换成16进制的字符串
	private static String bytes2Hex(byte[] bts) {
		String des = "";
		String tmp = null;

		for (int i = 0; i < bts.length; i++) {
			tmp = (Integer.toHexString(bts[i] & 0xFF));
			if (tmp.length() == 1) {
				des += "0";
			}
			des += tmp;
		}
		return des;
	}
}
