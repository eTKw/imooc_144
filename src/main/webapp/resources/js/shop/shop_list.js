$(function () {
    getlist();
    function getlist(e) {
        $.ajax({
            url: "/o2o/shopadmin/getshoplist",
            type: "get",
            dataType: "json",
            success: function (data) {
                if (data.success) {
                    handleList(data.shopList);
                    handleUser(data.user);
                }
            }
        });
    }
    function handleUser(data) {
        $('#user-name').text(data.name);
    }

    function handleList(data) {
        let html = "";
        data.map(function (item, index) {
            html += '<div class="row row-shop"><div class="col-5">'
                + item.shopName + '</div><div class="col-5">'
                + shopStatus(item.enableStatus)
                + '</div><div class="col-2">'
                + goShop(item.enableStatus, item.shopId) + '</div></div>';
        });
        $('.shop-wrap').html(html);
    }
    function shopStatus(status) {
        if (status == 0) {
            return "审核中";
        } else if (status == -1) {
            return "不可用";
        } else if (status == 1) {
            return "审核通过";
        } else {
            return "未知";
        }
    }

    function goShop(status, id) {
        if (status == 1) {
            return '<a href = "/o2o/shopadmin/shopmanagement?shopId='+id+'">进入</a>';
        } else {
            return "";
        }
    }
});