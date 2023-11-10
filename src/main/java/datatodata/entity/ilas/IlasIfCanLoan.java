package datatodata.entity.ilas;


/**
 * 功能： <p></p>
 * 创建人：黄维
 * 时间：2023/5/11 16:49
 */

public class IlasIfCanLoan {
    // 0 不可借
    // 1 可借
    private String OK;
    private String barcode;
    // 返回显示信息
    private String screenMessage;
    private String identifier;

    public String getOK() {
        return OK;
    }

    public void setOK(String OK) {
        this.OK = OK;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getScreenMessage() {
        return screenMessage;
    }

    public void setScreenMessage(String screenMessage) {
        this.screenMessage = screenMessage;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}
