package com.summit.constant.common.util.encryption;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Objects;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 *DES对称加密工具类
 */
public class DesUtils {

    /**
     * 对称加密密钥
     */
    private static final String PASSWORD = "2037160974043096965639708424590011416553193428956897480135656374257278867347454410695608942307532325452053895953189550149791562271893617579237042822813647557620";

    /**
     * 加密
     *
     * @param datasource 原始字符串
     * @return 加密后的字符串
     */
    public static String encrypt(String datasource) throws Exception{
        
         DESKeySpec desKey = new DESKeySpec(PASSWORD.getBytes());
         //创建一个密匙工厂，获取secretKey
         SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
         SecretKey secretKey = keyFactory.generateSecret(desKey);
         //指定获取DES的Cipher对象
         Cipher cipher = Cipher.getInstance("DES");
         //用密匙初始化Cipher对象
         cipher.init(Cipher.ENCRYPT_MODE, secretKey, new SecureRandom());
         //数据加密
         return parseByte2HexStr(cipher.doFinal(datasource.getBytes(StandardCharsets.UTF_8)));
     
    }

    /**
     * 解密
     *
     * @param src 待解密的字符串
     * @return 解密后的字符串
     */
    public static String decrypt(String src) throws Exception {
        // 创建一个DESKeySpec对象，PASSWORD可任意指定
        DESKeySpec desKey = new DESKeySpec(PASSWORD.getBytes());
        // 创建一个密匙工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        // 生成密钥
        SecretKey secretkey = keyFactory.generateSecret(desKey);
        // 指定获取DES的Cipher对象
        Cipher cipher = Cipher.getInstance("DES");
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, secretkey, new SecureRandom());
        // 真正开始解密操作
        return new String(cipher.doFinal(Objects.requireNonNull(parseHexStr2Byte(src))));
    }

    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return
     */
    private static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     *
     * @param hexStr
     * @return
     */
    private static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
    
    
//    public static void main(String[] args) {
//		String a = "山脉科技（Summit）软件部水务中心.123";
//		try {
//			String a1 = DesUtils.encrypt(a);
//			System.out.println(a1);
//			System.out.println(DesUtils.decrypt(a1));
//		}catch(Exception e ) {
//			System.out.print("加密、解密失败");
//		}	
//	}
}

