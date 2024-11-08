package perhitunganhariapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class PerhitunganHariApp {

    private JFrame frame;
    private JComboBox<String> monthComboBox;
    private JSpinner yearSpinner;
    private JLabel resultLabel;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                PerhitunganHariApp window = new PerhitunganHariApp();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // Konstruktor untuk inisialisasi GUI
    public PerhitunganHariApp() {
        frame = new JFrame("Perhitungan Hari dalam Bulan");
        frame.setBounds(100, 100, 400, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new FlowLayout());

        // Label instruksi untuk memilih bulan
        JLabel monthLabel = new JLabel("Pilih Bulan:");
        frame.getContentPane().add(monthLabel);

        // ComboBox untuk memilih bulan
        String[] months = {"Januari", "Februari", "Maret", "April", "Mei", "Juni",
                            "Juli", "Agustus", "September", "Oktober", "November", "Desember"};
        monthComboBox = new JComboBox<>(months);
        frame.getContentPane().add(monthComboBox);

        // Label instruksi untuk memasukkan tahun
        JLabel yearLabel = new JLabel("Masukkan Tahun:");
        frame.getContentPane().add(yearLabel);

        // JSpinner untuk memasukkan tahun
        yearSpinner = new JSpinner(new SpinnerNumberModel(2024, 1900, 9999, 1));
        frame.getContentPane().add(yearSpinner);

        // Tombol untuk menghitung jumlah hari
        JButton calculateButton = new JButton("Hitung Jumlah Hari");
        calculateButton.addActionListener(e -> calculateDays());
        frame.getContentPane().add(calculateButton);

        // Label untuk menampilkan hasil
        resultLabel = new JLabel("Hasil: ");
        frame.getContentPane().add(resultLabel);
    }

    // Fungsi untuk menghitung jumlah hari dan menampilkan hasil
    private void calculateDays() {
        // Mendapatkan bulan yang dipilih (dalam format 1-12)
        int monthIndex = monthComboBox.getSelectedIndex() + 1;

        // Mendapatkan tahun yang dimasukkan dari JSpinner
        int year = (Integer) yearSpinner.getValue();

        // Menggunakan LocalDate untuk mendapatkan jumlah hari dalam bulan tersebut
        LocalDate firstDayOfMonth = LocalDate.of(year, monthIndex, 1);
        int daysInMonth = firstDayOfMonth.lengthOfMonth(); // Mendapatkan jumlah hari dalam bulan

        // Mendapatkan hari pertama dan hari terakhir
        String firstDayOfMonthString = firstDayOfMonth.format(DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy"));
        LocalDate lastDayOfMonth = firstDayOfMonth.withDayOfMonth(daysInMonth);
        String lastDayOfMonthString = lastDayOfMonth.format(DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy"));

        // Menentukan apakah tahun kabisat
        boolean isLeapYear = firstDayOfMonth.isLeapYear();
        String leapYearMessage = isLeapYear ? "Tahun Kabisat!" : "Bukan Tahun Kabisat.";

        // Menampilkan hasil
        resultLabel.setText(String.format("<html>Jumlah Hari: %d<br>Hari Pertama: %s<br>Hari Terakhir: %s<br>%s</html>",
                daysInMonth, firstDayOfMonthString, lastDayOfMonthString, leapYearMessage));
    }
}