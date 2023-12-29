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
    private String cardNo;

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

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

    @Override
    public String toString() {
        return "OverBook{" +
                "phone='" + phone + '\'' +
                ", LoanDate='" + LoanDate + '\'' +
                ", ReturnTime='" + ReturnTime + '\'' +
                ", Title='" + Title + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if(this.getPhone().equals(((OverBook)obj).getPhone()) && this.getLoanDate().equals(((OverBook)obj).getLoanDate()) && this.getReturnTime().equals(((OverBook)obj).getReturnTime()))
        {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
