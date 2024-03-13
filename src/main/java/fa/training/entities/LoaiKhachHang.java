package fa.training.entities;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "loai_khach_hang")
public class LoaiKhachHang {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "loai_khach_hang_id")
    private int loaiKhachHangId;

    @Column (name = "mo_ta")
    @Pattern(regexp = "VIP|GOLD|Silver|Vang lai", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Vui lòng nhập: VIP, GOLD, SILVER hoặc Vãng lai cho cột mô tả")
    private String moTa;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "loaiKhachHang")
    private List<KhachHang> khachHangs;

    public LoaiKhachHang(String moTa) {
        this.moTa = moTa;
    }

    public LoaiKhachHang() {
    }

    public int getLoaiKhachHangId() {
        return loaiKhachHangId;
    }

    public void setLoaiKhachHangId(int loaiKhachHangId) {
        this.loaiKhachHangId = loaiKhachHangId;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public List<KhachHang> getKhachHangs() {
        return khachHangs;
    }

    public void setKhachHangs(KhachHang khachHangs) {
        if (this.khachHangs == null) {
            this.khachHangs = new ArrayList<>();
        }
        this.khachHangs.add(khachHangs);
    }
}
