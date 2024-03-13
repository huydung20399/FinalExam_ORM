package fa.training.entities;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table (name = "lich_su_cham_soc")
public class LichSuChamSoc {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "lich_su_cham_soc_id")
    private int lichSuChamSocId;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "khach_hang_id", referencedColumnName = "khach_hang_id")
    private KhachHang khachHang;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "nhan_vien_id", referencedColumnName = "nhan_vien_id")
    private NhanVien nhanVien;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "dich_vu_id", referencedColumnName = "dich_vu_id")
    private DichVu dichVu;

    @Column (name = "ngay_cham_soc")
    private LocalDate ngayChamSoc;

    @Column (name = "trang_thai")
//    @Pattern(regexp = "dang duoc cham soc|da hoan tat cham soc", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Vui lòng nhập 'dang duoc cham soc' hoặc 'da hoan tat cham soc'")
    private String trangThai;

    @Column (name = "tong_tien")
    private double tongTien;

    public LichSuChamSoc(int lichSuChamSocId, KhachHang khachHang, NhanVien nhanVien, DichVu dichVu, LocalDate ngayChamSoc, String trangThai, double tongTien) {
        this.lichSuChamSocId = lichSuChamSocId;
        this.khachHang = khachHang;
        this.nhanVien = nhanVien;
        this.dichVu = dichVu;
        this.ngayChamSoc = ngayChamSoc;
        this.trangThai = trangThai;
        this.tongTien = tongTien;
    }

    public LichSuChamSoc() {
    }

    public int getLichSuChamSocId() {
        return lichSuChamSocId;
    }

    public void setLichSuChamSocId(int lichSuChamSocId) {
        this.lichSuChamSocId = lichSuChamSocId;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public DichVu getDichVu() {
        return dichVu;
    }

    public void setDichVu(DichVu dichVu) {
        this.dichVu = dichVu;
    }

    public LocalDate getNgayChamSoc() {
        return ngayChamSoc;
    }

    public void setNgayChamSoc(LocalDate ngayChamSoc) {
        this.ngayChamSoc = ngayChamSoc;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }
}
