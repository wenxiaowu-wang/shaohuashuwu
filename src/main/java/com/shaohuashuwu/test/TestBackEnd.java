package com.shaohuashuwu.test;

import com.shaohuashuwu.controller.AttentionInfoController;
import com.shaohuashuwu.dao.AdminInfoDao;
import com.shaohuashuwu.dao.AttentionInfoDao;
import com.shaohuashuwu.dao.UserInfoDao;
import com.shaohuashuwu.domain.AdminInfo;
import com.shaohuashuwu.domain.UserInfo;
import com.shaohuashuwu.domain.vo.AttentionInfoVo;
import com.shaohuashuwu.domain.vo.NoticeInfoVo;
import com.shaohuashuwu.service.AccountService;
import com.shaohuashuwu.service.AdminInfoService;
import com.shaohuashuwu.service.AttentionInfoService;
import com.shaohuashuwu.service.NoticeInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

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
    
}
