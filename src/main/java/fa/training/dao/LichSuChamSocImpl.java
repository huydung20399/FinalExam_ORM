package fa.training.dao;

import fa.training.entities.DichVu;
import fa.training.entities.KhachHang;
import fa.training.entities.LichSuChamSoc;
import fa.training.entities.NhanVien;
import fa.training.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LichSuChamSocImpl implements LichSuChamSocDAO{
    private static final Scanner sc = new Scanner(System.in);
    // 3.1.2
    @Override
    public void layLSCS() {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            Query<LichSuChamSoc> query = session.createQuery("FROM LichSuChamSoc");
            List<LichSuChamSoc> lichSuChamSocs = query.list();
            int count = 0;
            System.out.println("== Lich su cham soc ==");
            for (LichSuChamSoc lichSuChamSoc : lichSuChamSocs) {
                System.out.println("[Id: " + lichSuChamSoc.getLichSuChamSocId() +
                                    ", ten khach hang: " + lichSuChamSoc.getKhachHang().getTenKhachHang() +
                                    ", ten nhan vien: " + lichSuChamSoc.getNhanVien().getTenNhanVien() +
                                    ", ten dich vu: " + lichSuChamSoc.getDichVu().getTenDichVu() +
                                    ", ngay cham soc: " + lichSuChamSoc.getNgayChamSoc() +
                                    ", trang thai: " + lichSuChamSoc.getTrangThai() +
                                    ", tong tien: " + lichSuChamSoc.getTongTien() + "]");
                System.out.println("------------------------------------");
                count++;
            }
            if (count == 0) {
                System.out.println("Hiện tại không có khách hàng nào cần chăm sóc!");
            }
        } catch (Exception e) {
            System.out.println("An error occurred during execution: " + e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    private static List<Integer> listKHIdKhongCoTrongLSCS() {
        List<Integer> list = new ArrayList<>();
        List<Integer> khachHangDaTonTai = new ArrayList<>();
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            Query<LichSuChamSoc> query = session.createQuery("FROM LichSuChamSoc");
            List<LichSuChamSoc> lichSuChamSocs = query.list();

            Query<KhachHang> query2 = session.createQuery("FROM KhachHang");
            List<KhachHang> khachHangs = query2.list();
            for (LichSuChamSoc lichSuChamSoc : lichSuChamSocs) {
                khachHangDaTonTai.add(lichSuChamSoc.getKhachHang().getKhachHangId());
            }

            for (KhachHang khachHang : khachHangs) {
                if (!khachHangDaTonTai.contains(khachHang.getKhachHangId())) {
                    list.add(khachHang.getKhachHangId());
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred during execution: " + e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return list;
    }

    // 3.1.1 danh sách khách hàng không có trong bảng
    public void danhSachKHKhongCoTrongBangLSCS() {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            Query<KhachHang> query = session.createQuery("FROM KhachHang");
            List<KhachHang> khachHangs = query.list();

            System.out.println("Danh sách khách hàng chưa có thông tin trong bảng Lich Su Cham Soc");
            for (KhachHang khachHang : khachHangs) {
                for (int id : listKHIdKhongCoTrongLSCS()) {
                    if (id == khachHang.getKhachHangId()) {
                        System.out.println("[Id: " + khachHang.getKhachHangId() +
                                ", loai khach hang: " + khachHang.getLoaiKhachHang().getMoTa() +
                                ", ten khach hang: " + khachHang.getTenKhachHang() +
                                ", gioi tinh: " + khachHang.getGioiTinh() +
                                ", dia chi: " + khachHang.getDiaChi() +
                                ", dien thoai: " + khachHang.getDienThoai() + "]");
                        System.out.println("------------------------------------");
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("An error occurred during execution: " + e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    // 3.2
    public void PhanCongChamSoc() {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            LichSuChamSoc lichSuChamSoc = new LichSuChamSoc();

            System.out.println("Nhập id khách hàng");
            int khachHangId = sc.nextInt();
            KhachHang khachHang = session.get(KhachHang.class, khachHangId);
            khachHang.setLichSuChamSocs(lichSuChamSoc);
            lichSuChamSoc.setKhachHang(khachHang);

            System.out.println("Nhập id nhân viên");
            NhanVien nhanVien = null;
            boolean check = false;
            do {
                int nhanVienId = sc.nextInt();
                nhanVien = session.get(NhanVien.class, nhanVienId);
                if (nhanVien.getGioiTinh().equals(khachHang.getGioiTinh())) {
                    check = true;
                }
                if (!check) {
                    System.out.println("Nhân viên phải cùng giới tính với khách hàng");
                }
            } while (!check);
            nhanVien.setLichSuChamSocs(lichSuChamSoc);
            lichSuChamSoc.setNhanVien(nhanVien);

            System.out.println("Nhap id dich vu");
            int dichVuId = sc.nextInt();
            DichVu dichVu = session.get(DichVu.class, dichVuId);
            dichVu.setLichSuChamSocs(lichSuChamSoc);
            lichSuChamSoc.setDichVu(dichVu);

            lichSuChamSoc.setNgayChamSoc(LocalDate.now());
            lichSuChamSoc.setTrangThai("dang duoc cham soc");
            lichSuChamSoc.setTongTien(0);

            session.save(lichSuChamSoc);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("An error occurred during execution: " + e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    // 3.3
    public void CapNhatQuaTrinhChamSoc() {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            System.out.println("Nhập id lịch sử chăm sóc bạn muốn cập nhật");
            int idLSCS = sc.nextInt();
            LichSuChamSoc lichSuChamSoc = session.get(LichSuChamSoc.class, idLSCS);

            double tongTien = 0;
            if (lichSuChamSoc.getKhachHang().getLoaiKhachHang().getMoTa().equalsIgnoreCase("VIP")) {
                tongTien = lichSuChamSoc.getDichVu().getGia() - (lichSuChamSoc.getDichVu().getGia()*0.2);
            } else if (lichSuChamSoc.getKhachHang().getLoaiKhachHang().getMoTa().equalsIgnoreCase("GOLD")) {
                tongTien = lichSuChamSoc.getDichVu().getGia() - (lichSuChamSoc.getDichVu().getGia()*0.1);
            } else if (lichSuChamSoc.getKhachHang().getLoaiKhachHang().getMoTa().equalsIgnoreCase("SILVER")) {
                tongTien = lichSuChamSoc.getDichVu().getGia() - (lichSuChamSoc.getDichVu().getGia() * 0.05);
            } else {
                tongTien = lichSuChamSoc.getDichVu().getGia();
            }


            Query<LichSuChamSoc> query = session.createQuery("UPDATE LichSuChamSoc SET trangThai = :trang_thai, tongTien = :tong_tien" +
                                                                " WHERE lichSuChamSocId = :id");
            query.setParameter("trang_thai", "da hoan tat cham soc");
            query.setParameter("tong_tien", tongTien);
            query.setParameter("id", idLSCS);
            int check = query.executeUpdate();
            if (check > 0 ) {
                System.out.println("Cập nhật thành công");
            }
            transaction.commit();
        } catch (Exception e) {
            System.out.println("An error occurred during execution: " + e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
