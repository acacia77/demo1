package com.yunagile.demo.goodsManage.goodsInDetail.actions;

import com.yunagile.demo.goodsManage.goodsInDetail.business.GoodsInDetailService;
import com.yunagile.system.data.ResultRender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**入库详细
 * @author SJQ
 * @create 2022-07-28 14:50
 */
@Controller
public class GoodsInDetailController {
    @Autowired
    private GoodsInDetailService inDetailService;

    /**
     * 入库主新增
     * @param render
     * @param createPsnName
     * @return
     */
    @RequestMapping("/demo/goodsInDetail/goodsInDetailNew")
    public ResultRender newGoodsInDetail (ResultRender render,String createPsnName){
        render.getBeanRender().setData(inDetailService.newData(createPsnName));
        return render;
    }

    /**
     * 入库数量
     * @param render
     * @param rowid
     * @return
     */
    @RequestMapping("/demo/goodsInDetail/goodsInNum")
    public ResultRender newGoodsInNum (ResultRender render, String rowid){
        inDetailService.goodsInNum(rowid);
        return render;
    }
}
