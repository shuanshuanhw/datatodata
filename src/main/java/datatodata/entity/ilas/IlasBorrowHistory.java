package datatodata.entity.ilas;


/**
 * 功能： <p>返回读者的历史借阅记录</p>
 * 创建人：黄维
 * 时间：2023/5/16 9:41
 */

public class IlasBorrowHistory {
    private String bookRecNO; // 书目记录号
    private String barCode; // 条码号
    private String titleIdentifier;// 书名
    private String Action; // 动作
    private String duelocal; // 操作馆藏地
    private String dueman;// 操作人

    private String duetime;// 操作时间

    // 以上是必须会返回的字段
    private String volInfo;// 卷册信息
    private String singlePrice;// 单价
    private String author;// 作者
    private String callNO;// 索书号
    private String orgLib;// 所属分馆
    private String orgLocal;// 所属馆藏地

    private String memo;// 备注

    public String getBookRecNO() {
        return bookRecNO;
    }

    public void setBookRecNO(String bookRecNO) {
        this.bookRecNO = bookRecNO;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getTitleIdentifier() {
        return titleIdentifier;
    }

    public void setTitleIdentifier(String titleIdentifier) {
        this.titleIdentifier = titleIdentifier;
    }

    public String getAction() {
        return Action;
    }

    public void setAction(String action) {
        Action = action;
    }

    public String getDuelocal() {
        return duelocal;
    }

    public void setDuelocal(String duelocal) {
        this.duelocal = duelocal;
    }

    public String getDueman() {
        return dueman;
    }

    public void setDueman(String dueman) {
        this.dueman = dueman;
    }

    public String getDuetime() {
        return duetime;
    }

    public void setDuetime(String duetime) {
        this.duetime = duetime;
    }

    public String getVolInfo() {
        return volInfo;
    }

    public void setVolInfo(String volInfo) {
        this.volInfo = volInfo;
    }

    public String getSinglePrice() {
        return singlePrice;
    }

    public void setSinglePrice(String singlePrice) {
        this.singlePrice = singlePrice;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCallNO() {
        return callNO;
    }

    public void setCallNO(String callNO) {
        this.callNO = callNO;
    }

    public String getOrgLib() {
        return orgLib;
    }

    public void setOrgLib(String orgLib) {
        this.orgLib = orgLib;
    }

    public String getOrgLocal() {
        return orgLocal;
    }

    public void setOrgLocal(String orgLocal) {
        this.orgLocal = orgLocal;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
