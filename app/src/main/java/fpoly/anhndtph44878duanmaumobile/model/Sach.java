package fpoly.anhndtph44878duanmaumobile.model;

public class Sach {
  private int masach;
  private String tensach;
  private int giathue;
  private int maloai;
  private String tenloai;


//tạo thêm tên loại sách để chạy sách in ra cả tên loại và mã loại;
  //tạo thêm số lượng đã mượn để tính tổng top 10 mượn sách
    private int soluongdamuon;


    public Sach() {
    }

    public Sach(int masach, String tensach, int giathue, int maloai) {
        this.masach = masach;
        this.tensach = tensach;
        this.giathue = giathue;
        this.maloai = maloai;
    }

    public Sach(int masach, String tensach, int soluongdamuon) {
        this.masach = masach;
        this.tensach = tensach;
        this.soluongdamuon = soluongdamuon;
    }

    public Sach(int masach, String tensach, int giathue, int maloai, String tenloai) {
        this.masach = masach;
        this.tensach = tensach;
        this.giathue = giathue;
        this.maloai = maloai;
        this.tenloai = tenloai;
    }

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }

    public int getSoluongdamuon() {
        return soluongdamuon;
    }

    public void setSoluongdamuon(int soluongdamuon) {
        this.soluongdamuon = soluongdamuon;
    }

    public int getMasach() {
        return masach;
    }

    public void setMasach(int masach) {
        this.masach = masach;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public int getGiathue() {
        return giathue;
    }

    public void setGiathue(int giathue) {
        this.giathue = giathue;
    }

    public int getMaloai() {
        return maloai;
    }

    public void setMaloai(int maloai) {
        this.maloai = maloai;
    }
}
