package com.auto.test.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import com.auto.test.common.config.GlobalValueConfig;
import com.auto.test.common.context.SpringContext;
import com.auto.test.common.exception.BusinessException;
import freemarker.template.Template;

public class EmailUtil {
	private static final String SERVER_HOST			= "mail.jddfun.com";
	private static final String SERVER_PORT			= "25";
	private static final String SERVER_TIMEOUT		= "25000";
	private static final String SERVER_IS_AUTH		= "true";
	private static final String SERVER_IS_SSL		= "true";
	private static final String SERVER_PROTOCOL		= "smtp";
	private static final String SERVER_FROM			= "zhouzhou@jddfun.com";
	private static final String SERVER_PASSWORD		= "cup6m8hp";
	
	public static void main(String[] args) {
		String[] email = {"zhouzhou@jddfun.com"};
		new EmailUtil().sendEmail("邮件测试-邮件标题", "邮件测试-你好啊！", email);
		/*sendEmail("邮件测试-邮件标题", "邮件测试-你好啊！");*/
	}
	
	public void sendEmail(String title){
		String mailTo = GlobalValueConfig.getConfig("mail.send.to");
		sendEmail(title, null, mailTo == null ? null : mailTo.split(","));
	}
	
	public void sendEmail(String title, String context){
		String mailTo = GlobalValueConfig.getConfig("mail.send.to");
		sendEmail(title, context, mailTo == null ? null : mailTo.split(","));
	}
	
	private synchronized void sendEmail(String title, String context, String[] email){
		if(email == null || email.length == 0){
			throw new BusinessException("邮件的收件人不可以为空！");
		}
        Transport ts = null;
		try {
			Properties prop = new Properties();
	        prop.setProperty("mail.smtp.host", SERVER_HOST);
	        prop.setProperty("mail.smtp.port", SERVER_PORT);
	        prop.setProperty("mail.smtp.auth", SERVER_IS_AUTH);
	        prop.setProperty("mail.smtp.timeout", SERVER_TIMEOUT);
	        prop.setProperty("mail.smtp.ssl.enable", SERVER_IS_SSL);
	        prop.setProperty("mail.transport.protocol", SERVER_PROTOCOL);
	        //使用JavaMail发送邮件的5个步骤
	        //1、创建session
	        Session session = Session.getInstance(prop);
	        //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
	        session.setDebug(true);
	        //2、通过session得到transport对象
			ts = session.getTransport();
			//3、使用邮箱的用户名和密码连上邮件服务器，发送邮件时，发件人需要提交邮箱的用户名和密码给smtp服务器，用户名和密码都通过验证之后才能够正常发送邮件给收件人。
	        ts.connect(SERVER_HOST, SERVER_FROM, SERVER_PASSWORD);
	        //4、创建邮件
	        Message message = createSimpleMail(session, title, context, email);
	        //5、发送邮件
	        ts.sendMessage(message, message.getAllRecipients());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(ts != null){
					ts.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private MimeMessage createSimpleMail(Session session, String title, String context, String[] email)
            throws Exception {
		InternetAddress[] adds = new InternetAddress[email.length];
        for (int i = 0; i < email.length; i++) {
        	adds[i] = new InternetAddress(email[i]);
		}
        //创建邮件对象
        MimeMessage message = new MimeMessage(session);
        /*//指明邮件的发件人	//发送文本
        message.setFrom(new InternetAddress(SERVER_FROM));
        //指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
        message.setRecipients(Message.RecipientType.TO, adds);
        //邮件的标题
        message.setSubject(title);
        //邮件的文本内容
        message.setContent(context, "text/html;charset=UTF-8");*/
        MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");	//发送模板
        FreeMarkerConfigurer freeMarkerConfigurer = (FreeMarkerConfigurer) SpringContext.getBean("freeMarkerConfigurer");
        Template tpl = freeMarkerConfigurer.getConfiguration().getTemplate("mail.html");
        Map<String, Object> root = new HashMap<String, Object>();
		String htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(tpl, root);
		helper.setText(htmlText, true);
		helper.setSubject(title);
		helper.setFrom(new InternetAddress(SERVER_FROM));
		helper.setTo(adds);
        //返回创建好的邮件对象
        return message;
    }

}
