package com.yunagile.demo.goodsManage.goodsInDetail.business;

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
 * 入库明细
 *
 * @author SJQ
 * 2022-07-28 14:51
 */
@Service
public class GoodsInDetailService {
    /**
     * 入库明细 主
     */
    public DaoNew newData(String createPsnName) {
        DaoNew daoNew = new DaoNew();
        daoNew.setId(Tools.getID());
        String key_pre = "WPRK";
        String seq = key_pre + Tools.nextSequence(key_pre, "000");
        daoNew.put("goodsInNumber", seq);
        daoNew.put("createTime", Calendar.getInstance().getTime());
        daoNew.put("goodsInTime", Calendar.getInstance().getTime());
        daoNew.put("goodsInStatusCode", "0");
        daoNew.put("createPsnName", createPsnName);
        daoNew.put("goodsInStatus", "编辑中");
        return daoNew;
    }

    /**
     * 入库明细中数量的控制
     */
    @Transactional
    public void goodsInNum(String rowid) {
        if (StringUtils.isEmpty(rowid)) {
            throw new SysRuntimeException("入库主数据ID为空，请重试！");
        }
        //1、通过主键ID查询从表数据
        List<DaoMap> list = SQlWrapperBuilder.build(Table.build("com.yunagile.demo", "goodsInDetail"))
                .getSelectTemplate().setRelations("*").setWhere("fgoodsInId = %s", rowid).findPart();
        if (list == null && list.size() == 0) {
            throw new SysRuntimeException("入库物品信息数据为空，请检查！");
        }

        /*
         * 2、通过查询出来的从表记录关联更新物品信息表数量
         * update 物品表 set 物品表.数量 =  物品表.数量 + 明细列表的数量  where 物品表.物品编码 = 明细表.物品编码
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
            Integer inCount = list.get(i).getInt("goodsInCount");
            if (inCount <= 0) {
                throw new SysRuntimeException("入库数量不可为0，请检查！");
            }
            if (inCount instanceof Integer == false) {
                throw new SysRuntimeException("入库数量不可为字符，请检查！");
            }
            dao.put("goodsCount", inCount + dao.getInt("goodsCount"));
            updateTemplate.update(dao);
        }
        //3、修改主表状态为已入库
        DaoMap daoMap = new DaoMap();
        daoMap.setId(rowid);
        daoMap.put("goodsInStatus", "已入库");
        daoMap.put("goodsInStatusCode", "1");
        SQlWrapperBuilder.build(Table.build("com.yunagile.demo", "goodsIn")).getUpdateTemplate().update(daoMap);
    }
}
