function SelCity(obj, e) {
    var ths = obj;
    var dal = '<div class="_citys"><span title="关闭" id="cColse" >×</span><div id="_citysheng" class="_citys0">请选择省份</div><div id="_citys0" class="_citys1"></div><div style="display:none" id="_citys1" class="_citys1"></div><div style="display:none" id="_citys2" class="_citys1"></div></div>';
    Iput.show({
        id: ths,
        event: e,
        content: dal,
        width: "470"
    });
    $("#cColse").click(function() {
        Iput.colse()
    });
    var tb_province = [];
    var b = province;
    for (var i = 0,
    len = b.length; i < len; i++) {
        tb_province.push('<a data-id="' + b[i]['id'] + '" data-name="' + b[i]['name'] + '" title="' + b[i]['name'] + '">' + b[i]['name'] + '</a>')
    }
    $("#_citys0").append(tb_province.join(""));
    $("#_citys0 a").click(function() {
        var g = getCity($(this));
        $("#_citys1 a").remove();
        $("#_citys1").append(g);
        $("._citys1").hide();
        $("._citys1:eq(1)").show();
        $("#_citys0 a,#_citys1 a,#_citys2 a").removeClass("AreaS");
        $(this).addClass("AreaS");
        var lev = $(this).data("name");
        ths.value = $(this).data("name");
        if (document.getElementById("hcity") == null) {
            var hcitys = $('<input>', {
                type: 'hidden',
                name: "hcity",
                "data-id": $(this).data("id"),
                id: "hcity",
                val: lev
            });
            $(ths).after(hcitys)
        } else {
            $("#hcity").val(lev);
            $("#hcity").attr("data-id", $(this).data("id"))
        }
        $("#_citys1 a").click(function() {
            $("#_citys1 a,#_citys2 a").removeClass("AreaS");
            $(this).addClass("AreaS");
            var lev = $(this).data("name");
            if (document.getElementById("hproper") == null) {
                var hcitys = $('<input>', {
                    type: 'hidden',
                    name: "hproper",
                    "data-id": $(this).data("id"),
                    id: "hproper",
                    val: lev
                });
                $(ths).after(hcitys)
            } else {
                $("#hproper").attr("data-id", $(this).data("id"));
                $("#hproper").val(lev)
            }
            var bc = $("#hcity").val();
            ths.value = bc + "/" + $(this).data("name");
            var ar = getArea($(this));
            $("#_citys2 a").remove();
            if (ar == '') Iput.colse();
            $("#_citys2").append(ar);
            $("._citys1").hide();
            $("._citys1:eq(2)").show();
            $("#_citys2 a").click(function() {
                $("#_citys2 a").removeClass("AreaS");
                $(this).addClass("AreaS");
                var lev = $(this).data("name");
                if (document.getElementById("harea") == null) {
                    var hcitys = $('<input>', {
                        type: 'hidden',
                        name: "harea",
                        "data-id": $(this).data("id"),
                        id: "harea",
                        val: lev
                    });
                    $(ths).after(hcitys)
                } else {
                    $("#harea").val(lev);
                    $("#harea").attr("data-id", $(this).data("id"))
                }
                var bc = $("#hcity").val();
                var bp = $("#hproper").val();
                ths.value = bc + "/" + bp + "/" + $(this).data("name");
                Iput.colse()
                $("#orderShow").children().text("请选择科室")



            })
        })
    })
}
function showOrder(){
    var department = $("#department").val();
    if(department == "") {
        if (data == "error") {
            swal({
                title: "提示",
                text: "请选择科室",
                type: "error",
                confirmButtonText: '重试'
            });
        }
    }else {
        showLoading();
        showOrderList();
    }
}
function showOrderList(){
    var address = $("#address").val()
    var department = $("#department").val()
    if(address != ''){
        $("#orderHidden").attr("hidden",false);
        $("#orderShow").attr("hidden",true);
        $.post(
            "/consumerOrder/updateOrder",
            "address="+ address + "&department=" + department,
            function (data){
                hiddenLoading();
                $("#id_order_refresh").html(data);
                showDateTime();
            }
        )
    }else if(address == ''){
        $("#orderHidden").attr("hidden",true);
        $("#orderShow").attr("hidden",false);
    }
}
function showDateTime(){
    var date = new Date();
    var date1 = new Date(date.getTime() + 86400000);
    var date2 = new Date(date.getTime() + 86400000 * 2);
    var date3 = new Date(date.getTime() + 86400000 * 3);
    var date4 = new Date(date.getTime() + 86400000 * 4);
    var date5 = new Date(date.getTime() + 86400000 * 5);
    var date6 = new Date(date.getTime() + 86400000 * 6);
    var day = date.getDay();
    if (day == "1") {
        var time = (date.getMonth() + 1) + "月" + date.getDate() + "日-星期一";
        var time1 = (date1.getMonth() + 1) + "月" + date1.getDate() + "日-星期二";
        var time2 = (date2.getMonth() + 1) + "月" + date2.getDate() + "日-星期三";
        var time3 = (date3.getMonth() + 1) + "月" + date3.getDate() + "日-星期四";
        var time4 = (date4.getMonth() + 1) + "月" + date4.getDate() + "日-星期五";
        var time5 = (date5.getMonth() + 1) + "月" + date5.getDate() + "日-星期六";
        var time6 = (date6.getMonth() + 1) + "月" + date6.getDate() + "日-星期日";
        $("#slotMonday").text(time)
        $("#slotMonday").attr("class", "nav-link active");
        $("#slot_monday").attr("class", "tab-pane fade active show");
        $("#slotTuesday").text(time1)
        $("#slotWednesday").text(time2)
        $("#slotThursday").text(time3)
        $("#slotFriday").text(time4)
        $("#slotSaturday").text(time5)
        $("#slotSunday").text(time6)
    } else if (day == "2") {
        var time = (date.getMonth() + 1) + "月" + date.getDate() + "日-星期二";
        var time1 = (date1.getMonth() + 1) + "月" + date1.getDate() + "日-星期三";
        var time2 = (date2.getMonth() + 1) + "月" + date2.getDate() + "日-星期四";
        var time3 = (date3.getMonth() + 1) + "月" + date3.getDate() + "日-星期五";
        var time4 = (date4.getMonth() + 1) + "月" + date4.getDate() + "日-星期六";
        var time5 = (date5.getMonth() + 1) + "月" + date5.getDate() + "日-星期日";
        var time6 = (date6.getMonth() + 1) + "月" + date6.getDate() + "日-星期一";
        $("#slotMonday").text(time6)
        $("#slotTuesday").text(time)
        $("#slotTuesday").attr("class", "nav-link active");
        $("#slot_tuesday").attr("class", "tab-pane fade active show");
        $("#slotWednesday").text(time1)
        $("#slotThursday").text(time2)
        $("#slotFriday").text(time3)
        $("#slotSaturday").text(time4)
        $("#slotSunday").text(time5)
    } else if (day == "3") {
        var time = (date.getMonth() + 1) + "月" + date.getDate() + "日-星期三";
        var time1 = (date1.getMonth() + 1) + "月" + date1.getDate() + "日-星期四";
        var time2 = (date2.getMonth() + 1) + "月" + date2.getDate() + "日-星期五";
        var time3 = (date3.getMonth() + 1) + "月" + date3.getDate() + "日-星期六";
        var time4 = (date4.getMonth() + 1) + "月" + date4.getDate() + "日-星期日";
        var time5 = (date5.getMonth() + 1) + "月" + date5.getDate() + "日-星期一";
        var time6 = (date6.getMonth() + 1) + "月" + date6.getDate() + "日-星期二";
        $("#slotMonday").text(time5)
        $("#slotTuesday").text(time6)
        $("#slotWednesday").text(time)
        $("#slotWednesday").attr("class", "nav-link active");
        $("#slot_wednesday").attr("class", "tab-pane fade active show");
        $("#slotThursday").text(time1)
        $("#slotFriday").text(time2)
        $("#slotSaturday").text(time3)
        $("#slotSunday").text(time4)
    } else if (day == "4") {
        var time = (date.getMonth() + 1) + "月" + date.getDate() + "日-星期四";
        var time1 = (date1.getMonth() + 1) + "月" + date1.getDate() + "日-星期五";
        var time2 = (date2.getMonth() + 1) + "月" + date2.getDate() + "日-星期六";
        var time3 = (date3.getMonth() + 1) + "月" + date3.getDate() + "日-星期日";
        var time4 = (date4.getMonth() + 1) + "月" + date4.getDate() + "日-星期一";
        var time5 = (date5.getMonth() + 1) + "月" + date5.getDate() + "日-星期二";
        var time6 = (date6.getMonth() + 1) + "月" + date6.getDate() + "日-星期三";
        $("#slotMonday").text(time4)
        $("#slotTuesday").text(time5)
        $("#slotWednesday").text(time6)
        $("#slotThursday").text(time)
        $("#slotThursday").attr("class", "nav-link active");
        $("#slot_thursday").attr("class", "tab-pane fade active show");
        $("#slotFriday").text(time1)
        $("#slotSaturday").text(time2)
        $("#slotSunday").text(time3)
    } else if (day == "5") {
        var time = (date.getMonth() + 1) + "月" + date.getDate() + "日-星期五";
        var time1 = (date1.getMonth() + 1) + "月" + date1.getDate() + "日-星期六";
        var time2 = (date2.getMonth() + 1) + "月" + date2.getDate() + "日-星期日";
        var time3 = (date3.getMonth() + 1) + "月" + date3.getDate() + "日-星期一";
        var time4 = (date4.getMonth() + 1) + "月" + date4.getDate() + "日-星期二";
        var time5 = (date5.getMonth() + 1) + "月" + date5.getDate() + "日-星期三";
        var time6 = (date6.getMonth() + 1) + "月" + date6.getDate() + "日-星期四";
        $("#slotMonday").text(time3)
        $("#slotTuesday").text(time4)
        $("#slotWednesday").text(time5)
        $("#slotThursday").text(time6)
        $("#slotFriday").text(time)
        $("#slotFriday").attr("class", "nav-link active");
        $("#slot_friday").attr("class", "tab-pane fade active show");
        $("#slotSaturday").text(time1)
        $("#slotSunday").text(time2)
    } else if (day == "6") {
        var time = (date.getMonth() + 1) + "月" + date.getDate() + "日-星期六";
        var time1 = (date1.getMonth() + 1) + "月" + date1.getDate() + "日-星期日";
        var time2 = (date2.getMonth() + 1) + "月" + date2.getDate() + "日-星期一";
        var time3 = (date3.getMonth() + 1) + "月" + date3.getDate() + "日-星期二";
        var time4 = (date4.getMonth() + 1) + "月" + date4.getDate() + "日-星期三";
        var time5 = (date5.getMonth() + 1) + "月" + date5.getDate() + "日-星期四";
        var time6 = (date6.getMonth() + 1) + "月" + date6.getDate() + "日-星期五";
        $("#slotMonday").text(time2)
        $("#slotTuesday").text(time3)
        $("#slotWednesday").text(time4)
        $("#slotThursday").text(time5)
        $("#slotFriday").text(time6)
        $("#slotSaturday").text(time)
        $("#slotSaturday").attr("class", "nav-link active");
        $("#slot_saturday").attr("class", "tab-pane fade active show");
        $("#slotSunday").text(time1)
    } else if (day == "0") {
        var time = (date.getMonth() + 1) + "月" + date.getDate() + "日-星期日";
        var time1 = (date1.getMonth() + 1) + "月" + date1.getDate() + "日-星期一";
        var time2 = (date2.getMonth() + 1) + "月" + date2.getDate() + "日-星期二";
        var time3 = (date3.getMonth() + 1) + "月" + date3.getDate() + "日-星期三";
        var time4 = (date4.getMonth() + 1) + "月" + date4.getDate() + "日-星期四";
        var time5 = (date5.getMonth() + 1) + "月" + date5.getDate() + "日-星期五";
        var time6 = (date6.getMonth() + 1) + "月" + date6.getDate() + "日-星期六";
        $("#slotMonday").text(time1)
        $("#slotTuesday").text(time2)
        $("#slotWednesday").text(time3)
        $("#slotThursday").text(time4)
        $("#slotFriday").text(time5)
        $("#slotSaturday").text(time6)
        $("#slotSunday").text(time)
        $("#slotSunday").attr("class", "nav-link active");
        $("#slot_sunday").attr("class", "tab-pane fade active show");
    }
}

