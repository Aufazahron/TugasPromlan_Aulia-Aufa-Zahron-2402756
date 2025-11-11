package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TransaksiData {
    private static final ObservableList<Transaksi> data = FXCollections.observableArrayList();

    public static ObservableList<Transaksi> getData() {
        return data;
    }

    public static void tambah(Transaksi transaksi) {
        data.add(transaksi);
    }

    public static void hapus(Transaksi transaksi) {
        data.remove(transaksi);
    }

    public static void ubah(Transaksi lama, Transaksi baru) {
        int index = data.indexOf(lama);
        if (index >= 0) {
            data.set(index, baru);
        }
    }
}
