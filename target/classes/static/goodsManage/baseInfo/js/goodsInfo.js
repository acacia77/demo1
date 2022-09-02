var mainData;
$(document).ready(function () {
    var dm = as.obtainData('mainData');
    mainData = dm.config({
        fields: {
            module: {
                editor: {
                    type: 'combobox',
                    options: {
                        valueField: 'name',
                        textField: 'name',
                        data: [{
                            code: 'tai',
                            name: '台'
                        }, {
                            code: 'ge',
                            name: '个'
                        }, {
                            code: 'tiao',
                            name: '条'
                        }, {
                            code: 'zhi',
                            name: '支'
                        }]
                    }
                }
            }
        }
    }).create().trigger('load').data
});

