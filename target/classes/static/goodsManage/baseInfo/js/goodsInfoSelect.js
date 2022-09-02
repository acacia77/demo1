var gridData;
$(document).ready(function() {
    var dm=as.obtainData('mainData');
    dm.config({
    }).create().trigger('load');
    gridData = dm.data;
});
var dailogEngin = function () {
    var rowids = gridData.getCheckedIDs();
    if(rowids.length <= 0) {
        Tips.tips("请选择需要入库的物品信息！", "warning");
        return false;
    } else {
        var arr = [];
        for (var i = 0; i< rowids.length; i++) {
            arr.push(gridData.getData(rowids[i]));
        }
        return arr;
    }
};