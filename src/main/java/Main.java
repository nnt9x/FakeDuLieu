import com.github.javafaker.Faker;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        List<Human> humanList = new ArrayList<>();
        Faker faker = new Faker(new Locale("vi"));

        for (int i = 0; i < 1000; i++) {
            // Fake thong tin
            Human human = new Human();
            human.setId(i);

            human.setFirstName(faker.name().lastName());
            human.setLastName(faker.name().firstName());
            human.setCity(faker.address().cityName());
            human.setGender(faker.number().numberBetween(0, 2));
            human.setAge(faker.number().numberBetween(10, 51));
            human.setSalary(faker.number().numberBetween(10, 3001));
            // Them vao humanList
            humanList.add(human);
        }

        // IN lai du lieu
        humanList.stream().forEach(p -> System.out.println(p));

        // a. Co bao nhieu nam tren 18 tuoi
        // Bo loc -> dem
        long count = humanList.stream()
                .filter(human -> human.getAge() > 18 && human.getGender() == 1)
                .count();
        System.out.println("a. So nguoi tren 18 va la nam: " + count);

        // b. Co bao nhieu nu va ten dem la thi?
        long count1 = humanList.stream()
                .filter(h -> h.getGender() == 0 && h.getFullName().contains("thị"))
                .count();

        System.out.println("b. Số người là nữ và có tên đệm thị: " + count1);

        // c. Lọc ra những người họ “Nguyễn” và sắp xếp theo thứ tự chữ cái.
        System.out.println("c. Danh sach họ Nguyễn: ");
        humanList.stream()
                .filter(h -> h.getLastName().equalsIgnoreCase("Nguyễn"))
                .sorted()
                .forEach(h -> System.out.println(h));

        // d. In ra danh sách những người độ tuổi từ 20-30
        System.out.println("d.In ra danh sách những người độ tuổi từ 20-30:");
        humanList.stream()
                .filter(h -> h.getAge() >= 20 && h.getAge() <= 30)
                .forEach(h -> System.out.println(h));

        // e. Đếm số lượng thành phố có trong danh sách trên. (gợi ý: distinct)
        long count2 = humanList.stream()
                .map(h -> h.getCity())
                .distinct()
                .count();
        System.out.printf("Có tất cả %d thành phố \n",count2);

        // f. 10 nguoi dau tien
        humanList.stream().limit(10)
                .forEach(e -> System.out.println(e));

        // h. Tính mức lương trung bình của nam ở độ tuổi từ 20-40
        System.out.println("h.Tính mức lương trung bình của nam ở độ tuổi từ 20-40");
        final double[] tongLuong = {0};
        final int[] soNguoi = {0};

        humanList.stream()
                .filter(h-> h.getAge()>= 20 && h.getAge() <= 40)
                .forEach(h -> {
                    soNguoi[0]++;
                    tongLuong[0] = tongLuong[0] + h.getSalary();
                });

        System.out.println("Trung binh luong: "+tongLuong[0]/ soNguoi[0]);

        // i. Sắp xếp danh sách theo độ tuổi.
        System.out.println("i.Sắp xếp danh sách theo độ tuổi.");
        humanList.stream()
                .sorted((o1, o2) -> {
                    if (o1.getAge() > o2.getAge()) {
                        return 1;
                    } else if (o1.getAge() < o2.getAge()) {
                        return -1;
                    }
                    return 0;
                })
                .forEach(e -> System.out.println(e));
    }
}
