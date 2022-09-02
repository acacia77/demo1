package com.yunagile.demo.goodsManage.baseInfo.actions;

import com.yunagile.demo.goodsManage.baseInfo.business.GoodsInfoService;
import com.yunagile.system.data.ResultRender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/** 物品信息
 * @author SJQ
 * @create 2022-07-26 17:13
 */
@Controller
public class GoodsInfoController {
    @Autowired
    private GoodsInfoService service;

    /**
     * 物品信息新增
     * @param result
     * @return
     */
    @RequestMapping("/demo/baseInfo/goodsInfoNew")
    public ResultRender newData ( ResultRender result){
        result.getBeanRender().setData(service.newData());
        return result;
    }
}
