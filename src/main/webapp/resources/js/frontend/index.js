$(function () {
    let url = '/o2o/frontend/listmainpageinfo';

    $.getJSON(url, function (data) {
        if (data.success) {
            // let headLineList = data.headLineList;
            // let swiperHtml = '';
            // headLineList.map(function (item, index) {
            //     swiperHtml += ''+'<div class="swiper-silde img-wrap">'
            //         + '<a href="' + item.lineLink
            //         + '"external><img class="banner-img" src="' + item.lineImg
            //         + '"alt="' + item.lineName + '"></a>' + '</div>';
            // });
            // $('.swiper-wrapper').html(swiperHtml);
            // $(".swiper-container").swiper({
            //     autoplay: 3000,
            //     autoplayDisableOnInteraction: false
            // });

            let shopCategoryList = data.shopCategoryList;
            let categoryHtml = '';
            shopCategoryList.map(function (item, index) {
                categoryHtml += ''
                    + '<div class="col-5 shop-classify" data-category='
                    + item.shopCategoryId + '>' + '<div class="word">'
                    + '<p class="shop-title">' + item.shopCategoryName
                    + '</p>' + '<p class="shop-desc">'
                    + item.shopCategoryDesc + '</p>' + '</div>'
                    + '<div class="shop-classify-img-wrap">'
                    + '<img class="shop-img" src="../../' + item.shopCategoryImg
                    + '">' + '</div>' + '</div>';
            });
            $('.row').html(categoryHtml);
        }
    });
    // $('#me').click(function () {
    //     $.openPanel()
    // })
    $('.row').on('click', 'shop-classify', function (e) {
        let shopCategoryId = e.currentTarget.dataset.category;
        let newUrl = '/o2o/frontend/shoplist?parentId=' + shopCategoryId;
        window.location.href = newUrl;
    })
});