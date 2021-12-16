function transform(rawData, filterData) {
  filterData.children = []
  
  filterData.size = 0
  filterData.value = 0
  if (toString.apply(rawData) == "[object Array]") {
    filterData.else = rawData
    var stack=[]
    var sum={}
    var cnt={}
    for (var i = 0; i < rawData.length; i += 1) {
     
      var newNode = {}
      newNode.name = rawData[i].国家
      newNode.value =parseInt( rawData[i].值)
      // newNode.size=1
      // filterData.else.push(rawData[i])
      filterData.size += 1
      filterData.value += parseInt(newNode.value)


      if (sum[newNode.name]==null){
        sum[newNode.name]=0
        stack.push(newNode.name)
        cnt[newNode.name]=0
      } 
      sum[newNode.name]+=parseInt(newNode.value)
      cnt[newNode.name]++
      // listData.push({first:,second:,third:,value})
      // transform(rawData[i],newNode)
    }
    // console.log(filterData.else)
    for (var c in stack) {
      var country = stack[c]
      filterData.children.push({ name: country, value:sum[country]/cnt[country] })
    }

  } else
    for (var i in rawData) {
      var newNode = {}
      newNode.name = i
      filterData.children.push(newNode)
      transform(rawData[i], newNode)
      filterData.size += 1
      filterData.value += parseInt(newNode.value)
    }
    filterData.sum= parseInt(filterData.value)
  filterData.value /= filterData.size
}
filterData = {}
listData = []
transform(rawData, filterData)
console.log(filterData)