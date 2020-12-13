package com.shaohuashuwu.service;

import com.shaohuashuwu.domain.vo.AdminSelectInfoVo;
import com.shaohuashuwu.domain.vo.ReportWholeInfoVo;
import java.util.List;

public interface ReportWholeInfoVoService {

    //分页获取举报信息
    //功能点：获取举报信息列表
    public List<ReportWholeInfoVo> getreportWholeInfoVoList(AdminSelectInfoVo adminSelectInfoVo);

    //获取书包信息数量
    //功能点：获取举报信息数量
    public int getreportWholeInfoVoTotal();

    //依据举报id获取举报信息
    //功能点：举报详情获取举报信息
    public ReportWholeInfoVo getreportWholeInfoVoByreport_id(int report_id);

    //分页获取处理结果信息
    //功能点：更改处理结果的举报信息列表
    public List<ReportWholeInfoVo> gethandleResultList(AdminSelectInfoVo adminSelectInfoVo);

    //获取处理结果数量
    //功能点：更改处理结果的举报信息数量
    public int gethandleResultListTotal(AdminSelectInfoVo adminSelectInfoVo);
}
