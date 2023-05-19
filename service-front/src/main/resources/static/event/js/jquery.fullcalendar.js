
!function($) {
    "use strict";

    var CalendarApp = function() {
        this.$body = $("body")
        this.$calendar = $('#calendar'),
        this.$event = ('#calendar-events div.calendar-events'),
        this.$categoryForm = $('#add_new_event form'),
        this.$extEvents = $('#calendar-events'),
        this.$modal = $('#my_event'),
        this.$saveCategoryBtn = $('.save-category'),
        this.$calendarObj = null
    };


    /* on drop */
    CalendarApp.prototype.onDrop = function (eventObj, date) { 
        var $this = this;
            // retrieve the dropped element's stored Event Object
            var originalEventObject = eventObj.data('eventObject');
            var $categoryClass = eventObj.attr('data-class');
            // we need to copy it, so that multiple events don't have a reference to the same object
            var copiedEventObject = $.extend({}, originalEventObject);
            // assign it the date that was reported
            copiedEventObject.start = date;
            if ($categoryClass)
                copiedEventObject['className'] = [$categoryClass];
            // render the event on the calendar
            $this.$calendar.fullCalendar('renderEvent', copiedEventObject, true);
            // is the "remove after drop" checkbox checked?
            if ($('#drop-remove').is(':checked')) {
                // if so, remove the element from the "Draggable Events" list
                eventObj.remove();
            }
    },
    /* on click on event */
    CalendarApp.prototype.onEventClick =  function (calEvent, jsEvent, view) {
        var $this = this;
            var form = $("<form></form>");
            form.append("<label>修改事件名称</label>");
            form.append("<div class='input-group'><input class='form-control' type=text value='" + calEvent.title + "' /><span class='input-group-append'><button type='submit' class='btn btn-success'><i class='fa fa-check'></i> 保存</button></span></div>");
            $this.$modal.modal({
                backdrop: 'static'
            });
            $this.$modal.find('.delete-event').show().end().find('.save-event').hide().end().find('.modal-body').empty().prepend(form).end().find('.delete-event').unbind('click').click(function () {
                $this.$calendarObj.fullCalendar('removeEvents', function (ev) {
                    return (ev._id == calEvent._id);
                });
                $.post(
                    "/consumerInformation/deleteCalendar",
                    "id=" + calEvent._id,
                    function (data){
                        if(data == "error"){
                            alert("删除失败")
                        }
                    }
                )
                $this.$modal.modal('hide');
            });
            $this.$modal.find('form').on('submit', function () {
                calEvent.title = form.find("input[type=text]").val();
                $this.$calendarObj.fullCalendar('updateEvent', calEvent);
                $this.$modal.modal('hide');
                $.post(
                    "/consumerInformation/updateCalendarTitle",
                    "title=" + calEvent.title + "&id=" + calEvent.id,
                    function (data){
                        if(data == "error"){
                            alert("修改失败，请重试");
                        }
                    }
                )
                return false;
            });
    },
    /* on select */
    CalendarApp.prototype.onSelect = function (start, end, allDay) {
        var $this = this;
            $this.$modal.modal({
                backdrop: 'static'
            });
            var form = $("<form></form>");
            form.append("<div class='event-inputs'></div>");
            form.find(".event-inputs")
                .append("<div class='form-group'><label class='control-label'>事件名称</label><input class='form-control' placeholder='事件名称' type='text' name='title'/></div>")
                .append("<div class='form-group mb-0'><label class='control-label'>颜色</label><select class='form-control' name='category'></select></div>")
                .find("select[name='category']")
                .append("<option value='bg-danger'>红色</option>")
                .append("<option value='bg-success'>绿色</option>")
                .append("<option value='bg-purple'>紫色</option>")
                .append("<option value='bg-primary'>浅绿</option>")
                .append("<option value='bg-info'>蓝色</option>")
                .append("<option value='bg-warning'>橙色</option></div></div>");
            $this.$modal.find('.delete-event').hide().end().find('.save-event').show().end().find('.modal-body').empty().prepend(form).end().find('.save-event').unbind('click').click(function () {
                form.submit();
            });
            $this.$modal.find('form').on('submit', function () {
                var title = form.find("input[name='title']").val();
                var beginning = form.find("input[name='beginning']").val();
                var ending = form.find("input[name='ending']").val();
                var categoryClass = form.find("select[name='category'] option:checked").val();
                if (title !== null && title.length != 0) {
                    var st = start["_d"];
                    var tCalendarNoteBook = {
                        "tConsumerId" : $("#consumerId").val(),
                        "title" : title,
                        "start" : new Date(st.getTime()),
                        "classname" :categoryClass
                    }
                    $.post(
                        "/consumerInformation/insertCalendar",
                        tCalendarNoteBook,
                        function (data){
                            if(data == "error"){
                                alert("事件添加失败")
                            }else{
                                var s =  data;
                                $this.$calendarObj.fullCalendar('renderEvent', {
                                    title: title,
                                    start:start,
                                    end: end,
                                    allDay: false,
                                    className: categoryClass,
                                    id:s
                                }, true);
                            }
                        }
                    )

                    $this.$modal.modal('hide');
                }
                else{
                    alert('请输入事件名称');
                }
                return false;
                
            });
            $this.$calendarObj.fullCalendar('unselect');
    },
    CalendarApp.prototype.enableDrag = function() {
        //init events
        $(this.$event).each(function () {
            // it doesn't need to have a start or end
            var eventObject = {
                title: $.trim($(this).text()) // use the element's text as the event title
            };
            // store the Event Object in the DOM element so we can get to it later
            $(this).data('eventObject', eventObject);
            // make the event draggable using jQuery UI
            $(this).draggable({
                zIndex: 999,
                revert: true,      // will cause the event to go back to its
                revertDuration: 0  //  original position after the drag
            });
        });
    }
    /* Initializing */
    CalendarApp.prototype.init = function() {
        this.enableDrag();
        /*  Initialize the calendar  */
        var date = new Date();
        var d = date.getDate();
        var m = date.getMonth();
        var y = date.getFullYear();
        var form = '';
        var today = new Date($.now());
        var tCalendarNotebooks = $("#tCalendarNotebooks").val();
        var defaultEvents = JSON.parse(tCalendarNotebooks);

        // var defaultEvents =  [{
        //         title: 'Event Name 4',
        //         start: new Date($.now() + 148000000),
        //         className: 'bg-purple'
        //     },
        //     {
        //         title: 'Test Event 1',
        //         start: today,
        //         end: today,
        //         className: 'bg-success'
        //     },
        //     {
        //         title: 'Test Event 2',
        //         start: new Date($.now() + 168000000),
        //         className: 'bg-info'
        //     },
        //     {
        //         title: 'Test Event 3',
        //         start: new Date($.now() + 338000000),
        //         className: 'bg-primary'
        //     }];

        var $this = this;
        $this.$calendarObj = $this.$calendar.fullCalendar({
            slotDuration: '00:15:00', /* If we want to split day time each 15minutes */
            minTime: '08:00:00',
            maxTime: '19:00:00',  
            defaultView: 'month',  
            handleWindowResize: true,   
             
            header: {
                left: 'prev,next today',
                center: 'title',
                right: 'month,agendaWeek,agendaDay'
            },
            events: defaultEvents,
            editable: true,
            droppable: true, // this allows things to be dropped onto the calendar !!!
            eventLimit: true, // allow "more" link when too many events
            selectable: true,
            drop: function(date) { $this.onDrop($(this), date); },
            select: function (start, end, allDay) { $this.onSelect(start, end, allDay); },
            eventClick: function(calEvent, jsEvent, view) { $this.onEventClick(calEvent, jsEvent, view); }

        });

        //on new event
        this.$saveCategoryBtn.on('click', function(){
            var categoryName = $this.$categoryForm.find("input[name='category-name']").val();
            var categoryColor = $this.$categoryForm.find("select[name='category-color']").val();
            if (categoryName !== null && categoryName.length != 0) {
                $this.$extEvents.append('<div class="calendar-events" data-class="bg-' + categoryColor + '" style="position: relative;"><i class="fa fa-circle text-' + categoryColor + '"></i>' + categoryName + '</div>')
                $this.enableDrag();
            }

        });
    },

   //init CalendarApp
    $.CalendarApp = new CalendarApp, $.CalendarApp.Constructor = CalendarApp
    
}(window.jQuery),

//initializing CalendarApp
function($) {
    "use strict";
    $.CalendarApp.init()
}(window.jQuery);
function map2Json(mapStr){
    var subStr = mapStr.substring(1,mapStr.length-1);
    var arr = subStr.split(",");
    var newJson = [];
    for(var i in arr){
        var tmpObj = arr[i].split("=");
        newJson[$.trim(tmpObj[0])] = tmpObj[1];
    }
    return newJson;
};


function mapToObj (map) {
    var obj = {};
    for (let [key, value] of map) {
        obj[key] = value;
    }

    return JSON.stringify(obj);
};