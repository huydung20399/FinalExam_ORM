package fa.training.service;

import fa.training.dao.*;
import fa.training.entities.DichVu;
import fa.training.entities.KhachHang;
import fa.training.entities.LoaiKhachHang;
import fa.training.entities.NhanVien;
import fa.training.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Scanner;
import java.util.Set;

public class Manage {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("0. Thoát");
            System.out.println("1. Yêu cầu 2: Lưu thông tin");
            System.out.println("2. Yêu cầu 3.1.1: Hiển thị khách hàng không có trong bảng Lịch sử chăm sóc");
            System.out.println("3. Yêu cầu 3.1.2: Hiển thị bảng Lịch sử chăm sóc");
            System.out.println("4. Yêu cầu 3.2: Phân công chăm sóc khách hàng");
            System.out.println("5. Yêu cầu 3.3: Hoàn tất quá trình chăm sóc");
            System.out.println("Nhập lựa chọn của bạn");
            choice = sc.nextInt();
            switch (choice) {
                case 0:
                    System.out.println("Đã đóng chương trình");
                    break;
                case 1:
                    new LoaiKhachHangImpl().luuLoaiKhachHang();
                    new KhachHangImpl().luuKhachHang();
                    new DichVuImpl().luuDichVu();
                    new NhanVienImpl().luuNhanVien();
                    break;
                case 2:
                    new LichSuChamSocImpl().danhSachKHKhongCoTrongBangLSCS();
                    break;
                case 3:
                    new LichSuChamSocImpl().layLSCS();
                    break;
                case 4:
                    new LichSuChamSocImpl().PhanCongChamSoc();
                    break;
                case 5:
                    new LichSuChamSocImpl().CapNhatQuaTrinhChamSoc();
                    break;
                default:
                    System.out.println("Nhập không chính xác. Vui lòng nhập lại");
                    break;
            }
        } while (choice != 0);
    }
}
