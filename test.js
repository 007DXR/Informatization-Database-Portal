data = [
    {
        "country_name": "中国",
        "data_value": 57.621430105217954,
        "first_index": "信息基础设施",
        "record_id": 0,
        "second_index": "带宽水平",
        "third_index": "互联网用户的平均上网带宽",
        "year": "2019"
    }
]
// console.log(data[0].country_name)
rawData = {}
for (let i in data) {
    item = data[i]
    index1 = item.first_index
    index2 = item.second_index
    index3 = item.third_index
    if (index1 in rawData == false) {
        rawData[index1] = {}
    }
    if (index2 in rawData[index1] == false) {
        rawData[index1][index2] = {}
    }
    if (index3 in rawData[index1][index2] == false) {
        rawData[index1][index2][index3] = []
    }
    rawData[index1][index2][index3].push({ "记录ID": item.record_id, "国家": item.country_name, "值": item.data_value, "年份": item.year })
}
