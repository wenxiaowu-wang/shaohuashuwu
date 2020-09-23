package com.shaohuashuwu.domain.vo;

import java.sql.Timestamp;

/**
 * 包:com.shaohuashuwu.domain.vo
 * 作者:王洪斌
 * 日期:2020/9/17
 * 项目:shaohuashuwu
 * 描述:值对象
 *      用于作品数据统计读者行为的数据处理以及展示
 *      包括读者阅读时间的统计、读者平时选择标签的统计
 */
public class ReaderBehaviorInfoVo {

    //最近阅读时间不可以和标签放在一起
    private Integer reader_id;          //读者ID
    private Timestamp reading_time;     //最近阅读时间
    private String label_name;          //标签名字
    private Integer select_num;         //选择次数
}
