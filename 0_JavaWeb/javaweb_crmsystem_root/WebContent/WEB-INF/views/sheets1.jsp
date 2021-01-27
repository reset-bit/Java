<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <meta name="renderer" content="webkit" />
  <meta http-equiv="X-UA-Compatible" content="IE=Edge" />

  <head>
    <style type="text/css" id="alertifyCSS"></style>
    <title>企客宝客户管理系统-工单一览</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="/crmsystem_root/static/css/vendor/bootstrap.min.css">
    <link rel="stylesheet" href="/crmsystem_root/static/css/vendor/alertify.min.css">
    <link rel="stylesheet" href="/crmsystem_root/static/css/vendor/toastr.min.css">
    <link rel="stylesheet" href="/crmsystem_root/static/css/vendor/bootstrap-treeview.min.css">
    <link rel="stylesheet" href="/crmsystem_root/static/css/vendor/asScrollable.min.css">
    <link rel="stylesheet" href="/crmsystem_root/static/css/vendor/asProgress.min.css">
    <link rel="stylesheet" href="/crmsystem_root/static/css/iconfont.css">
    <link rel="stylesheet" href="/crmsystem_root/static/css/bootstrap-extend.css">
    <link rel="stylesheet" href="/crmsystem_root/static/css/main.css">
    <script type="text/javascript" src="/crmsystem_root/static/script/vendor/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="/crmsystem_root/static/script/vendor/jquery-migrate-3.0.0.min.js"></script>
    <script type="text/javascript" src="/crmsystem_root/static/script/vendor/bootstrap.min.js"></script>
    <script type="text/javascript" src="/crmsystem_root/static/script/vendor/alertify.min.js"></script>
    <script type="text/javascript" src="/crmsystem_root/static/script/vendor/toastr.min.js"></script>
    <script type="text/javascript" src="/crmsystem_root/static/script/vendor/bootstrap-treeview.min.js"></script>
    <script type="text/javascript" src="/crmsystem_root/static/script/vendor/jquery-asScrollbar.min.js"></script>
    <script type="text/javascript" src="/crmsystem_root/static/script/vendor/jquery-asScrollable.min.js"></script>
    <script type="text/javascript" src="/crmsystem_root/static/script/vendor/jquery-asProgress.min.js"></script>
    <script type="text/javascript" src="/crmsystem_root/static/script/vendor/typeahead.bundle.min.js"></script>
    <script type="text/javascript" src="/crmsystem_root/static/script/vendor/handlebars.min.js"></script>
    <script type="text/javascript" src="/crmsystem_root/static/script/common.js"></script>
    <script type="text/javascript" src="/crmsystem_root/static/script/entity.js"></script>
    <style type="text/css">
      .sindu_dragger {
        list-style: none;
        margin: 0;
        padding: 0;
        overflow: hidden;
        box-sizing: border-box
      }

      .sindu_handle {
        cursor: move
      }

      .sindu_dragger li {
        margin: 0;
        padding: 0;
        list-style: none;
        text-align: inherit
      }

      .sindu_dragger li table,
      .sindu_dragger td,
      .sindu_dragger th,
      .sindu_dragger tr {
        box-sizing: border-box
      }

      .gu-mirror {
        list-style: none
      }

      .sindu_dragger.sindu_column li {
        float: left
      }

      .sindu_dragging .sindu_origin_table {
        visibility: hidden
      }

      .gu-mirror {
        position: fixed !important;
        margin: 0 !important;
        z-index: 9999 !important;
        opacity: .8
      }

      .gu-mirror li {
        margin: 0;
        padding: 0;
        list-style: none;
        text-align: inherit
      }

      .gu-mirror li table,
      .gu-mirror td,
      .gu-mirror th,
      .gu-mirror tr {
        box-sizing: border-box
      }

      .gu-hide {
        display: none !important
      }

      .gu-unselectable {
        -webkit-user-select: none !important;
        -moz-user-select: none !important;
        -ms-user-select: none !important;
        user-select: none !important
      }

      .gu-transit {
        opacity: .5
      }
    </style>
  </head>

