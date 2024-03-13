package fa.training.dao;

import fa.training.entities.NhanVien;
import fa.training.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NhanVienImpl implements NhanVienDAO{
    private static final Scanner sc = new Scanner(System.in);
    @Override
    public void luuNhanVien() {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();

            NhanVien nhanVien1 = new NhanVien("Le Thanh Huong", "Quy Nhon", "0123456789", 3, "Nu");
            NhanVien nhanVien2 = new NhanVien("Le Khanh Huyen", "Quy Nhon", "0123456789", 1, "Nu");
            NhanVien nhanVien3 = new NhanVien("Tran Thi Lan", "An Nhon", "0123456789", 2, "Nu");
            NhanVien nhanVien4 = new NhanVien("Nguyen Huong Lien", "Phu Cat", "0123456789", 3, "Nu");
            NhanVien nhanVien5 = new NhanVien("Hoang Thanh Khai", "Quy Nhon", "0123456789", 1, "Nam");
            NhanVien nhanVien6 = new NhanVien("Nguyen Minh Khanh", "Tuy Phuoc", "0123456789", 1, "Nu");

            Transaction transaction = session.beginTransaction();
            session.save(nhanVien1);
            session.save(nhanVien2);
            session.save(nhanVien3);
            session.save(nhanVien4);
            session.save(nhanVien5);
            session.save(nhanVien6);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("An error occurred during execution: " + e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    private static List<Integer> listNhanVienId() {
        List<Integer> list = new ArrayList<>();
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            Query<NhanVien> query = session.createQuery("FROM NhanVien ");
            List<NhanVien> nhanViens = query.list();
            for (NhanVien nhanVien : nhanViens) {
                list.add(nhanVien.getNhanVienId());
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

    public static int checkExistNhanVienId() {
        int nhanVienId;
        boolean check = false;
        do {
            nhanVienId = sc.nextInt();
            for (int id : listNhanVienId()) {
                if (id == nhanVienId) {
                    check = true;
                    break;
                }
            }
            if (!check) {
                System.out.println("Id nhân viên không tồn tại. Vui lòng nhập lại");
            }
        } while (!check);
        return nhanVienId;
    }
}
