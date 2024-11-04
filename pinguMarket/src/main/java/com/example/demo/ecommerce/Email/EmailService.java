package com.example.demo.ecommerce.Email;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMessage.RecipientType;

@Service
public class EmailService {
   
   @Autowired
   private JavaMailSender mailSender;
   

   public String sendCode(String email) throws MessagingException {
//      // TODO Auto-generated method stub
//      // 임시 비밀번호 발급
      int length = 8;
      String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
      Random random = new Random();
      StringBuilder tempPassword = new StringBuilder(length);
      
      for (int i = 0; i < length; i++) {
            tempPassword.append(chars.charAt(random.nextInt(chars.length())));
        }
      
      //SimpleMailMessage message = new SimpleMailMessage();
      MimeMessage message = mailSender.createMimeMessage();
      //message.setTo(email);
      message.addRecipients(RecipientType.TO, email);
       message.setSubject("[Fligent] 전화번호 변경을 위한 이메일 인증코드 입니다"); // 이메일 제목

           String msgg = "";
           // msgg += "<img src=../resources/static/image/emailheader.jpg />"; // header image
           msgg += "<h1>안녕하세요</h1>";
           msgg += "<h1>핑구누렁한 핑구 인더스트리 입니다.</h1>";
           msgg += "<br>";
           msgg += "<p>아래 인증코드를 개인정보 변경 페이지에 입력해주세요</p>";
           msgg += "<br>";
           msgg += "<br>";
           msgg += "<div align='center' style='border:1px solid black'>";
           msgg += "<h3 style='color:blue'>회원가입 인증코드 입니다</h3>";
           msgg += "<div style='font-size:130%'>";
           msgg += "<strong>" + tempPassword + "</strong></div><br/>" ; // 메일에 인증번호 ePw 넣기
           msgg += "</div>";
           // msgg += "<img src=../resources/static/image/emailfooter.jpg />"; // footer image

           message.setText(msgg, "utf-8", "html"); // 메일 내용, charset타입, subtype
      
      mailSender.send(message);
	  return tempPassword.toString();
      
      
   }

}