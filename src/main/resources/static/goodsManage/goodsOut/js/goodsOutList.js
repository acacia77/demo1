var dialog, mainData, rowid;
$(document).ready(function () {
    dialog = new as.dialog({
        width: 700, height: 600, title: '物品信息',
        bottomHTML: '<div class="dialog-button-container"><div class="dialog-button-menu">' +
            /*'<div class="dialog-button-item btnInsure">确定</div>' +
            '<div class="dialog-button-division"></div>' +*/
            '<div class="dialog-button-item btnCancel">取消</div>' +
            '</div></div>'
    });
    rowid = as.getUrlParam("id");
    var dm = as.obtainData('mainData');
    dm.config({
        fields: {
            goodsOutStatus: {
                editor: {
                    type: 'combobox',
                    options: {
                        valueField: 'name',
                        textField: 'name',
                        data: [{
                            code: '0',
                            name: '0'
                        }, {
                            code: '1',
                            name: '1'
                        }]
                    }
                }
            },
            opt: {
                formatter: function (value, row, index) {
                    // debugger
                    return '<span><a class="btn" onclick="opens(\''+row.id+'\');">详情</a></span>'
                }
            }
        }
    }).create().trigger('load');
    mainData = dm.data;

});
var toolbar = {
    submit: function () {
        location.assign('http://localhost:8082/goodsManage/goodsOut/goodsOutDetail.html')
    }
}
// debugger
var opens=function (id) {
    dialog.open('/goodsManage/goodsOut/goodsOutSee.html',null,{id:id},'入库明细查看',);
}





