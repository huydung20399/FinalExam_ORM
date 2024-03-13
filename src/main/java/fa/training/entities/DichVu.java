package fa.training.entities;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "dich_vu")
public class DichVu {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "dich_vu_id")
    private int dichVuId;

    @Column (name = "ten_dich_vu")
    private String tenDichVu;

    @Column (name = "gia")
    private double gia;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "dichVu")
    private List<LichSuChamSoc> lichSuChamSocs;

    public DichVu(String tenDichVu, double gia) {
        this.tenDichVu = tenDichVu;
        this.gia = gia;
    }

    public DichVu() {
    }

    public int getDichVuId() {
        return dichVuId;
    }

    public void setDichVuId(int dichVuId) {
        this.dichVuId = dichVuId;
    }

    public String getTenDichVu() {
        return tenDichVu;
    }

    public void setTenDichVu(String tenDichVu) {
        this.tenDichVu = tenDichVu;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
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
