<h1 align="center">Place an Order with ActiveMQ</h1>
<div align="center">
    <img src="https://techstack-generator.vercel.app/java-icon.svg" alt="icon" width="50" height="50" />
</div>

## 🚩 Mục lục
- [Yêu cầu của bài tập](#yêu-cầu-của-bài-tập) 
- [Diagram](#diagram)
- [Được xây dựng bằng](#được-xây-dựng-bằng)
- [Các dependency sử dụng](#các-Dependency-sử-dụng)
- [Demo Chương trình](#demo-Chương-trình)

## Yêu cầu của bài tập

- [Đặc tả yêu cầu]
  ![image](https://github.com/nguyenhieu1435/MakeAnOrderWithActiveMQ/assets/70377398/786e4505-31ba-4a46-82ed-3a11f92a3648)

## Diagram

![image](https://github.com/nguyenhieu1435/MakeAnOrderWithActiveMQ/assets/70377398/ad166cb7-add3-45a1-b30b-080b505912d9)


## Được xây dựng bằng

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?logo=java&logoColor=white&style=for-the-badge)
![Spring](https://img.shields.io/badge/Spring-6DB33F.svg?style=for-the-badge&logo=Spring&logoColor=white)
![SpringBoot](https://img.shields.io/badge/Spring%20Boot-6DB33F.svg?style=for-the-badge&logo=Spring-Boot&logoColor=white)
![activemq_logo_black](https://github.com/nguyenhieu1435/MakeAnOrderWithActiveMQ/assets/70377398/8facc348-163d-4d3e-8547-726cc83ee581)
![MariaDB](https://img.shields.io/badge/MariaDB-003545?logo=mariadb&logoColor=white&style=for-the-badge)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-%23005C0F.svg?logo=Thymeleaf&logoColor=white&style=for-the-badge)

## Các Dependency sử dụng
 - spring-boot-starter-activemq
    + Maven
    ```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-activemq</artifactId>
        </dependency>
    ```
 - spring-boot-starter-web
   + Maven
    ```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
    ```
 - spring-boot-starter-mail
   + Maven
    ```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-mail</artifactId>
        </dependency>
    ```
 - spring-boot-starter-data-jpa
   + Maven
    ```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
    ```
 - spring-boot-starter-thymeleaf
   + Maven
    ```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>
    ```
 - mariadb-java-client
    + Maven
    ```xml
        <dependency>
            <groupId>org.mariadb.jdbc</groupId>
            <artifactId>mariadb-java-client</artifactId>
            <scope>runtime</scope>
        </dependency>
    ```
 - lombok
    + Maven
    ```xml
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
    ```
 - jbcrypt (mindrot)
    + Maven
    ```xml
        <dependency>
            <groupId>org.mindrot</groupId>
            <artifactId>jbcrypt</artifactId>
            <version>0.4</version>
        </dependency>
    ```
 - gson
   + Maven
    ```xml
        <dependency>
            <groupId>com.google.code.gson</groupId>
            <artifactId>gson</artifactId>
            <version>2.10.1</version>
        </dependency>
    ```

## Demo Chương trình
  * Người dùng khi truy cập trang web có thể thấy được danh sách các sản phẩm
  ![image](https://github.com/nguyenhieu1435/MakeAnOrderWithActiveMQ/assets/70377398/fa612874-d84b-4ce8-8bed-01bfc70e1c26)
  * Người dùng có thể chọn số lượng sản phẩm và thêm vào Giỏ hàng, số lượng người dùng có thể nhập vào. Nếu người dùng chưa Đăng Nhập vào click vào Add To Cart, ứng dụng sẽ chuyển đến trang Đăng Nhập.
  ![image](https://github.com/nguyenhieu1435/MakeAnOrderWithActiveMQ/assets/70377398/e496a54c-9180-4e19-80a1-a1604bbf59aa)
  ![image](https://github.com/nguyenhieu1435/MakeAnOrderWithActiveMQ/assets/70377398/2a7f06cb-611b-4ce4-9003-8314a304733c)
  * Người dùng có thể Đăng ký nếu không có tài khoản
  ![image](https://github.com/nguyenhieu1435/MakeAnOrderWithActiveMQ/assets/70377398/294fb9b1-911b-4a9d-9a6c-6bb2a8f1eaa3)
  * Sau khi người dùng Đăng nhập thành công thì có thể Đặt hàng, các sản phẩm sẽ được lưu vào Giỏ hàng
  ![image](https://github.com/nguyenhieu1435/MakeAnOrderWithActiveMQ/assets/70377398/be0bc37a-d084-4f05-b31b-a5a801a4b5c3)
  ![image](https://github.com/nguyenhieu1435/MakeAnOrderWithActiveMQ/assets/70377398/c9c43298-8ab0-46ee-91ec-237014559f81)
  ![image](https://github.com/nguyenhieu1435/MakeAnOrderWithActiveMQ/assets/70377398/11b67b40-dd8b-44e3-9b60-8ad4d296b874)
  * Khi nhấn vào Proceed to Checkout trong Cart Page, ứng dụng sẽ thực hiện Logic phía dưới, nếu số lượng đặt hàng lớn hơn số lượng có sẵn trong khi thì hệ thông sẽ thông báo "Đơn đặt hàng đã bị từ chối" đến gmail của người dùng, ngược lại nếu số lượng <= số lượng sẳn trong khi thì hệ thông sẽ gửi mail thông báo đặt hàng thành công
  ![image](https://github.com/nguyenhieu1435/MakeAnOrderWithActiveMQ/assets/70377398/1364e9d4-699d-4bb3-b4d8-c043e85bb0eb)
  ![image](https://github.com/nguyenhieu1435/MakeAnOrderWithActiveMQ/assets/70377398/91132fd1-6f16-42c1-b2f8-cfd6e5bd41eb)
    - Khi đặt thành công Số lượng đặt <= Số lượng sẵn trong kho
    ![image](https://github.com/nguyenhieu1435/MakeAnOrderWithActiveMQ/assets/70377398/905049d9-1250-4ac2-84fc-1d17e6ca5b5d)
    - Khi đặt thất bại Số lượng đặt > Số lượng sẵn trong kho
    ![image](https://github.com/nguyenhieu1435/MakeAnOrderWithActiveMQ/assets/70377398/1d72be65-7c56-473e-9377-dea87f72f43e)
    ![image](https://github.com/nguyenhieu1435/MakeAnOrderWithActiveMQ/assets/70377398/d4408c16-bf7e-4097-92b1-40d290298317)
    ![image](https://github.com/nguyenhieu1435/MakeAnOrderWithActiveMQ/assets/70377398/dc574392-6b79-4925-9f85-46073bb2389d)





