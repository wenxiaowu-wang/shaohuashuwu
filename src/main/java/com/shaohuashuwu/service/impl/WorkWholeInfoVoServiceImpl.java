package com.shaohuashuwu.service.impl;

import com.shaohuashuwu.dao.ChapterPostInfoDao;

import com.shaohuashuwu.dao.WorkWholeInfoVoDao;
import com.shaohuashuwu.domain.WorksInfo;
import com.shaohuashuwu.domain.vo.WorkWholeInfoVo;
import com.shaohuashuwu.service.UserInfoService;
import com.shaohuashuwu.service.WorkWholeInfoVoService;
import com.shaohuashuwu.service.WorksInfoService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service("workWholeInfoVoService")
public class WorkWholeInfoVoServiceImpl implements WorkWholeInfoVoService {

    @Autowired
    private WorkWholeInfoVoDao workWholeInfoVoDao;


    @Autowired
    private WorksInfoService worksInfoService;


    private WorkWholeInfoVo workWholeInfoVo = null;
    private List<WorkWholeInfoVo> workWholeInfoVoList;
    private List<WorkWholeInfoVo> workWholeInfoVos = null;
    private WorksInfo worksInfo = null;
    List<WorksInfo> worksInfoList = new ArrayList<WorksInfo>();


    /**
     * 将WorkWholeInfoVoto加入到WorkWholeInfoVoto的List集合中
     *
     * @param workWholeInfoVo
     * @return
     */
    public List<WorkWholeInfoVo> addWorkWholeInfoVotoWorkWholeInfoVoList(WorkWholeInfoVo workWholeInfoVo) {
        if (workWholeInfoVo == null) {
            System.out.println("不存在该书籍，或该书籍未发布章节");
        } else {
            workWholeInfoVoList.add(workWholeInfoVo);
        }
        return workWholeInfoVoList;
    }

    @Override
    public List<WorkWholeInfoVo> getdifferentStateWork(Integer differentStateWork) {
        return null;
    }

    @Override
    public List<WorkWholeInfoVo> getWorkWholeInfoBySelectinput(WorksInfo worksInfo) {
        return null;
    }

    /**
     * 通过作品名称查询作品全部信息，并且作品状态不能为3
     *
     * @param worksInfo
     * @return
     */
    @Override
    public List<WorkWholeInfoVo> getWorkWholeInfoVobywork_name(WorksInfo worksInfo) {
        return workWholeInfoVoDao.selectWorkWholeInfoVobyuser_name(worksInfo);
    }

    /**
     * 根据用户id获取书架里的信息
     * @param user_id
     * @return
     */
    @Override
    public List<WorkWholeInfoVo> getWorkWholeInfoByuser_id(int user_id) throws NullPointerException {

        //根据用户id获取关注作品name
        List<WorksInfo> work_nameList = worksInfoService.getBookshelfWorkNameByWorkID(user_id);

        workWholeInfoVoList = new ArrayList<WorkWholeInfoVo>();
        for (int i = 0; i < work_nameList.size(); i++) {

            workWholeInfoVo = workWholeInfoVoDao.selectWorkWholeInfoVobywork_name(work_nameList.get(i));
            if (workWholeInfoVo == null) {
            } else {
                workWholeInfoVoList.add(workWholeInfoVo);
            }
        }
        return workWholeInfoVoList;
    }


    /**
     * 根据用户id获取阅读历史的信息
     * @param user_id
     * @return
     */
    @Override
    public List<WorkWholeInfoVo> getWorkWholeInfoToHistoryByUser_id(int user_id) throws NullPointerException {

        //根据用户id获取阅读历史作品name
        List<WorksInfo> work_nameList = worksInfoService.getReadingHistoryWorkNameByWorkID(user_id);

        workWholeInfoVoList = new ArrayList<WorkWholeInfoVo>();
        for (int i = 0; i < work_nameList.size(); i++) {
            workWholeInfoVo = workWholeInfoVoDao.selectWorkWholeInfoVobywork_name(work_nameList.get(i));
            if (workWholeInfoVo == null) {
            } else {
                workWholeInfoVoList.add(workWholeInfoVo);
            }
        }
        return workWholeInfoVoList;
    }


}