<body class="app-worksheet">
  <div class="page narrow">
    <div class="aside">
      <div class="aside-header">
        <a href="#"><img class="brand-img" src="/crmsystem_root/static/images/logo-72.png"></a>
      </div>
      <div class="aside-navi-container">
        <div id="aside-navi-wrap" class="aside-navi-wrap asScrollable is-enabled asScrollable-vertical">
          <div class="asScrollable-container" style="height: 607px; width: 87px;">
            <div class="asScrollable-content" style="width: 70px;">
              <ul class="aside-navi">
                <li>
                  <a id="aside-navi-workbench" href="workbench.html" data-tooltip="tooltip" data-placement="right"
                    data-container="body" data-original-title="工作台">
                    <i class="iconfont icon-workbench"></i> <span>工作台</span></a>
                </li>
                <li>
                  <a id="aside-navi-customer" href="customer.html" data-tooltip="tooltip" data-placement="right"
                    data-container="body" data-original-title="客户">
                    <i class="iconfont icon-company"></i> <span>客户</span></a>
                </li>
                <li>
                  <a id="aside-navi-publicCustomer" href="publicCustomer.html" data-tooltip="tooltip"
                    data-placement="right" data-container="body" data-original-title="公海">
                    <i class="iconfont icon-publicGroup"></i> <span>公海</span></a>
                </li>
                <li>
                  <a id="aside-navi-contact" href="contact.html" data-tooltip="tooltip" data-placement="right"
                    data-container="body" data-original-title="联系人">
                    <i class="iconfont icon-contacts"></i> <span>联系人</span></a>
                </li>
                <li>
                  <a id="aside-navi-weixin" href="weixin.html" data-tooltip="tooltip" data-placement="right"
                    data-container="body" data-original-title="微信">
                    <i class="iconfont icon-weixin"></i> <span>微信</span></a>
                </li>
                <li>
                  <a id="aside-navi-deal" href="deal.html" data-tooltip="tooltip" data-placement="right"
                    data-container="body" data-original-title="销售机会">
                    <i class="iconfont icon-deal"></i> <span>销售机会</span></a>
                </li>
                <li>
                  <a id="aside-navi-contract" href="contract.html" data-tooltip="tooltip" data-placement="right"
                    data-container="body" data-original-title="合同">
                    <i class="iconfont icon-contract"></i> <span>合同</span></a>
                </li>
                <li>
                  <a id="aside-navi-payment" href="payment.html" data-tooltip="tooltip" data-placement="right"
                    data-container="body" data-original-title="款项">
                    <i class="iconfont icon-payment"></i> <span>款项</span></a>
                </li>

                <li>
                  <a id="aside-navi-note" href="note.html" data-tooltip="tooltip" data-placement="right"
                    data-container="body" data-original-title="跟进记录">
                    <i class="iconfont icon-note2"></i> <span>跟进记录</span></a>
                </li>
                <li>
                  <a class="selected" id="aside-navi-worksheet" href="worksheet.html" data-tooltip="tooltip"
                    data-placement="right" data-container="body" data-original-title="工单">
                    <i class="iconfont icon-worksheet"></i> <span>工单</span></a>
                </li>
                <li>
                  <a id="aside-navi-workReport" href="workReport.html" data-tooltip="tooltip" data-placement="right"
                    data-container="body" data-original-title="工作汇报">
                    <i class="iconfont icon-workReport"></i> <span>工作汇报</span></a>
                </li>
                <li>
                  <a id="aside-navi-product" href="product.html" data-tooltip="tooltip" data-placement="right"
                    data-container="body" data-original-title="产品">
                    <i class="iconfont icon-product"></i> <span>产品</span></a>
                </li>
                <li>
                  <a id="aside-navi-callCenter" href="records.html" data-tooltip="tooltip" data-placement="right"
                    data-container="body" data-original-title="呼叫中心/短信">
                    <i class="iconfont icon-tel-busy"></i> <span>呼叫中心</span></a>
                </li>
                <li>
                  <a id="aside-navi-statistics" href="customer.html" data-tooltip="tooltip" data-placement="right"
                    data-container="body" data-original-title="统计分析">
                    <i class="iconfont icon-statistics"></i> <span>统计分析</span></a>
                </li>
              </ul>
            </div>
          </div>
          <div class="asScrollable-bar asScrollable-bar-vertical asScrollable-bar-hide" draggable="false">
            <div class="asScrollable-bar-handle" style="height:517.684px;"></div>
          </div>
        </div>
      </div>
    </div>
    <div class="page-main">
      <div class="page-main-header">
        <nav class="navbar navbar-default" role="navigation">
          <div class="container-fluid">
            <div class="narrow"><a href="#" id="navi-narrow-link" data-narrow="1"><i class="iconfont"></i></a></div>
            <div class="navbar-header">工单一览</div>
            <div class="collapse navbar-collapse navbar-collapse-toolbar">
              <div class="navbar-search">
                <form>
                  <div class="form-group">
                    <div class="input-search">
                      <i class="search-icon iconfont icon-search"></i>
                      <span class="twitter-typeahead" style="position: relative; display: inline-block;">
                        <input type="text" class="form-control typeahead tt-hint" data-step="1"
                          data-intro="快捷高效的模糊检索功能，帮您快速找到想要查看的客户" readonly="" autocomplete="off" spellcheck="false"
                          tabindex="-1"
                          style="position: absolute; top: 0px; left: 0px; border-color: transparent; box-shadow: none; opacity: 1; background: none 0% 0% / auto repeat scroll padding-box border-box rgba(0, 0, 0, 0);"
                          dir="ltr"><input type="text" class="form-control typeahead tt-input" id="navbar-search-term"
                          placeholder="输入客户名快速检索..." data-step="1" data-intro="快捷高效的模糊检索功能，帮您快速找到想要查看的客户"
                          autocomplete="off" spellcheck="false" dir="auto"
                          style="position: relative; vertical-align: top; background-color: transparent;">
                        <pre aria-hidden="true"
                          style="position: absolute; visibility: hidden; white-space: pre; font-family: &quot;Microsoft YaHei&quot;, simsun; font-size: 18px; font-style: normal; font-variant: normal; font-weight: 400; word-spacing: 0px; letter-spacing: 0px; text-indent: 0px; text-rendering: auto; text-transform: none;"></pre>
                        <div class="tt-menu"
                          style="position: absolute; top: 100%; left: 0px; z-index: 100; display: none;">
                          <div class="tt-dataset tt-dataset-search-cutomers"></div>
                        </div>
                      </span>
                    </div>
                  </div>
                </form>
              </div>

              <ul class="nav navbar-toolbar navbar-right navbar-toolbar-right">
                <li class="dropdown web-phone-dialpad-container">
                  <a data-toggle="dropdown" href="javascript:void(0)" title="呼叫中心" aria-expanded="false"
                    data-animation="slide-bottom" role="button">
                    <i class="font-size-22 iconfont icon-tel" aria-hidden="true"></i>
                  </a>
                  <div class="dropdown-menu dropdown-menu-right web-phone-panel" role="menu" id="web-phone-panel">
                    <div class="web-phone-header" role="presentation">
                      <div class="pull-right margin-top-10"><span data-status="0" id="callcenter-seat-status"
                          class="offline">离线</span>
                      </div>
                      <h5>呼叫中心 <span id="callcenter-phone"></span></h5>
                    </div>
                    <div class="web-phone-dialpad">
                      <div class="web-phone-dialpad-input-container">
                        <input pattern="\d*" type="tel" placeholder="" id="dialpad-number-input">
                        <a class="dialpad-number-backspace" id="dialpad-number-backspace" href="#">
                          <i class="font-size-26 iconfont icon-backspace"></i>
                        </a>
                      </div>

                      <ul class="web-phone-dialpad-number-container" id="web-phone-dialpad-number-container">
                        <li data-value="1">1</li>
                        <li data-value="2">2</li>
                        <li data-value="3">3</li>
                        <li data-value="4">4</li>
                        <li data-value="5">5</li>
                        <li data-value="6">6</li>
                        <li data-value="7">7</li>
                        <li data-value="8">8</li>
                        <li data-value="9">9</li>
                        <li data-value="*">*</li>
                        <li data-value="0">0</li>
                        <li data-value="#">#</li>
                      </ul>

                      <div class="text-center padding-bottom-20">
                        <a id="dialpad-call-btn" class="dialpad-call-btn disabled" href="#">
                          <i class="iconfont icon-tel"></i>
                        </a>
                        <div id="dialpad-message" class="margin-top-10 text-danger"></div>
                      </div>
                    </div>
                  </div>
                </li>
                <li class="dropdown notification">
                  <a data-toggle="dropdown" href="javascript:void(0)" title="通知提醒" aria-expanded="false"
                    data-animation="slide-bottom" role="button">
                    <i class="font-size-22 iconfont icon-bell-black" aria-hidden="true"></i>
                    <span class="badge badge-danger up hide" id="notificationNum"></span>
                  </a>
                  <ul class="dropdown-menu dropdown-menu-right dropdown-menu-media" role="menu">
                    <li class="dropdown-menu-header" role="presentation">
                      <h5>通知提醒</h5>
                    </li>
                    <li class="list-group" role="presentation">
                      <div data-role="container">
                        <div data-role="content">
                          <a class="list-group-item" role="menuitem"
                            href="/customer?ownerUserId=QVtaSg%3D%3D&amp;endNextContactDate=2018-09-11">
                            <div class="media">
                              <div class="media-left padding-right-10">
                                <i class="icon iconfont icon-company bg-red-600 white icon-circle"
                                  aria-hidden="true"></i>
                              </div>
                              <div class="media-body">
                                <h6 class="media-heading">截至到今天需要需要联系的客户</h6>
                                <time class="media-meta">合计 <span id="needContactCustomerNum">0</span></time>
                              </div>
                            </div>
                          </a>
                          <a class="list-group-item" role="menuitem" href="/task?startDate=2018-09-11">
                            <div class="media">
                              <div class="media-left padding-right-10">
                                <i class="icon iconfont icon-task bg-red-600 white icon-circle" aria-hidden="true"></i>
                              </div>
                              <div class="media-body">
                                <h6 class="media-heading">截至到今天未完成的任务</h6>
                                <time class="media-meta">合计 <span id="unfinishedTaskNum">0</span></time>
                              </div>
                            </div>
                          </a>
                          <a class="list-group-item" href="/contract?expireDay=30" role="menuitem">
                            <div class="media">
                              <div class="media-left padding-right-10">
                                <i class="icon iconfont icon-contract bg-red-600 white icon-circle"
                                  aria-hidden="true"></i>
                              </div>
                              <div class="media-body">
                                <h6 class="media-heading">30天内到期的合同</h6>
                                <time class="media-meta">合计 <span id="expiredContractNum">0</span></time>
                              </div>
                            </div>
                          </a>
                        </div>
                      </div>
                    </li>
                    <li class="dropdown-menu-footer" role="presentation">
                      <a href="/task" role="menuitem">查看所有任务提醒</a>
                    </li>
                  </ul>
                </li>
                <li class="dropdown">
                  <a class="navbar-avatar dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false"
                    data-step="2" data-position="left" data-intro="点击这里可以进行系统设置(比如创建部门，员工等)以及个人设置(比如修改登录密码，头像等)">
                    <span class="avatar avatar-online">
                      <img class="brand-img" src="/crmsystem_root/static/images/person-avatar.png">
                      <i></i>
                    </span>
                  </a>
                  <ul class="dropdown-menu" role="menu">
                    <li role="presentation">
                      <a role="menuitem" data-toggle="modal" data-target="#curemp">
                        <i class="icon iconfont icon-me" aria-hidden="true"></i> 个人信息</a>
                    </li>

                    <li role="presentation">
                      <a href="/setting" role="menuitem">
                        <i class="icon iconfont icon-system-setting" aria-hidden="true"></i> 系统设置</a>
                    </li>

                    <li role="presentation">
                      <a href="https://qikebao.kf5.com/forum/view/1047193/" role="menuitem" target="_blank">
                        <i class="icon iconfont icon-help" aria-hidden="true"></i> 在线帮助</a>
                    </li>
                    <li role="presentation">
                      <a href="#" data-toggle="modal" data-target="#downloadAppModal" role="menuitem">
                        <i class="icon iconfont icon-app" aria-hidden="true"></i> 下载手机App</a>
                    </li>
                    <li class="divider" role="presentation"></li>
                    <li role="presentation">
                      <a href="/crmsystem_root/login.jsp" role="menuitem">
                        <i class="icon iconfont icon-logout" aria-hidden="true"></i> 退出</a>
                    </li>
                  </ul>
                </li>
              </ul>
            </div>
          </div>
        </nav>

        <script type="text/javascript">
          /*<![CDATA[*/
          $(function () {
            loadNotification();
            $("#aside-navi-wrap").asScrollable({
              contentSelector: ">",
              containerSelector: ">"
            });
            $("#navi-narrow-link").click(function (e) {
              e.preventDefault();
              $("#aside-navi-wrap").asScrollable('destroy');
              var _narrow = $(this).data("narrow");
              if (_narrow == "1") {
                $(this).data("narrow", "0");
                $("body>div.page").removeClass("narrow");
              } else {
                $(this).data("narrow", "1");
                $("body>div.page").addClass("narrow");
              }
              setTimeout(function () {
                $("#aside-navi-wrap").asScrollable({
                  contentSelector: ">",
                  containerSelector: ">"
                });
              }, 500);
            });

            $('.navbar .notification').on('shown.bs.dropdown', function () {
              $(this).find('li.list-group').asScrollable({
                contentSelector: ">",
                containerSelector: ">"
              });
              loadNotification();
            });

            var customersBloodhound = new Bloodhound({
              datumTokenizer: Bloodhound.tokenizers.obj.whitespace('text'),
              queryTokenizer: Bloodhound.tokenizers.whitespace,
              remote: {
                url: '/customer/search/all?term=%QUERY',
                wildcard: '%QUERY'
              }
            });
            customersBloodhound.initialize();

            $('#navbar-search-term').typeahead({
              minLength: 2
            }, {
              name: 'search-cutomers',
              display: 'text',
              limit: 20,
              source: customersBloodhound,
              templates: {
                empty: [
                  '<div class="tt-suggestion empty-message">',
                  '没有找到符合条件的客户信息',
                  '</div>'
                ].join('\n'),
                suggestion: Handlebars.compile('<div><i class="iconfont icon-{{icon}} font-size-18"></i> {{text}}</div>')
              }
            });

            $('#navbar-search-term').bind('typeahead:select', function (ev, suggestion) {
              var _url = "";
              if (suggestion.type == ENTITY_CUSTOMER) {
                _url = "/customer/detail/";
              } else {
                _url = "/contact/detail/";
              }
              _url = _url + suggestion.id;
              window.location = _url;
            });

            callcenter.init();
          });

          function loadNotification() {
            $.ajax({
              url: '/notification?random=' + Math.random(),
              type: 'GET',
              dataType: 'json',
              timeout: 60000,
              success: function (json) {
                if (json.code <= 0) {
                  return;
                }
                $("#needContactCustomerNum").text(json.items.needContactCustomerNum);
                $("#unfinishedTaskNum").text(json.items.unfinishedTaskNum);
                $("#expiredContractNum").text(json.items.expiredContractNum);
                var _totalNum = json.items.needContactCustomerNum + json.items.unfinishedTaskNum + json.items.expiredContractNum;
                if (_totalNum > 0) {
                  $("#notificationNum").removeClass("hide").text(_totalNum);
                } else {
                  $("#notificationNum").addClass("hide");
                }
              }
            });
          }

          /*]]>*/
        </script>
        <script type="text/javascript" src="/script/callcenter.js?ver=1.11.0.1"></script>

        <div class="modal fade" id="downloadAppModal" aria-hidden="true" role="dialog" data-backdrop="static"
          data-keyboard="false" tabindex="-1">
          <div class="modal-dialog modal-center">
            <div class="modal-content">
              <div class="modal-header">
                <button type="button" class="close" aria-hidden="true" data-dismiss="modal">×</button>
                <h4 class="modal-title">下载安装企客宝旗舰版App</h4>
              </div>
              <div class="modal-body padding-horizontal-20">
                <div class="padding-20">
                  <img src="/images/appqrcode.png?ver=1.11.0.1" width="200" height="200">
                  <p class="padding-top-10 text-muted">微信扫一扫下载</p>
                </div>
              </div>
            </div>
          </div>
        </div>

      </div>
      <div class="page-main-container flex-container">
        <div class="page-main-aside">
          <section class="page-aside-section">
            <h5 class="page-aside-title">工单一览</h5>
            <div class="list-group" id="worksheet-navi">
          <FORM METHOD=POST ACTION="" onclick="setBg()">
            <a class="list-group-item active" href="/crmsystem_root/sheet/ListOp1" data-type="1">
              <span class="list-group-item-content">我提交的工单</span>
            </a>
            <a class="list-group-item" href="/crmsystem_root/sheet/ListOp2" data-type="2">
              <span class="list-group-item-content">我受理中的工单</span>
              
            </a>
            <a class="list-group-item" href="/crmsystem_root/sheet/ListOp3" data-type="3">
              <span class="list-group-item-content">我的已完结工单</span>
            </a>
            <a class="list-group-item" href="/crmsystem_root/sheet/ListOp4" data-type="4">
              <span class="list-group-item-content">我回复过的工单</span>
            </a>
            <a class="list-group-item" href="/crmsystem_root/sheet/ListOp5" data-type="5">
              <span class="list-group-item-content">已分配未受理的工单</span>
              
            </a>
            <a class="list-group-item" href="/crmsystem_root/sheet/sheetList" data-type="0">
              <span class="list-group-item-content">全部的工单</span>
            </a>
            </FORM>
          </div>
        </div>
        <div class="page-main-content">

          <div class="page-main-actions">
          <button type="button" data-toggle="modal" data-target="#worksheetModal" class="btn btn-success btn-sm pull-right">
            创建工单
          </button>
          <form action="/crmsystem_root/sheet/search" method="post" class="form-inline" id="search-worksheet-form">
            <div class="form-group">
              <label class="control-label padding-right-10" for="search-name">标题</label>
              <input class="form-control" name="search-name" id="search-name">
            </div>
            
            <div class="form-group padding-left-10">
              <label class="control-label padding-right-10" for="search-createDate">创建时间</label>
              <div class="input-group input-daterange" id="search-createDate">
                <input type="text" class="form-control" name="search-startCreateDate" id="search-startCreateDate" placeholder="yyyy-MM-dd HH:mm:ss">
                <div class="input-group-addon">到</div>
                <input type="text" class="form-control" name="search-endCreateDate" id="search-endCreateDate" placeholder="yyyy-MM-dd HH:mm:ss">
              </div>
            </div> 

            <div class="form-group padding-left-10">
              <input type="submit" value ="搜索" class="btn btn-success btn-sm" />
            </div>
         </form>
        </div>
          <div class="table-responsive page-content-table padding-top-20" id="entity-item-list">
            <table class="table table-pointer table-striped nowrap" id="itable">
              <thead>
                <tr>
                  <th class="cell-80">编号</th>
                  <th>标题</th>
                  <th class="cell-80">状态</th>
                  <th class="cell-100">发起人</th>
                  <th class="cell-120">创建时间</th>
                  <th class="cell-120">完结时间</th>
                  <th class="cell-120">操作</th>
                  <th></th>
                </tr>
              </thead>
              <tbody id="worksheet-body">
                <%int n = 0; %>
                <c:forEach items="${sheets}" var="s">
		            <tr>
		              <td>${s.id}</td>
		              <td>${s.title}</td>
		              <td>${s.status}</td>
		              <td>${s.originator}</td>
		              <td>${s.createtime}</td>
		              <td>${s.accepttime}</td>
		              <td><a href="editworksheet?id=${s.id}&opration=${s.opration}">${s.opration}</a></td>
		              <td><a href="/crmsystem_root/sheet/todelete?id=${s.id}"
        onclick ="javascript:return confirmDel('确定要删除该工单吗')" class="btn btn-success btn-sm">删除</a></td>
		            </tr>
		            <%++n;%>
		         </c:forEach>
		     	 <%
		     	 	request.setAttribute("n", n);
		     	 %>
		      </tbody>
              <tfoot>
				 <tr><td colspan="7"><div id="barcon" name="barcon" align="center"></div></td></tr>
			  </tfoot>
            </table>
            <!------------------------------------------------------>
            <script>
            	window.onload=function(){
    				var curemp = '<%=session.getAttribute("empname")%>';
    				if(curemp == 'null'){
    					alert("请先登录!");
    					window.location.href="/crmsystem_root/login.jsp";
    				}
            		goPage(1);
            	}
    			function isnull(val) {
    				var str = val.replace(/(^\s*)|(\s*$)/g, '');//去除空格
    				if (str == '' || str == undefined || str == null) {
    					return true;
    				} else {
    					return false;
    				}
    			}
                //实质：数据行全部加载，通过是否显示属性完成分页功能
                function goPage(pno){//当前页
               	  var itable = document.getElementById("itable");
				  var num = ${n};//总记录数
                  var pageSize = 10;//每页显示行数
                  var totalPage = 0;//总页数
                  if(num/pageSize > parseInt(num/pageSize)){
                      totalPage = parseInt(num/pageSize) + 1;
                  }
                  else{
                      totalPage = parseInt(num/pageSize);
                  }
                  
                  var currentPage = pno;//当前页数
                  var startRow = (currentPage - 1) * pageSize + 1;
                  var endRow = currentPage * pageSize;
                  endRow = (endRow > num)? num : endRow;
                  console.log(endRow);
                  //遍历显示数据实现分页
                  for(var i = 1; i < (num + 1); i++){
                    var irow = itable.rows[i];//-1
                    if(i >= startRow && i <= endRow){
                      irow.style.display = "table-row";
                    }else{
                      irow.style.display = "none";
                    }
                  }
                  
                  var tempStr = "共"+num+"条记录   分"+totalPage+"页   当前第"+currentPage+"页";
                  if(currentPage>1){
                    tempStr += "<button class=/\"btn btn-primary\" type=\"button\" onClick=\"goPage("+(1)+")\">首页</button>";//<a href=\"#\"
                    tempStr += "<button class=/\"btn btn-primary\" type=\"button\" onClick=\"goPage("+(currentPage-1)+")\">上一页</button>"
                  }else{
                    tempStr += "<button class=/\"btn btn-primary\" type=\"button\" \">首页</button>";//<a href=\"#\"
                    tempStr += "<button class=/\"btn btn-primary\" type=\"button\" \">上一页</button>"
                  }
                  if(currentPage<totalPage){
                    tempStr += "<button class=/\"btn btn-primary\" type=\"button\" onClick=\"goPage("+(currentPage+1)+")\">下一页</button>";
                    tempStr += "<button class=/\"btn btn-primary\" type=\"button\" onClick=\"goPage("+(totalPage)+")\">尾页</button>";
                  }else{
                    tempStr += "<button class=/\"btn btn-primary\" type=\"button\" \">下一页</button>";
                    tempStr += "<button class=/\"btn btn-primary\" type=\"button\" \">尾页</button>";
                  }
                  document.getElementById("barcon").innerHTML = tempStr;
                }
            </script>
            <!------------------------------------------------------>
          </div>

