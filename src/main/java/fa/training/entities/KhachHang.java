package fa.training.entities;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "khach_hang")
public class KhachHang {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "khach_hang_id")
    private int khachHangId;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "loai_khach_hang_id", referencedColumnName = "loai_khach_hang_id")
    private LoaiKhachHang loaiKhachHang;

    @Column(name = "ten_khach_hang")
    private String tenKhachHang;

    @Column (name = "gioi_tinh")
    @Pattern(regexp = "Nam|Nu|Khac", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Vui lòng nhập: Nam, Nu hoặc Khac cho cột giới tính")
    private String gioiTinh;

    @Column (name = "dia_chi")
    private String diaChi;

    @Column (name = "dien_thoai")
    @Pattern(regexp = "^[0-9]+$", message = "Please enter only digits from 0 to 9")
    private String dienThoai;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "khachHang")
    private List<LichSuChamSoc> lichSuChamSocs;

    public KhachHang(LoaiKhachHang loaiKhachHang, String tenKhachHang, String gioiTinh, String diaChi, String dienThoai) {
        this.loaiKhachHang = loaiKhachHang;
        this.tenKhachHang = tenKhachHang;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.dienThoai = dienThoai;
    }

    public KhachHang() {
    }

    public int getKhachHangId() {
        return khachHangId;
    }

    public void setKhachHangId(int khachHangId) {
        this.khachHangId = khachHangId;
    }

    public LoaiKhachHang getLoaiKhachHang() {
        return loaiKhachHang;
    }

    public void setLoaiKhachHang(LoaiKhachHang loaiKhachHang) {
        this.loaiKhachHang = loaiKhachHang;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public List<LichSuChamSoc> getLichSuChamSocs() {
        return lichSuChamSocs;
    }

    public void setLichSuChamSocs(LichSuChamSoc lichSuChamSocs) {
        if (this.lichSuChamSocs == null) {
            this.lichSuChamSocs = new ArrayList<>();
        }
        this.lichSuChamSocs.add(lichSuChamSocs);
    }
}
