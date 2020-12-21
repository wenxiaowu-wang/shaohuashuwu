package com.shaohuashuwu.test;

import com.shaohuashuwu.controller.AttentionInfoController;
import com.shaohuashuwu.controller.CommentInfoController;
import com.shaohuashuwu.dao.*;
import com.shaohuashuwu.domain.AdminInfo;
import com.shaohuashuwu.domain.BookshelfInfo;
import com.shaohuashuwu.domain.ReadingHistoryInfo;
import com.shaohuashuwu.domain.UserInfo;
import com.shaohuashuwu.domain.vo.*;
import com.shaohuashuwu.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestBackEnd {
    @Autowired
    private AccountService accountService;

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private AdminInfoDao adminInfoDao;

    @Autowired
    private AttentionInfoDao attentionInfoDao;

    @Autowired
    private CommentInfoDao commentInfoDao;
    /**
     * 测试推送 3
     * 在王洪斌的分支里，为所欲为，改名字
     */
    @Test
    public void testFindAll(){
        List list = accountService.findAll();
        System.out.println(list);
    }

    @Autowired
    private AdminInfoService adminInfoService;

    @Test
    public void deleteTest(){
        if (adminInfoService.deleteAdminInfo("334455")){
            System.out.println("test over");
        }
    }

    @Test
    public void deleteTest1(){
        if (adminInfoService.deleteAdminInfo("334455")){
            System.out.println("test over");
        }
    }

    @Test
    public void updateBeanNumByIdAndNumTest(){
        if (userInfoDao.updateGoldBeanNumByUserId(11,1000)!=0){
            System.out.println("充值金豆测试成功");
        }
    }

    @Test
    public void selectAdminInfoByAdminIdTest(){
        AdminInfo adminInfo = adminInfoDao.selectAdminInfoByAdminId("abin");
        System.out.println(adminInfo.toString());
    }

    /**
     * 测试根据读者ID获取关注者信息
     * 测试接口：AttentionInfoDao.selectAttentionUserInfoByUserId
     */
    @Test
    public void selectAttentionInfoByReaderId(){
        System.out.println("测试根据读者ID获取关注者信息");
        List<UserInfo> selectResult = attentionInfoDao.selectAttentionUserInfoByUserId(11);
        for (int i=0;i<selectResult.size();i++){
            System.out.println(selectResult.get(i).getUser_name());
        }
    }
    /*
    @Autowired
    public AttentionInfoController attentionInfoController;

    @Test
    public void testAttentionInfoControllerGetAttentionInfo(){
        List<AttentionInfoVo> attentionInfoVos = attentionInfoController.getAttentionAuthorInfo(11);
        System.out.println("attentionInfoVos = ");
        for (int i=0;i<attentionInfoVos.size();i++) {
            System.out.println(attentionInfoVos.get(i).getUser_name());
        }
    }
    */
    @Autowired
    public AttentionInfoService attentionInfoService;

    @Test
    public void testAttentionInfoServiceGetAttentionInfo(){
        List<AttentionInfoVo> attentionInfoVos = attentionInfoService.getAttentionAuthorInfo(11);
        System.out.println("attentionInfoVos = ");
        for (int i=0;i<attentionInfoVos.size();i++) {
            System.out.println(attentionInfoVos.get(i).getUser_name());
        }
    }

    @Autowired
    public NoticeInfoService noticeInfoService;

    @Test
    public void testGetAllNoticeInfo(){
        System.out.println("测试获取所有通知信息");
        List<NoticeInfoVo> noticeInfoVoList = noticeInfoService.getAllNoticeInfo(11);
        for (int i=0;i<noticeInfoVoList.size();i++){
            System.out.println(noticeInfoVoList.get(i).toString());
        }
    }

    @Autowired
    public TransactionInfoService transactionInfoService;

    @Test
    public void testGetAllIncomeTransaction(){
        List<TransactionInfoVo> transactionInfoVos = new ArrayList<>();
        transactionInfoVos = transactionInfoService.getAllIncomeTransactionInfo(11);
        for (int i=0;i<transactionInfoVos.size();i++){
            System.out.println(transactionInfoVos.get(i).toString());
        }
        System.out.println("test``````success");
    }

    @Test
    public void testGetAllWithdarw(){
        List<TransactionInfoVo> transactionInfoVos = transactionInfoService.getTransactionOfWithdraw(11);
        for (int i=0;i<transactionInfoVos.size();i++){
            System.out.println(transactionInfoVos.get(i).toString());
        }
    }

    @Test
    public void timeTest(){

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); //获得年月日
        Date currentTime = new Date();
        String systemDate1 = formatter.format(currentTime);


    }


    @Autowired
    public ReadingHistoryInfoService readingHistoryInfoService;

    @Autowired
    public BookshelfInfoDao bookshelfInfoDao;

    @Autowired
    private UserInfoService userInfoService;

    @Test
    public void test001(){

//        SimpleDateFormat formatter2 = new SimpleDateFormat("MMddHHmmss"); //获得年月日
//        Date currentTime = new Date();
//
//        String name2 = formatter2.format(currentTime);
//
//
//
//        Random random = new Random();
//        int one_code = random.nextInt(9999);
//        if (one_code < 1000) {//位数处理
//            one_code = one_code + 1000;
//        }
//        String code = String.valueOf(one_code);
//        String user_name = "韶华用户"+code+name2;
//        System.out.println(user_name);
//        List<String> work_id = bookshelfInfoDao.selectBookshelfWorkIdByWorkID(38);
//                System.out.println(work_id);

//
//dou


//        int a = userInfoService.getGoldBeanNumOfUser(1);

    }


//    @Autowired
//    private CommentInfoController commentInfoController;

    @Autowired
    private CommentInfoService commentInfoService;

    @Autowired
    private WorkWholeInfoVoService workWholeInfoVoService;
    @Autowired
    private WorksInfoService worksInfoService;
    @Test
    public void CommentTest(){

        List<CommentInfoParentVo> getResult = commentInfoService.getCommentParentInfoByChapterId(36);
//        List<CommentInfoChildVo> getResult = commentInfoService.getCommentChildInfoByChapterId(36);
//        List<CommentInfoParentVo> getResult = commentInfoController.getCommentParentInfoByWorkId(32);

        System.out.println(getResult);


    }

    @Autowired
    public WorksInfoDao worksInfoDao;


    @Test
    public void tsest2() throws Exception {


//        System.out.println(readingHistoryInfoService.getReadingHistoryCountByUserId(38));

        System.out.println(commentInfoService.deleteWorkComment(54));
//        System.out.println(worksInfoDao.selectTest());
//        Random random = new Random();
//        int headCode = random.nextInt(15);
//        String headCode1 = String.valueOf(headCode);
//        String headCode2;
//        if(headCode>=10){
//            headCode2 = "0" + headCode1;
//        }else{
//            headCode2 = "00" + headCode1;
//        }
//        String head_portrait = headCode2;
//        String head_portrait2 = "006";
//        System.out.println(head_portrait);
//        System.out.println(head_portrait2);



    }
    
}
