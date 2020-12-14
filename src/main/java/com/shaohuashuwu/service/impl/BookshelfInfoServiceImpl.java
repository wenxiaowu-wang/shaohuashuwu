package com.shaohuashuwu.service.impl;

import com.shaohuashuwu.dao.BookshelfInfoDao;
import com.shaohuashuwu.service.BookshelfInfoService;
import com.shaohuashuwu.utils.StatisticalHelp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 包:com.shaohuashuwu.service.impl
 * 作者:王洪斌
 * 日期:2020/11/29
 * 项目:shaohuashuwu
 * 描述:
 */
@Service("bookshelfInfoService")
public class BookshelfInfoServiceImpl implements BookshelfInfoService {

    @Autowired
    public BookshelfInfoDao bookshelfInfoDao;

    @Override
    public Map<String, List<Map<String, Object>>> getReaderLikeDistributionByWorkIdAndGender(List<String> type, int work_id) {

        Map<String, List<Map<String,Object>>> theResult = new HashMap<String,List<Map<String,Object>>>();
        List<Map<String,Object>> getDao_nan = bookshelfInfoDao.selectReaderLikeDistributionByWorkIdAndGender(work_id, "男");
        List<Map<String,Object>> getDao_nv = bookshelfInfoDao.selectReaderLikeDistributionByWorkIdAndGender(work_id, "女");

        //使用StatisticalHelp工具类进行数据解析以及补充

        StatisticalHelp statisticalHelp = new StatisticalHelp();
        getDao_nan = statisticalHelp.assemblyReadingDataDistribution(type,getDao_nan);
        getDao_nv = statisticalHelp.assemblyReadingDataDistribution(type,getDao_nv);

        System.out.println("男性读者："+getDao_nan.toString());
        System.out.println("女性读者："+getDao_nv.toString());

        theResult = statisticalHelp.sortingReaderNumASC(getDao_nan,getDao_nv);
        System.out.println("男性读者："+theResult.get("男").toString());
        System.out.println("女性读者："+theResult.get("女").toString());
        return theResult;
    }
}
