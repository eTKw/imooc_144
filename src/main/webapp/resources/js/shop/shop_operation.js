$(function () {
    let shopId = getQueryString("shopId");
    let isEdit = shopId?true: false;
    let init_url = '/o2o/shopadmin/getshopinitinfo';
    let register_shop_url = '/o2o/shopadmin/registershop';
    let shopInfoUrl = "/o2o/shopadmin/getshopbyid?shopId=" + shopId;
    let editShopUrl = "/o2o/shopadmin/modifyshop";
    if (!isEdit) {
        getShopInitInfo();
    } else {
        getShopInfo(shopId);
    }


    function getShopInfo(shopId) {
        $.getJSON(shopInfoUrl, function (data) {
            if (data.success) {
                let shop = data.shop;
                $("#shop-name").val(shop.shopName);
                $("#shop-addr").val(shop.shopAddr);
                $("#shop-phone").val(shop.phone);
                $("#shop-desc").val(shop.shopDesc);
                let shopCategory = '<option data-id="'
                    + shop.shopCategory.shopCategoryId + '" selected>'
                    + shop.shopCategory.shopCategoryName + '</option>';
                let tmpAreaHtml = '';
                data.areaList.map(function (item, index) {
                    tmpAreaHtml += '<option data-id="' + item.areaId + '">'
                        + item.areaName + '</option>'
                });
                $('#shop-category').html(shopCategory);
                $('#shop-category').attr('disabled', 'disabled');
                $('#shop-area').html(tmpAreaHtml);
                $("#shop-area option[data-id='" + shop.area.areaId +"']").attr('selected', 'selected');
            }
        })
    }

    function getShopInitInfo() {
        $.getJSON(init_url, function (data) {
            if (data.success) {
                let temp_html = '';
                let temp_area_html = '';
                data.shopCategoryList.map(function (item, index) {
                    temp_html += '<option data_id="' + item.shopCategoryId + '">' + item.shopCategoryName + '</option>'
                });
                data.areaList.map(function (item, index) {
                    temp_area_html += '<option data-id="' + item.areaId + '">' + item.areaName + '</option>'
                });
                $('#shop-category').html(temp_html);
                $('#shop-area').html(temp_area_html)
            }
        });
    }

        $('#submit').click(function () {
        // $("body").on('click', "#submit", function () {
            let shop = {};
            if (isEdit) {
                shop.shopId = shopId;
            }
            shop.shopName = $('#shop-name').val();
            shop.shopAddr = $('#shop-addr').val();
            shop.phone = $('#shop-phone').val();
            shop.shopDesc = $('#shop-desc').val();
            shop.shopCategory = {
                shopCategoryId: $('#shop-category').find('option').not(function () {
                    return !this.selected;
                }).data('id')
            };
            shop.area = {
                areaId: $('#shop-area').find('option').not(function () {
                    return !this.selected;
                }).data('id')
            };
            let shopImg = $('#shop-img')[0].files[0];
            let formData = new FormData();
            formData.append('shopImg', shopImg);
            console.log(formData.get("shopImg"));
            formData.append('shopStr', JSON.stringify(shop));
            console.log(formData.get("shopStr"));
            let verifyCodeActual = $('#kaptcha').val();
            if (!verifyCodeActual) {
                alert('请输入验证码!');
                return
            }
            formData.append('verifyCodeActual', verifyCodeActual);
            $.ajax({
                url: isEdit?editShopUrl:register_shop_url,
                type: 'POST',
                data: formData,
                contentType: false,
                processData: false,
                cache: false,
                success: function (data) {
                    if (data.success) {
                        $.toast("提交成功");
                    } else {
                        $.toast("提交失败" + data.errMsg);
                    }
                    $("#captcha_img").click();
                }
            })

        })
});
