package fa.training.entities;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "nhan_vien")
public class NhanVien {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "nhan_vien_id")
    private int nhanVienId;

    @Column (name = "ten_nhan_vien")
    private String tenNhanVien;

    @Column (name = "dia_chi")
    private String diaChi;

    @Column (name = "dien_thoai")
    @Pattern(regexp = "^[0-9]+$", message = "Please enter only digits from 0 to 9")
    private String dienThoai;

    @Column (name = "so_nam_kinh_nghiem")
    private int soNamKinhNghiem;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "nhanVien")
    private List<LichSuChamSoc> lichSuChamSocs;

    @Column (name = "gioi_tinh")
    @Pattern(regexp = "Nam|Nu|Khac", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Vui lòng nhập: Nam, Nu hoặc Khac cho cột giới tính")
    private String gioiTinh;

    public NhanVien(String tenNhanVien, String diaChi, String dienThoai, int soNamKinhNghiem, String gioiTinh) {
        this.tenNhanVien = tenNhanVien;
        this.diaChi = diaChi;
        this.dienThoai = dienThoai;
        this.soNamKinhNghiem = soNamKinhNghiem;
        this.gioiTinh = gioiTinh;
    }

    public NhanVien() {
    }

    public int getNhanVienId() {
        return nhanVienId;
    }

    public void setNhanVienId(int nhanVienId) {
        this.nhanVienId = nhanVienId;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
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

    public int getSoNamKinhNghiem() {
        return soNamKinhNghiem;
    }

    public void setSoNamKinhNghiem(int soNamKinhNghiem) {
        this.soNamKinhNghiem = soNamKinhNghiem;
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

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }
}
