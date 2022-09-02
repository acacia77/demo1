package com.yunagile.demo.goodsManage.goodsOutDeatil.business;

import com.yunagile.common.util.StringUtils;
import com.yunagile.dal.DaoNew;
import com.yunagile.dal.object.DaoMap;
import com.yunagile.mybatis.template.SQlWrapperBuilder;
import com.yunagile.mybatis.template.imp.SQLWrapper;
import com.yunagile.mybatis.template.imp.SelectSqlTemplate;
import com.yunagile.mybatis.template.imp.UpdateSqlTemplate;
import com.yunagile.system.SysRuntimeException;
import com.yunagile.system.data.Table;
import com.yunagile.system.utils.Tools;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

/**
 * 领用明细
 * @author SJQ
 * @create 2022-08-02 10:16
 */
@Service
public class GoodsOutDetailService {

    /**
     * 领用明细 主
     * @param createPsnName 领用人
     * @return
     */
    public DaoNew newMainData(String createPsnName) {
        DaoNew daoNew = new DaoNew();
        daoNew.setId(Tools.getID());
        String key_pre = "WPLY";
        String seq = key_pre + Tools.nextSequence(key_pre, "000");
        daoNew.put("goodsOutNumber", seq);
        daoNew.put("createTime", Calendar.getInstance().getTime());
        daoNew.put("goodsOutTime", Calendar.getInstance().getTime());//领用时间
        daoNew.put("goodsOutStatusCode", "0");
        daoNew.put("createPsnName", createPsnName);
        daoNew.put("goodsOutStatus", "编辑中");
        return daoNew;
    }

    /**
     * 领用明细中数量的控制
     *
     * @param rowid
     */
    @Transactional
    public void goodsOutNum(String rowid)  {
        if (StringUtils.isEmpty(rowid)) {
            throw new SysRuntimeException("领用主数据ID为空，请重试！");
        }

        //1、通过主键ID查询从表数据
        List<DaoMap> list = SQlWrapperBuilder.build(Table.build("com.yunagile.demo", "goodsOutDetail"))
                .getSelectTemplate().setRelations("*").setWhere("fgoodsOutId = %s", rowid).findPart();
        if (list == null && list.size() == 0) {
            throw new SysRuntimeException("领用物品信息数据为空，请检查！");
        }

        /**
         * 2、通过查询出来的从表记录关联更新物品信息表数量
         * update 物品表 set 物品表.数量 =  物品表.数量 - 明细列表的数量  where 物品表.物品编码 = 明细表.物品编码
         */
        SQLWrapper wrapper = SQlWrapperBuilder.build(Table.build("com.yunagile.demo", "goodsInfo"));
        SelectSqlTemplate selectTemplate = wrapper.getSelectTemplate().setRelations("*");
        UpdateSqlTemplate updateTemplate = wrapper.getUpdateTemplate();
        DaoMap dao = null;
        List<DaoMap> goodsInfo = null;
        for (int i = 0; i < list.size(); i++) {
            //物品信息表的物品编码
            goodsInfo = selectTemplate.setWhere("fgoodsCode = %s", list.get(i).getString("goodsCode")).findPart();
            if (goodsInfo != null && goodsInfo.size() > 0) {
                dao = goodsInfo.get(0);
            } else {
                continue;
            }
            //物品信息数量
            Integer goodsCount = dao.getInt("goodsCount");
            //领用的数量
            Integer outCount = list.get(i).getInt("goodsOutCount");
            if (goodsCount - outCount < 0) {
                throw new SysRuntimeException("领用数量不够，请查看物品数量后再出库！");
            }
            dao.put("goodsCount", goodsCount - outCount);
            updateTemplate.update(dao);
        }
        //3、修改主表状态为已领用
        DaoMap daoMap = new DaoMap();
        daoMap.setId(rowid);
        daoMap.put("goodsOutStatus", "已领用");
        daoMap.put("goodsOutStatusCode", "1");
        SQlWrapperBuilder.build(Table.build("com.yunagile.demo", "goodsOut")).getUpdateTemplate().update(daoMap);
    }

}
