package fa.training.dao;

import fa.training.entities.KhachHang;
import fa.training.entities.LoaiKhachHang;
import fa.training.utils.HibernateUtils;
import org.hibernate.Interceptor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class KhachHangImpl implements KhachHangDAO{
    private static final Scanner sc = new Scanner(System.in);
    @Override
    public void luuKhachHang() {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            LoaiKhachHang loaiKhachHangVIP = (LoaiKhachHang) session.createQuery("FROM LoaiKhachHang WHERE moTa = :mo_ta")
                                            .setParameter("mo_ta", "VIP")
                                            .uniqueResult();

            LoaiKhachHang loaiKhachHangGOLD = (LoaiKhachHang) session.createQuery("FROM LoaiKhachHang WHERE moTa = :mo_ta")
                                            .setParameter("mo_ta", "GOLD")
                                            .uniqueResult();

            LoaiKhachHang loaiKhachHangSILVER = (LoaiKhachHang) session.createQuery("FROM LoaiKhachHang WHERE moTa = :mo_ta")
                                            .setParameter("mo_ta", "SILVER")
                                            .uniqueResult();

            LoaiKhachHang loaiKhachHangVangLai = (LoaiKhachHang) session.createQuery("FROM LoaiKhachHang WHERE moTa = :mo_ta")
                                            .setParameter("mo_ta", "Vang lai")
                                            .uniqueResult();


            KhachHang khachHang1 = new KhachHang(loaiKhachHangVIP, "Nguyen Thi Van", "Nu", "Quy Nhon", "0984857464");
            KhachHang khachHang2 = new KhachHang(loaiKhachHangVIP, "Le Thanh Hai", "Nam", "Quy Nhon", "0984857464");
            KhachHang khachHang3 = new KhachHang(loaiKhachHangGOLD, "Nguyen Nhu Khang", "Nu", "Quy Nhon", "0984857464");
            KhachHang khachHang4 = new KhachHang(loaiKhachHangGOLD, "Nguyen Bao Truc", "Nu", "Tuy Phuoc", "0984857464");
            KhachHang khachHang5 = new KhachHang(loaiKhachHangSILVER, "Tran Van Minh", "Nam", "Quy Nhon", "0984857464");
            KhachHang khachHang6 = new KhachHang(loaiKhachHangSILVER, "Hoang Thi Kha", "Nu", "An Nhon", "0984857464");
            KhachHang khachHang7 = new KhachHang(loaiKhachHangVangLai, "Nguyen Thi B", "Nu", "An Nhon", "0984857464");
            KhachHang khachHang8 = new KhachHang(loaiKhachHangVangLai, "Le Khanh Huyen", "Nu", "An Nhon", "0984857464");

            Transaction transaction = session.beginTransaction();
            session.save(khachHang1);
            session.save(khachHang2);
            session.save(khachHang3);
            session.save(khachHang4);
            session.save(khachHang5);
            session.save(khachHang6);
            session.save(khachHang7);
            session.save(khachHang8);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("An error occurred during execution: " + e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    private static List<Integer> listKhachHangId() {
        List<Integer> list = new ArrayList<>();
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            Query<KhachHang> query = session.createQuery("FROM KhachHang ");
            List<KhachHang> khachHangs = query.list();
            for (KhachHang khachHang : khachHangs) {
                list.add(khachHang.getKhachHangId());
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

    public static int checkExistKhachHangId() {
        int khachHangId;
        boolean check = false;
        do {
            khachHangId = sc.nextInt();
            for (int id : listKhachHangId()) {
                if (id == khachHangId) {
                    check = true;
                    break;
                }
            }
            if (!check) {
                System.out.println("Id khách hàng không tồn tại. Vui lòng nhập lại");
            }
        } while (!check);
        return khachHangId;
    }
}
