package com.summit.constant.common.util.encryption;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * AES对称加密工具类
 */
@Slf4j
public class AESUtil {
    /**
     * 128位的AESkey
     */
    private static final byte[] AES_KEY = "1111111111111111".getBytes(StandardCharsets.UTF_8);

    /**
     * AES解密
     *
     * @param data 待解密内容
     * @return 字节数组
     */
    public static byte[] decrypt(byte[] data) throws InvalidKeyException, NoSuchAlgorithmException,
            NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = getCipher(AES_KEY, Cipher.DECRYPT_MODE);
        return cipher.doFinal(data);
    }

    /**
     * AES 加密操作
     *
     * @param data 待加密内容
     * @return 字节数组
     */
    public static byte[] encrypt(byte[] data) throws InvalidKeyException, NoSuchAlgorithmException,
            NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = getCipher(AES_KEY, Cipher.ENCRYPT_MODE);
        return cipher.doFinal(data);
    }


    /**
     * AES 加密操作
     *
     * @param text 待加密内容
     * @return Base64转码后的加密数据
     */
    public static String encrypt(String text) {
        byte[] byteContent = text.getBytes(StandardCharsets.UTF_8);
        try {
            byte[] result = encrypt(byteContent);// 加密
            return Base64.encodeBase64String(result);//通过Base64转码返回
        } catch (Exception e) {
            log.info("Error message: {}", e.getMessage());
        }
        return null;
    }

    /**
     * AES 解密操作
     *
     * @param text
     * @return
     */
    public static String decrypt(String text) {
        byte[] bytes = Base64.decodeBase64(text);
        try {
            byte[] result = decrypt(bytes);
            return new String(result, StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.info("Error message: {}", e.getMessage());
        }

        return null;
    }


    /**
     * 获取加密器
     * @param key
     * @param model
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     */
    private static Cipher getCipher(byte[] key, int model)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(model, secretKeySpec);
        return cipher;
    }
    
//    public static void main(String[] args) {
//		String a = "山脉科技（Summit）软件部水务中心.123";
//		String a1 = AESUtil.encrypt(a);
//		System.out.println(a1);
//		System.out.println(AESUtil.decrypt(a1));
//		
//	}

}