<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + ":/" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>基础Case管理</title>
</head>
<script>
    $(function () {
        if (${caseId}==0
    )
        {
            getAllUser(<%=session.getAttribute("userId")%>);
        }
        findBaseCaseList();
    });

    function findBaseCaseList() {
        if (${caseId}>
        0
    )
        {
            userId =
            <%=session.getAttribute("userId")%>
            var baseCase = {
                "caseId":${caseId},
            }
        }
    else
        {
            userId = $("#secUserList").val();
            projectId = $("#secProjectList").val();
            var baseCase = {
                "caseId":${caseId},
                "userId": userId,
                "projectId": projectId,
            }
        }
        var url = "case/findBaseCaseList.go";

        var table = $('#tableBaseCaseList')
            .DataTable(
                {
                    "lengthMenu": [[10, 20, 50, 100, 1000], ["10", "20", "50", "100", "1000"]],
                    "order": [[8, "asc"]],
                    "destroy": true,
                    "searching": false,
                    "ajax": {
                        "type": "post",
                        "url": url,
                        "contentType": "application/json;charset=utf-8",
                        "dataType": "json",
                        data: function () {
                            return JSON.stringify(baseCase)
                        }
                    },
                    "columns": [
                        {
                            "data": "id"
                        },
                        {
                            "targets": -1,//编辑
                            "data": null,
                            "render": function (data) {
                                var str = "<a href='javascript:void(0);' value='case/initBaseCaseDetail.go?baseCaseId=" + data.id + "' class='link'>" + data.name + "</a>";
                                return str;
                            }
                        },
                        {
                            "data": "comment"
                        },
                        {
                            "data": "groupName"
                        },
                        {
                            "data": "parentGroupName"
                        },
                        {
                            "data": "projectName"
                        },
                        {
                            "data": "userName"
                        },
                        {
                            "data": "status"
                        },
                        {
                            "data": "kind"
                        },
                        {
                            "data": "sort"
                        },
                        {
                            "targets": -1,//编辑
                            "data": null,
                            "render": function (data) {
                                var sessionUserId =<%=session.getAttribute("userId")%>;
                                var isAdmin =<%=session.getAttribute("isAdmin")%>;
                                var str = "";
                                if (sessionUserId == data.userId || isAdmin == 2) {
                                    str += "<a class='glyphicon glyphicon-pencil' onclick='showUpdateBaseCasePanel(this)' style='cursor:pointer;'></a>";
                                    str += "&nbsp;&nbsp;&nbsp;&nbsp;<a class='glyphicon glyphicon-trash'  style='cursor:pointer;' onclick='deleteBaseCase(this);'></a>";
                                }
                                str += "&nbsp;&nbsp;&nbsp;&nbsp;<a class='glyphicon glyphicon-copy' title='Copy为基础Case' style='cursor:pointer;' onclick='copyBaseCase(this);'></a>";
                                if (sessionUserId == data.userId || isAdmin == 2) {
                                    if (data.caseId > 0) {
                                        if (data.sort > 1) {
                                            str += "&nbsp;&nbsp;&nbsp;&nbsp;<a class='glyphicon glyphicon-arrow-up' title='上移' style='cursor:pointer;' onclick='updateBaseCaseSort(this,1);'></a>";
                                        }
                                        str += "&nbsp;&nbsp;&nbsp;&nbsp;<a class='glyphicon glyphicon-arrow-down' title='下移' style='cursor:pointer;' onclick='updateBaseCaseSort(this,2);'></a>";
                                    }
                                }
                                return str;
                            }
                        }],
                    "oLanguage": {
                        "sLengthMenu": "每页显示 _MENU_ 条记录",
                        "sZeroRecords": "抱歉，查询不到任何相关数据",
                        "sProcessing": "数据加载中...",
                        "sInfo": "从 _START_ 到 _END_ 共 _TOTAL_ 条数据",
                        "sInfoEmpty": "没有数据",
                        "sInfoFiltered": "(从 _MAX_ 条数据中检索)",
                        "oPaginate": {
                            "sFirst": "首页",
                            "sPrevious": "前一页",
                            "sNext": "后一页",
                            "sLast": "尾页"
                        }
                    },
                    "aoColumnDefs": [{
                        sDefaultContent: '',
                        aTargets: ['_all']
                    }]
                });
    }

    function updateBaseCaseSort(obj, option) {
        var baseCaseId = $(obj).parent("td").siblings().eq(0).text();
        $.ajax({
            type: 'POST',
            url: "case/updateBaseCaseSort?baseCaseId=" + baseCaseId + "&option=" + option + "&caseId=" +<%=request.getAttribute("caseId")%>,
            async: false,
            success: function (data) {
                if (data > 0) {
                    alert("更新成功");
                    refresh($.cookie("url"));
                }
            }
        });
    }

    function addBaseCase() {
        var baseCase = {
            "name": $("#txtBaseCaseName").val(),
            "comment": $("#txtBaseCaseComment").val(),
            "groupId": $("#secGroup").val(),
            "projectId": $("#secCustomer").val(),
            "status": $("#secBaseCaseStatus").val(),
            "kind": $("#secKind").val()
        }
        $.ajax({
            type: 'POST',
            url: "case/insertBaseCase.go?baseCaseId=" + $("#secBaseCase").val() + "&caseId=" +<%=request.getAttribute("caseId")%>,
            contentType: "application/json;charset=utf-8",
            dataType: "json",
            data: JSON.stringify(baseCase),
            success: function (data) {
                if (data > 0) {
                    alert("添加成功");
                    refresh($.cookie("url"));
                    $('#baseCaseModal').modal('hide');
                }
            }
        });

    }

    function updateBaseCase() {
        var baseCase = {
            "id": $("#txtBaseCaseId").val(),
            "name": $("#txtBaseCaseName").val(),
            "comment": $("#txtBaseCaseComment").val(),
            "groupId": $("#secGroup").val(),
            "projectId": $("#secCustomer").val(),
            "status": $("#secBaseCaseStatus").val(),
            "kind": $("#secKind").val()
        }
        $.ajax({
            type: 'POST',
            url: "case/updateBaseCase.go",
            contentType: "application/json;charset=utf-8",
            dataType: "json",
            data: JSON.stringify(baseCase),
            success: function (data) {
                if (data > 0) {
                    alert("修改成功");
                    refresh($.cookie("url"));
                    $('#baseCaseModal').modal('hide');
                }
            }
        });
    }

    function showUpdateBaseCasePanel(obj) {
        var baseCaseId = $(obj).parent("td").siblings().eq(0).text();
        $("#txtBaseCaseId").val(baseCaseId);
        $.ajax({
            type: 'POST',
            url: "case/findBaseCaseById.go?baseCaseId=" + baseCaseId,
            async: false,
            success: function (data) {
                var obj = data["aaData"];
                $("#baseCaseContainer").hide();
                $("#btnAddBaseCase").hide();
                $("#btnUpdateBaseCase").show();
                $("#txtBaseCaseId").val(obj[0]["id"]);
                $("#txtBaseCaseName").val(obj[0]["name"]);
                $("#txtBaseCaseComment").val(obj[0]["comment"]);
                $("#secCustomer").val(obj[0]["projectId"]);
                changeParentGroup($("#secCustomer"));
                $("#secParentGroup").val(obj[0]["parentGroupId"]);
                changeGroup($("#secParentGroup"));
                $("#secGroup").val(obj[0]["groupId"]);
                $("#secBaseCaseStatus").val(obj[0]["status"]);
                $("#secKind").val(obj[0]["kind"]);
                $('#baseCaseModal').modal('show');
            }
        });

    }

    function deleteBaseCase(obj) {
        if (confirm("确定删除？")) {
            $.ajax({
                type: 'POST',
                url: "case/deleteBaseCase.go?baseCaseId=" + $(obj).parent("td").siblings().eq(0).text(),
                success: function (data) {
                    if (data > 0) {
                        alert("删除成功");
                        refresh($.cookie("url"));
                    }
                },
                error: function () {
                    alert("删除失败,请联系开发人员");
                    refresh($.cookie("url"));
                }
            });
        }
    }

    function showAddBaseCasePanel() {
        $("#baseCaseContainer").show();
        $("#btnUpdateBaseCase").hide();
        $("#btnAddBaseCase").show();
        $("#baseCaseModalTitle").text("BaseCase添加");
        $('#baseCaseModal').modal('show');
    }

    function showBaseCaseList(obj) {
        var baseCase = {
            "groupId": $(obj).val()
        }
        $.ajax({
            type: 'POST',
            url: "case/findBaseCaseList.go",
            async: false,
            "contentType": "application/json;charset=utf-8",
            "dataType": "json",
            data: JSON.stringify(baseCase),
            success: function (data) {
                var obj = data["aaData"];
                $("#secBaseCase").empty();
                $("#secBaseCase").append("<option value='0'>新增基础case</option>")
                for (i = 0; i < obj.length; i++) {
                    var object = obj[i];
                    $("#secBaseCase").append("<option value='" + object["id"] + "'>" + object["name"] + "_" + object["comment"] + "</option>")
                }
            }
        });
    }

    function changeParentGroup(obj) {
        $.ajax({
            type: 'POST',
            url: "case/findGroupList.go?projectId=" + $(obj).val() + "&parentGroupId=-1",
            async: false,
            success: function (data) {
                var obj = data["aaData"];
                $("#secParentGroup").empty();
                $("#secParentGroup").append("<option value='0'>请选择</option>")
                for (i = 0; i < obj.length; i++) {
                    var object = obj[i];
                    $("#secParentGroup").append("<option value='" + object["id"] + "'>" + object["name"] + "</option>")
                }
            }
        });
    }

    function changeGroup(obj) {
        $.ajax({
            type: 'POST',
            url: "case/findGroupList.go?projectId=" + $("#secCustomer").val() + "&parentGroupId=" + $(obj).val(),
            async: false,
            success: function (data) {
                var obj = data["aaData"];
                $("#secGroup").empty();
                $("#secGroup").append("<option value='0'>请选择</option>")
                for (i = 0; i < obj.length; i++) {
                    var object = obj[i];
                    $("#secGroup").append(
                        "<option value='" + object["id"] + "'>"
                        + object["name"] + "</option>")
                }
            }
        });
    }

    function copyBaseCase(obj) {
        var baseCaseId = $(obj).parent("td").siblings().eq(0).text();
        $.ajax({
            type: 'POST',
            url: "case/copyBaseCase.go?baseCaseId=" + baseCaseId,
            success: function (data) {
                if (data > 0) {
                    alert("Copy成功");
                    refresh($.cookie("url"));
                }
            }

        });
    }

    function changeBaseCase(obj) {
        $.ajax({
            type: 'POST',
            url: "case/findBaseCaseById.go?baseCaseId=" + $(obj).val(),
            async: false,
            success: function (data) {
                var arr = data["aaData"];
                $("#txtBaseCaseName").val(arr[0]["name"]);
                $("#txtBaseCaseComment").val(arr[0]["comment"]);
            }
        });
    }
