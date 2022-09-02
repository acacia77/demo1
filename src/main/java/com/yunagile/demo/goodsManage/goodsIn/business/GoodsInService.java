package com.yunagile.demo.goodsManage.goodsIn.business;

import com.yunagile.dal.DaoNew;
import com.yunagile.system.utils.Tools;
import org.springframework.stereotype.Service;


/** 入库单
 * @author SJQ
 * @create 2022-07-27 16:42
 */
@Service
public class GoodsInService {
    /**
     * 新增入库单
     * @return
     */
    public DaoNew newData (){
        DaoNew daoNew = new DaoNew();
        daoNew.setId(Tools.getID());
        String key_pre="WPRK";
        String seq=key_pre+Tools.nextSequence(key_pre,key_pre+"000");
        daoNew.put("goodsInNumber",seq);
        daoNew.put("goodsInStatusCode","0");
        daoNew.put("goodsInStatus","编辑中");
        return daoNew;

    }

}
