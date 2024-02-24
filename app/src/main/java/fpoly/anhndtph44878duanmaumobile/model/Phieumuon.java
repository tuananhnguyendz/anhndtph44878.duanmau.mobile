package fpoly.anhndtph44878duanmaumobile.model;

public class Phieumuon {
    private int mapm;
    private int matv;
    private String hoten;
    private String matt;
    private String hotentt;
    private int masach;
    private String tensach;
    private String ngay;
    private int trasach;
    private int tienthue;

    public Phieumuon() {
    }

    public Phieumuon(int mapm, int matv, String hoten, String matt, String hotentt, int masach, String tensach, String ngay, int trasach, int tienthue) {
        this.mapm = mapm;
        this.matv = matv;
        this.hoten = hoten;
        this.matt = matt;
        this.hotentt = hotentt;
        this.masach = masach;
        this.tensach = tensach;
        this.ngay = ngay;
        this.trasach = trasach;
        this.tienthue = tienthue;
    }

    public Phieumuon(int matv, String matt, int masach, String ngay, int trasach, int tienthue) {
        this.matv = matv;
        this.matt = matt;
        this.masach = masach;
        this.ngay = ngay;
        this.trasach = trasach;
        this.tienthue = tienthue;
    }

    public int getMapm() {
        return mapm;
    }

    public void setMapm(int mapm) {
        this.mapm = mapm;
    }

    public int getMatv() {
        return matv;
    }

    public void setMatv(int matv) {
        this.matv = matv;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getMatt() {
        return matt;
    }

    public void setMatt(String matt) {
        this.matt = matt;
    }

    public String getHotentt() {
        return hotentt;
    }

    public void setHotentt(String hotentt) {
        this.hotentt = hotentt;
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

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public int getTrasach() {
        return trasach;
    }

    public void setTrasach(int trasach) {
        this.trasach = trasach;
    }

    public int getTienthue() {
        return tienthue;
    }

    public void setTienthue(int tienthue) {
        this.tienthue = tienthue;
    }
    //pm.mapm,pm.matv,tv.hoten,pm.matt,tt.hotentt,pm.masach,sc.tensach,pm.ngay,pm.trasach,pm.tienthue



}
