package com.yunagile.demo.goodsManage.baseInfo.business;

import com.yunagile.dal.DaoNew;
import com.yunagile.system.utils.Tools;
import org.springframework.stereotype.Service;

/** 物品信息
 * @author SJQ
 * @create 2022-07-26 17:13
 */
@Service
public class GoodsInfoService {

    /**
     * 物品信息新增
     * @return daoNew
     */
    public DaoNew newData() {
        DaoNew daoNew = new DaoNew();
        daoNew.setId(Tools.getID());
        daoNew.put("goodsCount",0);
        return daoNew;
    }


}
