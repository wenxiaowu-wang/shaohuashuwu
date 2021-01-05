package com.shaohuashuwu.dao;

import com.shaohuashuwu.domain.vo.AuthorInfoVo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorInfoVoDao {

    //依据作类型查询作品数量
    //功能点：作者端顶部用户信息
    @Select(" SELECT u1.user_id user_id,u1.user_name user_name,u1.head_portrait head_portrait,u1.ticket_num ticket_num " +
            " ,(SELECT SUM(w1.work_word_num) attention_num FROM works_info w1 WHERE w1.user_id = #{user_id}) allwork_word_num  " +
            " ,(SELECT COUNT(*)  allwork_word_num FROM attention_info a1 WHERE a1.author_id = #{user_id}) attention_num " +
            " FROM user_info u1 WHERE u1.user_id = #{user_id} ")
    public AuthorInfoVo selectAuthorInfoVoByuser_id(int user_id);

}
