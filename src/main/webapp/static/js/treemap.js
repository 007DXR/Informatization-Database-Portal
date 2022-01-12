ROOT_PATH = "/data.json"
const uploadedDataURL = ROOT_PATH

treemapChart = echarts.init(document.getElementById('treemap'));
// console.log(filterData)

var treemapOption = {
  title: {
    text: '信息化程度评价指标体系',
    subtext: '作者：\n范皓年\n董欣然\n吴泓霖',
    left: 'leafDepth'
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
      name: '评价指标体系',
      type: 'treemap',
      visibleMin: 1,
      data: filterData.children,
      leafDepth: 1,
      levels: [
        {
          itemStyle: {
            borderColor: '#555',
            borderWidth: 4,
            gapWidth: 4
          }
        },
        {
          colorSaturation: [0.3, 0.6],
          itemStyle: {
            borderColorSaturation: 0.7,
            gapWidth: 2,
            borderWidth: 2
          }
        },
        {
          colorSaturation: [0.3, 0.5],
          itemStyle: {
            borderColorSaturation: 0.6,
            gapWidth: 1
          }
        },
        {
          colorSaturation: [0.3, 0.5]
        }
      ]
    }
  ]
}

treemapChart.setOption(treemapOption);

treemapChart.on('click', function (params){
  console.log(params)
  lineChart.setOption({series:compute(params.data),"title": [
    {
        "text": params.name}]})
});