function showLoading(){
    $("#loading").attr("hidden",false);
}
function hiddenLoading(){
    $("#loading").attr("hidden",true);
}
function getCity(obj) {
    var c = obj.data('id');
    var e = province;
    var f = [];
    var g = '';
    for (var i = 0; i < e.length; i++) {
        if (e[i]['id'] == parseInt(c)) {
            f = e[i]['son'];
            break
        }
    }
    for (var j = 0; j < f.length; j++) {
        g += '<a data-id="' + f[j]['id'] + '" data-name="' + f[j]['name'] + '" title="' + f[j]['name'] + '">' + f[j]['name'] + '</a>'
    }
    $("#_citysheng").html('请选择城市');
    return g
}
function getArea(obj) {
    var c = obj.data('id');
    var e = province;
    var f = [];
    var g = '';
    for (var i = 0; i < e.length; i++) {
        for (var j = 0; j < e[i]['son'].length; j++) {
            if (e[i]['son'][j]['id'] == parseInt(c) && e[i]['son'][j]['sec']) {
                f = e[i]['son'][j]['sec'];
                break
            }
        }
    }
    if (f.length) {
        for (var k = 0; k < f.length; k++) {
            g += '<a data-id="' + f[k]['id'] + '" data-name="' + f[k]['name'] + '" title="' + f[k]['name'] + '">' + f[k]['name'] + '</a>'
        }
    }
    $("#_citysheng").html('请选择区县');
    return g
}