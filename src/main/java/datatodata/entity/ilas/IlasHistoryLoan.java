package datatodata.entity.ilas;


/**
 * 功能： <p></p>
 * 创建人：黄维
 * 时间：2023/5/15 9:34
 */

public class IlasHistoryLoan {
    private String bookRecNO;
    private String patronIdentifier;
    private String patronName;
    private String barCode;
    private String title;
    private String loanDate;
    private String dueDate;

    public String getBookRecNO() {
        return bookRecNO;
    }

    public void setBookRecNO(String bookRecNO) {
        this.bookRecNO = bookRecNO;
    }

    public String getPatronIdentifier() {
        return patronIdentifier;
    }

    public void setPatronIdentifier(String patronIdentifier) {
        this.patronIdentifier = patronIdentifier;
    }

    public String getPatronName() {
        return patronName;
    }

    public void setPatronName(String patronName) {
        this.patronName = patronName;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(String loanDate) {
        this.loanDate = loanDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}
