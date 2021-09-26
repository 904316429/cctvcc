package cn.cctvcc.core.utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * @description: RSA 工具类提供加密, 解密, 生成密钥对等方法
 * @author: Jiang
 * @create: 2021-09-26 15:06
 */
public class RSAUtil {

    public static String RSA_ALGORITHM = "RSA";
    public static String UTF8 = "UTF-8";

    /**
     * 密钥长度，DSA算法的默认密钥长度是1024
     * 密钥长度必须是64的倍数，在512到65536位之间
     * */
    private static final int KEY_SIZE=1024;

    public static void main(String[] args) throws Exception {
        String password = "a1234566";
        KeyStore keys = createKeys();
        byte[] publicKey = getPublicKey(keys);
        byte[] privateKey = getPrivateKey(keys);
        System.out.println("公钥："+ Base64.getEncoder().encodeToString(publicKey));
        System.out.println("私钥："+ Base64.getEncoder().encodeToString(privateKey));

        byte[] encryptByPublicKey = encryptByPublicKey(password.getBytes(), publicKey);
        System.out.println("使用公钥加密后的数据："+ Base64.getEncoder().encodeToString(encryptByPublicKey));
        byte[] b = Base64.getDecoder().decode("e+NGrHB2uP0vybwf4tgD1TnajBzqmHELV46I+2eNE4H+SnBPcwjYSTwQXcZyCLScohL1bWLkdcet/yb+9RbUW35WfGmpigNupM63aVvSPvMmB/9EYgB/rsPsdnuFNCo7FuTqUeK8EMStbbm/raLkdbOPVyTf9lt93mag8ctwdTU=");
        byte[] bb = Base64.getDecoder().decode("MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAIpM7Xcg/caKA+AROvst9gpl/4rLpOTEekcF8pzwv4+ZmJtgQ4RnCMJy0SX03lzK6Z1NlY3MtUogd+rpSv/x1XLZSAOPb+AL9st3mLWdwrFbdfEoEKTitadK0BMCHL9mhB238MAIouELy+Sy5P+RT1VTG4/5ST5oSDuHxNtO3hK5AgMBAAECgYAJjicaA/2EqiR6ZrZchGBrd4CgZlGOPMT2A0Mv9LJHiaUxlwUTMMs6JBSvOqviXWKmq6WZlnswE+41HMlBxcOpgUt80VV0CLOGJZIIc9fN6YGcgWEFcoo1eiYMTuXoGIzBP4FdEGMFy0ygoIzXyg5NaY4w+ungc178b4Clr4wHAQJBAOGcEbTEc/4rCi1Po2MqRYOqMiKbc91d7Vq04B1GDC5F7ixTTBO0cZt1ahR499PCT4fDWJ3XLjRMfQgdk3YUrM0CQQCc7hY3tuDZzoiw8leM5h35SvQ5KNHWUsAGEcmucBXoXUrlBazY4lN9xRHYdYJgCIecjeVt1AWA59qgYjz7EH2dAkAj758mYxBy0No7I29MEWSnN0nrF0XaYea3xw3Xu9ue10a7yrzYeqqJ76bpAee/1Dik0GniIm9ap0BXNZbSfTKVAkBMn1dLxshMsLI/K2SiEC2sbfOHAS5LpkXJ8N93g5S+yuZpona9wuwfKZzIAsAwoZmNUB8om/A/+3TdAHpj365lAkBBenrY9rI4WjF4ZoruRxgi55F27QP9B1seMOeXsuYhyHz5g+utZ7rdw40/CMgIwl5aOkvXR+G3r5r8VREWoLFN");
        byte[] decryptByPrivateKey = decryptByPrivateKey(b, bb);
        System.out.println("使用私钥解密后的数据："+new String(decryptByPrivateKey));

        System.out.println(createKeys());

    }

    /**
     * 生成密钥对
     * @return 密钥对对象
     * @throws NoSuchAlgorithmException
     */
    public static KeyStore createKeys() throws NoSuchAlgorithmException {
        //KeyPairGenerator用于生成公钥和私钥对。密钥对生成器是使用 getInstance 工厂方法
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA_ALGORITHM);
        keyPairGenerator.initialize(KEY_SIZE);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey)keyPair.getPrivate();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        KeyStore keyStore = new KeyStore( publicKey, privateKey);
        return keyStore;
    }

    /**
     * 获取私钥
     * @param keyStore
     * @return
     */
    public static byte[] getPrivateKey(KeyStore keyStore){
        return ((RSAPrivateKey)keyStore.privateKey).getEncoded();
    }

    /**
     * 获取公钥
     * @param keyStore
     * @return
     */
    public static byte[] getPublicKey(KeyStore keyStore){
        return ((RSAPublicKey)keyStore.publicKey).getEncoded();
    }

    /**
     * 私钥加密
     * @param data 待加密数据
     * @param key 密钥
     * @return byte[] 加密数据
     * */
    public static byte[] encryptByPrivateKey(byte[] data,byte[] key) throws Exception{

        //取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec=new PKCS8EncodedKeySpec(key);
        KeyFactory keyFactory=KeyFactory.getInstance(RSA_ALGORITHM);
        //生成私钥
        PrivateKey privateKey=keyFactory.generatePrivate(pkcs8KeySpec);
        //数据加密
        Cipher cipher=Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    /**
     * 公钥加密
     * @param data
     * @param key
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws NoSuchPaddingException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws InvalidKeyException
     */
    private static byte[] encryptByPublicKey(byte[] data, byte[] key) throws NoSuchAlgorithmException,
            InvalidKeySpecException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        //实例化密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        //初始化公钥,根据给定的编码密钥创建一个新的 X509EncodedKeySpec。
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(key);
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        //数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE,publicKey);
        return cipher.doFinal(data);
    }

    /**
     * 私钥解密
     * @param data 待解密数据
     * @param key 密钥
     * @return byte[] 解密数据
     * */
    public static byte[] decryptByPrivateKey(byte[] data,byte[] key) throws Exception{
        //取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec=new PKCS8EncodedKeySpec(key);
        KeyFactory keyFactory=KeyFactory.getInstance(RSA_ALGORITHM);
        //生成私钥
        PrivateKey privateKey=keyFactory.generatePrivate(pkcs8KeySpec);
        //数据解密
        Cipher cipher=Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    /**
     * 公钥解密
     * @param data 待解密数据
     * @param key 密钥
     * @return byte[] 解密数据
     * */
    public static byte[] decryptByPublicKey(byte[] data,byte[] key) throws Exception{

        //实例化密钥工厂
        KeyFactory keyFactory= KeyFactory.getInstance(RSA_ALGORITHM);
        //初始化公钥
        //密钥材料转换
        X509EncodedKeySpec x509KeySpec=new X509EncodedKeySpec(key);
        //产生公钥
        PublicKey pubKey=keyFactory.generatePublic(x509KeySpec);
        //数据解密
        Cipher cipher=Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, pubKey);
        return cipher.doFinal(data);
    }


    //定义密钥类
    public static class KeyStore{
        private Object publicKey;
        private Object privateKey;

        public KeyStore(Object publicKey, Object privateKey) {
            this.publicKey = publicKey;
            this.privateKey = privateKey;
        }

        public Object getPublicKey() {
            return publicKey;
        }

        public void setPublicKey(Object publicKey) {
            this.publicKey = publicKey;
        }

        public Object getPrivateKey() {
            return privateKey;
        }

        public void setPrivateKey(Object privateKey) {
            this.privateKey = privateKey;
        }
    }

}
