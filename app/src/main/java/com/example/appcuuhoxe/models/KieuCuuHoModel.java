package com.example.appcuuhoxe.models;

public class KieuCuuHoModel {
    String MaKieuCuuHo;
    String TenKieuCuuHo;
    float GiaKieuCuuHo;
    String GhiChu;

    public KieuCuuHoModel() {
    }

    public KieuCuuHoModel(String maKieuCuuHo, String tenKieuCuuHo, float giaKieuCuuHo, String ghiChu) {
        MaKieuCuuHo = maKieuCuuHo;
        TenKieuCuuHo = tenKieuCuuHo;
        GiaKieuCuuHo = giaKieuCuuHo;
        GhiChu = ghiChu;
    }

    public String getMaKieuCuuHo() {
        return MaKieuCuuHo;
    }

    public void setMaKieuCuuHo(String maKieuCuuHo) {
        MaKieuCuuHo = maKieuCuuHo;
    }

    public String getTenKieuCuuHo() {
        return TenKieuCuuHo;
    }

    public void setTenKieuCuuHo(String tenKieuCuuHo) {
        TenKieuCuuHo = tenKieuCuuHo;
    }

    public float getGiaKieuCuuHo() {
        return GiaKieuCuuHo;
    }

    public void setGiaKieuCuuHo(float giaKieuCuuHo) {
        GiaKieuCuuHo = giaKieuCuuHo;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String ghiChu) {
        GhiChu = ghiChu;
    }
}
