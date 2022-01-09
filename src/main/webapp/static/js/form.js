
function Listener3(level) {
    document.getElementById("select3").onclick = function () {
        for (var i in level) {
            var name1 = level[i].name

            if (document.getElementById(name1).selected) {
                // alert(name1)
                document.getElementById("level3").value = name1
              

            }
        }
    }
}
function Listener2(level) {
    document.getElementById("select2").onclick = function () {
        for (var i in level) {
            var name1 = level[i].name

            if (document.getElementById(name1).selected) {
                // alert(name1)
                document.getElementById("level2").value = name1
                var select2 = document.getElementById("select3")
                while (select2.length > 0) {
                    select2.remove(0);
                    // alert(select2.length)
                }
                var y = document.createElement('option');
                y.text = "新建"
                select2.add(y, null)
                for (var j in level[i].children) {
                    var name2 = level[i].children[j].name
                    var y = document.createElement('option');
                    y.id = name2
                    y.text = name2
                    select2.add(y, null)
                }
                Listener3(level[i].children)

            }
        }
    }
}
function Listener1(level1) {
    document.getElementById("select1").onclick = function () {
        for (var i in level1) {
            var name1 = level1[i].name

            if (document.getElementById(name1).selected) {
                // alert(name1)
                document.getElementById("level1").value = name1
                var select2 = document.getElementById("select2")
                while (select2.length > 0) {
                    select2.remove(0);
                    // alert(select2.length)
                }
                var y = document.createElement('option');
                y.text = "新建"
                select2.add(y, null)
                for (var j in level1[i].children) {
                    var name2 = level1[i].children[j].name
                    var y = document.createElement('option');
                    y.id = name2
                    y.text = name2
                    select2.add(y, null)
                }
                Listener2(level1[i].children)

            }
        }
    }
}
Listener1(filterData.children)
document.getElementById("submit").onclick = function () {
    var value1 = document.getElementById("level1").value
    var value2 = document.getElementById("level2").value
    var value3 = document.getElementById("level3").value
    let flag = 0
    pos=filterData.children
    for (var i in pos) {
        var name1 = pos[i].name
        if (value1 == name1) {
            pos=pos[i].children
            for (var j in pos){
                if (value2==pos[j].name){
                    pos=pos[j].children
                    for (var k in pos){
                        if (value3==pos[k].name){
                            pos=pos[k].children
                            pos.push({asdf:23})
                            flag = 1
                            console.log(pos)
                        }
                    }
                }
            }
        }
    }
    // if (!flag) {
    //     filterData.children.push({ name: value1, children: [{ name: value2, children: [{ name: value3,  }] }] })
    //     return
    // }
    // console.log(filterData)
}