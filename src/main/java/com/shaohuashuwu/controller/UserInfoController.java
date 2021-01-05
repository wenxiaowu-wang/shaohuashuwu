package com.shaohuashuwu.controller;

import com.shaohuashuwu.domain.UserInfo;
import com.shaohuashuwu.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

@Controller
@RequestMapping(path = "/userInfoController")
@SessionAttributes(value = {"user_id","user_name"},types = {Integer.class,String.class})
public class UserInfoController {

    @Autowired
    public UserInfoService userInfoService;

    /**
     * 将user_id存入sesssion
     * @param author_id
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/addAuthor_idSession")
    public void addAuthor_idSession(Integer author_id, HttpServletRequest request, HttpServletResponse response)  {

        System.out.println("作者信息"+author_id);
        HttpSession session = request.getSession();
        session.setAttribute("author_id",author_id);

    }


    /**
     *  获取用户登录信息，
     *  功能点：用户界面上的登录的用户信息,
     * @return
     */
    //需要去掉设置额session——id
    @ResponseBody
    @RequestMapping("/getUserLoginInfo")
    public UserInfo getUserLoginInfo(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        Object msg = session.getAttribute("user_id");
        int user_id=Integer.parseInt(String.valueOf(msg));
        return userInfoService.getUserLoginInfo(user_id);
    }

    /**
     * 获取网站注册人数
     * 功能点：用户界面上的获取的用户数量
     * @return
     */
    @ResponseBody
    @RequestMapping("/getUserNum")
    public int getUserNum(){
        return userInfoService.getUserNum();
    }





    /**
     *依据作品id查询作者信息
     * 功能点：作品详情时获取作者信息，
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("/getUserInfoByWork_id")
    public UserInfo getUserInfoByWork_id(HttpServletRequest request, HttpServletResponse response){

        HttpSession session = request.getSession();
        Object msg = session.getAttribute("work_id1");
        int work_id=Integer.parseInt(String.valueOf(msg));
        return userInfoService.getUserInfoByWork_id(work_id);
    }


    /**
     * 根据章节id获取用户信息
     * 功能点：阅读小说界面获取作者信息
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("/getauthorInfoBychapterid")
    public UserInfo getauthorInfoBychapterid(HttpServletRequest request, HttpServletResponse response){

        HttpSession session = request.getSession();
        Object msg = session.getAttribute("chapter_id");
        int chapter_id=Integer.parseInt(String.valueOf(msg));
        return userInfoService.getauthorInfoBychapterid(chapter_id);
    }


    /*
    * 郝振威
    *
    *
    *
    *
    *
    *
    *
    *
    * */

    //用户登录  账号密码登录
    @RequestMapping("/userLogin/{phone_number}/{password}")
    @ResponseBody
    public Boolean userLogin(@PathVariable(value = "phone_number") String phone_number, @PathVariable(value = "password") String password) {

        System.out.println("传入数据成功。。。");
        System.out.println("账号：" + phone_number + "密码：" + password);
        Boolean theResult = false;
        int isno = userInfoService.checkUserInfo(phone_number, password);
        if (isno == 1) {
            System.out.println("登陆成功");

            theResult = true;
        } else {
            System.out.println("登陆失败");
        }
        return theResult;
    }

    // //用户登录  验证码登录
    @RequestMapping("/userLogin2/{phone_number}")
    @ResponseBody
    public Boolean userLogin2(@PathVariable(value = "phone_number") String phone_number) {

        System.out.println("传入数据成功。。。");
        System.out.println("账号：" + phone_number);
        Boolean theResult = false;
        int isno = userInfoService.isUserInfohaveByphone_number(phone_number);
        if (isno == 1) {
            System.out.println("登陆成功");
            theResult = true;
        } else {
            System.out.println("登陆失败");
        }
        return theResult;
    }

    //验证码注册
    @RequestMapping("/userRegister_general/{phone_number}/{password}/{smsCode}")
    @ResponseBody
    public Boolean userRegister_general(@PathVariable(value = "phone_number") String phone_number, @PathVariable(value = "password") String password, @PathVariable(value = "smsCode") String smsCode) {
        System.out.println("模拟注册传入数据成功。。。");
        System.out.println("账号：" + phone_number + "、密码：" + password + "、网页输入的验证码：" + smsCode);
        Boolean theResult = false;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); //获得年月日
        SimpleDateFormat formatter2 = new SimpleDateFormat("MMddHHmmss"); //获得年月日
        Random random = new Random();
        int one_code = random.nextInt(9999);
        if (one_code < 1000) {//位数处理
            one_code = one_code + 1000;
        }
        String code = String.valueOf(one_code);


