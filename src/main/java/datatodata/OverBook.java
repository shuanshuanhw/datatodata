package datatodata;

/**
 * 功能： <p></p>
 * 创建人：黄维
 * 时间：2023/11/29 11:03
 */

public class OverBook {
    private String phone;
    private String LoanDate;
    private String ReturnTime;
    private String Title;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLoanDate() {
        return LoanDate;
    }

    public void setLoanDate(String loanDate) {
        LoanDate = loanDate;
    }

    public String getReturnTime() {
        return ReturnTime;
    }

    public void setReturnTime(String returnTime) {
        ReturnTime = returnTime;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
