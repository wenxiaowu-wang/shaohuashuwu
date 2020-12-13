package com.shaohuashuwu.service.impl;

import com.shaohuashuwu.dao.ReportWholeInfoVoDao;
import com.shaohuashuwu.domain.vo.AdminSelectInfoVo;
import com.shaohuashuwu.domain.vo.ReportWholeInfoVo;
import com.shaohuashuwu.service.ReportWholeInfoVoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("reportWholeInfoVoService")
public class ReportWholeInfoVoServiceImpl implements ReportWholeInfoVoService {

    @Autowired
    private ReportWholeInfoVoDao reportWholeInfoVoDao;

    List<ReportWholeInfoVo> reportWholeInfoVoList ;



    /**
     * 分页获取举报信息
     * 功能点：获取举报信息列表
     * @param adminSelectInfoVo
     * @return
     */
    @Override
    public List<ReportWholeInfoVo> getreportWholeInfoVoList(AdminSelectInfoVo adminSelectInfoVo) {
        //获取作品当前页，和每页数量
        int works_page = adminSelectInfoVo.getWorks_page();
        int works_page_num = adminSelectInfoVo.getWorks_page_num();
        //页面开始作品，页面结束作品
        int start = (works_page - 1) * works_page_num;
        int end = start + works_page_num;
        reportWholeInfoVoList = reportWholeInfoVoDao.selectReportWholeInfoVoList(adminSelectInfoVo);
        if (end > reportWholeInfoVoList.size()) {
            end = reportWholeInfoVoList.size();
        }
        return reportWholeInfoVoList.subList(start, end);
    }



    /**
     * 获取举报信息数量
     * 功能点：获取举报信息数量
     * @return
     */
    @Override
    public int getreportWholeInfoVoTotal() {
        return reportWholeInfoVoDao.selectReportWholeInfoVoTotal();
    }

    /**
     * 依据举报id获取举报信息
     * 功能点：举报详情获取举报信息
     * @return
     */
    @Override
    public ReportWholeInfoVo getreportWholeInfoVoByreport_id(int report_id) {
        return reportWholeInfoVoDao.selectreportWholeInfoVoByreport_id(report_id);
    }

    /**
     * 分页获取处理结果信息
     * 功能点：更改处理结果的举报信息列表
     * @param adminSelectInfoVo
     * @return
     */
    @Override
    public List<ReportWholeInfoVo> gethandleResultList(AdminSelectInfoVo adminSelectInfoVo) {

        //获取作品当前页，和每页数量
        int works_page = adminSelectInfoVo.getWorks_page();
        int works_page_num = adminSelectInfoVo.getWorks_page_num();
        //页面开始作品，页面结束作品
        int start = (works_page - 1) * works_page_num;
        int end = start + works_page_num;
        reportWholeInfoVoList = reportWholeInfoVoDao.selecthandleResultList(adminSelectInfoVo);
        if (end > reportWholeInfoVoList.size()) {
            end = reportWholeInfoVoList.size();
        }
        System.out.println("输出信息开始"+start);
        System.out.println("输出信息结束");
        return reportWholeInfoVoList.subList(start, end);

    }

    /**
     * 获取处理结果数量
     * 功能点：更改处理结果的举报信息数量
     * @return
     */
    @Override
    public int gethandleResultListTotal(AdminSelectInfoVo adminSelectInfoVo) {
        return reportWholeInfoVoDao.selecthandleResultListTotal(adminSelectInfoVo);
    }
}
