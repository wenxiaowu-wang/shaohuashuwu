package com.shaohuashuwu.test;


import com.shaohuashuwu.dao.AccountDao;
import com.shaohuashuwu.domain.Account;
import com.shaohuashuwu.service.AccountService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestSpring {

    @Test
    public void run1(){
        // 加载配置文件
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        // 获取对象
        AccountService as = (AccountService) ac.getBean("accountService");
        // 调用方法
        as.findAll();
    }

    @Test
    public void run2() throws Exception {
        //加载配置文件
        InputStream in = Resources.getResourceAsStream("applicationContext.xml");

        //创建SqlSessionBuolder
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //2.创建sqlSessionFactory对象
        SqlSessionFactory factory = builder.build(in);
        //创建SqlSession对象
        SqlSession session =factory.openSession();
        //获取代理对象
        AccountDao dao = session.getMapper(AccountDao.class);

        List<Account>list = dao.findAll();

        for (Account account:list){
            System.out.println(account);
        }
        //释放资源
        session.close();
        in.close();
    }

    @Test
    public void save() throws Exception {
        Account account = new Account();

        account.setName("张三");
        account.setMoney(400d);
        //加载配置文件
        InputStream in = Resources.getResourceAsStream("applicationContext.xml");

        //2.创建sqlSessionFactory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //创建SqlSession对象
        SqlSession session =factory.openSession();
        //获取代理对象
        AccountDao dao = session.getMapper(AccountDao.class);

        dao.saveAccount(account);
        //提交事务
        session.commit();
        //释放资源
        session.close();
        in.close();
    }

}
