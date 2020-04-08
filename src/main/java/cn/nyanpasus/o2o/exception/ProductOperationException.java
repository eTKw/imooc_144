package cn.nyanpasus.o2o.exception;

public class ProductOperationException extends RuntimeException {

    private static final long serialVersionUID = -576796163180866577L;

    public ProductOperationException(String msg) {
        super(msg);
    }
}
