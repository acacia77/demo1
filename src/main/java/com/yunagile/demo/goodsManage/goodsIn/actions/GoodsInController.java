package com.yunagile.demo.goodsManage.goodsIn.actions;

import com.yunagile.demo.goodsManage.goodsIn.business.GoodsInService;
import com.yunagile.system.data.ResultRender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**入库单
 * @author SJQ
 * @create 2022-07-27 16:41
 */
@Controller
public class GoodsInController {
    @Autowired
    private GoodsInService giservice;

    /**
     * 入库新增
     * @param render
     * @return
     */
    @RequestMapping("/demo/goodsIn/goodsInNew")
    public ResultRender newGoodsIn (ResultRender render){
        render.getBeanRender().setData(giservice.newData());
        return render;
    }




}
