function DataPie(prefilteredData,attribute)
{
    tmp = []
    let bo={}
    for (var i in prefilteredData) {
        let item=prefilteredData[i]
        if (bo[item[attribute]]==null){
            tmp.push({"value":item[attribute+'_value'],"name":item[attribute]});
            bo[item[attribute]]=1
        }       
    }
  
    return tmp;
}
function OptionPie(prefilteredData,attribute)
{
    var option = {
        title: {
            text: attribute,
            // subtext: '姓氏玫瑰图',
            left: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: '{b} : {c}分'
        },
        legend: {
            top: 'top',
            show: false
        },
        toolbox: {
            show: true,
            feature: {
                mark: { show: true },
                dataView: { show: true, readOnly: false },
                restore: { show: true },
                saveAsImage: { show: true }
            }
        },
        series: [
            {
                name: attribute,
                type: 'pie',
                radius: [10, 100],
                center: ['50%', '50%'],
                roseType: 'radius',
                label: {
                    show: true
                },
                emphasis: {
                    label: {
                        show: true
                    }
                },
                itemStyle: {
                    borderRadius: 10
                },
                data: DataPie(prefilteredData,attribute)
            }
        ]
    };
    return option;
}
function DrawPie(prefilteredData,attribute)
{
    console.log(attribute)
    let pie=document.getElementById('pie'+String(++pieCount))
    pieChart = echarts.init(pie);
    pieChart.setOption(OptionPie(prefilteredData,attribute));                     
    pie.style.visibility = "visible";
}