        int headCode = random.nextInt(15);
        String headCode1 = String.valueOf(headCode);
        String headCode2;
        if(headCode>=10){
            headCode2 = "0" + headCode1;
        }else{
            headCode2 = "00" + headCode1;
        }

        Date currentTime = new Date();
        String birthday = formatter.format(currentTime);
        String name2 = formatter2.format(currentTime);
        String user_name = "韶华用户"+code+name2;
        String head_portrait = headCode2;
        String gender = "男";
        String area = "北京市";
        int gold_bean_num =0;
        int gold_coin_num =0;
        int ticket_num =0;

        if (userInfoService.insertUserInfo2(phone_number, password, smsCode,user_name,head_portrait,gender,area,birthday,gold_bean_num,gold_coin_num,ticket_num)) {
            System.out.println("注册成功！");
            theResult = true;
        } else {
            System.out.println("用户已存在！注册失败！");
        }
        return theResult;
    }

    //根据用户手机号获取用户ID 用户昵称 头像 性别 地区 生日
    @RequestMapping("/getUserIdNameAndHeaderByPhone/{phone_number}")
    @ResponseBody
    public Map<String, Object> getUserIdNameAndHeaderByPhone(@PathVariable(value = "phone_number") String phone_number) {

        System.out.println("我得调用了-----------------------------------------------");
        System.out.println("进入 Controller getUserIdNameAndHeaderByPhone");
        return userInfoService.getUserIdNameAndHeaderByPhone(phone_number);
    }

    //更新用户资料
    @RequestMapping("/updateUserDataByID/{user_id}/{user_name}/{gender}/{birthday}/{area}")
    @ResponseBody
    public Boolean updateUserDataByID(@PathVariable(value = "user_id") int user_id,@PathVariable(value = "user_name")String user_name,@PathVariable(value = "gender")String gender,@PathVariable(value = "birthday")String birthday,@PathVariable(value = "area")String area) {
        boolean updateResult = false;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); //获得年月日
        SimpleDateFormat format =  new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss z", Locale.ENGLISH);

        birthday = birthday.replace("GMT", "").replaceAll("\\(.*\\)", "");//格式化字符串
        System.out.println("格式化字符串后birthday+"+birthday);
        try {
            System.out.println("进入日期转化：~~~~~~~~~~~~~~");
            Date date = format.parse(birthday);
            birthday = formatter.format(date);
            System.out.println("转化后birthday为："+birthday);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("日期转化失败");
        }
        if (userInfoService.updateUserData(user_id,user_name, gender,birthday,area)){
            updateResult = true;
        }
        return updateResult;

    }

    //修改密码
    @RequestMapping("/modifyPassword/{phone_number}/{newPassword}")
    @ResponseBody
    public Boolean modifyPassword(@PathVariable(value = "phone_number") String phone_number,@PathVariable(value = "newPassword") String newPassword) {
        System.out.println("进入修改密码Controller");
        System.out.println("账号：" + phone_number + "、密码：" + newPassword);
        boolean theResult = false;
        if(userInfoService.updateUserPwd(phone_number,newPassword)){
            System.out.println("更改成功！");
            theResult = true;
        }else {
            System.out.println("更改失败！");
        }
        return theResult;
    }

    //用户名是否存在
    @RequestMapping("/isUserNameHaveByUsername/{user_name}/{user_id}")
    @ResponseBody
    public Boolean isUserNameHaveByUsername(@PathVariable(value = "user_name") String user_name,@PathVariable(value = "user_id") int user_id) {
        System.out.println("----------------------------");
        System.out.println("用户信息"+user_name+user_id);
        boolean theResult = userInfoService.isUserNameHaveByUsername(user_name,user_id);

        return theResult;

    }

    //订阅 更新用户金豆数
    @RequestMapping(path = "/updateUserGoldBean/{user_id}/{goldBean}")
    @ResponseBody
    public Boolean updateUserGoldBean(@PathVariable(value = "user_id") Integer user_id, @PathVariable(value = "goldBean") Integer goldBean) {
        Boolean updateResult = false;
        updateResult = userInfoService.updateUserGoldBean(user_id, goldBean);
        return updateResult;
    }

    //根据chapter_id查询作者的id
    @RequestMapping(path = "/selectAuthorIDByChapterID/{chapter_id}")
    @ResponseBody
    public int selectAuthorIDByChapterID(@PathVariable(value = "chapter_id") Integer chapter_id) {
        return userInfoService.getAuthorIDByChapterID(chapter_id);
    }

    //跳转到用户登录页面
    @RequestMapping(path = "/userLoginInterface")
    public String userLoginInterface() {
        return "userLogin.html";
    }


    //跳转到用户注册页面
    @RequestMapping(path = "/register_generalInterface")
    public String register_generalInterface() {
        return "register_general.html";
    }

    //跳转到用户协议页面
    @RequestMapping(path = "/userAgreeInterface")
    public String userAgreeInterface() {
        return "userAgreement.html";
    }

    //跳转到隐私政策页面
    @RequestMapping(path = "/privacyPolicyInterface")
    public String privacyPolicyInterface() {
        return "privacyPolicy.html";
    }

    //跳转到用户主页
    @RequestMapping(path = "/myHomePaceInterface")
    public String myHomePaceInterface() {
        return "myHomePage.html";
    }

