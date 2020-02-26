$(function () {
    var init_url = '/o2o/shopadmin/getshopinitinfo';
    var register_shop_url = '/o2o/shopadmin/registershop';
    alert(init_url);
    getShopInitInfo();

    function getShopInitInfo() {
        $.getJSON(init_url, function (data) {
            if (data.success) {
                var temp_html = '';
                var temp_area_html = '';
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

        $('#submit').click(function () {
            var shop = {};
            shop.shopName = $('#shop-name').val();
            shop.shopAddr = $('#shop-addr').var();
            shop.phone = $('#shop-phone').var();
            shop.shopDesc = $('#shop-desc').var();
            shop.shopCategory = {
                shopCategoryId: $('#shop-category').find('option').not(function () {
                    return !this.selected;
                }).data('id')
            };
            shop.area = {
                areaId: $('#shop-area').find('option').not(function () {
                    return !this.selected;
                }).data('id')
            }
            var shopImg = $('#shop-img')[0].files[0];
            var formData = new FormData();
            formData.append('shopImg', shopImg);
            formData.append('shopStr', JSON.stringify(shop));
            $.ajax({
                url: register_shop_url,
                type: 'POST',
                data: formData,
                contentType: false,
                processData: false,
                cache: false,
                success: function (data) {
                    if (data.success) {
                        $.toast("提交成功")
                    } else {
                        $.toast("提交失败" + data.errMsg)
                    }
                }
            })

        })
    }
});
