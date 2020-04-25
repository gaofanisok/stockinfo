package com.stockinfo.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: gaofan
 * @Date: 2020/4/15 0015 15 11
 * @Description: 工具类
 */
public  class Util {
    /**
     *过期时间15分钟
     */
    private static final long EXPIRE_TIME=15*60*9999999;

    /**
     * token私密钥匙
     */
    private static  final String TOKEN="0cf92441214a4d9c811580b2b457018c";

    /**
     *<pre>
     * Description  : 产生token  <br/>
     * ChangeLog    : 1. 创建 (2020/4/15 0015 15:37 [gaofan]);
     * @author gaofan
     * @date 2020/4/15 0015 15:37
     *</pre>          
    */
    public static String sign(String userName,String userId) throws UnsupportedEncodingException {
        //过期时间
        Date date=new Date(System.currentTimeMillis()+EXPIRE_TIME);
        //私密钥匙算法
        Algorithm algorithm= Algorithm.HMAC256(TOKEN);
        //设置头部信息
        Map<String,Object> header=new HashMap<>(2);
        header.put("typ","JWT");
        header.put("alg","HS256");
        //附带username,userID信息，生成签名
        return JWT.create().withHeader(header).withClaim("loginName",userName).withClaim("userId",userId).withExpiresAt(date).sign(algorithm);
    }
    /**
     *<pre>
     * Description  : 验证token是否正确  <br/>
     * ChangeLog    : 1. 创建 (2020/4/15 0015 15:37 [Administrator]);
     * @author Administrator
     * @date 2020/4/15 0015 15:37
     *</pre>
    */
    public static boolean verify(String token){
        if (!"".equals(token)){
            try {
                Algorithm algorithm=Algorithm.HMAC256(TOKEN);
                JWTVerifier jwtVerifier=JWT.require(algorithm).build();
                DecodedJWT decodedJWT=jwtVerifier.verify(token);
                return true;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    /**
     *<pre>
     * Description  : token获取用户id<br/>
     * ChangeLog    : 1. 创建 (2020/4/15 0015 15:37 [gaofan]);
     * @author gaofan
     * @date 2020/4/15 0015 15:37
     *</pre>
     */
    public static String getUserId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userId").asString();
        } catch (JWTDecodeException e) {
            e.getStackTrace();
        }
        return null;
    }
    /**
     *<pre>
     * Description  : token获取用户name<br/>
     * ChangeLog    : 1. 创建 (2020/4/15 0015 15:37 [gaofan]);
     * @author gaofan
     * @date 2020/4/15 0015 15:37
     *</pre>
     */
    public static String getUserName(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("loginName").asString();
        } catch (JWTDecodeException e) {
            e.getStackTrace();
        }
        return null;
    }
    /**
     *<pre>
     * Description  : 日期转换字符串  <br/>
     * ChangeLog    : 1. 创建 (2020/4/16 0016 14:52 [gaofan]);
     * @author Administrator
     * @date 2020/4/16 0016 14:52
     *</pre>
    */
    public static  String dateToString(String date,String rlqx) throws ParseException {
        if (date!=null){
            SimpleDateFormat sdf =new SimpleDateFormat(rlqx);
            return sdf.format(sdf.parse(date));
        }
        return "";
    }


    /**
     *<pre>
     * Description  : 利用java原生的类实现SHA256加密  <br/>
     * ChangeLog    : 1. 创建 (2020/4/20 0020 14:00 [gaofan]);
     * @author gaofan
     * @date 2020/4/20 0020 14:00
     *</pre>
    */
    public static String getSHA256(String str){
        MessageDigest messageDigest;
        String encodestr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes("UTF-8"));
            encodestr = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodestr;
    }
    /**
     *<pre>
     * Description  : 加密算法  <br/>
     * ChangeLog    : 1. 创建 (2020/4/20 0020 14:00 [gaofan]);
     * @author gaofan
     * @date 2020/4/20 0020 14:00
     *</pre>
    */
    private static String byte2Hex(byte[] bytes){
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i=0;i<bytes.length;i++){
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length()==1){
                //1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }
    public static Date newdata(){
        Date date=new Date();
        Timestamp timestamp=new Timestamp(date.getTime());
        return timestamp;
    }

}
