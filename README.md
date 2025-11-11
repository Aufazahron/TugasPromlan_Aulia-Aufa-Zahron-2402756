![Logo](image.png)

# Aplikasi Manajemen Transaksi Keuangan

Aplikasi desktop berbasis JavaFX untuk mengelola transaksi keuangan (pemasukan dan pengeluaran) dengan fitur filter berdasarkan tanggal dan ringkasan keuangan.

## 📋 Fitur

- ✅ **CRUD Transaksi**: Tambah, Edit, dan Hapus transaksi
- 📊 **Ringkasan Keuangan**: Total uang, total pemasukan, dan total pengeluaran
- 🔍 **Filter Tanggal**: Filter transaksi berdasarkan rentang tanggal (Start Date dan End Date)
- 📅 **Sorting Otomatis**: Transaksi diurutkan berdasarkan tanggal terbaru di paling atas
- 💰 **Format Rupiah**: Semua nominal ditampilkan dalam format Rupiah Indonesia tanpa desimal

## 🖼️ Screenshot

<!-- Untuk menambahkan gambar, letakkan file gambar di folder images/ atau di root project -->
<!-- Kemudian uncomment dan sesuaikan path gambar di bawah ini -->

<!--
![Screenshot Aplikasi](images/screenshot.png)
![Dashboard](images/dashboard.png)
![Filter Transaksi](images/filter.png)
-->

**Cara menambahkan gambar:**

1. Buat folder `images/` di root project
2. Simpan screenshot aplikasi Anda di folder tersebut
3. Uncomment baris di atas dan sesuaikan nama file gambar
4. Atau gunakan format: `![Deskripsi](images/nama-file.png)`

## 🛠️ Teknologi

- **Java**: 17
- **JavaFX**: 21.0.1
- **Maven**: Build tool
- **FXML**: UI Design

## 📦 Struktur Project

```
belajar-javafx/
├── src/
│   └── main/
│       └── java/
│           ├── Main.java                 # Entry point aplikasi
│           ├── Controller/
│           │   └── TransaksiController.java  # Controller untuk logika transaksi
│           ├── Model/
│           │   ├── Transaksi.java        # Model data transaksi
│           │   └── TransaksiData.java    # Data storage (ObservableList)
│           └── View/
│               └── TransaksiView.fxml    # UI Design (FXML)
├── pom.xml                               # Maven configuration
└── README.md                             # Dokumentasi
```

## 🚀 Cara Menjalankan

### Prerequisites

- Java JDK 17 atau lebih tinggi
- Maven 3.6 atau lebih tinggi
- IDE (IntelliJ IDEA, NetBeans, atau Eclipse dengan JavaFX plugin)

### Langkah-langkah

1. **Clone atau download project**

   ```bash
   git clone <repository-url>
   cd belajar-javafx
   ```

2. **Compile dan run dengan Maven**

   ```bash
   mvn clean javafx:run
   ```

3. **Atau compile manual**

   ```bash
   mvn clean compile
   ```

4. **Run dari IDE**
   - Buka project di IDE Anda
   - Run class `Main.java`

## 📖 Cara Menggunakan

### Menambah Transaksi

1. Isi field **Nominal** (contoh: `5000000`)
2. Isi field **Deskripsi** (contoh: `Gaji Bulanan`)
3. Pilih **Jenis** transaksi: `Pemasukan` atau `Pengeluaran`
4. Pilih **Tanggal** transaksi
5. Klik tombol **Add**

### Mengedit Transaksi

1. Klik pada baris transaksi yang ingin diubah di tabel
2. Data akan muncul di field input
3. Ubah data yang diperlukan
4. Klik tombol **Edit**

### Menghapus Transaksi

1. Klik pada baris transaksi yang ingin dihapus di tabel
2. Klik tombol **Delete**

### Filter Transaksi

1. Pilih **Filter Start** untuk tanggal mulai
2. Pilih **Filter End** untuk tanggal akhir
3. Transaksi akan otomatis terfilter berdasarkan rentang tanggal
4. Klik **Clear Filter** untuk menghapus filter dan menampilkan semua transaksi

### Ringkasan Keuangan

- **Total Uang**: Selisih antara total pemasukan dan pengeluaran
- **Pemasukan**: Total semua transaksi dengan jenis "Pemasukan"
- **Pengeluaran**: Total semua transaksi dengan jenis "Pengeluaran"

Ringkasan akan otomatis ter-update berdasarkan data yang terfilter.

## 🎯 Fitur Detail

### Format Nominal

- Nominal ditampilkan tanpa desimal (contoh: `5000000` bukan `5000000.0`)
- Format currency Rupiah Indonesia (Rp) untuk ringkasan
- Input nominal dapat menggunakan koma sebagai pemisah ribuan (akan otomatis dihapus saat parsing)

### Sorting

- Transaksi otomatis diurutkan berdasarkan tanggal
- Tanggal terbaru berada di paling atas
- Sorting berlaku untuk semua data yang terfilter

### Filter Tanggal

- **Tanpa filter**: Menampilkan semua transaksi
- **Hanya Start Date**: Menampilkan transaksi dari tanggal start ke depan
- **Hanya End Date**: Menampilkan transaksi sampai tanggal end
- **Start + End Date**: Menampilkan transaksi dalam rentang tanggal tertentu

## 📝 Data Sample

Aplikasi dilengkapi dengan 4 data sample yang akan muncul saat pertama kali dijalankan:

1. Gaji Bulanan - Rp 5.000.000 (Pemasukan)
2. Bakso Matcha Enak Banget - Rp 1.500.000 (Pengeluaran)
3. Freelance - Rp 750.000 (Pemasukan)
4. Kutamatcha UwU - Rp 70.000 (Pengeluaran)

## 🔧 Konfigurasi

### Mengubah Java Version

Edit file `pom.xml`:

```xml
<maven.compiler.source>17</maven.compiler.source>
<maven.compiler.target>17</maven.compiler.target>
```

### Mengubah JavaFX Version

Edit file `pom.xml` pada bagian dependencies:

```xml
<version>21.0.1</version>
```

## 📄 Lisensi

Project ini dibuat untuk keperluan pembelajaran JavaFX.

## 👤 Author

**aufaz**

## 🙏 Acknowledgments

- JavaFX Community
- Maven Project

---

**Note**: Pastikan JavaFX SDK sudah terinstall atau menggunakan Maven dependency seperti yang sudah dikonfigurasi di `pom.xml`.
