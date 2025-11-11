/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import Model.Transaksi;
import Model.TransaksiData;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import java.util.Comparator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.text.NumberFormat;
import java.util.Locale;
import java.text.DecimalFormat;

/**
 * FXML Controller class
 *
 * @author aufaz
 */
public class TransaksiController implements Initializable {

    @FXML
    private TextField nominalInput;
    @FXML
    private TextField deskripsiInput;
    @FXML
    private TableView<Transaksi> data;
    @FXML
    private TableColumn<Transaksi, Double> colNominal;
    @FXML
    private TableColumn<Transaksi, String> colDeskripsi;
    @FXML
    private TableColumn<Transaksi, String> colJenis;
    @FXML
    private TableColumn<Transaksi, LocalDate> colTanggal;
    @FXML
    private DatePicker dateInput;
    @FXML
    private ComboBox<String> dropdown;
    @FXML
    private Label totalView;
    @FXML
    private Label pemasukanView;
    @FXML
    private Label pengeluaranView;
    @FXML
    private DatePicker filterStartInput;
    @FXML
    private DatePicker filterEndInput;

    private ObservableList<Transaksi> dataTransaksi;
    private ObservableList<Transaksi> filteredData;
    private SortedList<Transaksi> sortedData;
    private Transaksi selectedTransaksi;
    private NumberFormat currencyFormat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Setup currency formatter
        currencyFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        
        // Inisialisasi data dari TransaksiData
        dataTransaksi = TransaksiData.getData();
        
        // Inisialisasi filtered data
        filteredData = FXCollections.observableArrayList();
        
        // Setup SortedList untuk mengurutkan berdasarkan tanggal (terbaru di atas)
        sortedData = new SortedList<>(filteredData);
        sortedData.setComparator(Comparator.comparing(Transaksi::getTanggal).reversed());
        
        // Tambahkan 3 data sample jika data masih kosong
        if (dataTransaksi.isEmpty()) {
            dataTransaksi.add(new Transaksi(5000000, "Gaji Bulanan", "Pemasukan", LocalDate.now().minusDays(5)));
            dataTransaksi.add(new Transaksi(1500000, "Bakso Matcha Enak Banget", "Pengeluaran", LocalDate.now().minusDays(3)));
            dataTransaksi.add(new Transaksi(750000, "Freelance", "Pemasukan", LocalDate.now().minusDays(1)));
            dataTransaksi.add(new Transaksi(70000, "Kutamatcha UwU", "Pengeluaran", LocalDate.now().minusDays(3)));
        }
        
        // Setup ComboBox dengan opsi jenis transaksi
        ObservableList<String> jenisList = FXCollections.observableArrayList(
            "Pemasukan", "Pengeluaran"
        );
        dropdown.setItems(jenisList);
        
