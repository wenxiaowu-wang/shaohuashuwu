package com.shaohuashuwu.service.impl;

import com.shaohuashuwu.dao.WorkslabelInfoDao;
import com.shaohuashuwu.domain.WorkslabelInfo;
import com.shaohuashuwu.service.WorkslabelInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("workslabelInfoService")
public class WorkslabelInfoServiceImpl implements WorkslabelInfoService{

    @Autowired
    private WorkslabelInfoDao workslabelInfoDao;

    /**
     * 依据作品id查询作者自定义标签
     * 功能点：修改作品信息
     * @param work_id
     * @return
     */
    @Override
    public List<WorkslabelInfo> getWorkslabelInfoByWork_id(int work_id) {
        return workslabelInfoDao.selectWorkslabelInfoByWork_id(work_id);
    }


    /**
     * 依据作品id修改作者自定义标签
     * 功能点：修改作品信息
     * @param workslabelInfoList
     * @return
     */
    @Override
    public int updateWorkslabelInfoByWork_id(List<WorkslabelInfo> workslabelInfoList) {
        int deleteResult = 0;
        int addResult = 0;
        if(workslabelInfoList !=null){
            deleteResult = workslabelInfoDao.delectWorkslabelInfoByWork_id(workslabelInfoList.get(0).getWork_id());

            for( int i =0;i<workslabelInfoList.size();i++){
                addResult = workslabelInfoDao.insertWorkslabelInfoByWork_id(workslabelInfoList.get(i));
            }

        }
        else {
            System.out.println("获取的作品标签为空");
        }


        return 1;
    }
}
