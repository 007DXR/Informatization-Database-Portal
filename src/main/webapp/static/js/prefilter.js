// console.log(data[0].country_name)
function prefilter(prefilteredData){
    tmpData = {}
    for (let i in prefilteredData) {
        item = prefilteredData[i]
        index1 = item.first_index
        index2 = item.second_index
        index3 = item.third_index
        if (index1 in tmpData == false) {
            tmpData[index1] = {}
        }
        if (index2 in tmpData[index1] == false) {
            tmpData[index1][index2] = {}
        }
        if (index3 in tmpData[index1][index2] == false) {
            tmpData[index1][index2][index3] = []
        }
        tmpData[index1][index2][index3].push({ "记录ID": item.record_id, "国家": item.country_name, "值": item.data_value, "年份": item.year })
    }
    return tmpData
}
