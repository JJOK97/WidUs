package com.naver.myhome.controller;

import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.naver.myhome.domain.User;
import com.naver.myhome.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {


   //주영
   private PasswordEncoder passwordEncoder;
   //지니
   private UserService userService;
   
   @Autowired

   public UserController(UserService userservice,PasswordEncoder passwordEncoder) {
      this.userService = userservice;
      this.passwordEncoder= passwordEncoder;
   }
   
   @GetMapping(value = "/profile")

//    public ModelAndView user_update(Principal principal, ModelAndView mv) {
//          String id =S principal.getName();
//        
//       if (id == null) {
//          mv.setViewName("redirect:login");
//          
//       } else {
//          User user= userservice.user_info(id);
//          mv.setViewName("user/profile");
//          mv.addObject("userinfo",user);
//       }
   public String update() {
       return "user/profile";
    }
   
   // 수정처리
//      @PostMapping(value = "/update-process")
//      public String updateProcess(User user, Model model, HttpServletRequest request, RedirectAttributes rattr) {
//
//         int result = userService.update(user);
//         if (result == 1) {
//            rattr.addFlashAttribute("result", "updateSuccess");
//            return "redirect:/board/list";
//         } else {
//            model.addAttribute("url", request.getRequestURL());
//            model.addAttribute("message", "정보 수정 실패");
//            return "error/error";
//
//         }
//
//      }
      
      //파일 저장 경로
//      @Value("${file.path}")
//         private String fileRealPath;
//      
//      @RequestMapping(value = "/list")
//      public ModelAndView memberList(@RequestParam(value = "page", defaultValue = "1", required = false) int page,
//            @RequestParam(value = "limit", defaultValue = "3", required = false) int limit, ModelAndView mv,
//            @RequestParam(value = "search_field", defaultValue = "1", required = false) int index,
//            @RequestParam(value = "search_word", defaultValue = "", required = false) String search_word) {
//
//         int listcount = userservice.getSearchListCount(index, search_word);
//         List<User> list = userservice.getSearchList(index, search_word, page, limit);
//
//         // 총 페이지 수
//         int maxpage = (listcount + limit - 1) / limit;
//
//         // 현재 페이지에 보여줄 시작 페이지 수 (1, 11, 21 등...)
//         int startpage = ((page - 1) / 10) * 10 + 1;
//
//         // 현재 페이지에 보여줄 마지막 페이지 수 (10, 20, 30 등...)
//         int endpage = startpage + 10 - 1;
//
//         if (endpage > maxpage)
//            endpage = maxpage;
//         mv.setViewName("user/user_list");
//         mv.addObject("page", page);
//         mv.addObject("maxpage", maxpage);
//         mv.addObject("startpage", startpage);
//         mv.addObject("endpage", endpage);
//         mv.addObject("listcount", listcount);
//         mv.addObject("memberlist", list);
//         mv.addObject("search_field", index);
//         mv.addObject("search_word", search_word);
//         return mv;
//
//      }
//      
//      @RequestMapping(value = "/info", method = RequestMethod.GET)
//      public ModelAndView member_info(@RequestParam("id") 
//                              String id,
//                              ModelAndView mv,
//                              HttpServletRequest request) {
//           
//         User m = userservice.user_info(id);
//         //m=null;//오류 확인하는 값
//         if (m!= null) {
//            mv.setViewName("member/member_info");
//            mv.addObject("memberinfo", m);
//         } else {
//            mv.addObject("url", request.getRequestURL());
//            mv.addObject("message","해당 정보가 없습니다.");
//            mv.setViewName("error/error");
//            
//         }
//         return mv;
//      }
//      
//      @RequestMapping(value= "/delete", method = RequestMethod.GET)
//         public String member_delete(String id ) {
//            
//         userservice.delete(id);
//            return "redirect:list";
//         }
//   
   //지니 끝

   @GetMapping("/confirm")
   public String confirm() {
   return "user/confirm";
   }
   
   @GetMapping("/join")
   public String join() {
   return "user/join";
   }
   
   @GetMapping("/joinProcess")
   public String joinProcess(User user) {
   return "user/joinProcess";
   }
   
   @GetMapping("/create-company-domain")
   public String createCompanyDomain() {
   return "user/create-company-domain";
   }
   
   @PostMapping("/create-company-id")
   public String createCompanyId() {
      return "user/create-company-id";
   }
   
   @GetMapping("/join-company")
   public String joinCompany() {
   return "user/join-company";
   }
   
   @GetMapping("/login")
   public String login() {
   return "user/login";
   }
   
   @PostMapping("/join-success")
   public String joinSuccess() {
      return "user/login";
   }

   
    @ResponseBody
    @PostMapping("/send-mail-auth-code")
    public Integer sendMailAuthCode(String recipientEmail,HttpSession session) throws Exception {


        // 난수(인증번호) 생성
        Random random = new Random();
        int verificationCode = 100000 + random.nextInt(900000); // 6자리 난수 생성

        // 이메일로 인증번호 전송
        String senderEmail = "wndudajt@naver.com"; //  이메일 주소
        String senderPassword = "rlawndud1234@"; //  이메일 비밀번호

        String subject = "이메일 인증 번호";
        String body = "인증 번호는 다음과 같습니다: " + verificationCode;

        try {
           Properties props = new Properties();
           props.put("mail.smtp.host", "smtp.naver.com");
           props.put("mail.smtp.port", "465");
           props.put("mail.smtp.auth", "true");
           props.put("mail.smtp.ssl.enable", "true");
           props.put("mail.smtp.ssl.trust", "smtp.naver.com");
           props.put("mail.smtp.ssl.protocols", "TLSv1.2");

            Session mailSession = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(senderEmail, senderPassword);
                }
            });

            Message message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);
            
            
            session.setAttribute("verificationCode", verificationCode);
            
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return verificationCode;
    }  
    
    @ResponseBody
    @PostMapping("/chk-auth-code")
    public String chkAuthCode(User user, HttpSession session) throws Exception {
       String errMsg = "";
       int verCode = (int) session.getAttribute("verificationCode");
       int userAuthCode = user.getAuthNum();
       System.out.println("verificationCode== > " + verCode);
       System.out.println("userInputAuthNum== > " + userAuthCode);
       
       if(verCode == userAuthCode || userAuthCode == 0) {
           String encPassword = passwordEncoder.encode(user.getPassword());
             user.setPassword(encPassword);
             System.out.println(user.getPassword());

            userService.insert(user);
       }else {
          errMsg = "인증코드를 확인하세요";
       }
       
       return errMsg;
    }
    
    @ResponseBody
    @PostMapping("/chk-dupl-email")
    public String chkduplEmail(String email, HttpSession session) throws Exception {
       String errCode = "";
       System.out.println("userEMail" + email);         //입력받은 eMail;
             
        User tmpUser = userService.selectByMail(email);
        System.out.println("tmpUser" + tmpUser);               //고객 조회 (Email을 통해서)
        if(tmpUser != null) {
           errCode = "1";                                 //이미 Email로 가입한 경우
         }else {
           errCode = "0";
         }
        
        return errCode;
    }
}
