package datatodata.entity.ilas;


/**
 * 功能： <p>返回某个读者现在借书记录的单体</p>
 * 创建人：黄维
 * 时间：2023/5/16 9:17
 */
public class IlasBorrowBook {

    private String bookRecNO; // 书目记录号
    private String barCode; // 条码号
    private String titleIdentifier;// 书名
    private String loanCount; // 续借次数
    private String maxLoanCount; // 最大借书数量
    private String loanDate;// 借书日期
    private String returnDate;// 应还日期
    // 以上是必须会返回的字段
    private String volInfo;//   卷册信息
    private String singlePrice;// 单价
    private String author;// 作者
    private String callNO;// 索书号
    private String orgLib;// 所属分馆
    private String orgLocal;// 所属馆藏地

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

    public String getLoanCount() {
        return loanCount;
    }

    public void setLoanCount(String loanCount) {
        this.loanCount = loanCount;
    }

    public String getMaxLoanCount() {
        return maxLoanCount;
    }

    public void setMaxLoanCount(String maxLoanCount) {
        this.maxLoanCount = maxLoanCount;
    }

    public String getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(String loanDate) {
        this.loanDate = loanDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
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
}