<!--           <div class="pagination"> -->
<!--             <div class="page-bottom" id="item-pageNavi" style="display: none;"></div> -->
<!--           </div> -->

<!--           <div class="loading" id="search-loading" style="display: none;"> -->
<!--             <div class="liner"> -->
<!--               <h3>加载中...</h3> -->
<!--               <img alt="加载中" src="/images/dots-white.gif"> -->
<!--             </div> -->
<!--           </div> -->

<!--           <div id="worksheet-blank" class="loading" style="display: block;"> -->
<!--             <div class="liner"> -->
<!--               <h3>没有找到相关工单信息</h3> -->
<!--             </div> -->
<!--           </div> -->

        </div>
      </div>
    </div>
  </div>
 <div class="modal fade" id="curemp" aria-hidden="true" role="dialog" data-backdrop="static"
    data-keyboard="false" tabindex="-1">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" aria-hidden="true" data-dismiss="modal">×</button>
          <h4 class="modal-title">当前用户信息</h4>
        </div>
        <div class="modal-body padding-horizontal-20">
            <div class="form-group">
              <label class="col-sm-3 control-label" for="worksheet-title">用户名称</label>
              <div class="col-sm-9">
              <%String curemp=(String)session.getAttribute("empname");out.println(curemp);%>
              </div>
            </div>
			<div class="modal-footer">
			<a class="btn btn-primary" data-dismiss="modal" href="javascript:void(0)">确定</a>
			</div>
		</div>
		</div>
		</div>
		</div>

  <div class="modal fade" id="worksheetModal" aria-hidden="true" role="dialog" data-backdrop="static"
    data-keyboard="false" tabindex="-1">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" aria-hidden="true" data-dismiss="modal">×</button>
          <h4 class="modal-title">创建工单</h4>
        </div>
        <div class="modal-body padding-horizontal-20">