</script>
<style>
    .h2 {
        margin-bottom: 0;
    }

    .form-control {
        display: inline-block;
    }

    body {
        background-image: url(css/images/baseCase.jpg);
        background-repeat: no-repeat;
    }
</style>
<body>
<div class="maincontent">
    <c:choose>
        <c:when test="${caseId==0}">
            <div class="searchBox">
                <div class="search-line">
                    <label>用户名</label><select id="secUserList" class="form-control search-control"
                                              onchange="findBaseCaseList();"></select>
                </div>
                <div class="search-line">
                    <label>项目</label>
                    <select id="secProjectList" list="projectList" listKey="id" listValue="name" headerValue='请选择'
                            class="form-control search-control" onchange="findBaseCaseList();">
                        <option value='0'>All</option>
                        <c:forEach items="${projectList}" var="project">
                            <option value="${project.id}">${project.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="clear"></div>
            </div>
            <div class="maincontent" style="text-align:center;">
                <input type="button" value="查询" onclick="findBaseCaseList()" class="btn btn-default"/>
                <input type="button" value="新增基础Case" onclick="showAddBaseCasePanel()" class="btn btn-primary"/>
            </div>
        </c:when>
        <c:otherwise>
            <div class="maincontent" style="text-align:right;">
                <input type="button" value="新增基础Case" onclick="showAddBaseCasePanel()" class="btn btn-primary"/>
            </div>
        </c:otherwise>
    </c:choose>
    <div class="maincontent">
        <table id="tableBaseCaseList" class="table table-striped table-bordered">
            <thead>
            <tr>
                <th>序号</th>
                <th>Case名称</th>
                <th>描述</th>
                <th>组</th>
                <th>父级组</th>
                <th>项目</th>
                <th>用户</th>
                <th>状态</th>
                <th>前后台</th>
                <th>Sort</th>
                <th>操作</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
<div class="modal fade" id="baseCaseModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="baseCaseModalTitle">BaseCase编辑</h4>
            </div>
            <div class="modal-body">
                <input type="hidden" id="txtBaseCaseId"/>

                <div class='maincontent'>
                    <lable>所属项目</lable>
                    <select id="secCustomer" list="projectList" listKey="id" listValue="name" headerValue='请选择'
                            headerKey='' class="form-control" onchange="changeParentGroup(this);">
                        <option value='0'>请选择</option>
                        <c:forEach items="${projectList}" var="project">
                            <option value="${project.id}">${project.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class='maincontent'>
                    <lable>父级组</lable>
                    <select id="secParentGroup" onchange="changeGroup(this);" class="form-control"></select>
                </div>
                <div class='maincontent'>
                    <lable>组</lable>
                    <select id="secGroup" onchange="showBaseCaseList(this);" class="form-control"></select>
                </div>
                <div class='maincontent' id="baseCaseContainer">
                    <lable>BaseCase选择</lable>
                    <select id="secBaseCase" onchange="changeBaseCase(this)" class="form-control">
                        <option value='0'>新增基础case</option>
                    </select>
                </div>
                <div class='maincontent'>
                    <lable>状态</lable>
                    <select id="secBaseCaseStatus" class="form-control" class="form-control">
                        <option value="2">打开</option>
                        <option value="1">关闭</option>
                    </select>
                </div>
                <div class='maincontent'>
                    <lable>前台Or后台</lable>
                    <select id="secKind" class="form-control" class="form-control">
                        <option value="2">后台</option>
                        <option value="1">前台</option>
                    </select>
                </div>
                <div class='maincontent'>
                    <lable>BaseCase名称</lable>
                    <input type='text' id="txtBaseCaseName" class="form-control"/>
                </div>
                <div class='maincontent'>
                    <lable>描述</lable>
                    <input type='text' id="txtBaseCaseComment" class="form-control"/>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="addBaseCase();"
                        id="btnAddBaseCase">确定
                </button>
                <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="updateBaseCase();"
                        id="btnUpdateBaseCase">确定
                </button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

</body>
</html>