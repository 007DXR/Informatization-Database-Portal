// console.log(data[0].country_name)
function prefilter(prefilteredData){
    var tmpData = {}
    var sum={}
    var cnt={}
    for (let i in prefilteredData) {
        let item = prefilteredData[i]
        let index1 = item.first_index
        let index2 = item.second_index
        let index3 = item.third_index
        let year =item.year
        let country=item.country_name

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

        if (cnt[year]==null){
            cnt[year]=0
            sum[year]=0
        }
        cnt[year]++
        sum[year]+=item.data_value

        if (cnt[country]==null){
            cnt[country]=0
            sum[country]=0
        }
        cnt[country]++
        sum[country]+=item.data_value

        if (sum[index1]==null){
            sum[index1]=0
            cnt[index1]=0
        }
        if (sum[index2]==null){
            sum[index2]=0
            cnt[index2]=0
            cnt[index1]++
        }
        
        if (sum[index3]==null){
            sum[index3]=0
            cnt[index3]=0
            cnt[index2]++
        }else{
            sum[index1]-=sum[index2]/cnt[index2]
            sum[index2]-=sum[index3]/cnt[index3]
        }
        
        cnt[index3]++;
        sum[index3]+=item.data_value
        sum[index2]+=sum[index3]/cnt[index3]
        sum[index1]+=sum[index2]/cnt[index2]
            }
    for (let i in prefilteredData) {
        let item = prefilteredData[i]
        let index1 = item.first_index
        let index2 = item.second_index
        let index3 = item.third_index
        let year =item.year
        let country=item.country_name
        prefilteredData[i].first_index_value=sum[index1]/cnt[index1]
        prefilteredData[i].second_index_value=sum[index2]/cnt[index2]
        prefilteredData[i].third_index_value=sum[index3]/cnt[index3]
        prefilteredData[i].country_name_value=sum[country]/cnt[country]
        prefilteredData[i].year_value=sum[year]/cnt[year]
    }
    return tmpData
}
