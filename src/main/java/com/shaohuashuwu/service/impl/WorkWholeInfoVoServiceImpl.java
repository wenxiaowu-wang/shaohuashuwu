package com.shaohuashuwu.service.impl;

import com.shaohuashuwu.dao.ChapterPostInfoDao;
import com.shaohuashuwu.dao.UserInterestInfoDao;
import com.shaohuashuwu.dao.WorkWholeInfoVoDao;
import com.shaohuashuwu.dao.WorksInfoDao;
import com.shaohuashuwu.domain.UserinterestInfo;
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
    @Autowired
    private UserInterestInfoDao userInterestInfoDao;
    @Autowired
    private WorksInfoDao worksInfoDao;


    WorkWholeInfoVo workWholeInfoVo = null;
    List<WorkWholeInfoVo> workWholeInfoVoList = null;
    List<WorksInfo>  worksInfoList = new ArrayList<WorksInfo>() ;
    private UserinterestInfo userinterestInfo = null;

    /**
     * 获取主页不同分类的作品信息
     * 功能点：获取主页信息
     * @param differentStateWork
     * @return
     */
    @Override
    public List<WorkWholeInfoVo> getdifferentStateWork(Integer differentStateWork) {
        int work_serial_state;

        //如果获取数据为1时为完结的作品信息，为2时是连载作品信息，为3时是最新作品信息
        if(differentStateWork == 1){
            work_serial_state = 2;
             workWholeInfoVoList = workWholeInfoVoDao.selectWorkWholeInfoVoDaoBywork_state(work_serial_state);
        }
        else if(differentStateWork == 2){
            work_serial_state = 1;
            workWholeInfoVoList = workWholeInfoVoDao.selectWorkWholeInfoVoDaoBywork_state(work_serial_state);
        }
        else if(differentStateWork == 3){
            work_serial_state = 1;
            //获取新建作品，并且按照作品id排序，并且判断章节是否为空，不为空时输出作品信息
            workWholeInfoVoList = workWholeInfoVoDao.selectNewWorkWholeInfoVoDao(work_serial_state);
        }
        return workWholeInfoVoList;
    }


    /**
     * 通过搜索信息获取作品全部信息
     * 功能点：关键字搜索搜索内容，
     * @param needworksInfo
     * @return
     */
    @Override
    public List<WorkWholeInfoVo> getWorkWholeInfoBySelectinput(WorksInfo needworksInfo) throws NullPointerException{
        //向返回集合中添加数据
        workWholeInfoVoList = new ArrayList<WorkWholeInfoVo>();


//        1.先获取书籍名称相同的书籍，并判断作品是否发布章节，如果发布章节进行查询，不存在就查询不到
        workWholeInfoVo = workWholeInfoVoDao.selectWorkWholeInfoVobywork_name(needworksInfo);
        if (workWholeInfoVo == null){
            System.out.println("不存在该书籍，或该书籍未发布章节");
        }
        else {
            workWholeInfoVoList.add(workWholeInfoVo);
        }

//        2.再获取作者名称和提供类型相同的数据
        String user_name = needworksInfo.getWork_name();
        try {
            worksInfoList = worksInfoService.getwork_nameByuser_name(user_name);
        }catch (Exception e){
            System.out.println("异常"+e);
        }
        //获取的作者作品未空，和不为空
        if (worksInfoList == null) {
            System.out.println("没有该作者，或该作者无作品");
        }
        else {
            for(int i = 0; i < worksInfoList.size() ; i++){
                needworksInfo.setWork_name(worksInfoList.get(i).getWork_name());
                workWholeInfoVo = workWholeInfoVoDao.selectWorkWholeInfoVobywork_name(needworksInfo);
                if (workWholeInfoVo == null){
                    System.out.println("该书籍未发布章节");
                }
                else {
                    int isquels = 0;
                    for (int j = 0;j<workWholeInfoVoList.size();j++){
                        if(workWholeInfoVoList.get(j).getWork_name().equals(workWholeInfoVo.getWork_name())){
                            isquels = 1;
                            break;
                        }
                        else {
                            continue;
                        }
                    }
                    if (isquels == 0){
                        workWholeInfoVoList.add(workWholeInfoVo);
                    }
                    else {
                        System.out.println("重复数据");
                    }

                }
            }
        }

        //3.最后模糊查询获取数据,结果长度大于30就不在显示，少于30就获取
        if (workWholeInfoVoList.size() >= 30) {
            return workWholeInfoVoList.subList(0,30);
        }
        else {
            try {
                needworksInfo.setWork_name(user_name);
                worksInfoList = worksInfoService.getVaguework_nameBywork_name(needworksInfo);

            }catch (Exception e){
                System.out.println("异常"+e);
            }
//获取的作者作品未空，和不为空
            if (worksInfoList == null) {
                System.out.println("没有模糊查询到作品");
            }
            else {
                for(int i = 0; i < worksInfoList.size() ; i++){
                    needworksInfo.setWork_name(worksInfoList.get(i).getWork_name());
                    workWholeInfoVo = workWholeInfoVoDao.selectWorkWholeInfoVobywork_name(needworksInfo);
                    if (workWholeInfoVo == null){
                        System.out.println("该书籍未发布章节");
                    }
                    else {
                        int isquels = 0;
                        for (int j = 0;j<workWholeInfoVoList.size();j++){
                            if(workWholeInfoVoList.get(j).getWork_name().equals(workWholeInfoVo.getWork_name())){
                                isquels = 1;
                                break;
                            }
                            else {
                                continue;
                            }
                        }
                        if (isquels == 0){
                            workWholeInfoVoList.add(workWholeInfoVo);
                        }
                        else {
                            System.out.println("重复数据");
                        }
                    }
                }
            }
            if (workWholeInfoVoList.size() == 0) {
                return null;
            } else if (workWholeInfoVoList.size() != 0 && workWholeInfoVoList.size() <= 30) {
                return workWholeInfoVoList;
            } else  {
                return workWholeInfoVoList.subList(0, 30);
            }
        }
    }

    /**
     * 通过作者id信息获取作品全部信息
     * 功能点：作品信息内容
     * @param author_id
     * @return
     */
    @Override
    public List<WorkWholeInfoVo> getworkWholeInfoVoByauthor_id(int author_id) {

        //通过用户id
        worksInfoList = worksInfoService.getWorksInfoByUser_id(author_id);
        for(int i = 0; i < worksInfoList.size() ; i++){
            workWholeInfoVo = workWholeInfoVoDao.selectWorkWholeInfoVobywork_name(worksInfoList.get(i));
            if (workWholeInfoVo == null){
                System.out.println("该书籍未发布章节");
            }
            else {
                    workWholeInfoVoList.add(workWholeInfoVo);
            }
        }
        return workWholeInfoVoList;
    }

    /**
     * 通过用户id信息获取作品全部信息
     * 功能点：个性推荐作品
     * @param user_id
     * @return
     */
    @Override
    public List<WorkWholeInfoVo> getworkWholeInfoVoByuser_id(int user_id) throws NullPointerException{
        workWholeInfoVoList = new ArrayList<WorkWholeInfoVo>();
        //获取用户管兴趣的标签
        userinterestInfo = userInterestInfoDao.selectUserinterestInfoByUser_id(user_id);
        System.out.println("兴趣标签"+userinterestInfo);
        worksInfoList = worksInfoDao.selectworkInfoBywork_main_label1(userinterestInfo);
        worksInfoList.addAll(worksInfoDao.selectworkInfoBywork_main_label2(userinterestInfo));
        System.out.println("作品信息"+worksInfoList);

        //获取的作者作品未空，和不为空
        if (worksInfoList == null) {
            System.out.println("无作品");
        }
        else {
            for(int i = 0; i < worksInfoList.size() ; i++){
                workWholeInfoVo = workWholeInfoVoDao.selectWorkWholeInfoVobywork_name(worksInfoList.get(i));
                System.out.println("作品详细信息---"+workWholeInfoVo);
                if(workWholeInfoVo == null){

                }else {
                    workWholeInfoVoList.add(workWholeInfoVo);
                }
                System.out.println("作品详细信息+++++"+workWholeInfoVoList);
            }
        }

        System.out.println("最终信息=============="+workWholeInfoVoList);
        return workWholeInfoVoList;
    }


}
