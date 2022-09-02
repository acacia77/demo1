var mainData, gridData;
$(document).ready(function () {
})
var dailoagLoad=function (obj) {
    // console.log(obj.id)
    if (obj.id) {
        var dm1 = as.obtainData('grid');
        dm1.config({
        }).create();
        gridData=dm1.data;
        var dm = as.obtainData('main');
        dm.config({
            rowid : obj.id
        }).create().trigger('load');
        mainData=dm.data;
    }
}