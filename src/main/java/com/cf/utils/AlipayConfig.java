package com.cf.utils;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016092200573720";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDoTxZfOoEwsG5ug65Hk9KZldcLArvTrjuOQt5SgTvu+iEiLiM1vqSFzLpFCvjZOYEiAQk4cucFDZEp8YcAkWo08Fb5Jk/gWHsxkiaKYcjjDc0j7ObuLW9+mXEzywxp9N7Ar/DC90d4tet2imXa9rNy9+sB06dBSaoMuPZmSOpZTXLhL3oT7EzAVuyR5cGN8pnUpIu3McLJpl1Py6iJIHQbuaVVve3mh99PAu6L029FZmp/tW9OwvDVqmyZvOK1iFKrbBdu0fYJMOCxx9UbQn1XgT3/hk7O7tzKYhiUJZ+dEtO5B4UB6HY/Xsqhd9C2q8kcMdfZsUpvSHfayzkxdtWlAgMBAAECggEBAKD8bZyAhpafLWgYiOXBYuhcFjHjKxZ+X3cuj7LNLCNn4x40quHLw2eVgF/oQpsGZp6R6TndJosx468VWWDjZJ5bD2qJSkFGD9lCVsL/EJ+IKOkEeuQDSIiK/Q/PXkLvFo6hXk893aGhMBNtxTgATksV0x3o9rcfvWAjrV0kmoJW/2/GS5uTn0u+zmpZthxmK58ZpuPvCGqsBADJEXokTXw15xb+BvhBEcYLB6le26iR16vija9p+HbqE31kBD+PbYwVZzcQTf4Uw7Sw+EgLpAyY2g7Ie4pqa5CzBcjOSfE2j4ZbL4PTcxos+mDe/kbj1SiZn/0VDzIEsVdI5T+LcuECgYEA+m8g/7qB3TLUGMyzrFKgCKr4xFuoPOjoL+AnfFf3pfe8NLuxhnQs6Mg8YamhqBucIxTyzs2oPVZKR013dmqF8AZnLaSyy1F938JJz1y6a341FQMNr8h0sFE4hBCIAfDPsibQFZaIMArdWPR9YduNlDuH/Ls5Sq2Pg78+gqIJ8H8CgYEA7XjVXpsTyySK14GwWB38tR5HDK5XiLWQDbUxcKwNwDQdWRR+3WC5J0dBfXtMOE7TnLP7E3vI/be6ouNuCy9YiBkex1uCHsbV2fdEaLhr8/My9cLfG3gUxKYVkRxIJZb5lAmn6YpLRDByW129bOI7Il0s8QrHeeCLhlNoqENNZ9sCgYEAoMmKuUWorTk+HbIHxaLC5u3GjGOAr6KluF9FWrsr4B4fcwfy4a1jTA2LXvTHOqSHaarAffBijQcC56agBaI83i9uPUraFFn85BXcx8r0UaGVOSldtzePW8i57WijugM/hlUPEl00kQvYs409lv+cdciG9cfK79xhISg/XSXmFccCgYAml/u9W2C1JCQ4cAhT2dPHL7uGURNC0Zcu0yLM1/mIIjAe2RjhK6YLvyXimdZCVHVJw2pruTNqIKEBdgrdYH136PU6myGjlNcOjIQerNJGnGDM1jXiRfrCFufsZHdJPKclKwiSCPrAg0silDKD7qSDqjbymbjfkuG6G/YnWoDK/wKBgHeVg0zNOHC59YTCQFj8DwuXnk4PL4JDnBmeIec9KfntHZBgHrSW82I16IQa6ugcyTH243mYzpp8DhCl/2opNrau3xNEXHLFlnZbQPqeuolkmAMOxuGcEKgrpjALBR2wOQBJPSLrthtl2cJT3X+5ifWwWlg7JT5ghQqMNwkdwF4z";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA6E8WXzqBMLBuboOuR5PSmZXXCwK70647jkLeUoE77vohIi4jNb6khcy6RQr42TmBIgEJOHLnBQ2RKfGHAJFqNPBW+SZP4Fh7MZImimHI4w3NI+zm7i1vfplxM8sMafTewK/wwvdHeLXrdopl2vazcvfrAdOnQUmqDLj2ZkjqWU1y4S96E+xMwFbskeXBjfKZ1KSLtzHCyaZdT8uoiSB0G7mlVb3t5offTwLui9NvRWZqf7VvTsLw1apsmbzitYhSq2wXbtH2CTDgscfVG0J9V4E9/4ZOzu7cymIYlCWfnRLTuQeFAeh2P17KoXfQtqvJHDHX2bFKb0h32ss5MXbVpQIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8080/pay/notify";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:8080/pay/return";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "~/crowdfunding_log";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

