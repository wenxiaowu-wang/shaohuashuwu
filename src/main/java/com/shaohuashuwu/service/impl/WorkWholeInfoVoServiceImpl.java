package com.shaohuashuwu.service.impl;

import com.shaohuashuwu.dao.ChapterPostInfoDao;
import com.shaohuashuwu.dao.WorkWholeInfoVoDao;
import com.shaohuashuwu.domain.WorksInfo;
import com.shaohuashuwu.domain.vo.WorkWholeInfoVo;
import com.shaohuashuwu.service.UserInfoService;
import com.shaohuashuwu.service.WorkWholeInfoVoService;
import com.shaohuashuwu.service.WorksInfoService;
import com.sun.corba.se.spi.orbutil.threadpool.Work;
import org.apache.commons.collections.list.AbstractLinkedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.java2d.opengl.WGLSurfaceData;

import java.util.ArrayList;
import java.util.List;

@Service("workWholeInfoVoService")
public class WorkWholeInfoVoServiceImpl implements WorkWholeInfoVoService {

    @Autowired
    private WorkWholeInfoVoDao workWholeInfoVoDao;
    @Autowired
    private ChapterPostInfoDao chapterPostInfoDao;


    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private WorksInfoService worksInfoService;


    @Autowired
    private ChapterPostInfoServiceImpl chapterPostInfoService;

    private WorkWholeInfoVo workWholeInfoVo = null;
    private List<WorkWholeInfoVo> workWholeInfoVoList = null;
    private List<WorkWholeInfoVo> workWholeInfoVos = null ;
    private WorksInfo worksInfo = null;
    List<WorksInfo>  worksInfoList = new ArrayList<WorksInfo>() ;




    /**
     * 获取主页不同分类的作品信息
     * @param differentStateWork
     * @return
     */
    @Override
    public List<WorkWholeInfoVo> getdifferentStateWork(Integer differentStateWork) {
        int work_serial_state;

        System.out.println("信息");
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
        System.out.println("信息++"+workWholeInfoVoList);
        return workWholeInfoVoList;
    }


    /**
     * 通过搜索信息获取作品全部信息
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
            System.out.println("作品名称---------"+worksInfoList);

        }catch (Exception e){
            System.out.println("异常"+e);
        }
        //获取的作者作品未空，和不为空
        if (worksInfoList == null) {
            System.out.println("没有该作者，或该作者无作品");
        }
        else {
            System.out.println("获取到的作品"+worksInfoList);

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
            System.out.println("到这了1111---");
            return workWholeInfoVoList.subList(0,30);
        }
        else {
            try {
                needworksInfo.setWork_name(user_name);
                System.out.println("----"+worksInfoList);
                worksInfoList = worksInfoService.getVaguework_nameBywork_name(needworksInfo);
                System.out.println("作品名称---"+worksInfoList);

            }catch (Exception e){
                System.out.println("异常"+e);
            }
//获取的作者作品未空，和不为空
            if (worksInfoList == null) {
                System.out.println("没有模糊查询到作品");
            }
            else {
                System.out.println("获取到的作品----"+worksInfoList);

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
                System.out.println("到这了2222---");
                System.out.println("最终结果："+workWholeInfoVoList);
                return null;
            } else if (workWholeInfoVoList.size() != 0 && workWholeInfoVoList.size() <= 30) {
                System.out.println("到这了3333---");
                System.out.println("最终结果："+workWholeInfoVoList);
                return workWholeInfoVoList;
            } else  {
                System.out.println("到这了4444---");
                System.out.println("最终结果："+workWholeInfoVoList);
                return workWholeInfoVoList.subList(0, 30);
            }
        }


    }

    /**
     * 将WorkWholeInfoVoto加入到WorkWholeInfoVoto的List集合中
     * @param workWholeInfoVo
     * @return
     */
    public List<WorkWholeInfoVo> addWorkWholeInfoVotoWorkWholeInfoVoList(WorkWholeInfoVo workWholeInfoVo){
        if (workWholeInfoVo == null){
                System.out.println("不存在该书籍，或该书籍未发布章节");
            }
            else {
                workWholeInfoVoList.add(workWholeInfoVo);
            }
            return workWholeInfoVoList;
    }

    /**
     * 通过作品名称查询作品全部信息，并且作品状态不能为3
     * @param worksInfo
     * @return
     */
    @Override
    public List<WorkWholeInfoVo> getWorkWholeInfoVobywork_name(WorksInfo worksInfo) {
        return workWholeInfoVoDao.selectWorkWholeInfoVobyuser_name(worksInfo);
    }
}
