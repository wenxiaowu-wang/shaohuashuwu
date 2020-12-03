package com.shaohuashuwu.dao;

import com.shaohuashuwu.domain.Account;
import com.shaohuashuwu.domain.WorksInfo;
import com.shaohuashuwu.domain.vo.Difvolenum;
import com.shaohuashuwu.domain.vo.PageInfo;
import com.shaohuashuwu.domain.vo.UserandWorksInfoVo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 包:com.shaohuashuwu.dao
 * 作者:王洪斌
 * 日期:2020/9/17
 * 项目:shaohuashuwu
 * 描述:
 */
@Repository
public interface WorksInfoDao {

    //依据作类型查询作品数量
    @Select("SELECT COUNT(work_main_label) FROM works_info where work_main_label = #{work_main_label} and work_serial_state != 3 ")
    public int selectnumBywork_main_label(String work_main_label);

    //依据作者名称查询作品名称
    @Select("Select w1.work_name work_name" +
            "   from works_info w1 " +
            "   where w1.user_id = " +
            "   (select u1.user_id from user_info u1 where u1.user_name = #{user_name})")
    public List<WorksInfo> selectwork_nameByuser_name(String user_name);


    //模糊查询，依据模糊作品名称查询作品名称
    @Select("Select w1.work_name work_name " +
            "   from works_info w1 " +
            "   where w1.work_id in " +
            "   (select w2.work_id from works_info w2 where w2.work_name like '%${work_name}%'  and w2.work_name != #{work_name})")
    public List<WorksInfo> selectVaguework_nameBywork_name(WorksInfo worksInfo);

    //按条件查询作品信息，作品主副类型，作品状态
    @Select({
            "<script>",
            "SELECT * FROM works_info where 1=1 and work_serial_state != 3 and work_serial_state != 4",
            "<if test='work_main_label != null and work_main_label != \" \" '>",
            "and work_main_label = #{work_main_label} ",
            "</if>",
            "<if test='work_vice_label != null and work_vice_label != \" \" '>",
            "and work_vice_label = #{work_vice_label} ",
            "</if>",
            "<if test='work_serial_state != null and work_serial_state != \" \" '>",
            "and work_serial_state = #{work_serial_state} ",
            "</if>",
            "<if test='work_name != null and work_name != \" \" '>",
            "and work_name like '%${work_name}%'  and work_name != #{work_name}",
            "</if>",
            "</script>"
    })
    public List<WorksInfo> selectworksneed(WorksInfo worksInfo);

    //依据作品id查询作品信息
    @Select("select * from works_info where work_id = #{work_id}")
    public WorksInfo selectworkInfoByWork_id(int work_id);


    //依据作品id获取该作品作者的其他作品信息
    @Select(" select w1.work_id work_id,w1.work_name work_name,w1.work_cover work_cover,w1.work_introduct work_introduct,w1.work_word_num work_word_num " +
            " from works_info w1 " +
            " where w1.user_id = " +
            "   (select w2.user_id " +
            "    from works_info w2" +
            "    where w2.work_id = #{work_id})")
    public List<WorksInfo> selectOtherWorkInfoByWork_id(int work_id);


    //依据用户id查询该用户作品数量
    @Select("select count(*) from works_info where user_id = #{user_id}")
    public int selectWorksNumByUser_id(int user_id);

    //根据用户id获取作品信息
    @Select("select work_id,work_cover,work_name,work_main_label,work_vice_label,work_serial_state,work_word_num,work_tip_num,work_subscribe_num,work_vote_num,work_create_time  " +
            " from works_info where user_id = #{user_id}")
    public List<WorksInfo> selectWorksInfoByUser_id(int user_id);

    //通过作品名称查询作品数量，判断作品是否存在
    @Select("select count(*) from works_info where work_name = #{work_name}")
    public int selectWorkbywork_name(String work_name);

