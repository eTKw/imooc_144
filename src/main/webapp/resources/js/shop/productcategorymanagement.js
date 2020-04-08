$(function () {
    let listUrl = '/o2o/shopadmin/getproductcategorylist';
    let addUrl = '/o2o/shopadmin/addproductcategories';
    let deleteUrl = '/o2o/shopadmin/removeproductcategory';


    getList();

    function getList() {
        $.getJSON(
            listUrl,
            function (data) {
                if (data.success) {
                    let dataList = data.data;
                    $('.category-wrap').html('');
                    let tempHtml = '';
                    dataList.map(
                        function (item, index) {
                            tempHtml += '' + '<div class="row row-product-category now">'
                                + '<div class="col-5 product-category-name">'
                                + item.productCategoryName
                                + '</div>'
                                + '<div class="col-5">'
                                + item.priority
                                + '</div>'
                                + '<div class="col-2"><a href="#" class="btn delete" data-id="'
                                + item.productCategoryId
                                + '">删除</a></div>'
                                + '</div>';
                        }
                    );
                    $('.category-wrap').append(tempHtml);
                }
            }
        )
    }

    $('#new').click(
        function () {
            let tempHtml = '<div class="row row-product-category temp">'
                + '<div class="col-5"><input type="text" class="category-input category" placeholder="分类"></div>'
                + '<div class="col-5"><input type="number" class="category-input priority" placeholder="优先级"></div>'
                + '<div class="col-2"><a href="#" class="btn delete">删除</a></div>' + '</div>';
            $('.category-wrap').append(tempHtml);
        }
    );
    $('#submit').click(function () {
        let tempArr = $('.temp');
        let productCategoryList = [];
        tempArr.map(function (index, item) {
            let tempObj = {};
            tempObj.productCategoryName = $(item).find('.category').val();
            tempObj.priority = $(item).find('.priority').val();
            if (tempObj.productCategoryName && tempObj.priority) {
                productCategoryList.push(tempObj);
            }
        });
        $.ajax({
            url: addUrl,
            type: 'POST',
            data: JSON.stringify(productCategoryList),
            contentType: 'application/json',
            success: function (data) {
                if (data.success) {
                    $.toast('提交成功');
                    getList();
                } else {
                    $.toast('提交失败');
                }
            }
        })
    })
    $('.category-wrap').on('click', '.row-product-category.temp .delete',
        function (e) {
            console.log($(this).parent().parent());
            $(this).parent().parent().remove();
        });
    $('.category-wrap').on('click', '.row-product-category.now .delete',
        function (e) {
            let target = e.currentTarget;
            $.confirm({
                title: 'Confirm!',
                content: 'Simple confirm!',
                buttons: {
                    confirm: function () {
                        $.alert('Confirmed!');
                        $.ajax({
                            url: deleteUrl,
                            type: 'POST',
                            data: {
                                productCategoryId: target.dataset.id
                            },
                            dataType: 'json',
                            success: function (data) {
                                if (data.success) {
                                    $.toast('删除成功');
                                    getList();
                                } else {
                                    $.toast('删除失败');
                                }
                            }
                        })
                    },
                    cancel: function () {
                        $.alert('Canceled!');
                    }
                }
            });
        })
});