//    /**
//     * 根据用户ID获取该用户的金豆数量
//     *
//     * @param id
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping(path = "/getGoldBeanNum/{id}")
//    public int getGoldBeanNum(@PathVariable(value = "id") Integer id) {
//        return userInfoService.getGoldBeanNumOfUser(id);
//    }


    /**
     * 阿斌
     */
    /**
     * 根据用户ID获取该用户的金豆数量
     * @param modelMap session域中的一块域的变量名
     * @return
     */
    @ResponseBody
    @RequestMapping(path = "/getGoldBeanNum")
    public int getGoldBeanNum(ModelMap modelMap){
        Integer user_id = (Integer)modelMap.get("user_id");
        return userInfoService.getGoldBeanNumOfUser(user_id);
    }

    /**
     * 根据用户ID获取该用户的剩余推荐票
     * @param modelMap session域中的一块域的变量名
     * @return
     */
    @ResponseBody
    @RequestMapping(path = "/getTicketNum")
    public int getTicketNum(ModelMap modelMap){
        Integer user_id = (Integer)modelMap.get("user_id");
        return userInfoService.getTicketNumOfUser(user_id);
    }

    @RequestMapping(path = "/isAlreadyBecameAuthor")
    @ResponseBody
    public Boolean isAlreadyBecameAuthor(ModelMap modelMap){
        Integer user_id = (Integer)modelMap.get("user_id");
        return userInfoService.isAlreadyBecameAuthor(user_id);
    }

    @RequestMapping(path = "/isOldDoublePassword/{oldPass}")
    @ResponseBody
    public Boolean isOldDoublePassword(ModelMap modelMap,@PathVariable(value = "oldPass")String oldPass) {
        Integer user_id = (Integer)modelMap.get("user_id");
        return userInfoService.isDoublePassword(user_id,oldPass);
    }

    @RequestMapping(path = "/addDoublePassword/{pass}")
    @ResponseBody
    public Boolean addDoublePassword(ModelMap modelMap,@PathVariable(value = "pass")String pass) {
        Integer user_id = (Integer)modelMap.get("user_id");
        return userInfoService.updateAuthorDoublePassword(user_id,pass);
    }

    @RequestMapping(path = "/updateDoublePassword/{oldPass}/{pass}")
    @ResponseBody
    public int updateDoublePassword(ModelMap modelMap,@PathVariable(value = "oldPass")String oldPass,@PathVariable(value = "pass")String pass) {
        Integer user_id = (Integer)modelMap.get("user_id");
        int theResult = 0;  //表示原密码错误
        if (userInfoService.isDoublePassword(user_id,oldPass)){
            if (userInfoService.updateAuthorDoublePassword(user_id,pass)){
                theResult = 1;  //表示修改成功
            }else{
                theResult = 2;  //表示原密码正确，修改失败
            }
        }
        return theResult;
    }

    @RequestMapping(path = "/toBecomeAnAuthor")
    public String toBecomeAnAuthor(){
        return "authorVerifyManageInterface.html";
    }


}
