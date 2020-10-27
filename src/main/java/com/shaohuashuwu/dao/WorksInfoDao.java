package com.shaohuashuwu.dao;

import com.shaohuashuwu.domain.Account;
import com.shaohuashuwu.domain.WorksInfo;
import com.shaohuashuwu.domain.vo.Difvolenum;
import com.shaohuashuwu.domain.vo.PageInfo;
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


    //查询全部作品信息
    @Select("select * from works_info")
    public List<WorksInfo> selectAllworks();

    //依据作品id查询作品信息
    @Select("select * from works_info where work_id = #{work_id}")
    public WorksInfo selectworkByid(int work_id);

    //添加作品信息
    @Insert("insert into works_info(work_name,user_id,work_main_label,work_vice_label,work_serial_state,work_introduct,work_other_word,work_word_num,work_tip_num,work_subscribe_num,work_vote_num,work_create_time) " +
            "values (#{work_name},#{user_id},#{work_main_label},#{work_vice_label},#{work_serial_state},#{work_introduct},#{work_other_word},#{work_word_num},#{work_tip_num},#{work_subscribe_num},#{work_vote_num},#{work_create_time} )")
    public int insertworks_info(WorksInfo works_info);

    //通过作品名称查询作品数量
    @Select("select count(*) from works_info where work_name = #{work_name}" )
    public int selectWorkbywork_name(String work_name);

    //通过作品id修改作品状态
    @Update(" update works_info set work_serial_state = #{work_serial_state} where work_id = #{work_id} ")
    public int updateWorkSerialStateByid(WorksInfo worksInfo);


    //获取不同类型的书籍数量
    @Select("SELECT COUNT(work_main_label) FROM works_info GROUP BY work_main_label")
    public Difvolenum selectdifvolenum();



    //获取玄幻类型的书籍数量
    @Select("SELECT COUNT(work_main_label) FROM works_info where work_main_label = '玄幻' ")
    public int selectxuanhuannum();
    //获取玄幻类型的书籍数量
    @Select("SELECT COUNT(work_main_label) FROM works_info where work_main_label = '奇幻' ")
    public int selectqihuannum();
    //获取玄幻类型的书籍数量
    @Select("SELECT COUNT(work_main_label) FROM works_info where work_main_label = '武侠' ")
    public int selectwuxianum();
    //获取玄幻类型的书籍数量
    @Select("SELECT COUNT(work_main_label) FROM works_info where work_main_label = '仙侠' ")
    public int selectxianxianum();
    //获取玄幻类型的书籍数量
    @Select("SELECT COUNT(work_main_label) FROM works_info where work_main_label = '都市' ")
    public int selectdushinum();
    //获取玄幻类型的书籍数量
    @Select("SELECT COUNT(work_main_label) FROM works_info where work_main_label = '现实' ")
    public int selectxianshinum();
    //获取玄幻类型的书籍数量
    @Select("SELECT COUNT(work_main_label) FROM works_info where work_main_label = '历史' ")
    public int selectlishinum();
    //获取玄幻类型的书籍数量
    @Select("SELECT COUNT(work_main_label) FROM works_info where work_main_label = '军事' ")
    public int selectjunshinum();
    //获取玄幻类型的书籍数量
    @Select("SELECT COUNT(work_main_label) FROM works_info where work_main_label = '游戏' ")
    public int selectyouxinum();
    //获取玄幻类型的书籍数量
    @Select("SELECT COUNT(work_main_label) FROM works_info where work_main_label = '悬疑' ")
    public int selectxuanyinum();
    //获取玄幻类型的书籍数量
    @Select("SELECT COUNT(work_main_label) FROM works_info where work_main_label = '科幻' ")
    public int selectkehuannum();
    //获取玄幻类型的书籍数量
    @Select("SELECT COUNT(work_main_label) FROM works_info where work_main_label = '体育' ")
    public int selecttiyunum();
    //获取玄幻类型的书籍数量
    @Select("SELECT COUNT(work_main_label) FROM works_info where work_main_label = '轻小说' ")
    public int selectqingxiaoshuonum();

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
@Select({
        "<script>" ,
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

}
