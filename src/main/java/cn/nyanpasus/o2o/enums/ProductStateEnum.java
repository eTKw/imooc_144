package cn.nyanpasus.o2o.enums;

public enum ProductStateEnum {
    CHECK(0, "审核中"),
    SUCCESS(1, "成功"),
    EMPTY(-1, "失败");

    private int state;
    private String stateInfo;

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    private ProductStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public static ProductStateEnum stateOf(int state) {
        for (ProductStateEnum stateEnum : values()) {
            if (stateEnum.getState() == state) {
                return stateEnum;
            }
        }
        return null;
    }


}
