package com.yunagile.demo.goodsManage.goodsOutDeatil.actions;

import com.yunagile.demo.goodsManage.goodsOutDeatil.business.GoodsOutDetailService;
import com.yunagile.system.data.ResultRender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**领用明细
 * @author SJQ
 * @create 2022-08-02 10:23
 */
@Controller
public class GoodsOutDetailController {

    @Autowired
    private GoodsOutDetailService outDetailService;

    /**
     * 领用明细主
     * @param render
     * @param createPsnName
     * @return
     */
    @RequestMapping("/demo/goodsOutDetail/goodsOutDetailNew")
    public ResultRender newGoodsOutDetail (ResultRender render, String createPsnName){
        render.getBeanRender().setData(outDetailService.newMainData(createPsnName));
        return render;
    }

    /**
     * 领用数量
     * @param render
     * @param rowid
     * @return
     */
    @RequestMapping("/demo/goodsOutDetail/goodsOutNum")
    public ResultRender newGoodsOutNum (ResultRender render, String rowid){
        outDetailService.goodsOutNum(rowid);
        return render;
    }
}