        // Setup TableView Columns (Mencocokkan nama Property di Model)
        colNominal.setCellValueFactory(new PropertyValueFactory<>("nominal"));
        // Format nominal tanpa desimal
        colNominal.setCellFactory(column -> new javafx.scene.control.TableCell<Transaksi, Double>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    DecimalFormat df = new DecimalFormat("#,###");
                    setText(df.format(item.longValue()));
                }
            }
        });
        colDeskripsi.setCellValueFactory(new PropertyValueFactory<>("deskripsi"));
        colJenis.setCellValueFactory(new PropertyValueFactory<>("jenis"));
        colTanggal.setCellValueFactory(new PropertyValueFactory<>("tanggal"));
    
        // Bind sorted data ke TableView (sudah terurut berdasarkan tanggal terbaru di atas)
        data.setItems(sortedData);
        
        // Tambahkan Listener untuk menampilkan detail saat baris dipilih
        data.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> showTransaksiDetails(newValue));
        
        // Tambahkan Listener untuk update filter saat data berubah
        dataTransaksi.addListener((javafx.collections.ListChangeListener.Change<? extends Transaksi> change) -> {
            applyFilter();
        });
        
        // Setup filter DatePicker listeners
        filterStartInput.valueProperty().addListener((observable, oldValue, newValue) -> {
            applyFilter();
        });
        
        filterEndInput.valueProperty().addListener((observable, oldValue, newValue) -> {
            applyFilter();
        });
        
        // Set tanggal default ke hari ini
        dateInput.setValue(LocalDate.now());
        
        // Apply filter pertama kali (menampilkan semua data)
        applyFilter();
    }    

    /**
     * Listener: Menampilkan data Transaksi yang dipilih ke kolom input.
     */
    private void showTransaksiDetails(Transaksi transaksi) {
        if (transaksi != null) {
            selectedTransaksi = transaksi;
            // Format nominal tanpa desimal
            DecimalFormat df = new DecimalFormat("#,###");
            nominalInput.setText(df.format(transaksi.getNominal()).replace(",", ""));
            deskripsiInput.setText(transaksi.getDeskripsi());
            dropdown.setValue(transaksi.getJenis());
            dateInput.setValue(transaksi.getTanggal());
        } else {
            selectedTransaksi = null;
            clearFields();
        }
    }

    @FXML
    private void transactionAdd(ActionEvent event) {
        try {
            String nominalText = nominalInput.getText().replace(",", "").trim();
            double nominal = Double.parseDouble(nominalText);
            Transaksi baru = new Transaksi(
                nominal,
                deskripsiInput.getText(),
                dropdown.getValue(),
                dateInput.getValue()
            );
            TransaksiData.tambah(baru);
            clearFields();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Gagal menambahkan transaksi.");
        }
    }

    @FXML
    private void transactionEdit(ActionEvent event) {
        if (selectedTransaksi == null) {
            return;
        }
        
        try {
            String nominalText = nominalInput.getText().replace(",", "").trim();
            double nominal = Double.parseDouble(nominalText);
            selectedTransaksi.setNominal(nominal);
            selectedTransaksi.setDeskripsi(deskripsiInput.getText());
            selectedTransaksi.setJenis(dropdown.getValue());
            selectedTransaksi.setTanggal(dateInput.getValue());
            data.refresh();
            clearFields();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Gagal mengubah transaksi.");
        }
    }

    @FXML
    private void transactionDelete(ActionEvent event) {
        if (selectedTransaksi != null) {
            TransaksiData.hapus(selectedTransaksi);
            clearFields();
        }
    }
    
    @FXML
    private void clearFilter(ActionEvent event) {
        // Kosongkan filter
        filterStartInput.setValue(null);
        filterEndInput.setValue(null);
        // Filter akan otomatis di-update melalui listener
    }
    
    /**
     * Memfilter data berdasarkan tanggal start dan end
     */
    private void applyFilter() {
        filteredData.clear();
        
        LocalDate startDate = filterStartInput.getValue();
        LocalDate endDate = filterEndInput.getValue();
        
        for (Transaksi transaksi : dataTransaksi) {
            LocalDate transaksiDate = transaksi.getTanggal();
            
            // Jika tidak ada filter, tampilkan semua
            if (startDate == null && endDate == null) {
                filteredData.add(transaksi);
            }
            // Jika hanya start date
            else if (startDate != null && endDate == null) {
                if (!transaksiDate.isBefore(startDate)) {
                    filteredData.add(transaksi);
                }
            }
            // Jika hanya end date
            else if (startDate == null && endDate != null) {
                if (!transaksiDate.isAfter(endDate)) {
                    filteredData.add(transaksi);
                }
            }
            // Jika kedua filter ada
            else {
                if (!transaksiDate.isBefore(startDate) && !transaksiDate.isAfter(endDate)) {
                    filteredData.add(transaksi);
                }
            }
        }
        
        // Update summary setelah filter
        updateSummary();
    }
    
    /**
     * Menghitung dan menampilkan total, pemasukan, dan pengeluaran berdasarkan data yang terfilter
     */
    private void updateSummary() {
        double totalPemasukan = 0;
        double totalPengeluaran = 0;
        
        // Hitung berdasarkan data yang terfilter
        for (Transaksi transaksi : filteredData) {
            if ("Pemasukan".equals(transaksi.getJenis())) {
                totalPemasukan += transaksi.getNominal();
            } else if ("Pengeluaran".equals(transaksi.getJenis())) {
                totalPengeluaran += transaksi.getNominal();
            }
        }
        
        double total = totalPemasukan - totalPengeluaran;
        
        // Format currency tanpa desimal
        DecimalFormat df = new DecimalFormat("#,###");
        NumberFormat currencyFormatNoDecimal = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        currencyFormatNoDecimal.setMaximumFractionDigits(0);
        
        // Update labels dengan format currency tanpa desimal
        totalView.setText(currencyFormatNoDecimal.format(total));
        pemasukanView.setText(currencyFormatNoDecimal.format(totalPemasukan));
        pengeluaranView.setText(currencyFormatNoDecimal.format(totalPengeluaran));
    }
    
    // Utilitas
    private void clearFields() {
        nominalInput.clear();
        deskripsiInput.clear();
        dropdown.setValue(null);
        dateInput.setValue(LocalDate.now());
        data.getSelectionModel().clearSelection();
        selectedTransaksi = null;
    }
    
    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
}
