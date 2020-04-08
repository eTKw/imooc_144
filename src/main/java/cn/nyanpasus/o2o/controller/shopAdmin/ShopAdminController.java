package cn.nyanpasus.o2o.controller.shopAdmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "shopadmin", method = RequestMethod.GET)
public class ShopAdminController {

    @RequestMapping(value = "/shopoperation")
    public String shopOperation() {
        return "shop/shop_operation";
    }

    @RequestMapping(value = "/shoplist")
    public String shopList() {
        return "shop/shop_list";
    }

    @RequestMapping(value = "/shopmanagement")
    public String shopManagement() {
        return "shop/shop_management";
    }

    @RequestMapping(value = "/productoperation")
    public String productOperation() {
        return "shop/product_operation";
    }
}