    //通过作品id修改作品信息
    @Update("update works_info set work_serial_state = #{work_serial_state} where work_id = #{work_id} ")
    public int updateWorkInfoByworkid(WorksInfo worksInfo);



















    /*测试*/
    @Select("select work_name from works_info  where work_name like '%${work_name}%' ")
    public List<WorksInfo> selecttest(WorksInfo worksInfo);





















    /**********************************************8以下未修改*/


    //查询全部作品信息
    @Select("select * from works_info")
    public List<WorksInfo> selectAllworks();


    //添加作品信息
    @Insert("insert into works_info(work_name,user_id,work_main_label,work_vice_label,work_serial_state,work_introduct,work_other_word,work_word_num,work_tip_num,work_subscribe_num,work_vote_num,work_create_time) " +
            "values (#{work_name},#{user_id},#{work_main_label},#{work_vice_label},#{work_serial_state},#{work_introduct},#{work_other_word},#{work_word_num},#{work_tip_num},#{work_subscribe_num},#{work_vote_num},#{work_create_time} )")
    public int insertworks_info(WorksInfo works_info);






    //获取不同类型的书籍数量
    @Select("SELECT COUNT(work_main_label) FROM works_info GROUP BY work_main_label")
    public Difvolenum selectdifvolenum();


//    根据不同条件查询书籍，sql拼接查询寻
//    @Select("select * from works_info where work_main_label = #{work_main_label} and  work_vice_label = #{work_vice_label} and work_serial_state = #{work_serial_state}")
//    @Select({" <script> " +
//            " select * from works_info where 1=1 " +
//
//            " <when test=' work_main_label =! null '>" +
//            " and work_main_label = #{work_main_label} " +
//            " </when> " +
//            " <if test=\" work_vice_label =!null \"> and work_vice_label = #{work_vice_label}</if> " +
//            " <if test=\" work_serial_state =!null \"> and work_serial_state = #{work_serial_state}</if> " +
//
//            " </script> "})
//@Select({
//        "<script> select * from works_info where 1=1 <if test = ' work_main_label =! null '>and work_main_label = #{work_main_label} </if> </script>"
//
//})


    //通过作品名称查询作品全部信息
    @Select({
            "<script>",
            "select * from works_info where work_name = #{work_name}",
            "<if test='work_main_label != null and work_main_label != \" \" '>",
            "and work_main_label = #{work_main_label} ",
            "</if>",
            "<if test='work_vice_label != null and work_vice_label != \" \" '>",
            "and work_vice_label = #{work_vice_label} ",
            "</if>",
            "<if test='work_serial_state != null and work_serial_state != \" \" '>",
            "and work_serial_state = #{work_serial_state} ",
            "</if>",
            "</script>"
    })
    public WorksInfo selectWorkInfobywork_name(WorksInfo worksInfo);

    //通过作者id查询作品全部信息
    @Select({
            "<script>",
            "select * from works_info where user_id = #{user_id}",
            "<if test='work_main_label != null and work_main_label != \" \" '>",
            "and work_main_label = #{work_main_label} ",
            "</if>",
            "<if test='work_vice_label != null and work_vice_label != \" \" '>",
            "and work_vice_label = #{work_vice_label} ",
            "</if>",
            "<if test='work_serial_state != null and work_serial_state != \" \" '>",
            "and work_serial_state = #{work_serial_state} ",
            "</if>",
            "</script>"
    })
    public List<WorksInfo> selectWorkInfobyuser_id(WorksInfo worksInfo);


    //    查询出作品信息和作者
    @Select("SELECT works_info.* , user_info.`user_name` ,user_info.`head_portrait` FROM works_info ,user_info WHERE works_info.`user_id` = user_info.`user_id` AND works_info.`work_id` = 14 ")
    public List<UserandWorksInfoVo> selectUserandWork(WorksInfo worksInfo);


    //    查询作者创作总字数
    @Select("SELECT sum(work_word_num) from works_info where user_id = #{user_id}")
    public int selectallWorknum(int user_id);

}

