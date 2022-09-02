var mainData, gridData, rowid, dialog;
var dailoagLoad = function (obj) {

}

$(document).ready(function () {
    dialog = new as.dialog({
        width: 500, height: 400, title: '物品信息',
        bottomHTML: '<div class="dialog-button-container"><div class="dialog-button-menu">' +
            '<div class="dialog-button-item btnInsure">确定</div>' +
            '<div class="dialog-button-division"></div>' +
            '<div class="dialog-button-item btnCancel">取消</div>' +
            '</div></div>'
    });

    rowid = as.getUrlParam("id");
    var dm = as.obtainData('main');
    dm.config({
        newParam: {
            createPsnName: "name1"
        }
    }).create();
    mainData = dm.data;
    if (!rowid) {
        dm.trigger("new");
    } else {
        dm.trigger("load");
    }

    var dm1 = as.obtainData('grid');
    dm1.config({
        fields: {
            operate: {
                formatter: function (value, row, index) {
                    return '<span><a class="btn" onclick="toolbar.delRow(\'' + row.id + '\')">删除</a></span>'
                }
            }
        }

    }).create().trigger("load");
    gridData = dm1.data;
    dm1.toolbar.setAction({
        'add': function () {
            dialog.open('/goodsManage/goodsOut/goodsOutDetail.html', dialogCallBack, {}, '',)
        }
    });
});

var dialogCallBack = function (backData) {
    // Tips.tips('请先查看物品数量，再领用物品，避免物品数量不够！', "warning");

    var arr = [];
    for (var i = 0; i < backData.length; i++) {
        var datas = gridData.getDatas("@.goodsCode==='" + backData[i]['goodsCode'] + "'");
        if (datas.length > 0) {
            Tips.tips("选择的物品编码" + backData[i]['goodsCode'] + "重复！", "warning");
            continue;
        } else {
            arr.push(backData[i])
        }
    }
    gridData.newData(function (rowid, flag, msg) {
        if ($.isArray(rowid)) {
            for (var i = 0; i < arr.length; i++) {
                gridData.setValue("goodsCode", arr[i]['goodsCode'], rowid[i]);
                gridData.setValue("goodsName", arr[i]['goodsName'], rowid[i]);
                gridData.setValue("module", arr[i]['module'], rowid[i]);
                // gridData.setValue("goodsOutCount", 0, rowid[i]);
            }
        } else {
            gridData.setValue("goodsCode", arr[0]['goodsCode'], rowid);
            gridData.setValue("goodsName", arr[0]['goodsName'], rowid);
            gridData.setValue("module", arr[0]['module'], rowid);
            // gridData.setValue("goodsOutCount", 0, rowid);
            // gridData.getValue()
        }
    }, arr.length)
};

var toolbar = {
    submit: function () {
        //必须添加明细，出库数量必须正确
        var rows = gridData.getCount();
        if (rows <= 0) {
            Tips.tips('请添加领用明细', "warning");
            return;
        }
        var ids = gridData.getRowIds();
        for (var i = 0; i < ids.length; i++) {
            var data = gridData.getData(ids[i]);
            //入库的数量
            var num = data.goodsOutCount;
            if (num <= 0) {
                Tips.tips('领用数量不能小于等于0和字符！', "warning");
                return;
            }
            if (num == "" || num == null || num == undefined) {
                Tips.tips('领用数量不能为空和字符！', "warning");
                return;
            }
        }


        mainData.save(function (flag, mes) {
            if (flag === true) {
                as.ajax("/demo/goodsOutDetail/goodsOutNum", {rowid: mainData.getCurrentID()}, function (data, flag, msg) {
                    if (flag == "SUCCESS") {
                        Tips.tips('领用成功', true);
                        document.getElementById("sub").style.cursor = "not-allowed";
                        document.getElementById("sub").onclick = null;
                        document.getElementById("sub").style.color = '#ccc';
                        location.assign('http://localhost:8082/goodsManage/goodsOut/goodsOutList.html')
                    } else {
                        alert(msg || "领用失败");
                    }
                });
            } else {
                alert(mes || "领用失败");
            }
        });
    },
    delRow: function (rowId) {
        as.confirm('确定删除该信息吗？', function () {
            gridData.deleteRemote(rowId);
            Tips.tips('删除信息成功', true)
        })
    }
};



