$(function () {
    $('#submit').click(function (e) {
        e.preventDefault();
        var data = {
            first_index: $('#level1').val(),
            second_index: $('#level2').val(),
            third_index: $('#level3').val(),
            country_name: $('#country').val(),
            data_value: $('#value').val(),
            year: $('#year').val(),
            record_id: $('#recordID').val()
        };
        if (data.data_value == ""){
            data.data_value = -1;
        }
        console.log("请求:")
        console.log(data)
        $.ajax({
            type: 'POST',
            url: '/visual/update',
            data: JSON.stringify(data),
            success: function (resp) {
                if (resp.error) {
                    $('#resp').text("");
                    $('#error').text(resp.error);
                    alert(resp.error)
                } else {
                    $('#resp').text(resp.result);
                    $('#error').text();
                }
            },
            contentType: 'application/json;charset=utf-8',
            dataType: 'json'
        });
    });
});

$(function () {
    $('#query').click(function (e) {
        e.preventDefault();
        var data = {
            first_index: $('#level1').val(),
            second_index: $('#level2').val(),
            third_index: $('#level3').val(),
            country_name: $('#country').val(),
            data_value: $('#value').val(),
            year: $('#year').val(),
            record_id: null
        };
        console.log("请求:")
        console.log(data)
        $.ajax({
            type: 'POST',
            url: '/visual/inquiry',
            data: JSON.stringify(data),
            success: function (resp) {
                if (resp.error) {
                    $('#error').text(resp.error);
                } else {
                    drawTable(resp.data);
                    rawData = prefilter(resp.data);
                    $('#resp').text(resp.result);
                    $('#error').text();
                    // console.log("prefilteredData:");
                    console.log(resp.data);
                    // rawData = prefilteredData;
                    pieCount = 0;
                    // if (data['year']==null){
                    //     DrawPie(resp.data,'year');
                    // }
                    // if (data['country_name']==null){
                    //     DrawPie(resp.data,'country_name');
                    // }
                    DrawPie(resp.data, 'year');
                    DrawPie(resp.data, 'country_name');
                    if (data['first_index'] == null) {
                        DrawPie(resp.data, 'first_index');
                    } else {
                        if (data['second_index'] == null) {
                            DrawPie(resp.data, 'second_index');
                        } else {
                            // DrawPie(resp.data,'third_index');
                            if (data['third_index'] == null) {
                                DrawPie(resp.data, 'third_index');
                            } else {
                                let x = document.getElementById('pie3')
                                
                                x.style.visibility = "hidden";
                            }
                        }
                    }
                }
            },
            contentType: 'application/json;charset=utf-8',
            dataType: 'json'
        });
    });
});
function drawTable(respData) {
    let table = document.getElementById('query-result')
    // if (respData.length) {
    table.style.visibility = "visible";
    // }
    let tbody = document.createElement('tbody');
    for (var i in respData) {
        let row = document.createElement('tr');
        let td1 = document.createElement('td');
        td1.innerHTML = respData[i].record_id;
        row.appendChild(td1);
        let td2 = document.createElement('td');
        td2.innerHTML = respData[i].first_index;
        row.appendChild(td2);
        let td3 = document.createElement('td');
        td3.innerHTML = respData[i].second_index;
        row.appendChild(td3);
        let td4 = document.createElement('td');
        td4.innerHTML = respData[i].third_index;
        row.appendChild(td4);
        let td5 = document.createElement('td');
        td5.innerHTML = respData[i].country_name;
        row.appendChild(td5);
        let td6 = document.createElement('td');
        td6.innerHTML = respData[i].year;
        row.appendChild(td6);
        let td7 = document.createElement('td');
        td7.innerHTML = respData[i].data_value;
        row.appendChild(td7);
        tbody.appendChild(row);
    }

    table.replaceChild(tbody, table.children[1]);
}
