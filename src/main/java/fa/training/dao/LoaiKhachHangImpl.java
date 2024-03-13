package fa.training.dao;

import fa.training.entities.LoaiKhachHang;
import fa.training.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class LoaiKhachHangImpl implements LoaiKhachHangDAO{
    @Override
    public void luuLoaiKhachHang() {
        Session session = null;
        try {
            session = HibernateUtils.getSessionFactory().openSession();

            LoaiKhachHang loaiKhachHang1 = new LoaiKhachHang("VIP");
            LoaiKhachHang loaiKhachHang2 = new LoaiKhachHang("GOLD");
            LoaiKhachHang loaiKhachHang3 = new LoaiKhachHang("SILVER");
            LoaiKhachHang loaiKhachHang4 = new LoaiKhachHang("Vang lai");

            Transaction transaction = session.beginTransaction();
            session.save(loaiKhachHang1);
            session.save(loaiKhachHang2);
            session.save(loaiKhachHang3);
            session.save(loaiKhachHang4);
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
