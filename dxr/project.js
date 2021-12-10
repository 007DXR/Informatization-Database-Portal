ROOT_PATH="/data.json"
const uploadedDataURL =ROOT_PATH
var chartDom = document.getElementById('main');
var myChart = echarts.init(chartDom);
var option;

  function convert(source, target, basePath) {
    for (let key in source) {
      let path = basePath ? basePath + '.' + key : key;
      if (!key.match(/^\$/)) {
        target.children = target.children || [];
        const child = {
          name: path
        };
        target.children.push(child);
        convert(source[key], child, path);
      }
    }
    if (!target.children) {
      target.value = source.$count || 1;
    } else {
      target.children.push({
        name: basePath,
        value: source.$count
      });
    }
  }
//   filterData={}
  function transform(rawData,filterData){
    filterData.children=[]
    filterData.size=0
    filterData.value=0
    if (toString.apply(rawData) == "[object Array]"){
        for (var i =0; i<rawData.length; i+=1){
            newNode={}
            newNode.name=rawData[i].国家
            newNode.value=rawData[i].值
            // newNode.size=1
            filterData.children.push(newNode)
            filterData.size+=1
        filterData.value+=newNode.value
            // transform(rawData[i],newNode)
        }
    }else
    for (var i in rawData){
        newNode={}
        newNode.name=i
        filterData.children.push(newNode)
        transform(rawData[i],newNode)
        filterData.size+=1
        filterData.value+=newNode.value
    }
    filterData.value/=filterData.size
  }
  filterData={}
  transform(rawData[0],filterData)
console.log(filterData)
//   convert(rawData, data, '');
const treemapOption = {
    title: {
      text: 'Disk Usage',
      left: 'center'
    },
    tooltip: {
      formatter: function (info) {
        var value = info.value;
        var treePathInfo = info.treePathInfo;
        var treePath = [];
        for (var i = 1; i < treePathInfo.length; i++) {
          treePath.push(treePathInfo[i].name);
        }
        return [
          '<div class="tooltip-title">' +
            treePath.join('/') +
            '</div>',
          '得分: ' + value 
        ].join('');
      }
    },
    series: [
      {
        name: 'Disk Usage',
        type: 'treemap',
        
        visibleMin: 1,
          data: filterData.children,
          leafDepth: 2,
        label: {
          show: true,
          formatter: '{b}'
        },
        itemStyle: {
          borderColor: '#fff'
        },
        levels:  [
          {
            itemStyle: {
              borderWidth: 0,
              gapWidth: 5
            }
          },
          {
            itemStyle: {
              gapWidth: 1
            }
          },
          {
            colorSaturation: [0.35, 0.5],
            itemStyle: {
              gapWidth: 1,
              borderColorSaturation: 0.6
            }
          }
        ],
        // data: filterData.children,
      }
    ]
  };
 sunburstOption = {
  series: [
    {
      type: 'sunburst',
      id: 'echarts-package-size',
      radius: ['20%', '90%'],
      animationDurationUpdate: 1000,
      nodeClick: {
        label: {
          show: true
        },
      },
      data: filterData.children,
      universalTransition: true,
      itemStyle: {
        borderWidth: 1,
        borderColor: 'rgba(255,255,255,.5)'
      },
      label: {
        show: false
      }
    }
  ]
};
let currentOption = sunburstOption;
myChart.setOption(currentOption);
// setInterval(function () {
//   currentOption =
//     currentOption === treemapOption ? sunburstOption : treemapOption;
//   myChart.setOption(currentOption);
// }, 10000);

