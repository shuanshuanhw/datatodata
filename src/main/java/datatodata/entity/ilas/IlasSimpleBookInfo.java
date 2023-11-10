package datatodata.entity.ilas;


/**
 * 功能： <p></p>
 * 创建人：黄维
 * 时间：2023/5/16 14:22
 */

public class IlasSimpleBookInfo {
    private IlasBook ilasBook;// 书目对象数组
    private String itemCount;// 数据总数
    private String pages;// 总页数
    private String pagerows;// 每页行数
    private String curpage;// 当前页码

    public IlasBook getIlasBook() {
        return ilasBook;
    }

    public void setIlasBook(IlasBook ilasBook) {
        this.ilasBook = ilasBook;
    }

    public String getItemCount() {
        return itemCount;
    }

    public void setItemCount(String itemCount) {
        this.itemCount = itemCount;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getPagerows() {
        return pagerows;
    }

    public void setPagerows(String pagerows) {
        this.pagerows = pagerows;
    }

    public String getCurpage() {
        return curpage;
    }

    public void setCurpage(String curpage) {
        this.curpage = curpage;
    }
}
