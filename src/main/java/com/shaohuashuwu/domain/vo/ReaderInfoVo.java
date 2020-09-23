package com.shaohuashuwu.domain.vo;

import java.sql.Timestamp;

/**
 * 包:com.shaohuashuwu.domain.vo
 * 作者:王洪斌
 * 日期:2020/9/17
 * 项目:shaohuashuwu
 * 描述:用于业务层的数据交流以及表现层的数据展示
 *      作品数据统计，分析读者群体的男女比例以及年龄阶段
 *      待定
 */
public class ReaderInfoVo {

    private Integer user_id;
    private String user_name;           //用户昵称
    private String gender;              //用户性别
    private Timestamp birthday;         //用户生日

}
