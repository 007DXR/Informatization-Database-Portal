function compute(node) {
    var line_data = {}
    function dfs(node) {
        if (toString.apply(node)=="String") return;
        if ('else' in node) {
            // console.log(node['else'])
            for (var j in node['else']) {
                let com = node['else'][j]
                if (line_data[com.国家] == null) {
                    line_data[com.国家] = {}
                }
                // if (line_data[com.国家][com.年份] == null) {
                //     line_data[com.国家][com.年份] = 0
                // }
                line_data[com.国家][com.年份]=com.值
            }
            return
        }

        for (var i in node.children) {
            dfs(node.children[i])
        }
    }
    dfs(node)

    redata=[]
    for (var i in countryList){
        var country=countryList[i]
        redata.push({
            name: country,
            type: 'bar',           
            data: [line_data[country][2019],line_data[country][2020],line_data[country][2021]]
        }) 
    }
return redata

}
lineChart = echarts.init(document.getElementById('line'));

var lineOption = {

    "color": [
        "#c23531",
        "#2f4554",
        "#749f83",
        "#ca8622",
        "#c4ccd3",
        "#f05b72",

    ],
    "series": compute(filterData),
    "legend": [
        {
            "show": true,
            "padding": 5,
            "itemGap": 10,
            "itemWidth": 25,
            "itemHeight": 14,
            "orient": 'vertical',
            "top":'middle',
            "right": 10
        }
    ],
    "tooltip": {
        "show": true,
        "trigger": "axis",
        "triggerOn": "mousemove|click",
        "axisPointer": {
            "type": "line"
        },

    },
    toolbox: {
        show: true,
        feature: {
            mark: { show: true },
            dataView: { show: true, readOnly: false },
            magicType: { show: true, type: ['line', 'bar', 'stack', 'tiled'] },
            restore: { show: true },
            saveAsImage: { show: true }
        }
    },
    "xAxis": [
        {
            "name": "年份",
            "type": "category",

            "data":yearList
        }
    ],
    "yAxis": [
        {
            "name": "指标得分",
            "type": "value",

        }
    ],
    "title": [
        {
            "text": "评价指标体系",
            "padding": 5,
            "itemGap": 10
        }
    ],
    "dataZoom": [
        {
            "show": false,
            "type": "slider",
            "realtime": true,
            "start": 0,
            "end": 100,
            "orient": "horizontal",
            "zoomLock": false,
            "filterMode": "filter"
        },
    ]
}
lineChart.setOption(lineOption)