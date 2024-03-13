package fa.training.dao;

import fa.training.entities.DichVu;
import fa.training.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DichVuImpl implements DichVuDAO{
    private static final Scanner sc = new Scanner(System.in);
    @Override
    public void luuDichVu() {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();

            DichVu dichVu1 = new DichVu("Cham soc da", 300.000);
            DichVu dichVu2 = new DichVu("Lam trang da", 400.000);
            DichVu dichVu3 = new DichVu("Tay te bao chet", 100.000);

            Transaction transaction = session.beginTransaction();
            session.save(dichVu1);
            session.save(dichVu2);
            session.save(dichVu3);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("An error occurred during execution: " + e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    private static List<Integer> listDichVuId() {
        List<Integer> list = new ArrayList<>();
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            Query<DichVu> query = session.createQuery("FROM DichVu ");
            List<DichVu> dichVus = query.list();
            for (DichVu dichVu : dichVus) {
                list.add(dichVu.getDichVuId());
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

    public static int checkExistDichVuId() {
        int dichVuId;
        boolean check = false;
        do {
            dichVuId = sc.nextInt();
            for (int id : listDichVuId()) {
                if (id == dichVuId) {
                    check = true;
                    break;
                }
            }
            if (!check) {
                System.out.println("Id dịch vụ không tồn tại. Vui lòng nhập lại");
            }
        } while (!check);
        return dichVuId;
    }
}