<!---------------------------------------------------------------------->
          <form class="form-horizontal" id="form-add-worksheet" method="POST" action="/crmsystem_root/sheet/addworksheet">
            <input type="hidden" name="worksheet-attachmentFiles" id="worksheet-attachmentFiles" value="">
            <div class="form-group">
              <label class="col-sm-3 control-label" for="worksheet-title">工单标题</label>
              <div class="col-sm-9">
                <input class="form-control" id="title" name="title" data-field-required="true"
                  value="">
                <small class="help-block"></small>
              </div>
            </div>

            <div class="form-group">
              <label class="col-sm-3 control-label" for="worksheet-templateId">工单模板编号</label>
              <div class="col-sm-9">
				<select class="form-control" name="template"
				id="template" data-plugin="select2" data-language="zh-CN" data-width="100%"
				data-field-required="true" tabindex="-1" aria-hidden="true">
					<option>-请选择-</option>
					<option>1</option>
					<option>2</option>
					<option>3</option>
					<option>4</option>
				</select>
              </div>
            </div>

            <div class="form-group">
			  <label class="col-sm-3 control-label" for="worksheet-customerId">关联客户</label>
			  <div class="col-sm-9" id="more">
                <input class="form-control" id="associates" name="associate" data-field-required="true"
                  value="">
			    <button class="btn btn-primary" id="add-worksheet-btn" type="button" onclick="add()">+</button>
                <small class="help-block"></small>
			  </div>
            </div>

            <div class="form-group">
              <label class="col-sm-3 control-label" for="worksheet-priority">重要度(1级最高)</label>
              <div class="col-sm-9">
                <select class="form-control" name="importance" id="importance"
                  data-plugin="select2" data-language="zh-CN" data-width="100%" tabindex="-1" aria-hidden="true">
                  <option>-请选择-</option>
                  <option>1</option>
				  <option>2</option>
                  <option>3</option>
				  <option>4</option>
				</select>
              </div>
			</div>
			
			<div class="modal-footer">
			<button class="btn btn-primary" id="add-worksheet-btn" type="submit" onclick="return check()">确定</button>
			<a class="btn btn-sm btn-white btn-pure" data-dismiss="modal" href="javascript:void(0)">取消</a>
			</div>
		  </form>
      </div>
    </div>
  </div>
		  <script>
			function isnull(val) {
				var str = val.replace(/(^\s*)|(\s*$)/g, '');//去除空格
				if (str == '' || str == undefined || str == null) {
					return true;
				} else {
					return false;
				}
			}
			function check(){
				var select_importance = document.getElementById("importance");
				var index_importance = select_importance.selectedIndex;
				var select_template = document.getElementById("template");
				var index_template = select_template.selectedIndex;
				var title = document.getElementById("title");
				var associates = document.getElementsByName("associate");
				var curemp = '<%=session.getAttribute("empname")%>';
				if(isnull(title.value) |
					index_importance == 0 |
					index_template == 0){
					alert("信息填写不完整，请检查后再次尝试提交！");
					return false;
				}
				if(associates.length == 1 && isnull(associates[0].value)){
					alert("信息填写不完整，请检查后再次尝试提交！");
					return false;
				}
// 				alert(associates[0].value);
				for(var i = 0; i < associates.length; i++){
					for(var j = i + 1; j < associates.length; j++){
						if(associates[i].value == associates[j].value) {
							alert("不能重复关联人，请检查后再次尝试提交！");
							return false;
						}
					}
					if(associates[i].value == curemp){
						alert("不能关联自身，请检查后再次尝试提交！");
						return false;
					}
				}
				return true;
			}
			function add(){ 			
				var li = document.getElementById("li");
				var lix = document.createElement("li");
				var texts = document.createElement('input');
				texts.setAttribute('type', 'text');//输入框类型
				texts.setAttribute('name', "associate");//输入框名字
				texts.setAttribute('class', "form-control");//输入框类别
				//把文本添加到li下面
				lix.appendChild(texts);
				//获取到div
				var more = document.getElementById("more"); 
				more.insertBefore(lix, li);
				return true;
			}
		</script>
	<!---------------------------------------------------------------------->

  <div class="modal fade" id="updateNoteModal" aria-hidden="true" role="dialog" data-backdrop="static"
    data-keyboard="false" tabindex="-1">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" aria-hidden="true" data-dismiss="modal">×</button>
          <h4 class="modal-title">修改跟进记录</h4>
        </div>
        <div class="modal-body padding-horizontal-20">
        </div>
        <div class="modal-footer">
          <button class="btn btn-primary" id="update-note-btn" type="button">确定</button>
          <a class="btn btn-sm btn-white btn-pure" data-dismiss="modal" href="javascript:void(0)">取消</a>
        </div>
      </div>
    </div>
  </div>

  <div class="modal fade" id="transferModal" aria-hidden="true" role="dialog" data-backdrop="static"
    data-keyboard="false" tabindex="-1">
    <div class="modal-dialog modal-center">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" aria-hidden="true" data-dismiss="modal">×</button>
          <h4 class="modal-title">指定工单受理人</h4>
        </div>
        <div class="modal-body padding-horizontal-20">
          <form id="form-transfer-worksheet">
            <div class="form-group">
              <label class="control-label" for="worksheet-newProcessUserId">新的受理人</label>
              <select class="form-control select2-hidden-accessible" name="worksheet-newProcessUserId"
                id="worksheet-newProcessUserId" data-plugin="select2" data-language="zh-CN" data-width="100%"
                tabindex="-1" aria-hidden="true">
                <option value="QVtaSg==">孙伟</option>
              </select><span class="select2 select2-container select2-container--default" dir="ltr"
                style="width: 100%;"><span class="selection"><span class="select2-selection select2-selection--single"
                    role="combobox" aria-haspopup="true" aria-expanded="false" tabindex="0"
                    aria-labelledby="select2-worksheet-newProcessUserId-container"><span
                      class="select2-selection__rendered" id="select2-worksheet-newProcessUserId-container"
                      title="孙伟">孙伟</span><span class="select2-selection__arrow" role="presentation"><b
                        role="presentation"></b></span></span></span><span class="dropdown-wrapper"
                  aria-hidden="true"></span></span>
            </div>
          </form>
        </div>
        <div class="modal-footer">
          <button class="btn btn-primary" id="worksheet-transfer-btn" type="button">确定</button>
          <a class="btn btn-sm btn-white btn-pure" data-dismiss="modal" href="javascript:void(0)">取消</a>
        </div>
      </div>
    </div>
  </div>

  <div class="modal fade" id="changeCustomerModal" aria-hidden="true" role="dialog" data-backdrop="static"
    data-keyboard="false" tabindex="-1">
    <div class="modal-dialog modal-center">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" aria-hidden="true" data-dismiss="modal">×</button>
          <h4 class="modal-title">修改所属客户</h4>
        </div>
        <div class="modal-body padding-horizontal-20">
          <form id="form-change-customer">
            <div class="form-group">
              <label class="control-label" for="entity-change-customerId">所属客户</label>
              <select class="form-control select2-hidden-accessible" name="entity-change-customerId"
                id="entity-change-customerId" data-language="zh-CN" data-width="100%" data-field-required="false"
                tabindex="-1" aria-hidden="true">
              </select><span class="select2 select2-container select2-container--default" dir="ltr"
                style="width: 100%;"><span class="selection"><span class="select2-selection select2-selection--single"
                    role="combobox" aria-haspopup="true" aria-expanded="false" tabindex="0"
                    aria-labelledby="select2-entity-change-customerId-container"><span
                      class="select2-selection__rendered" id="select2-entity-change-customerId-container"><span
                        class="select2-selection__placeholder">请输入客户名检索</span></span><span
                      class="select2-selection__arrow" role="presentation"><b
                        role="presentation"></b></span></span></span><span class="dropdown-wrapper"
                  aria-hidden="true"></span></span>
              <small class="help-block"></small>
            </div>
          </form>
        </div>
        <div class="modal-footer">
          <button class="btn btn-primary" id="change-customer-btn" type="button" data-name="worksheet">确定</button>
          <a class="btn btn-sm btn-white btn-pure" data-dismiss="modal" href="javascript:void(0)">取消</a>
        </div>
      </div>
    </div>
  </div>

  <script type="text/javascript">
    /*<![CDATA[*/
    var _loginUserId = "QVtaSg==";
    var _pageSize = 10;

    var _users = [{ "name": "\u5B59\u4F1F", "imgPath": "images/person-avatar.png", "valid": true, "encodedGroupId": "", "encodedId": "QVtaSg==" }];
    var _groups = [];
    var _targetType = 11;
    var _targetId = "";
    var _isSinglePage = false;
  /*]]>*/
  </script>

  <link rel="stylesheet" href="/crmsystem_root/static/css/vendor/bootstrap-datepicker.min.css">
  <link rel="stylesheet" href="/crmsystem_root/static/css/vendor/bootstrap-datetimepicker.min.css">
  <link rel="stylesheet" href="/crmsystem_root/static/css/vendor/bootstrap-select2.min.css">
  <link rel="stylesheet" href="/crmsystem_root/static/css/vendor/slidePanel.min.css">
  <link rel="stylesheet" href="/crmsystem_root/static/css/vendor/bootstrap-editable.min.css">
  <link rel="stylesheet" href="/crmsystem_root/static/css/vendor/magnific-popup.min.css">
  <script type="text/javascript" src="/crmsystem_root/static/script/vendor/moment.min.js"></script>
  <script type="text/javascript" src="/crmsystem_root/static/script/vendor/bootstrap-datepicker.min.js"></script>
  <script type="text/javascript" src="/crmsystem_root/static/script/vendor/bootstrap-datetimepicker.min.js"></script>
  <script type="text/javascript" src="/crmsystem_root/static/script/vendor/bootstrap-select2.min.js"></script>
  <script type="text/javascript" src="/crmsystem_root/static/script/vendor/jquery-slidePanel.min.js"></script>
  <script type="text/javascript" src="/crmsystem_root/static/script/vendor/bootstrap-editable.min.js"></script>
  <script type="text/javascript" src="/crmsystem_root/static/script/vendor/plupload.full.min.js"></script>
  <script type="text/javascript" src="/crmsystem_root/static/script/vendor/jquery.magnific-popup.min.js"></script>
  <script type="text/javascript" src="/crmsystem_root/static/script/uploader.js"></script>
  <script type="text/javascript" src="/crmsystem_root/static/script/worksheet.js"></script>
  <script type="text/javascript" src="/crmsystem_root/static/script/worksheet-list.js"></script>
  <script type="text/javascript" src="/crmsystem_root/static/script/pageNavi.js"></script>

</body>

</html>