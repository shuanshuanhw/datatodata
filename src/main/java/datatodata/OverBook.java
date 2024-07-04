package datatodata;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private String name;
    private String barCode;// 书的唯一编码，可以用来分别不同的书

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
                ", cardNo='" + cardNo + '\'' +
                ", name='" + name + '\'' +
                ", barCode='" + barCode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if(this.getBarCode().equals(((OverBook)obj).getBarCode()))
        {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    public static void main(String[] args) {
        OverBook overBook = new OverBook();
        overBook.setName("huangwei");
        overBook.setPhone("150075725254444");
        overBook.setBarCode("aaa5");

        OverBook overBook2 = new OverBook();
        overBook2.setName("huangwei");
        overBook2.setPhone("15007572525");
        overBook2.setBarCode("aaa");

        OverBook overBook3 = new OverBook();
        overBook3.setName("huangwei");
        overBook3.setPhone("15007572525");
        overBook3.setBarCode("aaa");

        OverBook overBook4 = new OverBook();
        overBook4.setName("huangwei");
        overBook4.setPhone("15007572525");
        overBook4.setBarCode("aaa");

        List<OverBook> newList = new ArrayList<>();
        newList.add(overBook);
        newList.add(overBook2);
        newList.add(overBook3);
        newList.add(overBook4);

        List<OverBook> newList2 = newList.stream().distinct().collect(Collectors.toList());
        newList.clear();
        newList.addAll(newList);

        System.out.println(newList2);

    }
}
