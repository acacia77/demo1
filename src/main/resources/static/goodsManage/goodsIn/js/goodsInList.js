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
            goodsInStatus: {
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
                    return '<span><a class="btn" onclick="opens(\'' + row.id + '\');">详情</a></span>' +
                        '<span><a class="btn1" onClick="up(\'' + row.id + '\');">编辑</a></span>'
                }
            }
        }
    }).create().trigger('load');
    mainData = dm.data;

});
var toolbar = {
    submit: function () {
        location.assign('http://localhost:8082/goodsManage/goodsIn/goodsInDetail.html')
    }
}
// debugger
var opens = function (id) {
    dialog.open('/goodsManage/goodsIn/goodsInSee.html', null, {id: id}, '入库明细查看',);
}
var up = function (id) {
    var rowIds = mainData.getRowID();
    var data = mainData.getData(rowIds);
    var status = data.goodsOutStatusCode;
    // var status = mainData.getDataStatusCode(rowid);
    console.log(status)


}




