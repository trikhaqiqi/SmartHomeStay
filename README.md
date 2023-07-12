# Smart Home Stay - README

Selamat datang di Smart Home Stay! Aplikasi ini adalah sebuah platform pengelolaan penginapan berbasis rumah pintar. Dengan menggunakan aplikasi ini, Anda dapat dengan mudah memesan kamar, melakukan pembayaran, dan mengelola akun pengguna. Aplikasi ini menggunakan REST API yang dibangun dengan menggunakan framework Spring Boot dan terintegrasi dengan Midtrans sebagai solusi pembayaran.

## Persyaratan Sistem
Sebelum menjalankan aplikasi Smart Home Stay, pastikan komputer Anda memenuhi persyaratan sistem berikut:

- Java Development Kit (JDK) 8 atau yang lebih baru.
- Maven - alat manajemen dependensi dan pembangunan proyek.
- Database MySQL.

## Menjalankan Aplikasi
Berikut adalah langkah-langkah untuk menjalankan aplikasi Smart Home Stay di komputer Anda:

1. **Clone repository:** Mulailah dengan meng-clone repository Smart Home Stay ke komputer lokal Anda.

```bash
git clone https://github.com/trikhaqiqi/SmartHomeStay.git
```

2. **Konfigurasi database:** Buatlah database PostgreSQL di komputer Anda dan konfigurasikan pengaturan koneksi database di file `application.properties` yang terletak di folder `src/main/resources`. Gantilah nilai berikut sesuai dengan pengaturan database Anda:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/smarthome?useSSL=false&serverTimezone=UTC
spring.datasource.username=username_database
spring.datasource.password=password_database
```

3. **Pengaturan Midtrans:** Dalam aplikasi ini, kami menggunakan Midtrans sebagai solusi pembayaran. Untuk mengintegrasikan aplikasi dengan Midtrans, Anda perlu melakukan langkah-langkah berikut:

   - Buat akun Midtrans di [https://midtrans.com/](https://midtrans.com/) dan dapatkan kunci sandbox Midtrans (Server Key dan Client Key).
   - Gantilah nilai kunci-kunci ini di file `application.properties`:

   ```properties
   midtrans.server-key=kunci_sandbox_server
   midtrans.client-key=kunci_sandbox_client
   ```

4. **Menjalankan aplikasi:** Buka terminal dan navigasikan ke direktori root proyek Smart Home Stay. Jalankan perintah berikut untuk membangun dan menjalankan aplikasi menggunakan Maven:

```bash
mvn spring-boot:run
```

Aplikasi akan memulai proses kompilasi dan akan dijalankan di server lokal Anda.

5. **Mengakses aplikasi:** Setelah aplikasi berhasil dijalankan, Anda dapat mengakses aplikasi Smart Home Stay melalui peramban web dengan URL [http://localhost:9035](http://localhost:9035).

## Menggunakan Aplikasi
Setelah berhasil mengakses aplikasi Smart Home Stay, Anda dapat melakukan langkah-langkah berikut:

- Buatlah akun pengguna baru atau masuk dengan menggunakan akun yang sudah ada.
- Telusuri daftar kamar yang tersedia dan pilih kamar yang diinginkan.
- Isi formulir pemesanan dengan informasi yang diperlukan.
- Lanjutkan ke halaman pembayaran, dan pilih metode pembayaran yang diinginkan.
- Ikuti petunjuk untuk menyelesaikan pembayaran melalui antarmuka pembayaran Midtrans.
- Setelah pembayaran berhasil, Anda akan menerima konfirmasi pemesanan.
- Anda dapat melihat riwayat pemesanan dan mengelola akun Anda melalui menu pengaturan.

Selamat menikmati pengalaman menggunakan aplikasi Smart Home Stay!

## Kontribusi
Jika Anda ingin berkontribusi pada pengembangan aplikasi Smart Home Stay, kami sangat menghargainya. Silakan melakukan *pull request* dengan perubahan yang Anda usulkan, dan kami akan mempelajarinya.

## Kontak
Jika Anda memiliki pertanyaan atau masalah terkait dengan aplikasi Smart Home Stay, jangan ragu untuk menghubungi kami melalui email di [trikhaqiqi@gmail.com](mailto:trikhaqiqi@gmail.com).

Terima kasih telah menggunakan Smart Home Stay!
